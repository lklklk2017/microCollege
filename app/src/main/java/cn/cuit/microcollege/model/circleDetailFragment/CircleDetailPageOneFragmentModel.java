package cn.cuit.microcollege.model.circleDetailFragment;

import java.util.Map;

import cn.cuit.microcollege.contract.circleDetailFragment.CircleDetailPageOneFragmentContract;
import cn.cuit.microcollege.entity.HttpResultEntity.GetDynamicWithCircleResultBean;
import cn.cuit.microcollege.entity.HttpResultEntity.LikeResultBean;
import cn.cuit.microcollege.utils.RetrofitManager;
import cn.cuit.microcollege.utils.apiService.CustomResultCallBack;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @Author: Created by Rod Eden
 * @Date: 2019/5/22
 * @Descirption:
 */
public class CircleDetailPageOneFragmentModel implements CircleDetailPageOneFragmentContract.Model {
    @Override
    public void getNewDynamic(Map<String, Object> map, final DynamicHttpResult result) {
        RetrofitManager.getAdminApiService().getDynamicWithCircleByCondiction(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CustomResultCallBack<GetDynamicWithCircleResultBean>() {
                    @Override
                    public void onResultSuccess(GetDynamicWithCircleResultBean getDynamicResultBean) {
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
    public void addLike(String tid, final CircleDetailPageOneFragmentContract.Model.AddLikeHttpResult result) {
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
