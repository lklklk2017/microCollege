package cn.cuit.microcollege.presenter;

import java.util.HashMap;
import java.util.List;

import cn.cuit.microcollege.base.BasePresenter;
import cn.cuit.microcollege.contract.HomePagerFragmentOneContract;
import cn.cuit.microcollege.entity.HttpResultEntity.GetDynamicResultBean;
import cn.cuit.microcollege.fragment.HomePagerOneFragment;
import cn.cuit.microcollege.model.HomePagerFragmentOneModel;
import cn.cuit.microcollege.utils.LogU;

/**
 * @Author: Created by Rod Eden
 * @Date: 2019/4/24
 * @Descirption:
 */
public class HomePagerFragmentOnePresenter extends BasePresenter<HomePagerOneFragment, HomePagerFragmentOneModel> implements HomePagerFragmentOneContract.Presenter {

    public HomePagerFragmentOnePresenter(HomePagerOneFragment view) {
        super(view);
    }

    @Override
    public void initModel() {
        bindModel(new HomePagerFragmentOneModel());
    }

    @Override
    public void getDynamicTask(int currentPage) {

        LogU.i("one请求执行", "net");

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
        data.put("page", currentPage);
        data.put("pageSize", pageSize);

        getV().getSwip().setRefreshing(true);
        getM().getDynamicInfo(data, new HomePagerFragmentOneContract.Model.DynamicHttpResult() {
            @Override
            public void onSuccess(List<GetDynamicResultBean.TrendsBean> trends) {
                LogU.i("one请求成功", "net");
                if (getV() == null) {
                    return;
                }
                getV().getSwip().setRefreshing(false);
                if (trends == null) {
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
                getV().getSwip().setRefreshing(false);
                getV().toast("获取\"所有\"失败 ,网络异常", false);
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
        getM().getDynamicInfo(data, new HomePagerFragmentOneContract.Model.DynamicHttpResult() {
            @Override
            public void onSuccess(List<GetDynamicResultBean.TrendsBean> trends) {
                if (getV() == null) {
                    return;
                }
                getV().getSwip().setRefreshing(false);
                //适配数据
                getV().getAdapter().changeList(trends);
            }

            @Override
            public void onError(String error) {
                if (getV() == null) {
                    return;
                }
                getV().getSwip().setRefreshing(false);
                getV().toast("获取\"所有\"失败 ,网络异常", false);
            }
        });
    }

    @Override
    public void addLike(String tid) {

        getM().addLike(tid, new HomePagerFragmentOneContract.Model.AddLikeHttpResult() {
            @Override
            public void success() {

            }

            @Override
            public void error(String error) {

            }
        });
    }
}
