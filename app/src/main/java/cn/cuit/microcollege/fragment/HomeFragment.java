package cn.cuit.microcollege.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.cuit.microcollege.R;
import cn.cuit.microcollege.activity.CircleDetailActivity;
import cn.cuit.microcollege.activity.MyCircleActivity;
import cn.cuit.microcollege.activity.SearchActivity;
import cn.cuit.microcollege.adapter.HomeFragmentPagerAdapter;
import cn.cuit.microcollege.adapter.MyCircleHomeAdapter;
import cn.cuit.microcollege.application.MyApplication;
import cn.cuit.microcollege.base.BaseFragment;
import cn.cuit.microcollege.contract.HomeFragmentContract;
import cn.cuit.microcollege.entity.HttpResultEntity.GetMyCircleResultBean;
import cn.cuit.microcollege.presenter.HomeFragmentPresenter;
import cn.cuit.microcollege.widget.CustomViewPager;

import static cn.cuit.microcollege.utils.Config.SWIP_COLORS;


public class HomeFragment extends BaseFragment<HomeFragmentPresenter>
        implements HomeFragmentContract.View,
        SwipeRefreshLayout.OnRefreshListener,
        ViewPager.OnPageChangeListener,
        TabLayout.OnTabSelectedListener, MyCircleHomeAdapter.CircleItemClickListener {

    Unbinder unbinder;
    @BindView(R.id.home_fragment_myCircle_rly)
    RelativeLayout mMyCircleRly;
    @BindView(R.id.home_fragment_myCircleList_rcy)
    RecyclerView mMyCircleRcy;
    @BindView(R.id.home_fragment_tabLayout)
    TabLayout mTabLayout;
    @BindView(R.id.home_fragment_toolbar)
    Toolbar mToolBar;
    @BindView(R.id.home_fragment_vp)
    CustomViewPager mVp;
    @BindView(R.id.home_fragment_swipRly)
    SwipeRefreshLayout mSwipRly;
    @BindView(R.id.home_fragment_search)
    LinearLayout mSearchLyt;
    @BindView(R.id.home_fragment_tabLayout_wrapper)
    LinearLayout mTabLayoutWrapper;
    @BindView(R.id.home_fragment_coordinateLyt)
    CoordinatorLayout mCooLyt;
    @BindView(R.id.home_fragment_appBarLyt)
    AppBarLayout mAppBarLyt;

    private int currPage = 0;
    private static final int defaultPage = 0;
    private MyCircleHomeAdapter mMyCircleAdapter;
    private HomeFragmentPagerAdapter mViewPagerAdapter;
    private HomePagerOneFragment mHomeFragmentPagerOne;
    private HomePagerTwoFragment mHomeFragmentPagerTwo;
    private HomePagerThreeFragment mHomeFragmentPagerThree;
    private ArrayList<Fragment> mFramentList;

    public HomeFragment() {
    }

    public static HomeFragment newInstance(Bundle bundle) {

        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        setCreatedView(view);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public HomeFragmentPresenter initPresenter() {
        return new HomeFragmentPresenter(this);
    }

    @Override
    public void initView() {
        //recycle
        LinearLayoutManager lm = new LinearLayoutManager(MyApplication.getMyContext());
        lm.setOrientation(LinearLayoutManager.HORIZONTAL);
        mMyCircleRcy.setHasFixedSize(true);
        mMyCircleRcy.setLayoutManager(lm);
        mMyCircleAdapter = new MyCircleHomeAdapter();
        mMyCircleRcy.setAdapter(mMyCircleAdapter);

        //swip
        mSwipRly.setColorSchemeColors(SWIP_COLORS);

        //tablayout
        mTabLayout.addTab(mTabLayout.newTab());
        mTabLayout.addTab(mTabLayout.newTab());
        mTabLayout.addTab(mTabLayout.newTab());
        mTabLayout.setupWithViewPager(mVp);

        //vp
        mFramentList = new ArrayList<>();
        mHomeFragmentPagerOne = HomePagerOneFragment.newInstance(null);
        mHomeFragmentPagerTwo = HomePagerTwoFragment.newInstance(null);
        mHomeFragmentPagerThree = HomePagerThreeFragment.newInstance(null);
        //初始化fragment中的附加vp应用
        mHomeFragmentPagerOne.setViewPager(mVp);
        mHomeFragmentPagerTwo.setViewPager(mVp);
        mHomeFragmentPagerThree.setViewPager(mVp);
        mFramentList.add(mHomeFragmentPagerOne);
        mFramentList.add(mHomeFragmentPagerTwo);
        mFramentList.add(mHomeFragmentPagerThree);
        mViewPagerAdapter = new HomeFragmentPagerAdapter(getChildFragmentManager());
        mViewPagerAdapter.initList(mFramentList, new String[]{"所有", "最新", "最热"});
        mVp.setAdapter(mViewPagerAdapter);
        mVp.setCurrentItem(defaultPage);
        mVp.setOffscreenPageLimit(2);
    }

    @Override
    public void bindListener() {
        mSwipRly.setOnRefreshListener(this);
//        mMyCircleRcy.setOnTouchListener(this);
//        mTabLayoutWrapper.setOnTouchListener(this);

        //tablayout
        mTabLayout.addOnTabSelectedListener(this);

        //vp
        mVp.addOnPageChangeListener(this);
        mMyCircleAdapter.setListener(this);
    }

    @OnClick({R.id.home_fragment_search, R.id.home_fragment_myCircle_rly})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.home_fragment_search:
                toPage(SearchActivity.class);
                break;
            case R.id.home_fragment_myCircle_rly:
                toPage(MyCircleActivity.class);
                break;
        }
    }

    @Override
    protected void initData() {
        //获取我的圈子数据
        getPresent().myCircleListTask();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onRefresh() {
        getSwip().setRefreshing(false);

        int itemCount = mMyCircleAdapter.getItemCount();
        if (itemCount == 0) {
            getPresent().myCircleListTask();
        }

        if (currPage == 0) {
//            int itemCountFromOne = getPageOneFragment().getAdapter().getItemCount();
//            if (itemCountFromOne == 0) {
            getPageOneFragment().getPresent().changeData(1, 4);
//            }
        } else if (currPage == 1) {
//            int itemCountFromTwo = getPageTwoFragment().getAdapter().getItemCount();

//            if (itemCountFromTwo == 0) {
            getPageTwoFragment().getPresent().changeData(1, 4);
//            }
        } else if (currPage == 2) {
//            int itemCountFromThree = getPageThreeFragment().getAdapter().getItemCount();

//            if (itemCountFromThree == 0) {
            getPageThreeFragment().getPresent().changeData(1, 4);
//            }
        }
    }

    /******************************************滑动布局 滑动监听************************************/
//    @Override
//    public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
//
//        LogU.i("scrollX：" + scrollX + " scrollY：" + scrollY + " oldScrollX：" + oldScrollX + " oldScrollY：" + oldScrollY, this.getClass().getName());
//        if (oldScrollY == 0) {
//            canRefresh(false);
//        }
//
//        if (scrollY == 0) {
//
//        }
//    }

    /******************************************vp布局 页面滑动监听************************************/

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
        mVp.resetHeight(position);
        currPage = position;
        if (currPage == 0) {
            int itemCountFromOne = getPageOneFragment().getAdapter().getItemCount();
            if (itemCountFromOne == 0) {
                getPageOneFragment().getPresent().changeData(1, 4);
            }
        } else if (currPage == 1) {
            int itemCountFromTwo = getPageTwoFragment().getAdapter().getItemCount();

            if (itemCountFromTwo == 0) {
                getPageTwoFragment().getPresent().changeData(1, 4);
            }
        } else if (currPage == 2) {
            int itemCountFromThree = getPageThreeFragment().getAdapter().getItemCount();

            if (itemCountFromThree == 0) {
                getPageThreeFragment().getPresent().changeData(1, 4);
            }
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    /******************************************tablayout布局 页面滑动监听************************************/

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
//        int position = tab.getPosition();
//        mVp.setCurrentItem(position);
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    /********************************控件获取*****************************************/
    @Override
    public SwipeRefreshLayout getSwip() {
        return mSwipRly;
    }

    @Override
    public RecyclerView getMyCircleRcy() {
        return mMyCircleRcy;
    }

    @Override
    public MyCircleHomeAdapter getMycircleAdpater() {
        return mMyCircleAdapter;
    }

    @Override
    public CustomViewPager getVp() {
        return mVp;
    }

    @Override
    public HomePagerOneFragment getPageOneFragment() {
        if (mHomeFragmentPagerOne != null) {
            return mHomeFragmentPagerOne;
        }
        return mHomeFragmentPagerOne.newInstance(null);
    }

    @Override
    public HomePagerTwoFragment getPageTwoFragment() {
        if (mHomeFragmentPagerTwo != null) {
            return mHomeFragmentPagerTwo;
        }
        return mHomeFragmentPagerTwo.newInstance(null);
    }

    @Override
    public HomePagerThreeFragment getPageThreeFragment() {
        if (mHomeFragmentPagerThree != null) {
            return mHomeFragmentPagerThree;
        }
        return mHomeFragmentPagerThree.newInstance(null);
    }

    @Override
    public void onItemClick(GetMyCircleResultBean.CirclesBean circlesBean) {
        Intent intent = new Intent(getActivity(), CircleDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable("circle", circlesBean);
        intent.putExtra("data", bundle);
        intent.putExtra("from", "HomeFragmentMyCircleList");
        startActivity(intent);
    }
}
