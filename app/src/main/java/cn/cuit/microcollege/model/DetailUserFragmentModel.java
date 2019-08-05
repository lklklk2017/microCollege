package cn.cuit.microcollege.model;

import java.util.List;
import java.util.Map;

import cn.cuit.microcollege.contract.DetailUserFragmentContract;
import cn.cuit.microcollege.entity.HttpResultEntity.GetUserBaseInfoResultBean;
import cn.cuit.microcollege.utils.RetrofitManager;
import cn.cuit.microcollege.utils.apiService.CustomResultCallBack;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @Author: Created by Rod Eden
 * @Date: 2019/4/27
 * @Descirption:
 */
public class DetailUserFragmentModel implements DetailUserFragmentContract.Model {
    @Override
    public void getUsers(Map<String, Object> map, final UserResult result) {
        RetrofitManager.getAdminApiService().searchUsers(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CustomResultCallBack<GetUserBaseInfoResultBean>() {
                    @Override
                    public void onResultSuccess(GetUserBaseInfoResultBean baseInfoResultBean) {
                        String statusCode = baseInfoResultBean.getStatusCode();
                        if (statusCode.equals("0")) {
                            result.onError(baseInfoResultBean.getMessage());
                        } else if (statusCode.equals("1")) {
                            List<GetUserBaseInfoResultBean.UserListBean> userList = baseInfoResultBean.getUserList();
                            result.onSuccess(userList);
                        }
                    }

                    @Override
                    public void onResultError(String e) {
                        result.onError(e);
                    }
                });
    }
}
