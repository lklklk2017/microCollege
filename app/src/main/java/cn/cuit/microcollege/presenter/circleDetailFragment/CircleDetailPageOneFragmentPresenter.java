package cn.cuit.microcollege.presenter.circleDetailFragment;

import java.util.HashMap;
import java.util.List;

import cn.cuit.microcollege.activity.CircleDetailActivity;
import cn.cuit.microcollege.base.BasePresenter;
import cn.cuit.microcollege.contract.circleDetailFragment.CircleDetailPageOneFragmentContract;
import cn.cuit.microcollege.entity.HttpResultEntity.GetDynamicWithCircleResultBean;
import cn.cuit.microcollege.fragment.circleDetail.CircleDetailPageOneFragment;
import cn.cuit.microcollege.model.circleDetailFragment.CircleDetailPageOneFragmentModel;
import cn.cuit.microcollege.utils.LogU;

/**
 * @Author: Created by Rod Eden
 * @Date: 2019/5/22
 * @Descirption:
 */
public class CircleDetailPageOneFragmentPresenter extends BasePresenter<CircleDetailPageOneFragment, CircleDetailPageOneFragmentModel> implements CircleDetailPageOneFragmentContract.Presenter {

    public CircleDetailPageOneFragmentPresenter(CircleDetailPageOneFragment view) {
        super(view);
    }

    @Override
    public void initModel() {
        bindModel(new CircleDetailPageOneFragmentModel());
    }

    /**
     * @param currPage 起始搜索页码号
     *                 第一次返回 0-pageSize-1  ep:0-4
     *                 第二次返回 (page-1)*pageSize ep:5-9
     *                 如果没有数据就 page--
     *                 如果有数据就 list.add()  (在原来基础上添加)
     */
    @Override
    public void getNewDynamicTask(final int currPage) {

        LogU.i("circle new 请求执行", "net");

        //获取数据
        int pageSize = getV().getPageSize();

        String circleName = getV().getCircleName();
        if (circleName == null || circleName.equals("")) {
            getV().toast("圈子信息获取失败", false);
            return;
        }

        //封装数据
        HashMap<String, Object> data = new HashMap<>();
        data.put("name", "");
        data.put("trendType", "");
        data.put("tel", "");
        data.put("email", "");
        data.put("fromTime", "");
        data.put("toTime", "");
        data.put("circleName", circleName);
        data.put("content", "");
        data.put("page", currPage);
        data.put("pageSize", pageSize);

//        getV().showDialog(true);
        getM().getNewDynamic(data, new CircleDetailPageOneFragmentContract.Model.DynamicHttpResult() {
            @Override
            public void onSuccess(List<GetDynamicWithCircleResultBean.TrendsBean> trends) {
                if (getV() == null) {
                    return;
                }

                //查看是否能加入：
                CircleDetailActivity activity = (CircleDetailActivity) getV().getActivity();
                if (activity != null) {
                    if (!activity.isChecked()) {
                        activity.getPresent().checkUserIsInCircleTask();
                    }
                }

                if (trends == null) {
                    //请求成功 但是没数据
                    //1.第一次请求
                    if (currPage == 1) {
                        //show no result
                        getV().showNoResult();
                        return;
                    }
                    //2.非第一次请求
                    getV().toast("没有更多数据了", false);
                    int currentPage1 = getV().getCurrentPage();
                    getV().setCurrentPage(--currentPage1);
                    return;
                }
                //适配数据
                getV().getAdapter().addList(trends);
            }

            @Override
            public void onError(String error) {
                if (getV() == null) {
                    return;
                }

                //查看是否能加入：
                CircleDetailActivity activity = (CircleDetailActivity) getV().getActivity();
                if (activity != null) {
                    if (!activity.isChecked()) {
                        activity.getPresent().checkUserIsInCircleTask();
                    }
                }
                getV().toast("获取\"最新\"失败 ,网络异常", false);
            }
        });
    }

    /**
     * 不管之前的数据有多少 如果请求成功 直接更改数据结构
     * 特殊刷新使用
     *
     * @param currPage
     * @param currPageSize
     */
    @Override
    public void changeData(int currPage, int currPageSize) {
        //获取数据
        String circleName = getV().getCircleName();
        if (circleName == null || circleName.equals("")) {
            getV().toast("圈子信息获取失败", false);
            return;
        }

        //封装数据
        HashMap<String, Object> data = new HashMap<>();
        data.put("name", "");
        data.put("trendType", "");
        data.put("tel", "");
        data.put("email", "");
        data.put("fromTime", "");
        data.put("toTime", "");
        data.put("circleName", circleName);
        data.put("content", "");
        data.put("page", currPage);
        data.put("pageSize", currPageSize);

        getM().getNewDynamic(data, new CircleDetailPageOneFragmentContract.Model.DynamicHttpResult() {
            @Override
            public void onSuccess(List<GetDynamicWithCircleResultBean.TrendsBean> trends) {
                getV().showDialog(false);
                //适配数据
                getV().getAdapter().changeList(trends);
            }

            @Override
            public void onError(String error) {
                getV().toast("获取\"最新\"失败 ,网络异常", false);
            }
        });
    }

    @Override
    public void addLike(String tid) {
        getM().addLike(tid, new CircleDetailPageOneFragmentContract.Model.AddLikeHttpResult() {
            @Override
            public void success() {

            }

            @Override
            public void error(String error) {

            }
        });
    }
}
