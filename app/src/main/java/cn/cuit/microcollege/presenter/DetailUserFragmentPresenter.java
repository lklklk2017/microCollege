package cn.cuit.microcollege.presenter;

import android.os.Bundle;

import java.util.HashMap;
import java.util.List;

import cn.cuit.microcollege.base.BasePresenter;
import cn.cuit.microcollege.contract.DetailUserFragmentContract;
import cn.cuit.microcollege.entity.HttpResultEntity.GetUserBaseInfoResultBean;
import cn.cuit.microcollege.fragment.search.detail.DetailUserFragment;
import cn.cuit.microcollege.model.DetailUserFragmentModel;
import cn.cuit.microcollege.utils.LogU;
import cn.cuit.microcollege.utils.SPUtil;

/**
 * @Author: Created by Rod Eden
 * @Date: 2019/4/27
 * @Descirption:
 */
public class DetailUserFragmentPresenter extends BasePresenter<DetailUserFragment, DetailUserFragmentModel> implements DetailUserFragmentContract.Presenter {

    public DetailUserFragmentPresenter(DetailUserFragment view) {
        super(view);
    }

    @Override
    public void initModel() {
        bindModel(new DetailUserFragmentModel());
    }

    @Override
    public void doChangeUserTask(final int page, int pageSize) {
        //getname
        Bundle arguments = getV().getArguments();
        if (arguments == null) {
            LogU.i("参数获取失败： argument", getClass().getName());
            return;
        }
        String condiction = arguments.getString("condiction");
        int userId = SPUtil.getmUserSp().getInt("userId", -1);
        if (userId == -1) {
            LogU.i("参数获取失败： userId", getClass().getName());
            return;
        }

        getV().getSwip().setRefreshing(true);
        HashMap<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        map.put("tel", "");
        map.put("name", condiction);
        map.put("email", "");
        map.put("gender", "");
        map.put("fromTime", "");
        map.put("toTime", "");
        map.put("page", page);
        map.put("pageSize", pageSize);
        getM().getUsers(map, new DetailUserFragmentContract.Model.UserResult() {
            @Override
            public void onSuccess(List<GetUserBaseInfoResultBean.UserListBean> userList) {

                if (getV() == null) {
                    return;
                }
                getV().getSwip().setRefreshing(false);
                int itemCount = getV().getAdapter().getItemCount();
                if (userList == null || userList.size() == 0) {
                    //没有数据
                    if (itemCount == 0) {
                        //第一次
                        getV().showNoResultView();
                        return;
                    }
                }
                if (itemCount == userList.size()) {
                    getV().toast("没有更多数据", false);
                    int currentPage = getV().getCurrentPage();
                    getV().setCurrentPage(currentPage - 1);
                    return;
                }
                //有数据
                getV().getAdapter().changeList(userList);
            }

            @Override
            public void onError(String error) {
                if (getV() == null) {
                    return;
                }
                getV().getSwip().setRefreshing(false);
                int itemCount = getV().getAdapter().getItemCount();
                if (itemCount == 0) {
                    getV().setCurrentPage(page - 1);
                    getV().showNoResultView();
                }
                getV().toast(error, false);
            }
        });
    }
}
