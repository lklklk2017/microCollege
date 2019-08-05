package cn.cuit.microcollege.fragment.dynamic;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.cuit.microcollege.R;
import cn.cuit.microcollege.adapter.NewsFragmentPagerAdapter;
import cn.cuit.microcollege.base.BaseFragment;
import cn.cuit.microcollege.contract.DyanmicFragmentContract;
import cn.cuit.microcollege.presenter.DyanmicFragmentPresenter;
import cn.cuit.microcollege.widget.CustomViewPager;
import cn.cuit.microcollege.widget.actionBar.CustomDefaultCenterActionBar;

/**
 * @Author: Created by Rod Eden
 * @Date: 2019/4/16
 * @Descirption: 新闻动态页面：微信精选
 */
public class DynamicFragment extends BaseFragment<DyanmicFragmentPresenter> implements DyanmicFragmentContract.View, ViewPager.OnPageChangeListener, SwipeRefreshLayout.OnRefreshListener {


    Unbinder unbinder;
    @BindView(R.id.dynamic_fragment_actionbar)
    CustomDefaultCenterActionBar mActionbar;
    @BindView(R.id.dynamic_fragment_tablayout)
    TabLayout mTablayout;
    @BindView(R.id.dynamic_fragment_vp)
    CustomViewPager mVp;
    @BindView(R.id.dynamic_fragment_swip)
    SwipeRefreshLayout mSwip;
    private int currPagePosition = 0;
    private String[] titles = new String[]{"热点", "互联网", "时尚", "健康", "旅行"};//2.13.1.3.9
    private ArrayList<Fragment> mFragments;
    private NewHotFragment mHot;
    private NewsFragmentPagerAdapter mVpPagerAdapter;
    private NewInternetFragment mInternet;
    private NewFashionFragment mFashion;
    private NewHealthFragment mHealth;
    private NewTravelFragment mTravel;

    public DynamicFragment() {
    }

    public static DynamicFragment newInstance(Bundle bundle) {
        DynamicFragment fragment = new DynamicFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_dynamic, container, false);
        setCreatedView(view);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    protected void initFragment() {
        mHot = NewHotFragment.newInstance(null);
        mInternet = NewInternetFragment.newInstance(null);
        mFashion = NewFashionFragment.newInstance(null);
        mHealth = NewHealthFragment.newInstance(null);
        mTravel = NewTravelFragment.newInstance(null);

        mHot.setViewPager(mVp);
        mInternet.setViewPager(mVp);
        mFashion.setViewPager(mVp);
        mHealth.setViewPager(mVp);
        mTravel.setViewPager(mVp);
    }

    @Override
    public void initView() {
//        "热点", "互联网", "时尚", "健康", "旅行"
        //tab
        mTablayout.addTab(mTablayout.newTab());
        mTablayout.addTab(mTablayout.newTab());
        mTablayout.addTab(mTablayout.newTab());
        mTablayout.addTab(mTablayout.newTab());
        mTablayout.addTab(mTablayout.newTab());

        mTablayout.setupWithViewPager(mVp);

        //vp
        mFragments = new ArrayList<>();
        mFragments.add(mHot);
        mFragments.add(mInternet);
        mFragments.add(mFashion);
        mFragments.add(mHealth);
        mFragments.add(mTravel);
        //init Fragments...
        mVpPagerAdapter = new NewsFragmentPagerAdapter(getChildFragmentManager());
        mVpPagerAdapter.initList(mFragments, titles);
        mVp.setAdapter(mVpPagerAdapter);
        mVp.setOffscreenPageLimit(5);
        mVp.setCurrentItem(0);
    }

    @Override
    public DyanmicFragmentPresenter initPresenter() {
        return new DyanmicFragmentPresenter(this);
    }

    @Override
    public void bindListener() {
        mVp.addOnPageChangeListener(this);
        mSwip.setOnRefreshListener(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    /**********************************vp page change call back***********************************/
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        mVp.resetHeight(position);
        currPagePosition = position;
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }

    @Override
    public NewHotFragment getHotFragment() {
        if (mHot != null) {
            return mHot;
        } else {
            return mHot = NewHotFragment.newInstance(null);
        }
    }

    @Override
    public NewInternetFragment getInternetFragment() {
        if (mInternet != null) {
            return mInternet;
        } else {
            return mInternet = NewInternetFragment.newInstance(null);
        }
    }

    @Override
    public NewFashionFragment getFashionFragment() {
        if (mFashion != null) {
            return mFashion;
        } else {
            return mFashion = NewFashionFragment.newInstance(null);
        }
    }

    @Override
    public NewHealthFragment getHealthFragment() {
        if (mHealth != null) {
            return mHealth;
        } else {
            return mHealth = NewHealthFragment.newInstance(null);
        }
    }

    @Override
    public NewTravelFragment getTravelFragment() {
        if (mTravel != null) {
            return mTravel;
        } else {
            return mTravel = NewTravelFragment.newInstance(null);
        }
    }

    @Override
    public SwipeRefreshLayout getSwip() {
        return mSwip;
    }

    @Override
    public void onRefresh() {
        switch (currPagePosition) {
            case 0:
                getHotFragment().setRefresh();
                break;
            case 1:
                getInternetFragment().setRefresh();
                break;
            case 2:
                getFashionFragment().setRefresh();
                break;
            case 3:
                getHealthFragment().setRefresh();
                break;
            case 4:
                getTravelFragment().setRefresh();
                break;
        }
    }
}
