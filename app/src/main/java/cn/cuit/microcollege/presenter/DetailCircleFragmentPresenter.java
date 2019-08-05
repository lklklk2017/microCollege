package cn.cuit.microcollege.presenter;


import android.os.Bundle;

import java.util.List;

import cn.cuit.microcollege.base.BasePresenter;
import cn.cuit.microcollege.contract.DetailCircleFragmentContract;
import cn.cuit.microcollege.entity.HttpResultEntity.GetMyCircleResultBean;
import cn.cuit.microcollege.fragment.search.detail.DetailCircleFragment;
import cn.cuit.microcollege.model.DetailCircleFragmentModel;
import cn.cuit.microcollege.utils.LogU;
import cn.cuit.microcollege.utils.SPUtil;

/**
 * @Author: Created by Rod Ed
 * @Date: 2019/4/27
 * @Descirption:
 */
public class DetailCircleFragmentPresenter extends BasePresenter<DetailCircleFragment, DetailCircleFragmentModel> implements DetailCircleFragmentContract.Presenter {

    public DetailCircleFragmentPresenter(DetailCircleFragment view) {
        super(view);
    }

    @Override
    public void initModel() {
        bindModel(new DetailCircleFragmentModel());
    }

    @Override
    public void doSearchCircleTask(final int page) {

        //get condiction
        String condiction = getV().getCondiction();
        if (condiction == null) {
            return;
        }

        getV().getSwip().setRefreshing(true);
        getM().getCircles(condiction, String.valueOf(page), String.valueOf(getV().getPageSize()), new DetailCircleFragmentContract.Model.CirclesResult() {
            @Override
            public void onSuccess(List<GetMyCircleResultBean.CirclesBean> circles) {
                getV().getSwip().setRefreshing(false);

                if (circles == null || circles.size() == 0) {
                    getV().toast("没有更多数据", false);
                    //没有数据
                    if (page == 1) {
                        getV().showNoResultView();
                        return;
                    }
                    int finalPage = page - 1;
                    getV().setCurrentPage(finalPage);
                    return;
                }

                getV().getAdapter().addList(circles);
            }

            @Override
            public void onError(String error) {
                getV().getSwip().setRefreshing(false);
                getV().showNoResultView();
                getV().toast(error, false);
            }
        });
    }

    @Override
    public void doChangeCircleTask(final int page, int pageSize) {
        //get condiction
        Bundle arguments = getV().getArguments();
        if (arguments == null) {
            return;
        }
        String condiction = arguments.getString("condiction");

        if (condiction == null) {
            return;
        }

        getV().getSwip().setRefreshing(true);
        getM().getCircles(condiction, String.valueOf(page), String.valueOf(pageSize), new DetailCircleFragmentContract.Model.CirclesResult() {
            @Override
            public void onSuccess(List<GetMyCircleResultBean.CirclesBean> circles) {
                getV().getSwip().setRefreshing(false);

                if (circles == null || circles.size() == 0) {
                    //没有数据
                    if (page == 1) {
                        getV().showNoResultView();
                        return;
                    }
                    getV().toast("没有更多数据", false);
                    int finalPage = page - 1;
                    getV().setCurrentPage(finalPage);
                    return;
                }

                if (circles.size() == getV().getAdapter().getItemCount()) {
                    //说明已经最大了
                    getV().setCurrentPage(getV().getCurrentPage() - 1);
                    getV().toast("没有更多数据", false);
                    return;
                }

                getV().getAdapter().changeList(circles);
            }

            @Override
            public void onError(String error) {
                getV().getSwip().setRefreshing(false);
                getV().toast(error, false);
                getV().showNoResultView();
            }
        });
    }

    @Override
    public void doJoin(int mCurrPosition) {

        GetMyCircleResultBean.CirclesBean circlesBean = getV().getAdapter().getmList().get(mCurrPosition);
        if (circlesBean == null) {
            LogU.i("参数获取失败：cricle bean", getClass().getName());
            return;
        }
        int userId = SPUtil.getmUserSp().getInt("userId", -1);
        if (userId == -1) {
            LogU.i("参数获取失败：userId", getClass().getName());
            return;
        }

        getM().joinInCircles(String.valueOf(circlesBean.getCircleId()), String.valueOf(userId), new DetailCircleFragmentContract.Model.JoinCirclesResult() {
            @Override
            public void onSuccess(String message) {
                if (getV() == null) {
                    return;
                }
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
