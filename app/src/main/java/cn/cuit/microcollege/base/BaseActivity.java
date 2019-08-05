package cn.cuit.microcollege.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import butterknife.ButterKnife;
import cn.cuit.microcollege.application.MyApplication;
import cn.cuit.microcollege.utils.LogU;
import cn.cuit.microcollege.widget.loadWidget.ShapeLoadingDialog;


/**
 * @Author: Rod Eden
 * @Date: 2019/4/14
 * @Description: 这是Activity的基类
 */
public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity implements BaseView {

    public static final String TAG = "BaseActivity";
    private Intent intent = new Intent();
    private ShapeLoadingDialog mLoadingDialog;
    protected P mPresenter;
    private boolean inited = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (mPresenter == null) {
            mPresenter = initPresenter();
            if (mPresenter != null) {//有present的实现
                mPresenter.bindView(this);//p绑定v
                LogU.i("生成并绑定了P:" + mPresenter, TAG);
            }
        }
        preStatusForOther();
    }

    /**
     * init intent information from other page
     */
    protected void preStatusForOther() {

    }
    /********************************************需要实现的方法****************************************/

    /**
     * init View here
     */
    public abstract void initView();

    /**
     * init Present
     */
    public abstract P initPresenter();

    /**
     * bind view listener if need
     */
    public abstract void bindListener();


    /********************************************现有的方法****************************************/

    /**
     * 初始化控件
     * 初始化窗口
     * 绑定监听
     */
    public void init() {
        //获取控件
        ButterKnife.bind(this);
        initFragment();
        initView();
        initDialog();
        bindListener();
        initData();
    }

    protected void initData() {

    }

    /**
     * dialog init here,you have to override this method when you need,
     */
    public void initDialog() {
        mLoadingDialog = new ShapeLoadingDialog(this);
        mLoadingDialog.setCanceledOnTouchOutside(false);
        mLoadingDialog.setLoadingText("拼命加载中...");
    }

    /**
     * fragments init here,you have to override this method when you need
     */
    public void initFragment() {
    }

    /**
     * toast use with #SnackBar
     *
     * @param content
     * @param callBack
     */
    public void toast(String content, String positiveTitle, int during, final ToastCallBack callBack) {
        Snackbar.make(getWindow().getDecorView(), content, during)
                .setAction(positiveTitle, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        callBack.onEvent();
                    }
                })
                .show();
    }

    public void toast(String content, boolean sure) {
        if (!sure) {
            Snackbar.make(getWindow().getDecorView(), content, Snackbar.LENGTH_LONG)
                    .show();
        } else {
            Snackbar.make(getWindow().getDecorView(), content, Snackbar.LENGTH_INDEFINITE)
                    .setAction("知道了", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                        }
                    })
                    .show();
        }
    }

    public P getPresent() {
        if (null != mPresenter) {
            return mPresenter;
        } else {
            throw new RuntimeException("没有找到 Presenter 请初始化后使用");
        }
    }

    /**
     * 跳转至clzz的Activity
     *
     * @param clzz
     */
    public void toPage(Class clzz) {
        intent = new Intent(MyApplication.getMyContext(), clzz);
        startActivity(intent);
    }

    /**
     * 显示对话框
     *
     * @param start
     */
    public void showDialog(boolean start) {
        if (start) {
            mLoadingDialog.show();
        } else {
            mLoadingDialog.dismiss();
        }

    }

    /**
     * 隐藏键盘
     */
    public void hideInput() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);
    }

    /**
     * 显示键盘
     */
    public void showInput(EditText et) {
        if (et != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(et, InputMethodManager.SHOW_FORCED);
        }
    }

    /*******************************************生命周期管理**********************************************/
    @Override
    protected void onStart() {
        super.onStart();
        if (!inited) {
            init();
            LogU.i(TAG + "初始化在onStart完成:", TAG);
            inited = true;
        }

    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (mPresenter != null) {
            String pClassName = mPresenter.getClass().getName();
            mPresenter.onDestory();
            LogU.i(TAG + "已解绑P:" + pClassName, TAG);
            mPresenter = null;
        }
    }

    /*******************************************接口相关**********************************************/
    public interface ToastCallBack {
        void onEvent();
    }
}