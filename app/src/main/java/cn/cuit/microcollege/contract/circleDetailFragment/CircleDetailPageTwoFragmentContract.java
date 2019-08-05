package cn.cuit.microcollege.contract.circleDetailFragment;

import android.support.v7.widget.RecyclerView;

import java.util.List;
import java.util.Map;

import cn.cuit.microcollege.adapter.circleDetail.CircleDetailFragmentAdapter;
import cn.cuit.microcollege.base.BaseModel;
import cn.cuit.microcollege.entity.HttpResultEntity.GetDynamicWithCircleResultBean;

/**
 * @Author: Created by Rod Eden
 * @Date: 2019/5/22
 * @Descirption:
 */
public interface CircleDetailPageTwoFragmentContract {
    interface Model extends BaseModel {
        //获取最新的圈内的动态
        void getHotDynamic(Map<String, Object> map, CircleDetailPageTwoFragmentContract.Model.HotDynamicHttpResult result);

        interface HotDynamicHttpResult {
            void onSuccess(List<GetDynamicWithCircleResultBean.TrendsBean> trends);

            void onError(String message);
        }

        void addLike(String tid, CircleDetailPageTwoFragmentContract.Model.AddLikeHttpResult result);

        interface AddLikeHttpResult {
            void success();

            void error(String error);
        }
    }

    interface View {
        RecyclerView getRcy();

        //获取new dynamic list adapter
        CircleDetailFragmentAdapter getAdapter();

        String getCircleName();

        int getPageSize();

        int getCurrentPage();

        int setCurrentPage(int page);

        void showNoResult();

        void showHasResult();
    }

    interface Presenter {
        void getHotDynamicTask(int currPage);

        void addLike(String tid);
    }
}
