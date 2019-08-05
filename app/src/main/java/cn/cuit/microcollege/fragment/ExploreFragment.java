package cn.cuit.microcollege.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.cuit.microcollege.R;
import cn.cuit.microcollege.activity.ChatActivity;
import cn.cuit.microcollege.adapter.ChatListAdapter;
import cn.cuit.microcollege.base.BaseFragment;
import cn.cuit.microcollege.contract.ExploreFragmentContract;
import cn.cuit.microcollege.entity.NativeResultEntity.ChatListFromNativeResultBean;
import cn.cuit.microcollege.presenter.ExploreFragmentPresenter;
import cn.cuit.microcollege.utils.Config;
import cn.cuit.microcollege.widget.CustomItemDecoration;
import cn.cuit.microcollege.widget.actionBar.CustomDefaultCenterActionBar;

/**
 * @Author: Created by Rod Eden
 * @Date: 2019/4/16
 * @Descirption:
 */
public class ExploreFragment extends BaseFragment<ExploreFragmentPresenter> implements ExploreFragmentContract.View, SwipeRefreshLayout.OnRefreshListener, ChatListAdapter.OnItemClickListener {

    Unbinder unbinder;
    @BindView(R.id.explore_fragment_actionBar)
    CustomDefaultCenterActionBar mActionBar;
    @BindView(R.id.explore_fragment_rcy)
    RecyclerView mRcy;
    @BindView(R.id.explore_fragment_swip)
    SwipeRefreshLayout mSwip;
    private ChatListAdapter mChatListAdapter;
    private ArrayList<ChatListFromNativeResultBean> mChatList;

    public ExploreFragment() {

    }

    public static ExploreFragment newInstance(Bundle bundle) {

        ExploreFragment fragment = new ExploreFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_explore, container, false);
        setCreatedView(view);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public ExploreFragmentPresenter initPresenter() {
        return new ExploreFragmentPresenter(this);
    }

    @Override
    public void initView() {
        //swip
        mSwip.setColorSchemeColors(Config.SWIP_COLORS);

        //rcy
        mRcy.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRcy.setHasFixedSize(true);
        mRcy.setItemAnimator(new DefaultItemAnimator());
        mRcy.addItemDecoration(new CustomItemDecoration(0, 2, 0, 2));
        mChatList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            mChatList.add(new ChatListFromNativeResultBean());
        }
        mChatListAdapter = new ChatListAdapter(mChatList);
        mRcy.setAdapter(mChatListAdapter);
    }

    @Override
    public void bindListener() {
        mSwip.setOnRefreshListener(this);
        mChatListAdapter.setmListener(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onRefresh() {
        //刷新逻辑
    }

    @Override
    public void refresh(boolean isRefresh) {
        mSwip.setRefreshing(isRefresh);
    }

    /************************************recy item call back**************************************/
    @Override
    public void onItemClick(int position) {
        toast("点击了第" + position + "个", false);
        toPage(ChatActivity.class);
    }
}
