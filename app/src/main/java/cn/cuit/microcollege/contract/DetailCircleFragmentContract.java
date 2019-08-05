package cn.cuit.microcollege.contract;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import cn.cuit.microcollege.adapter.CircleSearchAdapter;
import cn.cuit.microcollege.base.BaseModel;
import cn.cuit.microcollege.entity.HttpResultEntity.GetMyCircleResultBean;

/**
 * @Author: Created by Rod Eden
 * @Date: 2019/4/27
 * @Descirption:
 */
public interface DetailCircleFragmentContract {
    interface Model extends BaseModel {
        void getCircles(String circleName, String page, String pageSize, CirclesResult result);

        interface CirclesResult {
            void onSuccess(List<GetMyCircleResultBean.CirclesBean> circles);

            void onError(String error);
        }

        //加入圈子
        void joinInCircles(String circleId, String userId, JoinCirclesResult result);

        interface JoinCirclesResult {
            void onSuccess(String message);

            void onError(String error);
        }
    }

    interface View {
        void showHasResultView();

        void showNoResultView();

        SwipeRefreshLayout getSwip();

        RecyclerView getCircleResultRcy();

        CircleSearchAdapter getAdapter();

        int getCurrentPage();

        int getPageSize();

        int setCurrentPage(int page);

        String getCondiction();
    }

    interface Presenter {
        void doSearchCircleTask(int page);

        void doChangeCircleTask(int page, int pageSize);

        void doJoin(int mCurrPosition);
    }
}
