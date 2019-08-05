package cn.cuit.microcollege.contract;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import cn.cuit.microcollege.adapter.MyCircleHomeAdapter;
import cn.cuit.microcollege.base.BaseModel;
import cn.cuit.microcollege.entity.HttpResultEntity.GetMyCircleResultBean;
import cn.cuit.microcollege.fragment.HomePagerOneFragment;
import cn.cuit.microcollege.fragment.HomePagerThreeFragment;
import cn.cuit.microcollege.fragment.HomePagerTwoFragment;
import cn.cuit.microcollege.widget.CustomViewPager;

/**
 * @Author: Created by Rod Eden
 * @Date: 2019/4/16
 * @Descirption:
 */
public interface HomeFragmentContract {
    interface Model extends BaseModel {
        void getMyCircleList(String userId, String page, String pageSize, MyCircleHttpResult result);

        interface MyCircleHttpResult {
            void onError(String message);

            void onSuccess(List<GetMyCircleResultBean.CirclesBean> circles);
        }
    }

    interface View {
        SwipeRefreshLayout getSwip();

        RecyclerView getMyCircleRcy();

        MyCircleHomeAdapter getMycircleAdpater();

        CustomViewPager getVp();

        HomePagerOneFragment getPageOneFragment();

        HomePagerTwoFragment getPageTwoFragment();

        HomePagerThreeFragment getPageThreeFragment();
    }

    interface Presenter {
        void myCircleListTask();
        void myCircleListUpdateTask();
    }
}
