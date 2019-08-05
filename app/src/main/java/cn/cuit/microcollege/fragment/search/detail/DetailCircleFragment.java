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

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.cuit.microcollege.R;
import cn.cuit.microcollege.activity.CircleDetailActivity;
import cn.cuit.microcollege.activity.SearchActivity;
import cn.cuit.microcollege.adapter.CircleSearchAdapter;
import cn.cuit.microcollege.application.MyApplication;
import cn.cuit.microcollege.base.BaseFragment;
import cn.cuit.microcollege.contract.DetailCircleFragmentContract;
import cn.cuit.microcollege.entity.HttpResultEntity.GetMyCircleResultBean;
import cn.cuit.microcollege.fragment.search.SearchDetailFragment;
import cn.cuit.microcollege.presenter.DetailCircleFragmentPresenter;
import cn.cuit.microcollege.utils.LogU;
import cn.cuit.microcollege.widget.CustomMyCircleSelectAddlDialog;
import cn.cuit.microcollege.widget.CustomViewPager;

/**
 * @Author: Created by Rod Eden
 * @Date: 2019/4/27
 * @Descirption: searchActivity-DetailFragment—circle
 */
public class DetailCircleFragment extends BaseFragment<DetailCircleFragmentPresenter> implements DetailCircleFragmentContract.View, CircleSearchAdapter.AdapterClickListener, CustomMyCircleSelectAddlDialog.ContentClickListener {

    @BindView(R.id.detail_fragment_circle_rcy)
    RecyclerView mDetailRcy;
    Unbinder unbinder;
    @BindView(R.id.detail_fragment_circle_no_result)
    LinearLayout mCircleNoResult;
    private CircleSearchAdapter mDetailAdapter;
    private List<GetMyCircleResultBean.CirclesBean> mDetailList;
    private LinearLayoutManager mLinManager;
    private int mLastComVisPosition;
    private int mCurrPage = 1;
    private int mCurrPageSize = 15;
    private CustomMyCircleSelectAddlDialog mOptionDialog;
    private int mCurrPosition;

    public DetailCircleFragment() {
    }

    public static DetailCircleFragment newInstance(Bundle bundle) {
        DetailCircleFragment currfragment = new DetailCircleFragment();
        if (bundle != null) {
            currfragment.setArguments(bundle);
        }
        return currfragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View inflate = inflater.inflate(R.layout.detail_fragment_circle, container, false);
        setCreatedView(inflate);
        CustomViewPager viewPager = (CustomViewPager) getViewPager();
        if (viewPager != null) {
            viewPager.setObjectForPosition(inflate, 0);
        }
        unbinder = ButterKnife.bind(this, inflate);
        return inflate;
    }

    @Override
    public DetailCircleFragmentPresenter initPresenter() {
        return new DetailCircleFragmentPresenter(this);
    }

    @Override
    public void initView() {
        mLinManager = new LinearLayoutManager(getContext());
        mLinManager.setOrientation(LinearLayoutManager.VERTICAL);

        mDetailAdapter = new CircleSearchAdapter();
        mDetailRcy.setLayoutManager(mLinManager);
        mDetailRcy.setAdapter(mDetailAdapter);
    }

    @Override
    public void initDialog() {
        mOptionDialog = new CustomMyCircleSelectAddlDialog(getActivity());
    }

    @Override
    public void bindListener() {
        mDetailAdapter.setAdapterClickListener(this);
        mDetailRcy.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                mLastComVisPosition = mLinManager.findLastCompletelyVisibleItemPosition();
                int itemCount = mDetailAdapter.getItemCount();
                if ((itemCount - 1) == mLastComVisPosition) {
                    getPresent().doChangeCircleTask(1, getPageSize() * setCurrentPage(1 + getCurrentPage()));
                }
            }
        });
        mOptionDialog.setContentClickListener(this);
    }

    @Override
    protected void initData() {
    }

    @Override
    public void onResume() {
        super.onResume();
        LogU.i("onResume-circle", "life");
    }

    @Override
    public void onStart() {
        super.onStart();
        LogU.i("onStart-circle", "life");
    }

    @Override
    public void onPause() {
        super.onPause();
        LogU.i("onPause-circle", "life");
    }

    @Override
    public void onStop() {
        super.onStop();
        LogU.i("onStop-circle", "life");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void showHasResultView() {
        mDetailRcy.setVisibility(View.VISIBLE);
        mCircleNoResult.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showNoResultView() {
        mDetailRcy.setVisibility(View.INVISIBLE);
        mCircleNoResult.setVisibility(View.VISIBLE);
    }

    @Override
    public SwipeRefreshLayout getSwip() {
        return ((SearchActivity) getActivity()).getSwip();
    }

    @Override
    public RecyclerView getCircleResultRcy() {
        return mDetailRcy;
    }

    @Override
    public CircleSearchAdapter getAdapter() {
        return mDetailAdapter;
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
    public String getCondiction() {
        return ((SearchDetailFragment) getParentFragment()).getmCondiction();
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(MyApplication.getMyContext(), CircleDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable("circle", getAdapter().getmList().get(position));
        intent.putExtra("data", bundle);
        intent.putExtra("from", "DetailCircleFragment");
        startActivity(intent);
    }

    @Override
    public void onItemOptionClick(int position) {
        mCurrPosition = position;
        mOptionDialog.show();
    }

    @Override
    public void onDialogItemOne() {
        getPresent().doJoin(mCurrPosition);
    }
}
