package cn.cuit.microcollege.presenter;

import java.util.List;

import cn.cuit.microcollege.base.BasePresenter;
import cn.cuit.microcollege.contract.HomeFragmentContract;
import cn.cuit.microcollege.entity.HttpResultEntity.GetMyCircleResultBean;
import cn.cuit.microcollege.fragment.HomeFragment;
import cn.cuit.microcollege.model.HomeFragmentModel;
import cn.cuit.microcollege.utils.SPUtil;

/**
 * @Author: Created by Rod Eden
 * @Date: 2019/4/16
 * @Descirption:
 */
public class HomeFragmentPresenter extends BasePresenter<HomeFragment, HomeFragmentModel> implements HomeFragmentContract.Presenter {
    public HomeFragmentPresenter(HomeFragment view) {
        super(view);
    }

    @Override
    public void initModel() {
        bindModel(new HomeFragmentModel());
    }

    @Override
    public void myCircleListTask() {

        //获取参数
        int userId = SPUtil.getmUserSp().getInt("userId", -1);
        if (userId == -1) {
            getV().toast("用户数据获取失败,请重新登录", true);
            return;
        }
        //开始请求
        getM().getMyCircleList(userId + "", "1", "10", new HomeFragmentContract.Model.MyCircleHttpResult() {
            @Override
            public void onError(String message) {
                //获取圈子失败
                getV().toast(message, false);
            }

            @Override
            public void onSuccess(List<GetMyCircleResultBean.CirclesBean> circles) {
                //获取圈子成功
                getV().getMycircleAdpater().changeList(circles);

                //刷新page one
                int itemCount = getV().getPageOneFragment().getPresent().getV().getAdapter().getItemCount();
                if (itemCount == 0) {
                    getV().getPageOneFragment().getPresent().getDynamicTask(1);
                }
            }
        });

    }

    @Override
    public void myCircleListUpdateTask() {
        //获取参数
        int userId = SPUtil.getmUserSp().getInt("userId", -1);
        if (userId == -1) {
            getV().toast("用户数据获取失败,请重新登录", true);
            return;
        }
        //开始请求
        getM().getMyCircleList(userId + "", "1", "10", new HomeFragmentContract.Model.MyCircleHttpResult() {
            @Override
            public void onError(String message) {
                //获取圈子失败
                getV().toast(message, false);
            }

            @Override
            public void onSuccess(List<GetMyCircleResultBean.CirclesBean> circles) {
                //获取圈子成功
                getV().getMycircleAdpater().changeList(circles);
            }
        });
    }
}
