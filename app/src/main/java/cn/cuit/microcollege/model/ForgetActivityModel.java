package cn.cuit.microcollege.model;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.util.HashMap;
import java.util.Map;

import cn.cuit.microcollege.contract.ForgetActivityContract;
import cn.cuit.microcollege.entity.HttpResultEntity.SMSResultBean;
import cn.cuit.microcollege.entity.HttpResultEntity.UserCheckTelExistResultBean;
import cn.cuit.microcollege.utils.Config;
import cn.cuit.microcollege.utils.LogU;
import cn.cuit.microcollege.utils.RetrofitManager;
import cn.cuit.microcollege.utils.apiService.CustomResultCallBack;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @Author: Created by Rod Eden
 * @Date: 2019/4/22
 * @Descirption:
 */
public class ForgetActivityModel implements ForgetActivityContract.Model {
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void sendMessage(String tel, Integer id, String value, String key, final SMSHttpResult result) {

        Map<String, Object> map = new HashMap<>();
        map.put("mobile", tel);
        map.put("tpl_id", id);
        map.put("tpl_value", value);
        map.put("key", key);
        map.put("dtype", "json");

        RetrofitManager.getEmsApiService().sendsms(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CustomResultCallBack<SMSResultBean>() {
                    @Override
                    public void onResultSuccess(SMSResultBean smsResultBean) {
//                        LogU.i("短信返回结果:" + smsResultBean, "SMSHttpResult");
                        SMSResultBean.ResultBean bean = smsResultBean.getResult();
                        int error_code = smsResultBean.getError_code();
                        if (error_code == 0) {//成功
                            if (bean != null) {
                                result.onSuccess(bean);
                            }
                        } else {
                            result.onError("短信发送失败..请稍后尝试");
                        }
                    }

                    @Override
                    public void onResultError(String e) {
                        result.onError(Config.ERROR_BAD_NET);
                    }
                });
    }

    @Override
    public void checkTel(String tel, final CheckTelHttpResult result) {

        RetrofitManager.getMainApiService().checkTel(tel)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CustomResultCallBack<UserCheckTelExistResultBean>() {

                    @Override
                    public void onResultSuccess(UserCheckTelExistResultBean userCheckTelExistResultBean) {
                        LogU.i("tel检查返回结果:" + userCheckTelExistResultBean, "CheckTelHttpResult");
                        int isExist = userCheckTelExistResultBean.getIsExist();
                        if (isExist == 1) {
                            result.onSuccess();
                        } else {
                            result.onError("电话号码还没注册！");
                        }
                    }

                    @Override
                    public void onResultError(String e) {
                        result.onError(e);
                    }
                });
    }
}
