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
import cn.cuit.microcollege.activity.SearchActivity;
import cn.cuit.microcollege.activity.about.PersonDetailActivity;
import cn.cuit.microcollege.adapter.UserSearchAdapter;
import cn.cuit.microcollege.application.MyApplication;
import cn.cuit.microcollege.base.BaseFragment;
import cn.cuit.microcollege.contract.DetailUserFragmentContract;
import cn.cuit.microcollege.presenter.DetailUserFragmentPresenter;
import cn.cuit.microcollege.utils.LogU;
import cn.cuit.microcollege.widget.CustomViewPager;

/**
 * @Author: Created by Rod Eden
 * @Date: 2019/4/27
 * @Descirption: searchActivity-DetailFragment—dynamic
 */
public class DetailUserFragment extends BaseFragment<DetailUserFragmentPresenter> implements DetailUserFragmentContract.View, UserSearchAdapter.AdapterClickListener {

    @BindView(R.id.detail_fragment_user_rcy)
    RecyclerView mDetailRcy;
    Unbinder unbinder;
    @BindView(R.id.detail_fragment_user_no_result)
    LinearLayout mUserNoResult;
    private UserSearchAdapter mAdapter;
    private int mLastComVisPosition;
    private int mCurrPage = 1;
    private int mCurrPageSize = 10;
    private LinearLayoutManager mLinManager;

    public DetailUserFragment() {
    }

    public static DetailUserFragment newInstance(Bundle bundle) {
        DetailUserFragment currfragment = new DetailUserFragment();
        if (bundle != null) {
            currfragment.setArguments(bundle);
        }
        return currfragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View inflate = inflater.inflate(R.layout.detail_fragment_user, container, false);
        setCreatedView(inflate);
        CustomViewPager viewPager = (CustomViewPager) getViewPager();
        if (viewPager != null) {
            viewPager.setObjectForPosition(inflate, 2);
        }
        unbinder = ButterKnife.bind(this, inflate);
        return inflate;
    }

    @Override
    public DetailUserFragmentPresenter initPresenter() {
        return new DetailUserFragmentPresenter(this);
    }

    @Override
    public void initView() {
        mLinManager = new LinearLayoutManager(getContext());
        mLinManager.setOrientation(LinearLayoutManager.VERTICAL);
        mDetailRcy.setLayoutManager(mLinManager);
        mAdapter = new UserSearchAdapter();
        mDetailRcy.setAdapter(mAdapter);
    }

    @Override
    public void bindListener() {
        mAdapter.setAdapterClickListener(this);
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
                if (itemCount - 1 == mLastComVisPosition) {
                    getPresent().doChangeUserTask(1, setCurrentPage(getCurrentPage() + 1) * mCurrPageSize);
                }
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        LogU.i("onResume-user", "life");
    }

    @Override
    public void onStart() {
        super.onStart();
        LogU.i("onStart-user", "life");
    }

    @Override
    public void onPause() {
        super.onPause();
        LogU.i("onPause-user", "life");
    }

    @Override
    public void onStop() {
        super.onStop();
        LogU.i("onStop-user", "life");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void showHasResultView() {
        mDetailRcy.setVisibility(View.VISIBLE);
        mUserNoResult.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showNoResultView() {
        mDetailRcy.setVisibility(View.INVISIBLE);
        mUserNoResult.setVisibility(View.VISIBLE);
    }

    @Override
    public SwipeRefreshLayout getSwip() {
        return ((SearchActivity) getActivity()).getSwip();
    }

    @Override
    public UserSearchAdapter getAdapter() {
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
    public void onItemClick(int position) {
        //todo 跳转个人详情
        Intent intent = new Intent(MyApplication.getMyContext(), PersonDetailActivity.class);
        intent.putExtra("from","other");
        Bundle bundle = new Bundle();
        bundle.putParcelable("userBean",getAdapter().getmList().get(position));
        intent.putExtra("data",bundle);
        startActivity(intent);
    }
}
