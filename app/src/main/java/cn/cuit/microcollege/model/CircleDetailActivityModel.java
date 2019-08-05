package cn.cuit.microcollege.model;

import java.util.Map;

import cn.cuit.microcollege.contract.CircleDetailActivityContract;
import cn.cuit.microcollege.entity.HttpResultEntity.GetUserInCircleCheckResultBean;
import cn.cuit.microcollege.entity.HttpResultEntity.JoinInCircleResultBean;
import cn.cuit.microcollege.utils.RetrofitManager;
import cn.cuit.microcollege.utils.apiService.CustomResultCallBack;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @Author: Created by Rod Eden
 * @Date: 2019/4/29
 * @Descirption:
 */
public class CircleDetailActivityModel implements CircleDetailActivityContract.Model {
    @Override
    public void checkUserInCircle(Map<String, Object> map, final CheckUserInCircleHttpResult result) {
        RetrofitManager.getAdminApiService().checkUserInCircle(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CustomResultCallBack<GetUserInCircleCheckResultBean>() {
                    @Override
                    public void onResultSuccess(GetUserInCircleCheckResultBean getUserInCircleCheckResultBean) {
                        int statusCode = getUserInCircleCheckResultBean.getStatusCode();
                        String message = getUserInCircleCheckResultBean.getMessage();
                        if (statusCode == 1) {
                            int isJoined = getUserInCircleCheckResultBean.getIsJoined();
                            if (isJoined == 1) {
                                //加入
                                result.onSuccess();
                            } else {
                                //未加入
                                result.onError(message);
                            }
                        } else {
                            result.onError(message);
                        }

                    }

                    @Override
                    public void onResultError(String e) {
                        result.onError(e);
                    }
                });
    }

    @Override
    public void joinInCircles(String circleId, String userId, final JoinCirclesResult result) {
        RetrofitManager.getAdminApiService().joinInCircle(
                circleId,
                userId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CustomResultCallBack<JoinInCircleResultBean>() {
                    @Override
                    public void onResultSuccess(JoinInCircleResultBean resultBean) {
                        int statusCode = resultBean.getStatusCode();
                        if (statusCode == 1) {
                            int isJoined = resultBean.getIsJoined();
                            if (isJoined == 0) {
                                //未加入
                                result.onError(resultBean.getMessage());
                            } else {
                                //已加入
                                result.onSuccess(resultBean.getMessage());
                            }
                        } else if (statusCode == 0) {
                            result.onError(resultBean.getMessage());
                        }
                    }

                    @Override
                    public void onResultError(String e) {
                        result.onError(e);
                    }
                });
    }
}
