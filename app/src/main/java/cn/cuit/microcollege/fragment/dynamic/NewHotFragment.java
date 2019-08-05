package cn.cuit.microcollege.fragment.dynamic;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
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
import cn.cuit.microcollege.activity.NewDetailActivity;
import cn.cuit.microcollege.adapter.NewHotAdapter;
import cn.cuit.microcollege.base.BaseFragment;
import cn.cuit.microcollege.contract.newfragments.NewHotFragmentContract;
import cn.cuit.microcollege.presenter.newfragments.NewHotFragmentPresenter;
import cn.cuit.microcollege.widget.CustomItemDecoration;
import cn.cuit.microcollege.widget.CustomViewPager;

/**
 * @Author: Created by Rod Eden
 * @Date: 2019/4/27
 * @Descirption: searchActivity-DetailFragment—circle
 */
public class NewHotFragment extends BaseFragment<NewHotFragmentPresenter> implements
        NewHotFragmentContract.View, NewHotAdapter.OnItemClickListener,
        SwipeRefreshLayout.OnRefreshListener{


    @BindView(R.id.fragment_new_hot_rcy)
    RecyclerView mRcy;
    Unbinder unbinder;
    @BindView(R.id.fragment_new_hot_result)
    LinearLayout mNoResultLyt;
    private NewHotAdapter mAdapter;
    private int currPage = 1;
    private LinearLayoutManager mLinManager;

    public NewHotFragment() {
    }

    public static NewHotFragment newInstance(Bundle bundle) {
        NewHotFragment currfragment = new NewHotFragment();
        if (bundle != null) {
            currfragment.setArguments(bundle);
        }
        return currfragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View inflate = inflater.inflate(R.layout.fragment_new_hot, container, false);
        setCreatedView(inflate);
        ((CustomViewPager) getViewPager()).setObjectForPosition(inflate, 0);
        unbinder = ButterKnife.bind(this, inflate);
        return inflate;
    }

    @Override
    public NewHotFragmentPresenter initPresenter() {
        return new NewHotFragmentPresenter(this);
    }

    @Override
    public void initView() {
        //rcy
        mLinManager = new LinearLayoutManager(getContext());
        mLinManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRcy.setHasFixedSize(true);
        mRcy.setItemAnimator(new DefaultItemAnimator());
        mRcy.addItemDecoration(new CustomItemDecoration(0, 10, 0, 5));
        mAdapter = new NewHotAdapter();
        mRcy.setLayoutManager(mLinManager);
        mRcy.setAdapter(mAdapter);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void bindListener() {
        mAdapter.setmListener(this);
        mRcy.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int lastVisibleItemPosition = mLinManager.findLastVisibleItemPosition();
                int itemCount = mAdapter.getItemCount();
                if (lastVisibleItemPosition == (itemCount-1)) {
                    getPresent().getHotNewListTask(++currPage);
                }
            }
        });
    }

    @Override
    protected void initData() {
        getPresent().getHotNewListTask(currPage);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onItemClick(int position) {
        String sourceUrl = mAdapter.getmList().get(position).getSourceUrl();
        if (sourceUrl != null) {
            Bundle bundle = new Bundle();
            bundle.putString("url", sourceUrl);
            Intent intent = new Intent(getActivity(), NewDetailActivity.class);
            intent.putExtra("data", bundle);
            getActivity().startActivity(intent);
        }
    }

    @Override
    public void showNoResult() {
        mRcy.setVisibility(View.INVISIBLE);
        mNoResultLyt.setVisibility(View.VISIBLE);
    }

    @Override
    public void showResult() {
        mRcy.setVisibility(View.VISIBLE);
        mNoResultLyt.setVisibility(View.INVISIBLE);
    }


    @Override
    public NewHotAdapter getAdpater() {
        return mAdapter;
    }

    @Override
    public void onRefresh() {
        getPresent().getHotNewListTask(++currPage);
    }

    public SwipeRefreshLayout getSwip() {
        return ((DynamicFragment) getParentFragment()).getSwip();
    }

    public void setRefresh(){
        getPresent().getHotNewListTask(++currPage);
    }
}
