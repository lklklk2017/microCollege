package cn.cuit.microcollege.presenter;

import java.util.List;

import cn.cuit.microcollege.activity.MyCircleActivity;
import cn.cuit.microcollege.base.BasePresenter;
import cn.cuit.microcollege.contract.MyCircleActivityContract;
import cn.cuit.microcollege.entity.HttpResultEntity.GetMyCircleResultBean;
import cn.cuit.microcollege.model.MyCircleActivityModel;
import cn.cuit.microcollege.utils.LogU;
import cn.cuit.microcollege.utils.SPUtil;

/**
 * @Author: Created by Rod Eden
 * @Date: 2019/4/29
 * @Descirption:
 */
public class MyCircleActivityPresenter extends BasePresenter<MyCircleActivity, MyCircleActivityModel> implements MyCircleActivityContract.Presenter {

    public MyCircleActivityPresenter(MyCircleActivity view) {
        super(view);
    }

    @Override
    public void initModel() {
        bindModel(new MyCircleActivityModel());
    }

    @Override
    public void myCircleListTask() {

        if (getV() == null) {
            return;
        }

        //获取参数
        int userId = SPUtil.getmUserSp().getInt("userId", -1);
        if (userId == -1) {
            getV().toast("用户数据获取失败,请重新登录", true);
            return;
        }

        getV().getSwip().setRefreshing(true);
        //开始请求
        getM().getMyCircleList(userId + "", "1", "100", new MyCircleActivityContract.Model.MyCircleHttpResult() {
            @Override
            public void onError(String message) {

                if (getV() == null) {
                    return;
                }
                getV().toast(message, false);
                //获取圈子失败
                MyCircleActivity v = getV();
                if (v != null) {
                    getV().getSwip().setRefreshing(false);
                } else {
                    getV().toast("网络异常..", false);
                }
            }

            @Override
            public void onSuccess(List<GetMyCircleResultBean.CirclesBean> circles) {

                if (getV() == null) {
                    return;
                }
                getV().getSwip().setRefreshing(false);

                //获取圈子成功
                MyCircleActivity v = getV();
                if (v != null) {
                    getV().getMycircleAdpater().changeList(circles);
                } else {
                    getV().toast("获取圈子失败..", false);

                }
            }
        });


    }

    @Override
    public void cancelFromCircle(final int position) {

        GetMyCircleResultBean.CirclesBean circlesBean = getV().getMycircleAdpater().getmList().get(position);
        if (circlesBean == null) {
            LogU.i("参数获取失败：cricle bean", getClass().getName());
            return;
        }
        int userId = SPUtil.getmUserSp().getInt("userId", -1);
        if (userId == -1) {
            LogU.i("参数获取失败：userId", getClass().getName());
            return;
        }

        getM().cancelFromCircles(String.valueOf(circlesBean.getCircleId()), String.valueOf(userId), new MyCircleActivityContract.Model.cancelCirclesResult() {
            @Override
            public void onSuccess(String message) {
                if (getV() == null) {
                    return;
                }
                getV().getMycircleAdpater().removeItem(position);
                getV().toast(message, false);
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
