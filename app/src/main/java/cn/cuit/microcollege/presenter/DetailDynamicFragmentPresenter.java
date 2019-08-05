package cn.cuit.microcollege.presenter;

import android.os.Bundle;

import java.util.HashMap;
import java.util.List;

import cn.cuit.microcollege.base.BasePresenter;
import cn.cuit.microcollege.contract.DetailDynamicFragmentContract;
import cn.cuit.microcollege.entity.HttpResultEntity.GetDynamicResultBean;
import cn.cuit.microcollege.fragment.search.detail.DetailDynamicFragment;
import cn.cuit.microcollege.model.DetailDynamicFragmentModel;

/**
 * @Author: Created by Rod Eden
 * @Date: 2019/4/27
 * @Descirption:
 */
public class DetailDynamicFragmentPresenter extends BasePresenter<DetailDynamicFragment, DetailDynamicFragmentModel> implements DetailDynamicFragmentContract.Presenter {

    public DetailDynamicFragmentPresenter(DetailDynamicFragment view) {
        super(view);
    }

    @Override
    public void initModel() {
        bindModel(new DetailDynamicFragmentModel());
    }

    @Override
    public void doChangeDynamicTask(final int page, int pageSize) {
        Bundle arguments = getV().getArguments();
        if (arguments == null) {
            return;
        }
        String condiction = arguments.getString("condiction");

        if (condiction == null) {
            return;
        }

        getV().getSwip().setRefreshing(true);
        HashMap<String, Object> data = new HashMap<>();
        data.put("name", "");
        data.put("trendType", "");
        data.put("tel", "");
        data.put("email", "");
        data.put("fromTime", "");
        data.put("toTime", "");
        data.put("circleName", "");
        data.put("content", condiction);
        data.put("page", page);
        data.put("pageSize", pageSize);
        getM().getDynamic(data, new DetailDynamicFragmentContract.Model.DynamicResult() {

            @Override
            public void onSuccess(List<GetDynamicResultBean.TrendsBean> trends) {
                getV().getSwip().setRefreshing(false);
                if (trends == null || trends.size() == 0) {
                    //没有数据
                    if (page == 1) {
                        getV().showNoResultView();
                        return;
                    }
                    getV().toast("没有更多数据", false);
                    int finalPage = page - 1;
                    getV().setCurrentPage(finalPage);
                    return;
                }

                if (trends.size() == getV().getAdapter().getItemCount()) {
                    //说明已经最大了
                    getV().setCurrentPage(getV().getCurrentPage() - 1);
                    getV().toast("没有更多数据", false);
                    return;
                }

                getV().getAdapter().changeList(trends);
            }

            @Override
            public void onError(String error) {
                getV().getSwip().setRefreshing(false);
                getV().toast(error, false);
                getV().showNoResultView();
            }
        });
    }
}
