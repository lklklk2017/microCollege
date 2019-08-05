package cn.cuit.microcollege.fragment.circleDetail;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.cuit.microcollege.R;
import cn.cuit.microcollege.activity.DynamicDetailActivity;
import cn.cuit.microcollege.adapter.circleDetail.CircleDetailFragmentAdapter;
import cn.cuit.microcollege.application.MyApplication;
import cn.cuit.microcollege.base.BaseFragment;
import cn.cuit.microcollege.contract.circleDetailFragment.CircleDetailPageOneFragmentContract;
import cn.cuit.microcollege.entity.HttpResultEntity.GetDynamicWithCircleResultBean;
import cn.cuit.microcollege.entity.HttpResultEntity.GetMyCircleResultBean;
import cn.cuit.microcollege.presenter.circleDetailFragment.CircleDetailPageOneFragmentPresenter;
import cn.cuit.microcollege.utils.LogU;
import cn.cuit.microcollege.widget.CustomItemDecoration;
import cn.cuit.microcollege.widget.CustomViewPager;

/**
 * @Author: Created by Rod Eden
 * @Date: 2019/4/27
 * @Descirption: CircleDetail-fragment—new
 */
public class CircleDetailPageOneFragment extends BaseFragment<CircleDetailPageOneFragmentPresenter> implements CircleDetailPageOneFragmentContract.View, CircleDetailFragmentAdapter.TrendItemClick {

    @BindView(R.id.detail_fragment_circle_rcy)
    RecyclerView mDetailRcy;
    Unbinder unbinder;
    @BindView(R.id.detail_fragment_circle_no_result)
    LinearLayout mCircleNoResult;
    private CircleDetailFragmentAdapter mDetailAdapter;
    private List<GetMyCircleResultBean.CirclesBean> mDetailList;
    private LinearLayoutManager mLinManager;
    private int mPageSize = 3;
    private int mCurrPage = 1;
    private static String mCircleName = "";
    private int mLastVisComPosition;


    public CircleDetailPageOneFragment() {
    }

    public static CircleDetailPageOneFragment newInstance(Bundle bundle) {
        CircleDetailPageOneFragment currfragment = new CircleDetailPageOneFragment();
        if (bundle != null) {
            currfragment.setArguments(bundle);
            String circleName = bundle.getString("circleName");
            if (circleName != null) {
                mCircleName = circleName;
                LogU.i("圈子名称为:" + circleName, "circle detail new");
            }
        }
        return currfragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View inflate = inflater.inflate(R.layout.detail_fragment_circle_detail_page_one, container, false);
        setCreatedView(inflate);
        ((CustomViewPager) getViewPager()).setObjectForPosition(inflate, 0);
        unbinder = ButterKnife.bind(this, inflate);
        return inflate;
    }

    @Override
    public CircleDetailPageOneFragmentPresenter initPresenter() {
        return new CircleDetailPageOneFragmentPresenter(this);
    }

    @Override
    public void initView() {
        mLinManager = new LinearLayoutManager(getContext());
        mLinManager.setOrientation(LinearLayoutManager.VERTICAL);

        mDetailList = new ArrayList<>();
        mDetailAdapter = new CircleDetailFragmentAdapter();
        mDetailRcy.setLayoutManager(mLinManager);
        mDetailRcy.addItemDecoration(new CustomItemDecoration(0, 10, 0, 5));
        mDetailRcy.setAdapter(mDetailAdapter);
    }

    @Override
    public void bindListener() {
        mDetailRcy.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                mLastVisComPosition = mLinManager.findLastCompletelyVisibleItemPosition();
                int itemCount = mDetailAdapter.getItemCount();
                if ((itemCount - 1) == mLastVisComPosition) {
                    getPresent().getNewDynamicTask(++mCurrPage);
                }
            }
        });
        mDetailAdapter.setListener(this);
    }

    @Override
    protected void initData() {
        getPresent().getNewDynamicTask(1);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
//
//    @Override
//    public void showHasResultView() {
//        mDetailRcy.setVisibility(View.VISIBLE);
//        mCircleNoResult.setVisibility(View.INVISIBLE);
//    }
//
//    @Override
//    public void showNoResultView() {
//        mDetailRcy.setVisibility(View.INVISIBLE);
//        mCircleNoResult.setVisibility(View.VISIBLE);
//    }

    @Override
    public void onStart() {
        super.onStart();
        LogU.i("onStart-one:", getClass().getName());
    }

    @Override
    public void onResume() {
        super.onResume();
        LogU.i("onResume-one:", getClass().getName());
    }

    @Override
    public RecyclerView getRcy() {
        return mDetailRcy;
    }

    @Override
    public CircleDetailFragmentAdapter getAdapter() {
        return mDetailAdapter;
    }

    @Override
    public int getPageSize() {
        return mPageSize;
    }

    @Override
    public int getCurrentPage() {
        return mCurrPage;
    }

    @Override
    public int setCurrentPage(int page) {
        return mCurrPage = page;
    }

    @Override
    public void showNoResult() {
        mDetailRcy.setVisibility(View.INVISIBLE);
        mCircleNoResult.setVisibility(View.VISIBLE);
    }

    @Override
    public void showHasResult() {
        mDetailRcy.setVisibility(View.VISIBLE);
        mCircleNoResult.setVisibility(View.INVISIBLE);
    }

    @Override
    public String getCircleName() {
        return mCircleName;
    }

    /**************************rcy call back**********************************************************/
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

    }
}
