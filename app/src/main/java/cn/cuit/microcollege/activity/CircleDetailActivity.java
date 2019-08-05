package cn.cuit.microcollege.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.cuit.microcollege.R;
import cn.cuit.microcollege.adapter.DetailFragmentPagerAdapter;
import cn.cuit.microcollege.base.BaseActivity;
import cn.cuit.microcollege.contract.CircleDetailActivityContract;
import cn.cuit.microcollege.entity.DataEntity.CircleDataBean;
import cn.cuit.microcollege.entity.HttpResultEntity.GetDynamicResultBean;
import cn.cuit.microcollege.entity.HttpResultEntity.GetDynamicWithCircleResultBean;
import cn.cuit.microcollege.entity.HttpResultEntity.GetMyCircleResultBean;
import cn.cuit.microcollege.fragment.circleDetail.CircleDetailPageOneFragment;
import cn.cuit.microcollege.fragment.circleDetail.CircleDetailPageTwoFragment;
import cn.cuit.microcollege.presenter.CircleDetailActivityPresenter;
import cn.cuit.microcollege.utils.TransFormUtil;
import cn.cuit.microcollege.widget.CustomViewPager;

public class CircleDetailActivity extends BaseActivity<CircleDetailActivityPresenter> implements CircleDetailActivityContract.View, View.OnClickListener, ViewPager.OnPageChangeListener {

    @BindView(R.id.activity_circle_detail_bg_img)
    ImageView mBgImg;
    @BindView(R.id.activity_circle_detail_portrait_img)
    ImageView mPortraitImg;
    @BindView(R.id.activity_circle_detail_circleCreateDate)
    TextView mCreateDate;
    @BindView(R.id.activity_circle_detail_circleTitle)
    TextView mTitle;
    @BindView(R.id.activity_circle_detail_toolbar)
    Toolbar mToolbar;
    @BindView(R.id.activity_circle_detail_collapseToolBarLyt)
    CollapsingToolbarLayout mCollapseToolBarLyt;
    @BindView(R.id.activity_circle_detail_tabLyt)
    TabLayout mTabLyt;
    @BindView(R.id.activity_circle_detail_appBarLyt)
    AppBarLayout mAppBarLyt;
    @BindView(R.id.activity_circle_detail_nsv)
    NestedScrollView mNsv;
    @BindView(R.id.activity_circle_detail_fab)
    FloatingActionButton mFab;
    @BindView(R.id.activity_circle_detail_cooLyt)
    CoordinatorLayout mCooLyt;
    @BindView(R.id.activity_circle_detail_vp)
    CustomViewPager mVp;
    @BindView(R.id.activity_circle_detail_join_tv)
    TextView mJoinTv;
    @BindView(R.id.activity_circle_detail_count_tv)
    TextView mJoinedCountTv;

    private String[] titles = new String[]{"最新", "最热"};
    private CircleDetailPageOneFragment mDetailCircleNewFragment;
    private CircleDetailPageTwoFragment mDetailCircleHotFragment;
    private Fragment currFragment;
    private FragmentManager mFm;
    private ArrayList<Fragment> mFragmentList;
    private DetailFragmentPagerAdapter mFragmentPageAdapter;
    private CircleDataBean circleDataBean = new CircleDataBean();//统一bean
    private static final int STATE_FROM_OTHER = 1;//查看别人的圈子
    private static final int STATE_FROM_ME = 0;//查看自己的圈子
    private int currentState = 0;//默认当前状态
    private int mCurrPostion = 0;//默认当前vp的位置为显示0
    private boolean isChecked = false;//判断是否加入 （默认：false）

    public int getmCurrPostion() {
        return mCurrPostion;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*沉浸式状态栏*/
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        setContentView(R.layout.activity_circle_detail);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
    }

    /**
     * 获取数据circleDataBean
     *
     * @return
     */
    public CircleDataBean getCircleDataBean() {
        return circleDataBean;
    }

