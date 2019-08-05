package cn.cuit.microcollege.model;

import java.util.Map;

import cn.cuit.microcollege.contract.PublishDynamicActivityContract;
import cn.cuit.microcollege.entity.HttpResultEntity.GetAddTrendResultBean;
import cn.cuit.microcollege.entity.HttpResultEntity.GetUploadFilesResultBean;
import cn.cuit.microcollege.utils.RetrofitManager;
import cn.cuit.microcollege.utils.apiService.CustomResultCallBack;
import okhttp3.RequestBody;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @Author: Created by Rod Eden
 * @Date: 2019/5/1
 * @Descirption:
 */
public class PublishDynamicActivityModel implements PublishDynamicActivityContract.Model {
    /**
     * 发布动态
     * @param map
     * @param result
     */
    @Override
    public void doUpload(Map<String, RequestBody> map, final UploadHttpResult result) {

        RetrofitManager.getMainApiService().uploadFiles(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CustomResultCallBack<GetUploadFilesResultBean>() {
                    @Override
                    public void onResultSuccess(GetUploadFilesResultBean getUploadFilesResultBean) {
                        String statusCode = getUploadFilesResultBean.getStatusCode();
                        if (statusCode.equals("1")) {
                            //返回
                            result.onSuccess(getUploadFilesResultBean);
                        } else {
                            result.onError(getUploadFilesResultBean.getMessage());
                        }
                    }

                    @Override
                    public void onResultError(String e) {
                        result.onError(e);
                    }
                });
    }

    @Override
    public void doPublish(Map<String, Object> map, final PublishHttpResult result) {
        RetrofitManager.getAdminApiService().addTrend(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CustomResultCallBack<GetAddTrendResultBean>() {
                    @Override
                    public void onResultSuccess(GetAddTrendResultBean getUploadFilesResultBean) {
                        String statusCode = getUploadFilesResultBean.getStatusCode();
                        if (statusCode.equals("1")) {
                            //返回
                            result.onSuccess();
                        } else {
                            result.onError(getUploadFilesResultBean.getMessage());
                        }
                    }

                    @Override
                    public void onResultError(String e) {
                        result.onError(e);
                    }
                });
    }


}
