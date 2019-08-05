package cn.cuit.microcollege.fragment.search;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.cuit.microcollege.R;
import cn.cuit.microcollege.adapter.DetailFragmentPagerAdapter;
import cn.cuit.microcollege.base.BaseFragment;
import cn.cuit.microcollege.contract.SearchFragmentDetailContract;
import cn.cuit.microcollege.fragment.search.detail.DetailCircleFragment;
import cn.cuit.microcollege.fragment.search.detail.DetailDynamicFragment;
import cn.cuit.microcollege.fragment.search.detail.DetailUserFragment;
import cn.cuit.microcollege.presenter.SearchFragmentDetailPresenter;
import cn.cuit.microcollege.utils.LogU;
import cn.cuit.microcollege.widget.CustomViewPager;

/**
 * @Author: Created by Rod Eden
 * @Date: 2019/4/25
 * @Descirption: Search-DetailFragment
 */
public class SearchDetailFragment extends BaseFragment<SearchFragmentDetailPresenter> implements SearchFragmentDetailContract.View, ViewPager.OnPageChangeListener {

    Unbinder unbinder;
    @BindView(R.id.search_detail_fragment_tabLyt)
    TabLayout mTabLyt;
    @BindView(R.id.search_detail_fragment_vp)
    CustomViewPager mVp;
    private String[] mTitleList;
    private DetailFragmentPagerAdapter mVpAdapter;
    private ArrayList<Fragment> mFragmentList;
    private DetailCircleFragment mDetailCircleFragment;
    private DetailDynamicFragment mDetailDynamicFragment;
    private DetailUserFragment mDetailUserFragment;
    private String mCondiction = "";//condiction
    private int mCurrPosition = 0;

    public String getmCondiction() {
        return mCondiction;
    }

    public void setmCondiction(String mCondiction) {
        this.mCondiction = mCondiction;
    }

    public static SearchDetailFragment newInstance(Bundle bundle) {
        SearchDetailFragment currFragment = new SearchDetailFragment();
        if (bundle != null) {
            currFragment.setArguments(bundle);
        }
        return currFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.search_fragment_detail, container, false);
        setCreatedView(view);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public SearchFragmentDetailPresenter initPresenter() {
        return new SearchFragmentDetailPresenter(this);
    }

    @Override
    protected void initFragment() {
        mDetailCircleFragment = DetailCircleFragment.newInstance(getArguments());
        mDetailDynamicFragment = DetailDynamicFragment.newInstance(getArguments());
        mDetailUserFragment = DetailUserFragment.newInstance(getArguments());

        mDetailCircleFragment.setViewPager(mVp);
        mDetailDynamicFragment.setViewPager(mVp);
        mDetailUserFragment.setViewPager(mVp);
    }


    @Override
    public void initView() {
        //tab layout
        mTitleList = new String[]{"圈子", "动态", "用户"};
        mTabLyt.addTab(mTabLyt.newTab());
        mTabLyt.addTab(mTabLyt.newTab());
        mTabLyt.addTab(mTabLyt.newTab());
        mTabLyt.setupWithViewPager(mVp);

        //vp
        //1.initFragment
        mFragmentList = new ArrayList<>();
        mFragmentList.add(mDetailCircleFragment);
        mFragmentList.add(mDetailDynamicFragment);
        mFragmentList.add(mDetailUserFragment);

        mVpAdapter = new DetailFragmentPagerAdapter(getChildFragmentManager());
        mVpAdapter.initList(mFragmentList, mTitleList);
        mVp.setAdapter(mVpAdapter);
        mVp.setOffscreenPageLimit(2);
        mVp.setCurrentItem(0);
    }

    public void setCurrentItem(int currentPosition) {
        if (mVp != null) {
            mVp.setCurrentItem(currentPosition);
        }
    }

    @Override
    public void bindListener() {
        mVp.addOnPageChangeListener(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onResume() {
        super.onResume();
        LogU.i("onResume-Search", "life");
        switch (mCurrPosition) {
            case 0:
                int itemCount = getDetailCircleFragment().getPresent().getV().getAdapter().getItemCount();
                if (itemCount == 0) {
                    getDetailCircleFragment().getPresent().doChangeCircleTask(1, 10);
                }
                return;
            case 1:
                break;
            case 2:
                break;
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        LogU.i("onStart-Search", "life");
    }

    @Override
    public void onPause() {
        super.onPause();
        LogU.i("onPause-Search", "life");
    }

    @Override
    public void onStop() {
        super.onStop();
        LogU.i("onStop-Search", "life");
    }

    /*************************************vp listener**************************************************/
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        mCurrPosition = position;
        if (position == 0) {
            int itemCount = getDetailCircleFragment().getAdapter().getItemCount();
            if (itemCount == 0) {
                getDetailCircleFragment().getPresent().doChangeCircleTask(1, 5);
            }
        } else if (position == 1) {
            int itemCount = getDetailDynamicFragment().getAdapter().getItemCount();
            if (itemCount == 0) {
                getDetailDynamicFragment().getPresent().doChangeDynamicTask(1, 5);
            }
        } else if (position == 2) {
            int itemCount = getDetailUserFragment().getAdapter().getItemCount();
            if (itemCount == 0) {
                getDetailUserFragment().getPresent().doChangeUserTask(1, 12);
            }
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public DetailCircleFragment getDetailCircleFragment() {
        return mDetailCircleFragment;
    }

    @Override
    public DetailDynamicFragment getDetailDynamicFragment() {
        return mDetailDynamicFragment;
    }

    @Override
    public DetailUserFragment getDetailUserFragment() {
        return mDetailUserFragment;
    }
}