    @Override
    protected void preStatusForOther() {
        Intent intent = getIntent();

        if (intent != null) {
            Bundle data = intent.getBundleExtra("data");
            if (data != null) {
                String from = intent.getStringExtra("from");
                if (from != null) {

                    if (from.equals("MyCircleActivity")) {
                        GetMyCircleResultBean.CirclesBean mCircleDataFromHomeFragPage = data.getParcelable("circle");
                        if (mCircleDataFromHomeFragPage == null) {
                            return;
                        }

                        //id
                        circleDataBean.setCircleId(mCircleDataFromHomeFragPage.getCircleId());
                        //name
                        circleDataBean.setCircleName(mCircleDataFromHomeFragPage.getCircleName());
                        //title
                        circleDataBean.setCircleTitle(mCircleDataFromHomeFragPage.getCircleTitle());
                        //cricle protrait img
                        circleDataBean.setCircleAvaterUrl(mCircleDataFromHomeFragPage.getCircleAvaterUrl());
                        //circle bg img
                        circleDataBean.setCircleTopicImgUrl(mCircleDataFromHomeFragPage.getCircleTopicImgUrl());
                        //create time
                        circleDataBean.setCircleCreatedTime(mCircleDataFromHomeFragPage.getCircleCreatedTime());
                        //founder id
                        circleDataBean.setFounderId(mCircleDataFromHomeFragPage.getFounderId());
                        //joined user count
                        circleDataBean.setUserJoinedCount(mCircleDataFromHomeFragPage.getUserJoinedCount());
                        //joined dynamic count
                        circleDataBean.setDynamicJoinedCount(mCircleDataFromHomeFragPage.getDynamicJoinedCount());

                        currentState = STATE_FROM_ME;//来自我的圈子

                    } else if (from.equals("HomeFragmentMyCircleList")) {

                        GetMyCircleResultBean.CirclesBean circleFromHomeFragment = data.getParcelable("circle");
                        if (circleFromHomeFragment == null) {
                            return;
                        }
                        //id
                        circleDataBean.setCircleId(circleFromHomeFragment.getCircleId());
                        //name
                        circleDataBean.setCircleName(circleFromHomeFragment.getCircleName());
                        //title
                        circleDataBean.setCircleTitle(circleFromHomeFragment.getCircleTitle());
                        //cricle protrait img
                        circleDataBean.setCircleAvaterUrl(circleFromHomeFragment.getCircleAvaterUrl());
                        //circle bg img
                        circleDataBean.setCircleTopicImgUrl(circleFromHomeFragment.getCircleTopicImgUrl());
                        //create time
                        circleDataBean.setCircleCreatedTime(circleFromHomeFragment.getCircleCreatedTime());
                        //founder id
                        circleDataBean.setFounderId(circleFromHomeFragment.getFounderId());
                        //joined user count
                        circleDataBean.setUserJoinedCount(circleFromHomeFragment.getUserJoinedCount());
                        //joined dynamic count
                        circleDataBean.setDynamicJoinedCount(circleFromHomeFragment.getDynamicJoinedCount());
                        currentState = STATE_FROM_ME;//来自我的圈子 和第一种数据完全一样
                    } else if (from.equals("HomeFragmentDynamicListWithCircle")) {
                        //从主页动态列表来 page two/page three
                        GetDynamicWithCircleResultBean.TrendsBean.CircleBean circleBeanFromHomeFragmentDyanmicList = data.getParcelable("circle");
                        if (circleBeanFromHomeFragmentDyanmicList == null) {
                            return;
                        }
                        //id
                        circleDataBean.setCircleId(circleBeanFromHomeFragmentDyanmicList.getCircleId());
                        //name
                        circleDataBean.setCircleName(circleBeanFromHomeFragmentDyanmicList.getCircleName());
                        //title
                        circleDataBean.setCircleTitle(circleBeanFromHomeFragmentDyanmicList.getCircleTitle());
                        //cricle protrait img
                        circleDataBean.setCircleAvaterUrl(circleBeanFromHomeFragmentDyanmicList.getCircleAvaterUrl());
                        //circle bg img
                        circleDataBean.setCircleTopicImgUrl(circleBeanFromHomeFragmentDyanmicList.getCircleTopicImgUrl());
                        //create time
                        circleDataBean.setCircleCreatedTime(circleBeanFromHomeFragmentDyanmicList.getCircleCreatedTime());
                        //founder id
                        circleDataBean.setFounderId(circleBeanFromHomeFragmentDyanmicList.getFounderId());
                        //joined user count
                        circleDataBean.setUserJoinedCount(circleBeanFromHomeFragmentDyanmicList.getUserJoinedCount());
                        //joined dynamic count
                        circleDataBean.setDynamicJoinedCount(circleBeanFromHomeFragmentDyanmicList.getDynamicJoinedCount());
                        currentState = STATE_FROM_OTHER;//不一定是自己加入的圈子

                    } else if (from.equals("HomeFragmentDynamicList")) {
                        //从主页动态列表来 page two/page three
                        GetDynamicResultBean.TrendsBean.CircleBean circleBeanFromHomeFragmentDyanmicList = data.getParcelable("circle");
                        if (circleBeanFromHomeFragmentDyanmicList == null) {
                            return;
                        }
                        //id
                        circleDataBean.setCircleId(circleBeanFromHomeFragmentDyanmicList.getCircleId());
                        //name
                        circleDataBean.setCircleName(circleBeanFromHomeFragmentDyanmicList.getCircleName());
                        //title
                        circleDataBean.setCircleTitle(circleBeanFromHomeFragmentDyanmicList.getCircleTitle());
                        //cricle protrait img
                        circleDataBean.setCircleAvaterUrl(circleBeanFromHomeFragmentDyanmicList.getCircleAvaterUrl());
                        //circle bg img
                        circleDataBean.setCircleTopicImgUrl(circleBeanFromHomeFragmentDyanmicList.getCircleTopicImgUrl());
                        //create time
                        circleDataBean.setCircleCreatedTime(circleBeanFromHomeFragmentDyanmicList.getCircleCreatedTime());
                        //founder id
                        circleDataBean.setFounderId(circleBeanFromHomeFragmentDyanmicList.getFounderId());
                        //joined user count
                        circleDataBean.setUserJoinedCount(circleBeanFromHomeFragmentDyanmicList.getUserJoinedCount());
                        //joined dynamic count
                        circleDataBean.setDynamicJoinedCount(circleBeanFromHomeFragmentDyanmicList.getDynamicJoinedCount());
                        currentState = STATE_FROM_OTHER;//不一定是自己加入的圈子

                    } else if (from.equals("DynamicDetailActivity")) {
                        //从动态详情页面来
                        GetDynamicResultBean.TrendsBean.CircleBean circleBeanFromDynamicDetailActivity = data.getParcelable("circle");
                        if (circleBeanFromDynamicDetailActivity == null) {
                            return;
                        }
                        //id
                        circleDataBean.setCircleId(circleBeanFromDynamicDetailActivity.getCircleId());
                        //name
                        circleDataBean.setCircleName(circleBeanFromDynamicDetailActivity.getCircleName());
                        //title
                        circleDataBean.setCircleTitle(circleBeanFromDynamicDetailActivity.getCircleTitle());
                        //cricle protrait img
                        circleDataBean.setCircleAvaterUrl(circleBeanFromDynamicDetailActivity.getCircleAvaterUrl());
                        //circle bg img
                        circleDataBean.setCircleTopicImgUrl(circleBeanFromDynamicDetailActivity.getCircleTopicImgUrl());
                        //create time
                        circleDataBean.setCircleCreatedTime(circleBeanFromDynamicDetailActivity.getCircleCreatedTime());
                        //founder id
                        circleDataBean.setFounderId(circleBeanFromDynamicDetailActivity.getFounderId());
                        //joined user count
                        circleDataBean.setUserJoinedCount(circleBeanFromDynamicDetailActivity.getUserJoinedCount());
                        //joined dynamic count
                        circleDataBean.setDynamicJoinedCount(circleBeanFromDynamicDetailActivity.getDynamicJoinedCount());
                        currentState = STATE_FROM_OTHER;//不一定是自己加入的圈子
                    } else if (from.equals("DetailCircleFragment")) {

                        GetMyCircleResultBean.CirclesBean circleFromHomeFragment = data.getParcelable("circle");
                        if (circleFromHomeFragment == null) {
                            return;
                        }
                        //id
                        circleDataBean.setCircleId(circleFromHomeFragment.getCircleId());
                        //name
                        circleDataBean.setCircleName(circleFromHomeFragment.getCircleName());
                        //title
                        circleDataBean.setCircleTitle(circleFromHomeFragment.getCircleTitle());
                        //cricle protrait img
                        circleDataBean.setCircleAvaterUrl(circleFromHomeFragment.getCircleAvaterUrl());
                        //circle bg img
                        circleDataBean.setCircleTopicImgUrl(circleFromHomeFragment.getCircleTopicImgUrl());
                        //create time
                        circleDataBean.setCircleCreatedTime(circleFromHomeFragment.getCircleCreatedTime());
                        //founder id
                        circleDataBean.setFounderId(circleFromHomeFragment.getFounderId());
                        //joined user count
                        circleDataBean.setUserJoinedCount(circleFromHomeFragment.getUserJoinedCount());
                        //joined dynamic count
                        circleDataBean.setDynamicJoinedCount(circleFromHomeFragment.getDynamicJoinedCount());
                        currentState = STATE_FROM_OTHER;//来自我的圈子 和第一种数据完全一样
                    }
                }
            }
        }
    }

