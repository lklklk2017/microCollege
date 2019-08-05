package cn.cuit.microcollege.presenter;

import cn.cuit.microcollege.activity.about.AboutActivity;
import cn.cuit.microcollege.base.BasePresenter;
import cn.cuit.microcollege.contract.AboutActivityContract;
import cn.cuit.microcollege.model.AboutActivityModel;

/**
 * @Author: Created by Rod Eden
 * @Date: 2019/5/3
 * @Descirption:
 */
public class AboutActivityPresenter extends BasePresenter<AboutActivity, AboutActivityModel> implements AboutActivityContract.Presenter {

    public AboutActivityPresenter(AboutActivity view) {
        super(view);
    }

    @Override
    public void initModel() {
        bindModel(new AboutActivityModel());
    }
}
