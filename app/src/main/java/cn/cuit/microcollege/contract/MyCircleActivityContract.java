package cn.cuit.microcollege.contract;

import android.support.v4.widget.SwipeRefreshLayout;

import java.util.List;

import cn.cuit.microcollege.adapter.MyCircleAdapter;
import cn.cuit.microcollege.base.BaseModel;
import cn.cuit.microcollege.entity.HttpResultEntity.GetMyCircleResultBean;

/**
 * @Author: Created by Rod Eden
 * @Date: 2019/4/29
 * @Descirption:
 */
public interface MyCircleActivityContract {
    interface Model extends BaseModel {
        void getMyCircleList(String userId, String page, String pageSize, MyCircleHttpResult result);

        interface MyCircleHttpResult {
            void onError(String message);

            void onSuccess(List<GetMyCircleResultBean.CirclesBean> circles);
        }

        //取消订阅圈子
        void cancelFromCircles(String circleId, String userId, MyCircleActivityContract.Model.cancelCirclesResult result);

        interface cancelCirclesResult {
            void onSuccess(String message);

            void onError(String error);
        }
    }

    interface View {

        SwipeRefreshLayout getSwip();

        MyCircleAdapter getMycircleAdpater();
    }

    interface Presenter {
        void myCircleListTask();

        void cancelFromCircle(int position);
    }
}
