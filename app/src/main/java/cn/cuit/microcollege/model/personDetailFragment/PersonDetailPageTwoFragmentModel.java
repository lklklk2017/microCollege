package cn.cuit.microcollege.model.personDetailFragment;

import java.util.List;
import java.util.Map;

import cn.cuit.microcollege.contract.personDetailFragment.PersonDetailPageTwoFragmentContract;
import cn.cuit.microcollege.entity.HttpResultEntity.GetUserBaseInfoResultBean;
import cn.cuit.microcollege.utils.RetrofitManager;
import cn.cuit.microcollege.utils.apiService.CustomResultCallBack;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @Author: Created by Rod Eden
 * @Date: 2019/5/25
 * @Descirption:
 */
public class PersonDetailPageTwoFragmentModel implements PersonDetailPageTwoFragmentContract.Model {
    @Override
    public void getBaseInfoTask(Map<String, Object> map, final BaseInfoHttpResult result) {
        RetrofitManager.getAdminApiService().getUserInfoByCondiction(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CustomResultCallBack<GetUserBaseInfoResultBean>() {
                    @Override
                    public void onResultSuccess(GetUserBaseInfoResultBean baseInfoResultBean) {
                        String statusCode = baseInfoResultBean.getStatusCode();
                        if (statusCode.equals("0")) {
                            result.onError(baseInfoResultBean.getMessage());
                        } else if (statusCode.equals("1")) {
                            List<GetUserBaseInfoResultBean.UserListBean> userList = baseInfoResultBean.getUserList();
                            if (userList != null) {
                                GetUserBaseInfoResultBean.UserListBean userListBean = userList.get(0);
                                if (userListBean != null) {
                                    result.onSuccess(userListBean);
                                }
                            }
                        }
                    }

                    @Override
                    public void onResultError(String e) {
                        result.onError(e);
                    }
                });
    }
}
