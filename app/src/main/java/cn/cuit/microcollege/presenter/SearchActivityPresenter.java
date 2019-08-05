package cn.cuit.microcollege.presenter;

import cn.cuit.microcollege.activity.SearchActivity;
import cn.cuit.microcollege.base.BasePresenter;
import cn.cuit.microcollege.contract.SearchActivityContract;
import cn.cuit.microcollege.model.SearchActivityModel;

/**
 * @Author: Created by Rod Eden
 * @Date: 2019/4/25
 * @Descirption:
 */
public class SearchActivityPresenter extends BasePresenter<SearchActivity, SearchActivityModel> implements SearchActivityContract.Presenter {

    public SearchActivityPresenter(SearchActivity view) {
        super(view);
    }

    @Override
    public void initModel() {
        bindModel(new SearchActivityModel());
    }

    @Override
    public void search(String content) {
        //1.触发时候已经进行过条件判断
        //2.显示详情列表
        getV().getSearchBar().getEd().setText(content);
        getV().getHistoryFragment().getPresent().addRecord(content);
        getV().getSearchBar().setSearchBarEditTextFoucs(false);
        getV().showDetailFragment(content);
    }

    @Override
    public void clearAllHistory() {
        getV().getHistoryFragment().getPresent().clearAllRecords();
    }

}
