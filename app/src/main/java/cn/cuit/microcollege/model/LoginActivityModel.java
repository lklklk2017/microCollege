package cn.cuit.microcollege.model;

import cn.cuit.microcollege.contract.LoginActivityContract;
import cn.cuit.microcollege.entity.HttpResultEntity.UserLoginResultBean;
import cn.cuit.microcollege.utils.RetrofitManager;
import cn.cuit.microcollege.utils.apiService.CustomResultCallBack;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @Author: Created by Rod Eden
 * @Date: 2019/4/20
 * @Descirption:
 */
public class LoginActivityModel implements LoginActivityContract.Model {
    @Override
    public void login(String tel, String password, final LoginResult result) {
        RetrofitManager.getMainApiService().login(tel, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CustomResultCallBack<UserLoginResultBean>() {
                    @Override
                    public void onResultSuccess(UserLoginResultBean userLoginResultBean) {
                        int statusCode = userLoginResultBean.getStatusCode();
                        if (statusCode == 0) {
                            result.onError(userLoginResultBean.getMessage());
                        } else if (statusCode == 1) {
                            UserLoginResultBean.UserBean user = userLoginResultBean.getUser();
                            result.onSuccess(user);
                        }
                    }

                    @Override
                    public void onResultError(String e) {
                        result.onError(e);
                    }
                });
    }
}
