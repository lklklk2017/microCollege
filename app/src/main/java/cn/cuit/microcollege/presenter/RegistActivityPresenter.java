package cn.cuit.microcollege.presenter;

import android.text.Editable;

import cn.cuit.microcollege.activity.LoginActivity;
import cn.cuit.microcollege.activity.RegistActivity;
import cn.cuit.microcollege.base.BasePresenter;
import cn.cuit.microcollege.contract.RegistActivityContract;
import cn.cuit.microcollege.model.RegistActivityModel;
import cn.cuit.microcollege.utils.TransFormUtil;

/**
 * @Author: Created by Rod Eden
 * @Date: 2019/4/20
 * @Descirption:
 */
public class RegistActivityPresenter extends BasePresenter<RegistActivity, RegistActivityModel> implements RegistActivityContract.Presenter {

    public RegistActivityPresenter(RegistActivity view) {
        super(view);
    }

    @Override
    public void initModel() {
        bindModel(new RegistActivityModel());
    }

    @Override
    public void registTask() {
        getV().hideInput();
        String telContent = getV().getTelView().getTelContent();
        Editable pwd = getV().getPwdView().getText();
        Editable email = getV().getEmailView().getText();

        if (telContent == null || telContent.length() == 0) {
            getV().toast("电话号码不能为空", false);
            return;
        }
        if (pwd == null || pwd.length() == 0) {
            getV().toast("密码不能为空", false);
            return;
        }
        if (email == null || email.length() == 0) {
            getV().toast("邮箱不能为空", false);
            return;
        }
        if (!TransFormUtil.checkIsEmail(email.toString())) {
            getV().toast("邮箱格式不正确", true);
            return;
        }

        getV().showDialog(true);
        getM().regist(telContent, pwd.toString(), email.toString(),
                new RegistActivityContract.Model.RegistHttpResult() {
                    @Override
                    public void onSuccess() {
                        getV().showDialog(false);
                        getV().toast("注册成功", false);
                        getV().toPage(LoginActivity.class);
                        getV().finish();
                    }

                    @Override
                    public void onError(String errorBadNet) {
                        getV().showDialog(false);
                        getV().toast(errorBadNet, true);
                    }
                });
    }
}
