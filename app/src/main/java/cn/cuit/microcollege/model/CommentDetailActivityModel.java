package cn.cuit.microcollege.model;

import java.util.Map;

import cn.cuit.microcollege.contract.CommentDetailActivityContract;
import cn.cuit.microcollege.entity.HttpResultEntity.GetAddCommentResultBean;
import cn.cuit.microcollege.entity.HttpResultEntity.GetCommentResultBean;
import cn.cuit.microcollege.utils.RetrofitManager;
import cn.cuit.microcollege.utils.apiService.CustomResultCallBack;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @Author: Created by Rod Eden
 * @Date: 2019/5/12
 * @Descirption:
 */
public class CommentDetailActivityModel implements CommentDetailActivityContract.Model {

    @Override
    public void getComment(Map<String, Object> data, final CommentDetailActivityContract.Model.CommentHttpResult result) {

        RetrofitManager.getAdminApiService().getCommentByCondiction(data)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CustomResultCallBack<GetCommentResultBean>() {
                    @Override
                    public void onResultSuccess(GetCommentResultBean getDynamicResultBean) {
                        String statusCode = getDynamicResultBean.getStatusCode();

                        if ("1".equals(statusCode)) {
                            result.onSuccess(getDynamicResultBean.getComments());
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
    public void addComment(Map<String, Object> data, final CommentDetailActivityContract.Model.addCommentHttpResult result) {
        RetrofitManager.getAdminApiService().addComment(data)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CustomResultCallBack<GetAddCommentResultBean>() {
                    @Override
                    public void onResultSuccess(GetAddCommentResultBean getAddCommentResultBean) {
                        String statusCode = getAddCommentResultBean.getStatusCode();
                        if ("1".equals(statusCode)) {
                            result.onSuccess();
                        } else {
                            result.onError(getAddCommentResultBean.getMessage());
                        }
                    }

                    @Override
                    public void onResultError(String e) {
                        result.onError(e);
                    }
                });
    }
}
