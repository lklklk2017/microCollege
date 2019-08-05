package cn.cuit.microcollege.fragment.person;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
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
import cn.cuit.microcollege.activity.about.PersonDetailActivity;
import cn.cuit.microcollege.adapter.HomePageOneAdapter;
import cn.cuit.microcollege.application.MyApplication;
import cn.cuit.microcollege.base.BaseFragment;
import cn.cuit.microcollege.contract.personDetailFragment.PersonDetailPageOneFragmentContract;
import cn.cuit.microcollege.entity.HttpResultEntity.GetDynamicResultBean;
import cn.cuit.microcollege.presenter.personDetailFragment.PersonDetailPageOneFragmentPresenter;
import cn.cuit.microcollege.utils.LogU;
import cn.cuit.microcollege.widget.CustomItemDecoration;
import cn.cuit.microcollege.widget.CustomViewPager;

/**
 * @Author: Created by Rod Eden
 * @Date: 2019/4/24
 * @Descirption:
 */
public class PersonDetailPageOneFragment extends BaseFragment<PersonDetailPageOneFragmentPresenter> implements PersonDetailPageOneFragmentContract.View, HomePageOneAdapter.TrendItemClick {

    @BindView(R.id.home_fragment_pageOne_rcy)
    RecyclerView mRcy;
    Unbinder unbinder;
    private HomePageOneAdapter mAdapter;
    private int pageSize = 4;
    private int currentPage = 1;
    private LinearLayoutManager lm;
    private int currItemCount;
    private int lastCompletelyVisibleItemPosition;

    public static PersonDetailPageOneFragment newInstance(Bundle bundle) {
        PersonDetailPageOneFragment currFragment = new PersonDetailPageOneFragment();
        if (bundle != null) {
            currFragment.setArguments(bundle);
        }
        return currFragment;
    }

    @Override
    public PersonDetailPageOneFragmentPresenter initPresenter() {
        return new PersonDetailPageOneFragmentPresenter(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        LogU.i("PersonDetailPageOneFragment-onCreateView()", this.getClass().getName());
        View inflate = inflater.inflate(R.layout.home_fragment_verpager_one, container, false);
        setCreatedView(inflate);

        CustomViewPager viewPager = (CustomViewPager) getViewPager();
        if (viewPager != null) {
            viewPager.setObjectForPosition(inflate, 0);
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
        mRcy.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new HomePageOneAdapter();
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
                lastCompletelyVisibleItemPosition = lm.findLastCompletelyVisibleItemPosition();
                currItemCount = mAdapter.getItemCount();
                if (lastCompletelyVisibleItemPosition == (currItemCount - 1)) {
                    getPresent().personDynamicTask(++currentPage);
                }
            }
        });
    }

    @Override
    protected void initData() {
        getPresent().personDynamicTask(currentPage);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        LogU.i("one-onDestroyView()", this.getClass().getName());
    }

    public RecyclerView getRcy() {
        return mRcy;
    }


    @Override
    public HomePageOneAdapter getAdapter() {
        return mAdapter;
    }

    @Override
    public void setDynamicCount(int count) {
        ((PersonDetailActivity) getActivity()).updateDynamicCounts(count);
    }

    @Override
    public void showResult() {
        //todo
    }

    @Override
    public void showNoResult() {
        //todo
        mRcy.setVisibility(View.INVISIBLE);

    }

    @Override
    public int getPageSize() {
        return pageSize;
    }

    @Override
    public int getCurrentPage() {
        return currentPage;
    }

    @Override
    public int setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
        return currentPage;
    }

    /************************** item click event*****************************************************/
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
//        toast("点赞", false);
        getPresent().addLike(getAdapter().getmList().get(position).getDynamicId() + "");
    }

    @Override
    public void onCommentClick() {
//        toast("点评论", false);

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
    public void onLongItemClick(final GetDynamicResultBean.TrendsBean trendsBean, final int position) {
        toast("确认删除该动态？", "确认", 8000, new ToastCallBack() {
            @Override
            public void onEvent() {
                int dynamicId = trendsBean.getDynamicId();
                getPresent().deleteDyanmicTask(dynamicId, position);
            }
        });
    }
}
