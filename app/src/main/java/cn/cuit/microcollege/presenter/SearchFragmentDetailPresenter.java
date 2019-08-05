package cn.cuit.microcollege.presenter;

import cn.cuit.microcollege.base.BasePresenter;
import cn.cuit.microcollege.contract.SearchFragmentDetailContract;
import cn.cuit.microcollege.fragment.search.SearchDetailFragment;
import cn.cuit.microcollege.model.SearchFragmentDetailModel;

/**
 * @Author: Created by Rod Eden
 * @Date: 2019/4/26
 * @Descirption:
 */
public class SearchFragmentDetailPresenter extends BasePresenter<SearchDetailFragment, SearchFragmentDetailModel> implements SearchFragmentDetailContract.Presenter {
    public SearchFragmentDetailPresenter(SearchDetailFragment view) {
        super(view);
    }

    @Override
    public void initModel() {

    }
}
