package cn.cuit.microcollege.contract;

import android.support.v4.widget.SwipeRefreshLayout;

import java.util.List;
import java.util.Map;

import cn.cuit.microcollege.adapter.HomePageOneAdapter;
import cn.cuit.microcollege.base.BaseModel;
import cn.cuit.microcollege.entity.HttpResultEntity.GetDynamicResultBean;

/**
 * @Author: Created by Rod Eden
 * @Date: 2019/4/27
 * @Descirption:
 */
public interface DetailDynamicFragmentContract {
    interface Model extends BaseModel {
        void getDynamic(Map<String, Object> data, DynamicResult result);

        interface DynamicResult {
            void onSuccess(List<GetDynamicResultBean.TrendsBean> trends);

            void onError(String error);
        }
    }

    interface View {
        void showHasResultView();

        void showNoResultView();

        SwipeRefreshLayout getSwip();

        HomePageOneAdapter getAdapter();

        int getCurrentPage();

        int getPageSize();

        int setCurrentPage(int page);
    }

    interface Presenter {
        void doChangeDynamicTask(int page, int pageSize);
    }
}
