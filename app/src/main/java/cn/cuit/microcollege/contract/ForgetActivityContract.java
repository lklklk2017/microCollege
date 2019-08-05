package cn.cuit.microcollege.contract;

import android.widget.EditText;

import cn.cuit.microcollege.base.BaseModel;
import cn.cuit.microcollege.entity.HttpResultEntity.SMSResultBean;
import cn.cuit.microcollege.widget.CustomVertifyCodeView;

/**
 * @Author: Created by Rod Eden
 * @Date: 2019/4/22
 * @Descirption:
 */
public interface ForgetActivityContract {
    interface Model extends BaseModel {

        void sendMessage(String tel, Integer Id, String value, String key, SMSHttpResult result);

        void checkTel(String tel, CheckTelHttpResult result);

        interface SMSHttpResult {
            void onSuccess(SMSResultBean.ResultBean smsResultBean);

            void onError(String errorBadNet);
        }

        interface CheckTelHttpResult {
            void onSuccess();

            void onError(String errorBadNet);
        }
    }

    interface View {
        CustomVertifyCodeView getTelView();

        EditText getCodeView();
    }

    interface Presenter {

        void checkCodeIsEqualsLocal();

        void getCodeTask();
    }
}
