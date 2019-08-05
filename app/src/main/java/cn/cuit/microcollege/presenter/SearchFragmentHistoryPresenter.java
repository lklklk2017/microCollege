package cn.cuit.microcollege.presenter;

import java.util.ArrayList;

import cn.cuit.microcollege.base.BasePresenter;
import cn.cuit.microcollege.contract.SearchFragmentHistoryContract;
import cn.cuit.microcollege.fragment.search.SearchHistoryFragment;
import cn.cuit.microcollege.model.SearchFragmentHistoryModel;

/**
 * @Author: Created by Rod Eden
 * @Date: 2019/4/25
 * @Descirption:
 */
public class SearchFragmentHistoryPresenter extends BasePresenter<SearchHistoryFragment, SearchFragmentHistoryModel> implements SearchFragmentHistoryContract.Presenter {

    public SearchFragmentHistoryPresenter(SearchHistoryFragment view) {
        super(view);
    }

    @Override
    public void initModel() {

    }

    @Override
    public void addRecord(String content) {
        //数据已验证过非空
        ArrayList<String> historyList = getV().getHistoryList();
        if (!historyList.contains(content)) {
            historyList.add(content);
            getV().setHistoryList(historyList);
            getV().getHistoryAdatper().changeList(historyList);//通知改变视图
            getV().showHistory(true);
        }
    }

    @Override
    public void clearAllRecords() {
        ArrayList<String> historyList = getV().getHistoryList();
        historyList.clear();
        getV().setHistoryList(historyList);
        getV().getHistoryAdatper().changeList(historyList);
    }
}
