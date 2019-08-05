package cn.cuit.microcollege.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.cuit.microcollege.R;
import cn.cuit.microcollege.base.BaseActivity;
import cn.cuit.microcollege.contract.SearchActivityContract;
import cn.cuit.microcollege.fragment.search.SearchDetailFragment;
import cn.cuit.microcollege.fragment.search.SearchHistoryFragment;
import cn.cuit.microcollege.presenter.SearchActivityPresenter;
import cn.cuit.microcollege.utils.Config;
import cn.cuit.microcollege.widget.actionBar.CustomSearchBar;

/**
 * @Author: Created by Rod Eden
 * @Date: 2019/4/25
 * @Descirption:
 */
public class SearchActivity extends BaseActivity<SearchActivityPresenter> implements SearchActivityContract.View, CustomSearchBar.SearchBarListener {

    @BindView(R.id.search_searchBar)
    CustomSearchBar mSearchBar;
    @BindView(R.id.search_swip)
    SwipeRefreshLayout mSwip;
    private FragmentManager mFm;
    private SearchHistoryFragment mHistoryFragment;

    private Fragment currentFragment;
    private SearchDetailFragment mDetailFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
    }

    @Override
    public void initFragment() {
        mHistoryFragment = SearchHistoryFragment.newInstance(null);
        mDetailFragment = SearchDetailFragment.newInstance(null);
    }

    @Override
    public void initView() {
        //swip
        mSwip.setEnabled(false);
        mSwip.setColorSchemeColors(Config.SWIP_COLORS);
        setDefaultCurrentFragment();
    }

    @Override
    public void bindListener() {
        mSearchBar.setListener(this);
    }

    @Override
    public void setDefaultCurrentFragment() {
        mFm = getSupportFragmentManager();
        FragmentTransaction ft = mFm.beginTransaction();
        ft.add(R.id.search_frameLyt, mHistoryFragment);
        ft.commit();
        setCurrentFragment(mHistoryFragment);
    }

    @Override
    public void setReFresh(boolean canRefresh) {
        getSwip().setRefreshing(canRefresh);
    }

    @Override
    public void showHistoryFragment() {
        shiftCurrentFragment(getHistoryFragment());
    }

    @Override
    public void showDetailFragment(String condiction) {
        hideInput();
        shiftCurrentFragment(getDetailFragment(condiction));
    }

    @Override
    public void setCurrentFragment(Fragment fragment) {
        currentFragment = fragment;
    }

    @Override
    public Fragment getCurrentFragment() {
        if (null != currentFragment) {
            return currentFragment;
        } else {
            return null;
        }
    }

    @Override
    public void shiftCurrentFragment(Fragment fragment) {

        //history
        if (fragment instanceof SearchHistoryFragment) {
            boolean same = (getCurrentFragment() == fragment);
            if (!same) {
                FragmentTransaction t = mFm.beginTransaction();
                if (!mHistoryFragment.isAdded()) {
                    t.add(R.id.search_frameLyt, mHistoryFragment);
                }
                t.hide(getCurrentFragment());
                t.show(mHistoryFragment);
                t.commit();
                setCurrentFragment(mHistoryFragment);
            }
        }

        //detail
        if (fragment instanceof SearchDetailFragment) {
            boolean same = (getCurrentFragment() == fragment);
            if (!same) {
//                Fragment currentFragment = getCurrentFragment();
//                if (currentFragment instanceof SearchDetailFragment) {
//                    //当前是结果类型
//                    //1.替换
//                    FragmentTransaction t = mFm.beginTransaction();
//                    t.remove(currentFragment);
//                    t.add(R.id.search_frameLyt, fragment);
//                    t.addToBackStack("detail-other");
//                    t.commit();
//                } else {
                //当前是 历史类型
                //1.加入
                FragmentTransaction t = mFm.beginTransaction();
                if (!fragment.isAdded()) {
                    t.add(R.id.search_frameLyt, fragment);
                }
                t.hide(getCurrentFragment());
                t.show(fragment);
                t.commit();
//                }
                setCurrentFragment(fragment);
            }
        }
    }

    @Override
    public SearchActivityPresenter initPresenter() {
        return new SearchActivityPresenter(this);
    }

    @Override
    public SearchHistoryFragment getHistoryFragment() {
        if (mHistoryFragment != null) {
            return mHistoryFragment;
        }
        return SearchHistoryFragment.newInstance(null);
    }

    @Override
    public SearchDetailFragment getDetailFragment(String condiction) {
//        if (mDetailFragment != null) {
//            return mDetailFragment;
//        }
        Bundle bundle = new Bundle();
        bundle.putString("condiction", condiction);
        return SearchDetailFragment.newInstance(bundle);
    }

    @Override
    public SwipeRefreshLayout getSwip() {
        if (mSwip != null) {
            return mSwip;
        }
        throw new RuntimeException(getClass().getName() + "-mSwip 为null");
    }

    @Override
    public CustomSearchBar getSearchBar() {
        if (mSearchBar != null) {
            return mSearchBar;
        }
        throw new RuntimeException(getClass().getName() + "-mSearchBar 为null");
    }

    /********************************搜索bar 监听回调*************************/
    @Override
    public void onBack() {
        finish();
    }

    @Override
    public void onSearch(String content) {
        boolean refreshing = getSwip().isRefreshing();
        if (!refreshing) {
            getPresent().search(content);
        }
    }

    @Override
    public void onClear() {
        getSearchBar().setSearchBarEditTextFoucs(true);
        showInput(getSearchBar().getEd());
        showHistoryFragment();
    }
}
