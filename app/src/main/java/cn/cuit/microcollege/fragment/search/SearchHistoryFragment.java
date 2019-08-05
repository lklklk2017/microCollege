package cn.cuit.microcollege.fragment.search;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.cuit.microcollege.R;
import cn.cuit.microcollege.activity.SearchActivity;
import cn.cuit.microcollege.adapter.SearchFragmentHistoryAdapter;
import cn.cuit.microcollege.base.BaseFragment;
import cn.cuit.microcollege.contract.SearchFragmentHistoryContract;
import cn.cuit.microcollege.presenter.SearchFragmentHistoryPresenter;

/**
 * @Author: Created by Rod Eden
 * @Date: 2019/4/25
 * @Descirption: Search-HistoryFragment
 */
public class SearchHistoryFragment extends BaseFragment<SearchFragmentHistoryPresenter> implements SearchFragmentHistoryContract.View, SearchFragmentHistoryAdapter.onItemClickListener {

    @BindView(R.id.search_fragment_history_clear_img)
    ImageView mClearImg;
    @BindView(R.id.search_fragment_history_rcy)
    RecyclerView mRcy;
    Unbinder unbinder;
    @BindView(R.id.search_fragment_history_rly)
    RelativeLayout mRly;
    private ArrayList<String> historyRecordList;
    private SearchFragmentHistoryAdapter mHistoryAdapter;


    public static SearchHistoryFragment newInstance(Bundle bundle) {
        SearchHistoryFragment currFragment = new SearchHistoryFragment();
        if (bundle != null) {
            currFragment.setArguments(bundle);
        }
        return currFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.search_fragment_history, container, false);
        setCreatedView(view);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public SearchFragmentHistoryPresenter initPresenter() {
        return new SearchFragmentHistoryPresenter(this);
    }

    @Override
    public void initView() {
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        mRcy.setLayoutManager(staggeredGridLayoutManager);

        historyRecordList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            historyRecordList.add("历史记录：" + i);
        }

        historyRecordList.add("区域链");
        historyRecordList.add("Java");
        historyRecordList.add("Android");
        historyRecordList.add("Python");
        historyRecordList.add("你好");
        historyRecordList.add("中国");
        historyRecordList.add("哈哈哈哈哈哈哈");

        mHistoryAdapter = new SearchFragmentHistoryAdapter(historyRecordList);
        mRcy.setAdapter(mHistoryAdapter);
    }

    @Override
    public void bindListener() {
        mHistoryAdapter.setListener(
                this
        );
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.search_fragment_history_clear_img)
    public void onClick() {
        if (historyRecordList != null) {
            historyRecordList.clear();
            mHistoryAdapter.removeAll();
            mClearImg.setVisibility(View.GONE);
        }
    }

    @Override
    public void showHistory(boolean show) {
        if (show) {
            mClearImg.setVisibility(View.VISIBLE);
        } else {
            mClearImg.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public ArrayList<String> getHistoryList() {
        if (historyRecordList != null) {
            return historyRecordList;
        } else {
            //TODO 重新获取本地信息-从数据库获取历史记录 asyTask
            return null;
        }
    }

    @Override
    public void setHistoryList(ArrayList<String> list) {
        historyRecordList = list;
    }

    @Override
    public SearchFragmentHistoryAdapter getHistoryAdatper() {
        if (mHistoryAdapter != null) {
            return mHistoryAdapter;
        }
        return new SearchFragmentHistoryAdapter(getHistoryList());
    }

    @Override
    public void onItemClick(String recordName) {
        ((SearchActivity) getActivity()).getPresent().search(recordName);
    }
}
