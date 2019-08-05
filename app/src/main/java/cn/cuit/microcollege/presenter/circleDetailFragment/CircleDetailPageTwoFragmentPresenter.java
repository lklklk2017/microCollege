package cn.cuit.microcollege.presenter.circleDetailFragment;

import java.util.HashMap;
import java.util.List;

import cn.cuit.microcollege.base.BasePresenter;
import cn.cuit.microcollege.contract.circleDetailFragment.CircleDetailPageTwoFragmentContract;
import cn.cuit.microcollege.entity.HttpResultEntity.GetDynamicWithCircleResultBean;
import cn.cuit.microcollege.fragment.circleDetail.CircleDetailPageTwoFragment;
import cn.cuit.microcollege.model.circleDetailFragment.CircleDetailPageTwoFragmentModel;
import cn.cuit.microcollege.utils.LogU;

/**
 * @Author: Created by Rod Eden
 * @Date: 2019/5/22
 * @Descirption:
 */
public class CircleDetailPageTwoFragmentPresenter extends BasePresenter<CircleDetailPageTwoFragment, CircleDetailPageTwoFragmentModel> implements CircleDetailPageTwoFragmentContract.Presenter {

    public CircleDetailPageTwoFragmentPresenter(CircleDetailPageTwoFragment view) {
        super(view);
    }

    @Override
    public void initModel() {
        bindModel(new CircleDetailPageTwoFragmentModel());
    }

    @Override
    public void getHotDynamicTask(final int currPage) {
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

        getM().getHotDynamic(data, new CircleDetailPageTwoFragmentContract.Model.HotDynamicHttpResult() {
            @Override
            public void onSuccess(List<GetDynamicWithCircleResultBean.TrendsBean> trends) {
                if (getV() == null) {
                    return;
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
                getV().toast("获取\"最新\"失败 ,网络异常", false);
            }
        });
    }

    @Override
    public void addLike(String tid) {
        getM().addLike(tid, new CircleDetailPageTwoFragmentContract.Model.AddLikeHttpResult() {
            @Override
            public void success() {

            }

            @Override
            public void error(String error) {

            }
        });
    }
}
