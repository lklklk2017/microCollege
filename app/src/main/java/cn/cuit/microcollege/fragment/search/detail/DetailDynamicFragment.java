package cn.cuit.microcollege.fragment.search.detail;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.cuit.microcollege.R;
import cn.cuit.microcollege.activity.CircleDetailActivity;
import cn.cuit.microcollege.activity.DynamicDetailActivity;
import cn.cuit.microcollege.activity.SearchActivity;
import cn.cuit.microcollege.adapter.HomePageOneAdapter;
import cn.cuit.microcollege.application.MyApplication;
import cn.cuit.microcollege.base.BaseFragment;
import cn.cuit.microcollege.contract.DetailDynamicFragmentContract;
import cn.cuit.microcollege.entity.HttpResultEntity.GetDynamicResultBean;
import cn.cuit.microcollege.presenter.DetailDynamicFragmentPresenter;
import cn.cuit.microcollege.utils.LogU;
import cn.cuit.microcollege.widget.CustomViewPager;

/**
 * @Author: Created by Rod Eden
 * @Date: 2019/4/27
 * @Descirption: searchActivity-DetailFragment—dynamic
 */
public class DetailDynamicFragment extends BaseFragment<DetailDynamicFragmentPresenter> implements DetailDynamicFragmentContract.View, HomePageOneAdapter.TrendItemClick {

    @BindView(R.id.detail_fragment_dynamic_rcy)
    RecyclerView mDetailRcy;
    Unbinder unbinder;
    @BindView(R.id.detail_fragment_dynamic_no_result)
    LinearLayout mDynamicNoResult;
    private int mLastComVisPosition;
    private int mCurrPage = 1;
    private int mCurrPageSize = 5;
    private HomePageOneAdapter mAdapter;
    private LinearLayoutManager mLinManager;

    public DetailDynamicFragment() {
    }

    public static DetailDynamicFragment newInstance(Bundle bundle) {
        DetailDynamicFragment currfragment = new DetailDynamicFragment();
        if (bundle != null) {
            currfragment.setArguments(bundle);
        }
        return currfragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View inflate = inflater.inflate(R.layout.detail_fragment_dynamic, container, false);
        setCreatedView(inflate);
        CustomViewPager viewPager = (CustomViewPager) getViewPager();
        if (viewPager != null) {
            viewPager.setObjectForPosition(inflate, 1);
        }
        unbinder = ButterKnife.bind(this, inflate);
        return inflate;
    }

    @Override
    public DetailDynamicFragmentPresenter initPresenter() {
        return new DetailDynamicFragmentPresenter(this);
    }

    @Override
    public void initView() {
        mLinManager = new LinearLayoutManager(getContext());
        mLinManager.setOrientation(LinearLayoutManager.VERTICAL);
        mDetailRcy.setLayoutManager(mLinManager);
        mAdapter = new HomePageOneAdapter();
        mDetailRcy.setAdapter(mAdapter);
    }

    @Override
    public void bindListener() {
        mAdapter.setListener(this);

        mDetailRcy.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                mLastComVisPosition = mLinManager.findLastCompletelyVisibleItemPosition();
                int itemCount = mAdapter.getItemCount();
                if ((itemCount - 1) == mLastComVisPosition) {
                    getPresent().doChangeDynamicTask(1, getPageSize() * setCurrentPage(1 + getCurrentPage()));
                }
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onResume() {
        super.onResume();
        LogU.i("onResume-dynamic", "life");
    }

    @Override
    public void onStart() {
        super.onStart();
        LogU.i("onStart-dynamic", "life");
    }

    @Override
    public void onPause() {
        super.onPause();
        LogU.i("onPause-dynamic", "life");
    }

    @Override
    public void onStop() {
        super.onStop();
        LogU.i("onStop-dynamic", "life");
    }

    @Override
    public void showHasResultView() {
        mDetailRcy.setVisibility(View.VISIBLE);
        mDynamicNoResult.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showNoResultView() {
        mDetailRcy.setVisibility(View.INVISIBLE);
        mDynamicNoResult.setVisibility(View.VISIBLE);
    }

    @Override
    public SwipeRefreshLayout getSwip() {
        return ((SearchActivity) getActivity()).getSwip();
    }

    @Override
    public HomePageOneAdapter getAdapter() {
        return mAdapter;
    }

    @Override
    public int getCurrentPage() {
        return mCurrPage;
    }

    @Override
    public int getPageSize() {
        return mCurrPageSize;
    }

    @Override
    public int setCurrentPage(int page) {
        return mCurrPage = page;
    }

    @Override
    public void onItemClick(GetDynamicResultBean.TrendsBean trendsBean) {
        Intent intent = new Intent(MyApplication.getMyContext(), DynamicDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable("trend", trendsBean);
        intent.putExtra("from", "HomePageOne");
        intent.putExtra("data", bundle);
        startActivity(intent);
    }

    @Override
    public void onLikeClick(int position) {

    }

    @Override
    public void onCommentClick() {

    }

    @Override
    public void onCircleTagClick(GetDynamicResultBean.TrendsBean trendsBean) {
        Intent intent = new Intent(MyApplication.getMyContext(), CircleDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable("circle", trendsBean.getCircle());
        intent.putExtra("data", bundle);
        intent.putExtra("from", "HomeFragmentDynamicList");
        startActivity(intent);
    }

    @Override
    public void onLongItemClick(GetDynamicResultBean.TrendsBean trendsBean, int position) {

    }
}
