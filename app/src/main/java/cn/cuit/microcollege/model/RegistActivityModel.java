package cn.cuit.microcollege.model;

import cn.cuit.microcollege.contract.RegistActivityContract;
import cn.cuit.microcollege.entity.HttpResultEntity.UserRegistResultBean;
import cn.cuit.microcollege.utils.LogU;
import cn.cuit.microcollege.utils.RetrofitManager;
import cn.cuit.microcollege.utils.apiService.CustomResultCallBack;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static cn.cuit.microcollege.utils.Config.ERROR_BAD_NET;


/**
 * @Author: Created by Rod Eden
 * @Date: 2019/4/20
 * @Descirption:
 */
public class RegistActivityModel implements RegistActivityContract.Model {

    @Override
    public void regist(String tel, String pwd, String email, final RegistHttpResult result) {

        RetrofitManager.getAdminApiService().regist("", pwd, "男", email, "", tel)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CustomResultCallBack<UserRegistResultBean>() {
                    @Override
                    public void onResultSuccess(UserRegistResultBean userRegistResultBean) {

                        String statusCode = userRegistResultBean.getStatusCode();
                        LogU.i("result" + userRegistResultBean.toString(), "");
                        if (statusCode.equals("1")) {
                            result.onSuccess();

                        } else if (statusCode.equals("0")) {
                            String message = userRegistResultBean.getMessage();
                            if (message != null) {
                                result.onError(message);
                            } else {
                                result.onError("返回错误结果");
                            }
                        }
                    }

                    @Override
                    public void onResultError(String e) {
                        result.onError(e);
                    }
                });
    }
}
