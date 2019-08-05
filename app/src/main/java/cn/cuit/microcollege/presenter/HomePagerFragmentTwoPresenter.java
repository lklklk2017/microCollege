package cn.cuit.microcollege.presenter;

import java.util.HashMap;
import java.util.List;

import cn.cuit.microcollege.base.BasePresenter;
import cn.cuit.microcollege.contract.HomePagerFragmentTwoContract;
import cn.cuit.microcollege.entity.HttpResultEntity.GetDynamicWithCircleResultBean;
import cn.cuit.microcollege.fragment.HomePagerTwoFragment;
import cn.cuit.microcollege.model.HomePagerFragmentTwoModel;
import cn.cuit.microcollege.utils.LogU;

/**
 * @Author: Created by Rod Eden
 * @Date: 2019/4/24
 * @Descirption:
 */
public class HomePagerFragmentTwoPresenter extends BasePresenter<HomePagerTwoFragment, HomePagerFragmentTwoModel> implements HomePagerFragmentTwoContract.Presenter {
    public HomePagerFragmentTwoPresenter(HomePagerTwoFragment view) {
        super(view);
    }

    @Override
    public void initModel() {
        bindModel(new HomePagerFragmentTwoModel());
    }

    @Override
    public void getDynamicTask(int currPage) {
        LogU.i("two请求执行","net");

        //获取数据
        int pageSize = getV().getPageSize();

        //封装数据
        HashMap<String, Object> data = new HashMap<>();
        data.put("name", "");
        data.put("trendType", "");
        data.put("tel", "");
        data.put("email", "");
        data.put("fromTime", "");
        data.put("toTime", "");
        data.put("circleName", "");
        data.put("content", "");
        data.put("page", currPage);
        data.put("pageSize", pageSize);

        getV().getSwip().setRefreshing(true);
        getM().getDynamicInfo(data, new HomePagerFragmentTwoContract.Model.DynamicHttpResult() {
            @Override
            public void onSuccess(List<GetDynamicWithCircleResultBean.TrendsBean> trends) {
                LogU.i("two请求成功","net");

                getV().getSwip().setRefreshing(false);
                if (trends == null) {
                    getV().toast("没有更多数据", false);
                    int currentPage = getV().getCurrentPage();
                    getV().setCurrentPage(--currentPage);
                    return;
                }
                //适配数据
                getV().getAdapter().addList(trends);
            }

            @Override
            public void onError(String error) {
                getV().getSwip().setRefreshing(false);
                getV().toast("获取\"最新\"失败 ,网络异常", false);
            }
        });
    }

    @Override
    public void changeData(int currPage, int currPageSize) {
        //获取数据
        int pageSize = getV().getPageSize();

        //封装数据
        HashMap<String, Object> data = new HashMap<>();
        data.put("name", "");
        data.put("trendType", "");
        data.put("tel", "");
        data.put("email", "");
        data.put("fromTime", "");
        data.put("toTime", "");
        data.put("circleName", "");
        data.put("content", "");
        data.put("page", currPage);
        data.put("pageSize", currPageSize);

        getV().getSwip().setRefreshing(true);
        getM().getDynamicInfo(data, new HomePagerFragmentTwoContract.Model.DynamicHttpResult() {
            @Override
            public void onSuccess(List<GetDynamicWithCircleResultBean.TrendsBean> trends) {
                getV().getSwip().setRefreshing(false);
                //适配数据
                getV().getAdapter().changeList(trends);
            }

            @Override
            public void onError(String error) {
                getV().getSwip().setRefreshing(false);
                getV().toast("获取\"所有\"失败 ,网络异常", false);
            }
        });
    }

    @Override
    public void addlike(String tid) {
         getM().addLike(tid, new HomePagerFragmentTwoContract.Model.AddLikeHttpResult() {
            @Override
            public void success() {

            }

            @Override
            public void error(String error) {

            }
        });
    }
}
