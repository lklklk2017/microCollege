package cn.cuit.microcollege.model;

import cn.cuit.microcollege.contract.FeedBackActivityContract;
import cn.cuit.microcollege.entity.HttpResultEntity.AddFeedBackResultBean;
import cn.cuit.microcollege.utils.RetrofitManager;
import cn.cuit.microcollege.utils.apiService.CustomResultCallBack;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @Author: Created by Rod Eden
 * @Date: 2019/5/3
 * @Descirption:
 */
public class FeedBackActivityModel implements FeedBackActivityContract.Model {
    @Override
    public void addFeedback(int userId, String content, final AddFeedBackHttpResult result) {
        RetrofitManager.getAdminApiService().addFeedBack(userId,content)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CustomResultCallBack<AddFeedBackResultBean>() {
                    @Override
                    public void onResultSuccess(AddFeedBackResultBean deleteCommentHttpResult) {
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
