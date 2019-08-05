package cn.cuit.microcollege.model;

import cn.cuit.microcollege.contract.HomeFragmentContract;
import cn.cuit.microcollege.entity.HttpResultEntity.GetMyCircleResultBean;
import cn.cuit.microcollege.utils.LogU;
import cn.cuit.microcollege.utils.RetrofitManager;
import cn.cuit.microcollege.utils.apiService.CustomResultCallBack;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @Author: Created by Rod Eden
 * @Date: 2019/4/16
 * @Descirption:
 */
public class HomeFragmentModel implements HomeFragmentContract.Model {
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
}
