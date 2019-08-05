package cn.cuit.microcollege.contract;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import java.util.List;
import java.util.Map;

import cn.cuit.microcollege.adapter.HomePageThreeAdapter;
import cn.cuit.microcollege.base.BaseModel;
import cn.cuit.microcollege.entity.HttpResultEntity.GetDynamicWithCircleResultBean;

/**
 * @Author: Created by Rod Eden
 * @Date: 2019/4/24
 * @Descirption:
 */
public interface HomePagerFragmentThreeContract {
    interface Model extends BaseModel {
        void getDynamicInfo(Map<String, Object> data, HomePagerFragmentThreeContract.Model.DynamicHttpResult result);

        interface DynamicHttpResult {

            void onSuccess(List<GetDynamicWithCircleResultBean.TrendsBean> trends);

            void onError(String error);
        }


        void addLike(String tid, HomePagerFragmentThreeContract.Model.AddLikeHttpResult result);

        interface AddLikeHttpResult {
            void success();

            void error(String error);
        }
    }

    interface View {
        RecyclerView getRcy();

        SwipeRefreshLayout getSwip();

        HomePageThreeAdapter getAdapter();

        int getPageSize();

        int getCurrentPage();

        int setCurrentPage(int page);
    }

    interface Presenter {
        void getDynamicTask(int currPage);
        void changeData(int page,int pageSize);
        void addLike(String tid);
    }
}
