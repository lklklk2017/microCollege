package cn.cuit.microcollege.contract;

import android.widget.EditText;
import android.widget.TextView;

import cn.cuit.microcollege.base.BaseModel;
import cn.cuit.microcollege.widget.CustomTelInputView;

/**
 * @Author: Created by Rod Eden
 * @Date: 2019/4/20
 * @Descirption:
 */
public interface RegistActivityContract {
    interface Model extends BaseModel {
        void regist(String tel, String pwd, String email, RegistHttpResult result);

        interface RegistHttpResult {
            void onSuccess();

            void onError(String errorBadNet);
        }
    }

    interface View {
        CustomTelInputView getTelView();

        EditText getPwdView();

        EditText getEmailView();

        TextView getRegistView();

    }

    interface Presenter {

        void registTask();
    }
}
