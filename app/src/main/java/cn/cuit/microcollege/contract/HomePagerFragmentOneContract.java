package cn.cuit.microcollege.contract;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import java.util.List;
import java.util.Map;

import cn.cuit.microcollege.adapter.HomePageOneAdapter;
import cn.cuit.microcollege.base.BaseModel;
import cn.cuit.microcollege.entity.HttpResultEntity.GetDynamicResultBean;

/**
 * @Author: Created by Rod Eden
 * @Date: 2019/4/24
 * @Descirption:
 */
public interface HomePagerFragmentOneContract {
    interface Model extends BaseModel {
        void getDynamicInfo(Map<String, Object> data, DynamicHttpResult result);

        interface DynamicHttpResult {
            void onSuccess(List<GetDynamicResultBean.TrendsBean> trends);

            void onError(String error);
        }

        void addLike(String tid, AddLikeHttpResult result);

        interface AddLikeHttpResult {
            void success();

            void error(String error);
        }
    }

    interface View {
        RecyclerView getRcy();

        SwipeRefreshLayout getSwip();

        HomePageOneAdapter getAdapter();

        int getPageSize();

        int getCurrentPage();

        int setCurrentPage(int page);
    }

    interface Presenter {
        void getDynamicTask(int currPage);

        void changeData(int currPage, int currPageSize);

        void addLike(String tid);
    }
}
