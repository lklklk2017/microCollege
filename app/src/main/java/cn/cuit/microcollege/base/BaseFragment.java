package cn.cuit.microcollege.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.cuit.microcollege.application.MyApplication;
import cn.cuit.microcollege.utils.LogU;
import cn.cuit.microcollege.widget.loadWidget.ShapeLoadingDialog;

/**
 * @Author: Created by Rod Eden
 * @Date: 2019/4/16
 * @Descirption:
 */
public abstract class BaseFragment<P extends BasePresenter> extends Fragment implements BaseView {

    private static final String TAG = "BaseFragment";
    protected P mPresenter;
    private ShapeLoadingDialog mLoadingDialog;
    private boolean inited = false;
    private View view;
    private Intent intent = new Intent();
    private ViewPager viewPager;

    public BaseFragment() {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        LogU.d("执行Fragment:onAttach()", TAG);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogU.d("执行Fragment:onCreate()", TAG);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        LogU.d("执行Fragment:onCreateView()", TAG);
        if (mPresenter == null) {
            mPresenter = initPresenter();
            mPresenter.bindView(this);//p绑定v
            LogU.i("生成并绑定了P:" + mPresenter, TAG);
        }
        return super.onCreateView(inflater, container, savedInstanceState);
    }
    /********************************************需要实现的方法****************************************/

    /**
     * static function needed
     *
     * @param
     * @return
     */
    public static BaseFragment newInstance() {
        return null;
    }

    /**
     * init Present
     */
    public abstract P initPresenter();

    /**
     * init View here
     */
    public abstract void initView();


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
        initFragment();
        initView();
        initDialog();
        bindListener();
        initData();
    }

    protected void initData() {

    }

    protected void initFragment() {
    }

    /**
     * dialog init here,you have to override this method when you need,
     */
    protected void initDialog() {
        mLoadingDialog = new ShapeLoadingDialog(getContext());
        mLoadingDialog.setCanceledOnTouchOutside(false);
        mLoadingDialog.setLoadingText("拼命加载中...");
    }


    public void setCreatedView(View view) {
        this.view = view;
    }

    /**
     * 用于绑定viewPager 以便解决自适应高度
     *
     * @param viewPager
     */
    public void setViewPager(ViewPager viewPager) {
        this.viewPager = viewPager;
    }

    public View getViewPager() {
        if (null != viewPager) {
            return viewPager;
        } else {
            return null;
        }
    }


    public View getCreatedView() {
        if (null != view) {
            return view;
        } else {
            throw new RuntimeException("view 还未被初始化，请确认setCreateView已被调用");
        }
    }

    /**
     * toast use with #SnackBar
     *
     * @param content
     * @param callBack
     */
    public void toast(String content, final BaseActivity.ToastCallBack callBack) {
        Snackbar.make(getCreatedView(), content, Snackbar.LENGTH_INDEFINITE)
                .setAction("知道了", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        callBack.onEvent();
                    }
                })
                .show();
    }

    public void toast(String content, boolean sure) {
        if (!sure) {
            Snackbar.make(getCreatedView(), content, Snackbar.LENGTH_LONG)
                    .show();
        } else {
            Snackbar.make(getCreatedView(), content, Snackbar.LENGTH_INDEFINITE)
                    .setAction("知道了", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                        }
                    })
                    .show();
        }
    }

    /**
     * toast use with #SnackBar
     *
     * @param content
     * @param callBack
     */
    public void toast(String content, String positiveTitle, int during, final ToastCallBack callBack) {
        Snackbar.make(getActivity().getWindow().getDecorView(), content, during)
                .setAction(positiveTitle, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        callBack.onEvent();
                    }
                })
                .show();
    }

    public P getPresent() {
        if (null != mPresenter) {
            return mPresenter;
        } else {
            throw new RuntimeException("没有找到 Presenter 请初始化后使用");
        }
    }

    public void toPage(Class clzz) {
        intent.setClass(MyApplication.getMyContext(), clzz);
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

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        LogU.d("执行Fragment:onActivityCreated()", TAG);
    }

    @Override
    public void onStart() {
        super.onStart();
        LogU.d("执行Fragment:onStart()", TAG);
        if (!inited) {
            init();
            inited = true;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        LogU.d("执行Fragment:onResume()", TAG);
    }

    @Override
    public void onPause() {
        super.onPause();
        LogU.d("执行Fragment:onPause()", TAG);
    }

    @Override
    public void onStop() {
        super.onStop();
        LogU.d("执行Fragment:onStop()", TAG);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        LogU.d("执行Fragment:onDestroyView()", TAG);
        inited = false;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogU.d("执行Fragment:onDestroy()", TAG);
        getPresent().onDestory();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        LogU.d("执行Fragment:onDetach()", TAG);
    }


    /*******************************************接口相关**********************************************/
    public interface ToastCallBack {
        void onEvent();
    }
}
