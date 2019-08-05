package cn.cuit.microcollege.model;

import java.util.Map;

import cn.cuit.microcollege.contract.DynamicDetailActivityContract;
import cn.cuit.microcollege.entity.HttpResultEntity.DeleteResultBean;
import cn.cuit.microcollege.entity.HttpResultEntity.GetAddCommentResultBean;
import cn.cuit.microcollege.entity.HttpResultEntity.GetCommentResultBean;
import cn.cuit.microcollege.entity.HttpResultEntity.LikeResultBean;
import cn.cuit.microcollege.utils.RetrofitManager;
import cn.cuit.microcollege.utils.apiService.CustomResultCallBack;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @Author: Created by Rod Eden
 * @Date: 2019/5/10
 * @Descirption:
 */
public class DynamicDetailActivityModel implements DynamicDetailActivityContract.Model {

    @Override
    public void getComment(Map<String, Object> data, final CommentHttpResult result) {

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
    public void addComment(Map<String, Object> data, final addCommentHttpResult result) {
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

    @Override
    public void deleteComment(String comId, final DeleteCommentHttpResult result) {
        RetrofitManager.getAdminApiService().deleteComment(comId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CustomResultCallBack<DeleteResultBean>() {
                    @Override
                    public void onResultSuccess(DeleteResultBean deleteCommentHttpResult) {
                        String statusCode = deleteCommentHttpResult.getStatusCode();
                        if ("1".equals(statusCode)) {
                            result.success();
                        } else {
                            result.error(deleteCommentHttpResult.getMessage());
                        }
                    }

                    @Override
                    public void onResultError(String e) {
                        result.error(e);
                    }
                });
    }
}
