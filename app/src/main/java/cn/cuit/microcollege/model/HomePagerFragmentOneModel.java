package cn.cuit.microcollege.model;

import java.util.Map;

import cn.cuit.microcollege.contract.HomePagerFragmentOneContract;
import cn.cuit.microcollege.entity.HttpResultEntity.GetDynamicResultBean;
import cn.cuit.microcollege.entity.HttpResultEntity.LikeResultBean;
import cn.cuit.microcollege.utils.RetrofitManager;
import cn.cuit.microcollege.utils.apiService.CustomResultCallBack;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @Author: Created by Rod Eden
 * @Date: 2019/4/24
 * @Descirption:
 */
public class HomePagerFragmentOneModel implements HomePagerFragmentOneContract.Model {
    @Override
    public void getDynamicInfo(Map<String, Object> data, final DynamicHttpResult result) {
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

    @Override
    public void addLike(String tid, final AddLikeHttpResult result) {
        RetrofitManager.getAdminApiService().addLike(tid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CustomResultCallBack<LikeResultBean>() {
                    @Override
                    public void onResultSuccess(LikeResultBean resultBean) {
                        String statusCode = resultBean.getStatusCode();
                        if ("1".equals(statusCode)) {
                            result.success();
                        } else {
                            result.error(resultBean.getMessage());
                        }
                    }

                    @Override
                    public void onResultError(String e) {
                        result.error(e);
                    }
                });
    }
}