package cn.cuit.microcollege.presenter;

import java.util.HashMap;

import cn.cuit.microcollege.activity.CircleDetailActivity;
import cn.cuit.microcollege.base.BasePresenter;
import cn.cuit.microcollege.contract.CircleDetailActivityContract;
import cn.cuit.microcollege.entity.DataEntity.CircleDataBean;
import cn.cuit.microcollege.model.CircleDetailActivityModel;
import cn.cuit.microcollege.utils.LogU;
import cn.cuit.microcollege.utils.SPUtil;

/**
 * @Author: Created by Rod Eden
 * @Date: 2019/4/29
 * @Descirption:
 */
public class CircleDetailActivityPresenter extends BasePresenter<CircleDetailActivity, CircleDetailActivityModel> implements CircleDetailActivityContract.Presenter {

    public CircleDetailActivityPresenter(CircleDetailActivity view) {
        super(view);
    }

    @Override
    public void initModel() {
        bindModel(new CircleDetailActivityModel());
    }


    @Override
    public void checkUserIsInCircleTask() {
        //get user id
        int userId = SPUtil.getmUserSp().getInt("userId", -1);
        if (userId == -1) {
            LogU.i("用户参数有误", this.getClass().getName());
            return;
        }

        //get circle id
        CircleDataBean circleDataBean = getV().getCircleDataBean();
        if (circleDataBean == null) {
            LogU.i("circle 参数有误", this.getClass().getName());
            return;
        }

        int circleId = circleDataBean.getCircleId();//获取用户的id
        HashMap<String, Object> map = new HashMap<>();
        map.put("circleId", circleId);
        map.put("userJoinedId", userId);

        getM().checkUserInCircle(map, new CircleDetailActivityContract.Model.CheckUserInCircleHttpResult() {
            @Override
            public void onSuccess() {
                if (getV() == null) {
                    return;
                }
                getV().setChecked(true);
                //已加入
                getV().showJoinedStatus();
            }

            @Override
            public void onError(String error) {
                if (getV() == null) {
                    return;
                }
                getV().setChecked(true);
                //未加入
                getV().showUnJoinedStatus();
            }
        });
    }

    @Override
    public void doJoin() {

        //get circle id
        CircleDataBean circleDataBean = getV().getCircleDataBean();
        if (circleDataBean == null) {
            LogU.i("circle 参数有误", this.getClass().getName());
            return;
        }

        int circleId = circleDataBean.getCircleId();//获取用户的id

        int userId = SPUtil.getmUserSp().getInt("userId", -1);
        if (userId == -1) {
            LogU.i("参数获取失败：userId", getClass().getName());
            return;
        }

        getM().joinInCircles(String.valueOf(circleId), String.valueOf(userId), new CircleDetailActivityContract.Model.JoinCirclesResult() {
            @Override
            public void onSuccess(String message) {
                if (getV() == null) {
                    return;
                }
                //showjoin
                getV().showJoinedStatus();
            }

            @Override
            public void onError(String error) {
                if (getV() == null) {
                    return;
                }
                getV().toast(error, false);
            }
        });
    }
}
