package cn.cuit.microcollege.presenter;

import cn.cuit.microcollege.activity.HomeActivity;
import cn.cuit.microcollege.base.BasePresenter;
import cn.cuit.microcollege.contract.HomeActivityContract;
import cn.cuit.microcollege.model.HomeActivityModel;

/**
 * @Author: Created by Rod Eden
 * @Date: 2019/4/14
 * @Descirption:
 */
public class HomeActivityPresenter extends BasePresenter<HomeActivity, HomeActivityModel> implements HomeActivityContract.Presenter {


    public HomeActivityPresenter(HomeActivity view) {
        super(view);
    }

    @Override
    public void initModel() {

        bindModel(new HomeActivityModel());
    }

}
