package cn.cuit.microcollege.utils.apiService;

import cn.cuit.microcollege.utils.Config;
import cn.cuit.microcollege.utils.LogU;
import rx.Observer;

/**
 * @Author: Created by Rod Eden
 * @Date: 2019/5/4
 * @Descirption: 自定义Rx结果返回集
 */
public abstract class CustomResultCallBack<T> implements Observer<T> {

    public static final String TAG = "CustomResultCallBack";

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
        LogU.i("网路请求失败：错误：" + e.getMessage(), getClass().getName());
        onResultError(Config.ERROR_BAD_NET);
    }

    @Override
    public void onNext(T t) {
        LogU.i("网络请求成功，当前线程：" + Thread.currentThread(), TAG);
        onResultSuccess(t);
    }

    public abstract void onResultSuccess(T t);

    public abstract void onResultError(String e);
}
