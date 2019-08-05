package cn.cuit.microcollege.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.cuit.microcollege.R;
import cn.cuit.microcollege.adapter.MyCircleAdapter;
import cn.cuit.microcollege.base.BaseActivity;
import cn.cuit.microcollege.contract.MyCircleActivityContract;
import cn.cuit.microcollege.entity.HttpResultEntity.GetMyCircleResultBean;
import cn.cuit.microcollege.presenter.MyCircleActivityPresenter;
import cn.cuit.microcollege.utils.Config;
import cn.cuit.microcollege.utils.LogU;
import cn.cuit.microcollege.widget.CustomItemDecoration;
import cn.cuit.microcollege.widget.CustomMyCircleSelectCancelDialog;
import cn.cuit.microcollege.widget.actionBar.CustomDefaultActionBar;

public class MyCircleActivity extends BaseActivity<MyCircleActivityPresenter> implements MyCircleActivityContract.View,
        CustomDefaultActionBar.setBarListener,
        MyCircleAdapter.AdapterClickListener,
        CustomMyCircleSelectCancelDialog.ContentClickListener
        , DialogInterface.OnClickListener, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.activity_my_circle_actionBar)
    CustomDefaultActionBar mBar;
    @BindView(R.id.activity_my_circle_rcy)
    RecyclerView mRcy;
    @BindView(R.id.activity_my_circle_swip)
    SwipeRefreshLayout mSwip;
    private int[] colors = new int[]{Color.YELLOW, Color.RED, Color.GREEN, Color.BLUE};
    private MyCircleAdapter myCircleAdapter;
    private ArrayList<GetMyCircleResultBean.CirclesBean> mMyCircleList;
    private CustomMyCircleSelectCancelDialog mOptionDialog;
    private int currItemPosition;

    //操作模式
    private static final int OPERATION_MODE_DEFAULT = 0;
    private static final int OPERATION_MODE_FROM_PUBLISH = 1;
    private int currOperate = OPERATION_MODE_DEFAULT;//默认

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mycircle);
        ButterKnife.bind(this);
    }

    protected void preStatusForOther() {
        Intent intent = getIntent();
        if (intent == null) {
            return;
        }

//        Bundle data = intent.getBundleExtra("data");
        String from = intent.getStringExtra("from");

        if (from == null) {
            LogU.i("from 来源不明", getClass().getName());
            return;
        }

        //来自publish
        if (from.equals(Config.FLAG_PUBLISH)) {
            setCurrOperation(OPERATION_MODE_FROM_PUBLISH);
        }
    }

    @Override
    public void initView() {
        //swip
        mSwip.setColorSchemeColors(colors);

        //rcy
        CustomItemDecoration decor = new CustomItemDecoration().setColor(getResources().getColor(R.color.colorDefaultBackGroundDark));
        mRcy.addItemDecoration(decor);

        //list
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRcy.setLayoutManager(linearLayoutManager);
        myCircleAdapter = new MyCircleAdapter();
        mRcy.setAdapter(myCircleAdapter);
        mRcy.setItemAnimator(new DefaultItemAnimator());

    }

    @Override
    protected void initData() {
        getPresent().myCircleListTask();
    }

    @Override
    public MyCircleActivityPresenter initPresenter() {
        return new MyCircleActivityPresenter(this);
    }

    @Override
    public void initDialog() {
        mOptionDialog = new CustomMyCircleSelectCancelDialog(this);
    }

    @Override
    public void bindListener() {
        mBar.setBarListener(this);
        myCircleAdapter.setAdapterClickListener(this);
        mOptionDialog.seContentClickListener(this);
        mSwip.setOnRefreshListener(this);
    }


    @Override
    public void onBack() {
        finish();
    }

    /**
     * 列表监听回调
     *
     * @param position
     */
    @Override
    public void onItemClick(int position) {
        if (getCurrOperation() == OPERATION_MODE_DEFAULT) {
            Bundle bundle = new Bundle();
            bundle.putParcelable("circle", getMycircleAdpater().getmList().get(position));
            Intent intent = new Intent(this, CircleDetailActivity.class);
            intent.putExtra("data", bundle);
            intent.putExtra("from", "MyCircleActivity");
            startActivity(intent);
        } else if (getCurrOperation() == OPERATION_MODE_FROM_PUBLISH) {
            //获取circleId 并返回数据
            List<GetMyCircleResultBean.CirclesBean> myCircleResultBeans = myCircleAdapter.getmList();
            GetMyCircleResultBean.CirclesBean selectedBean = myCircleResultBeans.get(position);
            //初始化结果的参数
            Bundle bundle = new Bundle();
            bundle.putParcelable("selectedBean", selectedBean);
            Intent intent = new Intent(this, PublishDynamicActivity.class);
            intent.putExtra("dataFromMyCircle", bundle);
            setResult(RESULT_OK, intent);
            finish();
        }
    }

    @Override
    public void onItemOptionClick(int position) {
        if (getCurrOperation() == OPERATION_MODE_DEFAULT) {
            currItemPosition = position;
            mOptionDialog.show();
        }
    }


    /************************************ item dialog监听回调 ***********************************************/
    @Override
    public void onDialogItemOne() {
        getPresent().cancelFromCircle(currItemPosition);
    }

    /************************************ alert dialog监听回调 ***********************************************/
    @Override
    public void onClick(DialogInterface dialog, int which) {
        toast("which:" + which, false);
        switch (which) {
            case -1://确认
                toast("退出圈子，position：" + currItemPosition, false);
                break;
            case -2://取消
                break;
        }
    }

    /**
     * 获取当前操作模式
     *
     * @return
     */
    public int getCurrOperation() {
        return currOperate;
    }

    public void setCurrOperation(int currOperate) {
        this.currOperate = currOperate;
    }

    @Override
    public SwipeRefreshLayout getSwip() {
        return mSwip;
    }

    @Override
    public MyCircleAdapter getMycircleAdpater() {
        return myCircleAdapter;
    }

    /**************************************swip refresh call back*******************************/
    @Override
    public void onRefresh() {
        getPresent().myCircleListTask();
    }
}
