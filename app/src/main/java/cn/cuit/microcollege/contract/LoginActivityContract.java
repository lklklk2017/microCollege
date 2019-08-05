package cn.cuit.microcollege.contract;

import android.widget.EditText;

import cn.cuit.microcollege.base.BaseModel;
import cn.cuit.microcollege.entity.HttpResultEntity.UserLoginResultBean;
import cn.cuit.microcollege.widget.CustomTelInputView;

/**
 * @Author: Created by Rod Eden
 * @Date: 2019/4/20
 * @Descirption:
 */
public interface LoginActivityContract {
    interface Model extends BaseModel {
        void login(String tel, String password, LoginResult result);

        interface LoginResult {
            void onSuccess(UserLoginResultBean.UserBean user);

            void onError(String message);
        }
    }

    interface View {
        CustomTelInputView getTelView();

        EditText getPasswordView();

    }

    interface Presenter {
        void loginTask();
    }
}
