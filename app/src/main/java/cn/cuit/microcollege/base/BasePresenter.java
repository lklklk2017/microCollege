package cn.cuit.microcollege.base;

import android.content.Context;

import cn.cuit.microcollege.utils.LogU;

/**
 * @Author: Created by Rod Eden
 * @Date: 2019/4/15
 * @Descirption: P基类
 */
public abstract class BasePresenter<V extends BaseView, M extends BaseModel> {

    protected V v;//需要持有V的引用
    protected M m;//需要持有M的引用
    private static final String TAG = "BasePresenter";
    private Context mContext;

    public Context getmContext() {
        if (mContext == null) {
            throw new RuntimeException("must invoke setmContext first");
        }
        return mContext;
    }

    public void setmContext(Context mContext) {
        this.mContext = mContext;
    }

    public BasePresenter(V view) {
        bindView(view);//绑定view
        initModel();
    }

    /**
     * 需要在子类重写该方法
     */
    public abstract void initModel();

    /**
     * 获取View
     *
     * @return
     */
    public V getV() {
        if (isViewAlive()) {
            return v;
        } else {
//                throw new RuntimeException(TAG + " View 还没有关联");
            LogU.i("danger! baseView 已经被销毁", TAG);
            return null;
        }
    }

    /**
     * 获取Model
     *
     * @return
     */
    public M getM() {
        if (isModelAlive()) {
            return m;
        } else {
            throw new RuntimeException(TAG + " Model 还没有关联 请检查initModel()是否被重写");
        }
    }

    /**
     * 生命周期 销毁
     */
    public void onDestory() {
        LogU.d("BasePresent执行了onDestory()", TAG);
        unbindView();
        unbindModel();
    }

    /**
     * 绑定view
     */
    public void bindView(V view) {
        if (null != view) {
            this.v = view;
        }
    }

    /**
     * 解绑view
     */
    public void unbindView() {
        if (isViewAlive()) {
            this.v = null;
        }
    }

    /**
     * 判断view 是否存在
     *
     * @return
     */
    public boolean isViewAlive() {

        return v != null;
    }

    /***********************************model******************************************/
    /**
     * 绑定model
     *
     * @param model
     */
    public void bindModel(M model) {
        if (null != model) {
            this.m = model;
        }
    }

    /**
     * 解除model
     */
    public void unbindModel() {
        if (isModelAlive()) {
            this.m = null;
        }
    }

    /**
     * 判断 model 是否存在
     *
     * @return
     */
    public boolean isModelAlive() {
        return this.m != null;
    }
}
