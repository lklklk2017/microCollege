package cn.cuit.microcollege.contract;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Map;

import cn.cuit.microcollege.base.BaseModel;
import cn.cuit.microcollege.fragment.circleDetail.CircleDetailPageOneFragment;
import cn.cuit.microcollege.fragment.circleDetail.CircleDetailPageTwoFragment;

/**
 * @Author: Created by Rod Eden
 * @Date: 2019/4/29
 * @Descirption:
 */
public interface CircleDetailActivityContract {
    interface Model extends BaseModel {
        void checkUserInCircle(Map<String, Object> map, CheckUserInCircleHttpResult result);

        interface CheckUserInCircleHttpResult {
            void onSuccess();

            void onError(String error);
        }

        //加入圈子
        void joinInCircles(String circleId, String userId, CircleDetailActivityContract.Model.JoinCirclesResult result);

        interface JoinCirclesResult {
            void onSuccess(String message);

            void onError(String error);
        }
    }

    interface View {

        void showCircleNewFragment();

        void showCircleHotFragment();

        CircleDetailPageOneFragment getCircleNewFragment();

        CircleDetailPageTwoFragment getCircleHotFragment();

        //获取view
        Toolbar getToolBar();

        ImageView getBgImg();

        ImageView getPortraitBgImg();

        TextView getCircleTitleTv();

        TextView getCircleDateTv();

        TabLayout getTabLayout();

        ViewPager getViewPager();

        FloatingActionButton getFloatingActionBar();

        void showJoinedStatus();

        void showUnJoinedStatus();

        boolean isChecked();

        void setChecked(boolean checked);
    }

    interface Presenter {
        void checkUserIsInCircleTask();

        void doJoin();
    }
}
