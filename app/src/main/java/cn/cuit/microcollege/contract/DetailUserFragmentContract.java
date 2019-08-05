package cn.cuit.microcollege.contract;

import android.support.v4.widget.SwipeRefreshLayout;

import java.util.List;
import java.util.Map;

import cn.cuit.microcollege.adapter.UserSearchAdapter;
import cn.cuit.microcollege.base.BaseModel;
import cn.cuit.microcollege.entity.HttpResultEntity.GetUserBaseInfoResultBean;

/**
 * @Author: Created by Rod Eden
 * @Date: 2019/4/27
 * @Descirption:
 */
public interface DetailUserFragmentContract {
    interface Model extends BaseModel {
        void getUsers(Map<String, Object> map, DetailUserFragmentContract.Model.UserResult result);

        interface UserResult {
            void onSuccess(List<GetUserBaseInfoResultBean.UserListBean> userList);

            void onError(String error);
        }
    }

    interface View {
        void showHasResultView();

        void showNoResultView();

        SwipeRefreshLayout getSwip();

        UserSearchAdapter getAdapter();

        int getCurrentPage();

        int getPageSize();

        int setCurrentPage(int page);
    }

    interface Presenter {
        void doChangeUserTask(int page, int pageSize);
    }
}
