package cn.cuit.microcollege.model;

import cn.cuit.microcollege.contract.MyCircleActivityContract;
import cn.cuit.microcollege.entity.HttpResultEntity.GetMyCircleResultBean;
import cn.cuit.microcollege.entity.HttpResultEntity.QuitFromCircleResultBean;
import cn.cuit.microcollege.utils.RetrofitManager;
import cn.cuit.microcollege.utils.apiService.CustomResultCallBack;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @Author: Created by Rod Eden
 * @Date: 2019/4/29
 * @Descirption:
 */
public class MyCircleActivityModel implements MyCircleActivityContract.Model {
    @Override
    public void getMyCircleList(String userId, String page, String pageSize, final MyCircleHttpResult result) {
        RetrofitManager.getAdminApiService().getCircleByConditions(
                "",
                "",
                "",
                userId,
                "",
                null,
                null,
                page,
                pageSize)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CustomResultCallBack<GetMyCircleResultBean>() {
                    @Override
                    public void onResultSuccess(GetMyCircleResultBean getMyCircleResultBean) {
//                        LogU.i("成功:" + getMyCircleResultBean.toString(), "MyCircleResult");
                        String statusCode = getMyCircleResultBean.getStatusCode();
                        if (statusCode.equals("1")) {
                            result.onSuccess(getMyCircleResultBean.getCircles());
                        } else if (statusCode.equals("0")) {
                            result.onError(getMyCircleResultBean.getMessage());
                        }
                    }

                    @Override
                    public void onResultError(String e) {
                        result.onError(e);
                    }
                });
    }

    @Override
    public void cancelFromCircles(String circleId, String userId, final cancelCirclesResult result) {
        RetrofitManager.getAdminApiService().quitFromCircle(
                circleId,
                userId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CustomResultCallBack<QuitFromCircleResultBean>() {
                    @Override
                    public void onResultSuccess(QuitFromCircleResultBean resultBean) {
                        int statusCode = resultBean.getStatusCode();
                        if (statusCode == 1) {
                            int isQuited = resultBean.getIsQuited();
                            if (isQuited == 0) {
                                //未加入
                                result.onError(resultBean.getMessage());
                            } else {
                                //已加入
                                result.onSuccess(resultBean.getMessage());
                            }
                        } else if (statusCode == 0){
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
