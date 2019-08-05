package cn.cuit.microcollege.presenter;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.UUID;

import cn.cuit.microcollege.activity.ForgetActivity;
import cn.cuit.microcollege.activity.ResetPwdActivity;
import cn.cuit.microcollege.base.BasePresenter;
import cn.cuit.microcollege.contract.ForgetActivityContract;
import cn.cuit.microcollege.entity.HttpResultEntity.SMSResultBean;
import cn.cuit.microcollege.model.ForgetActivityModel;
import cn.cuit.microcollege.utils.Config;
import cn.cuit.microcollege.utils.SPUtil;

/**
 * @Author: Created by Rod Eden
 * @Date: 2019/4/22
 * @Descirption:
 */
public class ForgetActivityPresenter extends BasePresenter<ForgetActivity, ForgetActivityModel> implements ForgetActivityContract.Presenter {
    public ForgetActivityPresenter(ForgetActivity view) {
        super(view);
    }

    @Override
    public void initModel() {
        bindModel(new ForgetActivityModel());
    }

    @Override
    public void checkCodeIsEqualsLocal() {

        getV().hideInput();
        String code = getV().getCodeView().getText().toString().trim();

        if (code == null || code.length() == 0) {
            getV().toast("验证码不能为空", false);
            return;
        }

        String code_local = SPUtil.getmParamSp().getString("code", "");
        if (code_local.equals(code) && !code_local.equals("")) {
            //清除本地验证码
            SPUtil.getmParamSpEt().remove("code").commit();
            //可以开始下一步  跳转修改密码
            getV().toPage(ResetPwdActivity.class);
        } else {
            getV().toast("验证码不正确，请重试", false);
        }
    }

    @Override
    public void getCodeTask() {

        getV().hideInput();
        final String telContent = getV().getTelView().getTelContent();
        if (telContent == null || telContent.length() == 0) {
            getV().toast("电话号码不能为空", false);
            return;
        }

        getV().showDialog(true);
        //checkTel is regist
        getM().checkTel(telContent, new ForgetActivityContract.Model.CheckTelHttpResult() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onSuccess() {
                getV().showDialog(false);
                //存在

                //生成本地uuid
                final String code = UUID.randomUUID().toString().substring(0, 4);
                SPUtil.getmParamSpEt().clear().commit();
                SPUtil.getmParamSpEt().putString("code", code).commit();//存验证码
                SPUtil.getmParamSpEt().putString("tel", telContent).commit();//存手机号码

                //发送验证码
                //1.开始计时
                getV().getTelView().startSend();

                //2.发送
                //2.1封装数据
                /**
                 * http://v.juhe.cn/sms/send?mobile=15008476607&tpl_id=156331&tpl_value=%23code%23%3d431515&key=cff0b86df5017d1f62621f5574418f5f&d
                 */
                String value = "#code#=" + code;
                String finalVal = null;
                try {
                    finalVal = URLEncoder.encode(value, "utf-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                getM().sendMessage(telContent, Config.TEMP_ID, finalVal, Config.APPKEY_JUHE_SMS, new ForgetActivityContract.Model.SMSHttpResult() {
                    @Override
                    public void onSuccess(SMSResultBean.ResultBean smsResultBean) {
                        //发送成功
//                        getV().toast("已发送：code:" + code, true);
                    }

                    @Override
                    public void onError(String errorBadNet) {
                        getV().toast("发送失败，请稍后重试", true);
                    }
                });
            }

            @Override
            public void onError(String errorBadNet) {
                getV().showDialog(false);
                getV().toast(errorBadNet, true);
            }
        });


    }
}
