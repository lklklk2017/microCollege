package cn.cuit.microcollege.model;


import cn.cuit.microcollege.contract.DetailCircleFragmentContract;
import cn.cuit.microcollege.entity.HttpResultEntity.GetMyCircleResultBean;
import cn.cuit.microcollege.entity.HttpResultEntity.JoinInCircleResultBean;
import cn.cuit.microcollege.utils.RetrofitManager;
import cn.cuit.microcollege.utils.apiService.CustomResultCallBack;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @Author: Created by Rod Eden
 * @Date: 2019/4/27
 * @Descirption:
 */
public class DetailCircleFragmentModel implements DetailCircleFragmentContract.Model {

    @Override
    public void getCircles(String circleName, String page, String pageSize, final CirclesResult result) {

        RetrofitManager.getAdminApiService().getCircleByConditions(
                "",
                "",
                "",
                "",
                circleName,
                null,
                null,
                page,
                pageSize)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CustomResultCallBack<GetMyCircleResultBean>() {
                    @Override
                    public void onResultSuccess(GetMyCircleResultBean getMyCircleResultBean) {
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
