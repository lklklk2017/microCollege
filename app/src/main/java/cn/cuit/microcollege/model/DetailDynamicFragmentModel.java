package cn.cuit.microcollege.model;

import java.util.Map;

import cn.cuit.microcollege.contract.DetailDynamicFragmentContract;
import cn.cuit.microcollege.entity.HttpResultEntity.GetDynamicResultBean;
import cn.cuit.microcollege.utils.RetrofitManager;
import cn.cuit.microcollege.utils.apiService.CustomResultCallBack;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @Author: Created by Rod Eden
 * @Date: 2019/4/27
 * @Descirption:
 */
public class DetailDynamicFragmentModel implements DetailDynamicFragmentContract.Model {
    @Override
    public void getDynamic(Map<String, Object> data, final DynamicResult result) {
        RetrofitManager.getAdminApiService().getDynamicByCondiction(data)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CustomResultCallBack<GetDynamicResultBean>() {
                    @Override
                    public void onResultSuccess(GetDynamicResultBean getDynamicResultBean) {
                        String statusCode = getDynamicResultBean.getStatusCode();
                        if ("1".equals(statusCode)) {
                            result.onSuccess(getDynamicResultBean.getTrends());
                        } else {
                            result.onError(getDynamicResultBean.getMessage());
                        }
                    }

                    @Override
                    public void onResultError(String e) {
                        result.onError(e);
                    }
                });
    }
}
