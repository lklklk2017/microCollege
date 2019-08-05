package cn.cuit.microcollege.presenter;

import android.text.Editable;

import cn.cuit.microcollege.activity.HomeActivity;
import cn.cuit.microcollege.activity.LoginActivity;
import cn.cuit.microcollege.base.BasePresenter;
import cn.cuit.microcollege.contract.LoginActivityContract;
import cn.cuit.microcollege.entity.HttpResultEntity.UserLoginResultBean;
import cn.cuit.microcollege.model.LoginActivityModel;
import cn.cuit.microcollege.utils.SPUtil;

/**
 * @Author: Created by Rod Eden
 * @Date: 2019/4/20
 * @Descirption:
 */
public class LoginActivityPresenter extends BasePresenter<LoginActivity, LoginActivityModel> implements LoginActivityContract.Presenter {

    public LoginActivityPresenter(LoginActivity view) {
        super(view);
    }

    @Override
    public void initModel() {
        bindModel(new LoginActivityModel());
    }

    @Override
    public void loginTask() {
        Editable pwd = getV().getPasswordView().getText();
        String telContent = getV().getTelView().getTelContent();

        if (pwd == null || telContent == null) {
            getV().toast("电话号码或者密码不能为空", false);
            return;
        }

        getV().showDialog(true);
        getM().login(telContent, pwd.toString(), new LoginActivityContract.Model.LoginResult() {
            @Override
            public void onSuccess(UserLoginResultBean.UserBean user) {
                getV().showDialog(false);

                //存入本地
                SPUtil.saveUserBysp(user);

                //跳转
                getV().toPage(HomeActivity.class);
                getV().finish();
            }

            @Override
            public void onError(String message) {
                getV().showDialog(false);
                getV().toast(message, true);
            }
        });
    }
}
