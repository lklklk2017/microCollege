package cn.cuit.microcollege.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.cuit.microcollege.R;
import cn.cuit.microcollege.activity.CircleDetailActivity;
import cn.cuit.microcollege.activity.DynamicDetailActivity;
import cn.cuit.microcollege.adapter.HomePageThreeAdapter;
import cn.cuit.microcollege.application.MyApplication;
import cn.cuit.microcollege.base.BaseFragment;
import cn.cuit.microcollege.contract.HomePagerFragmentThreeContract;
import cn.cuit.microcollege.entity.HttpResultEntity.GetDynamicWithCircleResultBean;
import cn.cuit.microcollege.presenter.HomePagerFragmentThreePresenter;
import cn.cuit.microcollege.utils.LogU;
import cn.cuit.microcollege.widget.CustomItemDecoration;
import cn.cuit.microcollege.widget.CustomViewPager;

/**
 * @Author: Created by Rod Eden
 * @Date: 2019/4/24
 * @Descirption:
 */
public class HomePagerThreeFragment extends BaseFragment<HomePagerFragmentThreePresenter> implements HomePagerFragmentThreeContract.View, HomePageThreeAdapter.TrendItemClick {

    @BindView(R.id.home_fragment_pageThree_rcy)
    RecyclerView mRcy;
    Unbinder unbinder;
    private HomePageThreeAdapter mAdapter;
    private int mCurrPage = 1;
    private int lastVisibleItemPosition;
    private LinearLayoutManager lm;
    private int itemCount;

    public static HomePagerThreeFragment newInstance(Bundle bundle) {
        LogU.i("three-HomePagerThreeFragment()", "");

        HomePagerThreeFragment currFragment = new HomePagerThreeFragment();
        if (bundle != null) {

            currFragment.setArguments(bundle);
        }
        return currFragment;
    }

    @Override
    public HomePagerFragmentThreePresenter initPresenter() {
        return new HomePagerFragmentThreePresenter(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        LogU.i("three-onCreateView()", this.getClass().getName());

        View inflate = inflater.inflate(R.layout.home_fragment_verpager_three, container, false);
        setCreatedView(inflate);
        CustomViewPager viewPager = (CustomViewPager) getViewPager();
        if (viewPager != null) {
            viewPager.setObjectForPosition(inflate, 2);
        }
        unbinder = ButterKnife.bind(this, inflate);
        return inflate;
    }

    @Override
    public void initView() {
        //rcy
        lm = new LinearLayoutManager(MyApplication.getMyContext());
        lm.setOrientation(LinearLayoutManager.VERTICAL);
        mRcy.setLayoutManager(lm);
        mRcy.addItemDecoration(new CustomItemDecoration(0, 10, 0, 5));
        mAdapter = new HomePageThreeAdapter();
        mRcy.setAdapter(mAdapter);
    }

    @Override
    public void bindListener() {
        mAdapter.setListener(this);
        mRcy.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (!getSwip().isRefreshing()) {
                    lastVisibleItemPosition = lm.findLastCompletelyVisibleItemPosition();
                    itemCount = recyclerView.getAdapter().getItemCount();
                    if (itemCount - 1 == lastVisibleItemPosition) {
                        getPresent().getDynamicTask(++mCurrPage);
                    }
                }
            }
        });
    }

    @Override
    protected void initData() {
//        getPresent().getDynamicTask(mCurrPage);
//        LogU.i("three - initData",this.getClass().getName());

    }

    @Override
    public void onResume() {
        super.onResume();
//        if (mAdapter.getItemCount()==0) {
//            getPresent().getDynamicTask(mCurrPage);
//        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        LogU.i("three-onDestroyView()", this.getClass().getName());
        unbinder.unbind();
    }

    @Override
    public RecyclerView getRcy() {
        return mRcy;
    }

    @Override
    public SwipeRefreshLayout getSwip() {
        return ((HomeFragment) getParentFragment()).getSwip();
    }

    @Override
    public HomePageThreeAdapter getAdapter() {
        return mAdapter;
    }

    @Override
    public int getPageSize() {
        return 4;
    }

    @Override
    public int getCurrentPage() {
        return mCurrPage;
    }

    @Override
    public int setCurrentPage(int page) {
        return mCurrPage = page;
    }

    /*****************************rcy item click***************************************************/
    @Override
    public void onItemClick(GetDynamicWithCircleResultBean.TrendsBean trendsBean) {
        Intent intent = new Intent(MyApplication.getMyContext(), DynamicDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable("trend", trendsBean);
        intent.putExtra("from", "HomePage");
        intent.putExtra("data", bundle);
        startActivity(intent);
    }

    @Override
    public void onLikeClick(int position) {
        getPresent().addLike(getAdapter().getmList().get(position).getDynamicId() + "");
    }

    @Override
    public void onCommentClick() {

    }

    @Override
    public void onCircleTagClick(GetDynamicWithCircleResultBean.TrendsBean trendsBean) {
        Intent intent = new Intent(MyApplication.getMyContext(), CircleDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable("circle", trendsBean.getCircle());
        intent.putExtra("data", bundle);
        intent.putExtra("from", "HomeFragmentDynamicListWithCircle");
        startActivity(intent);
    }
}
