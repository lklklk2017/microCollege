package cn.cuit.microcollege.model.newfragments;

import cn.cuit.microcollege.contract.newfragments.NewHotFragmentContract;
import cn.cuit.microcollege.entity.HttpResultEntity.NewResultBean;
import cn.cuit.microcollege.entity.PostEntity.NewPostBean;
import cn.cuit.microcollege.utils.RetrofitManager;
import cn.cuit.microcollege.utils.apiService.CustomResultCallBack;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @Author: Created by Rod Eden
 * @Date: 2019/5/4
 * @Descirption:
 */
public class NewHotFragmentModel implements NewHotFragmentContract.Model {

    @Override
    public void getHotNew(NewPostBean bean, final HotNewResult result) {
        RetrofitManager.getNewApiService()
                .getNew(bean.getKey(), bean.getCid(), bean.getPage(), bean.getSize())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CustomResultCallBack<NewResultBean>() {
                    @Override
                    public void onResultSuccess(NewResultBean newResultBean) {
                        if (newResultBean.getMsg().equals("success")) {
                            result.onSuccess(newResultBean);
                        } else {
                            result.onError("数据获取失败");
                        }
                    }

                    @Override
                    public void onResultError(String e) {
                        result.onError("网络超时，请检查网络状态");
                    }
                });
    }
}