    @Override
    public void initFragment() {
        if (circleDataBean == null) {
            toast("圈子信息获取失败", false);
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putString("circleName", circleDataBean.getCircleName());
        mDetailCircleNewFragment = CircleDetailPageOneFragment.newInstance(bundle);
        mDetailCircleHotFragment = CircleDetailPageTwoFragment.newInstance(bundle);
        mDetailCircleNewFragment.setViewPager(mVp);
        mDetailCircleHotFragment.setViewPager(mVp);
    }

    @Override
    public void initView() {
        //tablayout
        mTabLyt.addTab(mTabLyt.newTab());
        mTabLyt.addTab(mTabLyt.newTab());
        mTabLyt.setupWithViewPager(mVp);

        //vp
        mFragmentList = new ArrayList<>();
        mFragmentList.add(mDetailCircleNewFragment);
        mFragmentList.add(mDetailCircleHotFragment);
        mFragmentPageAdapter = new DetailFragmentPagerAdapter(getSupportFragmentManager());
        mFragmentPageAdapter.initList(mFragmentList, titles);
        mVp.setAdapter(mFragmentPageAdapter);
        mVp.setOffscreenPageLimit(1);
        mVp.setCurrentItem(0);

        /************************************** state *********************************************/
        if (currentState == STATE_FROM_ME) {
            showJoinedStatus();
        } else {
        }
    }

    @Override
    public void initDialog() {
        super.initDialog();
    }

    @Override
    public void bindListener() {
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mVp.addOnPageChangeListener(this);
    }

    @Override
    protected void initData() {
        //开始适配数据
        if (circleDataBean != null) {
            //img
            String circleAvaterUrl = circleDataBean.getCircleAvaterUrl();
            if (circleAvaterUrl == null) {
                Glide.with(this)
                        .load(R.drawable.logo_temp)
                        .apply(new RequestOptions().centerCrop())
                        .into(mPortraitImg);

            } else {
                Glide.with(this)
                        .load(TransFormUtil.getLocalImageUrl(circleAvaterUrl))
                        .apply(new RequestOptions()
                                .placeholder(R.drawable.logo_temp)
                                .error(R.drawable.error)
                                .centerCrop()
                        )
                        .into(mPortraitImg);
            }

            //bgImg
            String bgUrl = circleDataBean.getCircleTopicImgUrl();
            if (bgUrl == null) {
                Glide.with(this)
                        .load(R.drawable.logo_temp)
                        .apply(new RequestOptions().centerCrop())
                        .into(mPortraitImg);

            } else {
                Glide.with(this)
                        .load(TransFormUtil.getLocalImageUrl(bgUrl))
                        .apply(new RequestOptions()
                                .centerCrop()
                                .placeholder(R.drawable.logo_temp)
                                .error(R.drawable.error)
                        )
                        .into(mBgImg);
            }

            //circle date
            String circleCreatedTime = circleDataBean.getCircleCreatedTime();

            if (circleCreatedTime != null) {
                String replace = circleCreatedTime.replace("T", " ");
                mCreateDate.setText(replace);
            }
            //title
            String circleTitle = circleDataBean.getCircleTitle();
            if (circleTitle != null) {
                mTitle.setText(circleTitle);
            }

            //circle name
            String circleName = circleDataBean.getCircleName();
            if (circleName != null) {
                mToolbar.setTitle(circleName);
            }

            //circle join count
            int userJoinedCount = circleDataBean.getUserJoinedCount();
            mJoinedCountTv.setText(userJoinedCount + 1 + "名朋友正在圈内参与话题");
        }
    }

    @Override
    public CircleDetailActivityPresenter initPresenter() {
        return new CircleDetailActivityPresenter(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.circle_detail_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.share:
                toast("执行分享逻辑", false);
                break;
            case R.id.search:
                toPage(SearchActivity.class);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showCircleNewFragment() {
        if (mVp != null) {

            mVp.setCurrentItem(0);
        }
    }

    @Override
    public void showCircleHotFragment() {
        if (mVp != null) {

            mVp.setCurrentItem(1);
        }
    }

    @Override
    public CircleDetailPageOneFragment getCircleNewFragment() {
        if (mDetailCircleNewFragment != null) {
            return mDetailCircleNewFragment;
        }
        return mDetailCircleNewFragment = CircleDetailPageOneFragment.newInstance(null);
    }

    @Override
    public CircleDetailPageTwoFragment getCircleHotFragment() {
        if (mDetailCircleHotFragment != null) {
            return mDetailCircleHotFragment;
        }
        return mDetailCircleHotFragment = CircleDetailPageTwoFragment.newInstance(null);
    }

    @Override
    public Toolbar getToolBar() {
        return mToolbar;
    }

    @Override
    public ImageView getBgImg() {
        return mBgImg;
    }

    @Override
    public ImageView getPortraitBgImg() {
        return mPortraitImg;
    }

    @Override
    public TextView getCircleTitleTv() {
        return mTitle;
    }

    @Override
    public TextView getCircleDateTv() {
        return mCreateDate;
    }

    @Override
    public TabLayout getTabLayout() {
        return mTabLyt;
    }

    @Override
    public ViewPager getViewPager() {
        return mVp;
    }

    @Override
    public FloatingActionButton getFloatingActionBar() {
        return mFab;
    }

    @Override
    public void showJoinedStatus() {
        //已加入
        mJoinTv.setVisibility(View.VISIBLE);
        mJoinTv.setEnabled(false);
        mJoinTv.setText("已加入");
    }

    @Override
    public void showUnJoinedStatus() {
        mJoinTv.setVisibility(View.VISIBLE);
        mJoinTv.setEnabled(true);
        mJoinTv.setText("未加入");
    }

    /***********************************toobar-navIcon-click*************************************************/

    @OnClick({R.id.activity_circle_detail_join_tv, R.id.activity_circle_detail_fab})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.activity_circle_detail_join_tv:
                //todo 加入圈子的逻辑
                getPresent().doJoin();
                break;
            case R.id.activity_circle_detail_fab:
                Intent intent = new Intent(this, PublishDynamicActivity.class);
                Bundle bundle = new Bundle();
                bundle.putParcelable("circle_bean", getCircleDataBean());//带出去封装好的circle_bean
                intent.putExtra("data", bundle);
                intent.putExtra("from", "circle_detail");
                startActivity(intent);
                break;
        }
    }

    /***********************************vp select call back*************************************************/

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        mCurrPostion = position;

        if (position == 1) {
            CircleDetailPageTwoFragment circleHotFragment = getCircleHotFragment();
            int itemCount = circleHotFragment.getAdapter().getItemCount();
            if (itemCount == 0) {
                getCircleHotFragment().getPresent().getHotDynamicTask(1);
            }
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }
}
