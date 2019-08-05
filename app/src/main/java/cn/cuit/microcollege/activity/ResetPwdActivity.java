package cn.cuit.microcollege.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.cuit.microcollege.R;
import cn.cuit.microcollege.base.BaseActivity;
import cn.cuit.microcollege.base.BasePresenter;
import cn.cuit.microcollege.entity.HttpResultEntity.UserReSetPwdResultBean;
import cn.cuit.microcollege.utils.RetrofitManager;
import cn.cuit.microcollege.utils.SPUtil;
import cn.cuit.microcollege.utils.apiService.CustomResultCallBack;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ResetPwdActivity extends BaseActivity implements TextWatcher {

    @BindView(R.id.back)
    ImageButton mBackImgBtn;
    @BindView(R.id.reset_password_et)
    EditText mPwdEt;
    @BindView(R.id.reset_eye_iv)
    ImageView mEyeIv;
    @BindView(R.id.reset_finish_tv)
    TextView mFinishTv;


    private boolean open = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_pwd);
        ButterKnife.bind(this);

        mPwdEt.addTextChangedListener(this);
    }

    @Override
    public void initView() {

    }

    @Override
    public BasePresenter initPresenter() {
        return null;
    }

    @Override
    public void bindListener() {

    }

    @OnClick({R.id.back, R.id.reset_eye_iv, R.id.reset_finish_tv})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.reset_eye_iv:
                if (open) {//当前是明文
                    mPwdEt.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    mEyeIv.setImageResource(R.drawable.eye_close);
                    open = false;
                } else {//当前是密文
                    mPwdEt.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    mEyeIv.setImageResource(R.drawable.eye_open);
                    open = true;
                }
                break;
            case R.id.reset_finish_tv:
                resetPwdTask();
                break;
        }
    }

    private void resetPwdTask() {

        hideInput();
        String tel = SPUtil.getmParamSp().getString("tel", "");
        String pwd = mPwdEt.getText().toString().trim();
        if (tel == null || tel.equals("")) {
            toast("出现异常，本地文件丢失,请重新申请验证码并修改密码", true);
            return;
        }

        if (pwd == null || pwd.length() == 0) {
            toast("密码不能为空", false);
            return;
        }

        RetrofitManager.getMainApiService().resetPwd(tel, pwd)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CustomResultCallBack<UserReSetPwdResultBean>() {
                    @Override
                    public void onResultSuccess(UserReSetPwdResultBean userReSetPwdResultBean) {
                        toast("更改密码成功！", "前往登录", 150000, new ToastCallBack() {
                            @Override
                            public void onEvent() {
                                toPage(LoginActivity.class);
                                finish();
                            }
                        });
                    }

                    @Override
                    public void onResultError(String e) {

                    }
                });

    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        boolean empty = TextUtils.isEmpty(s);
        if (empty) {
            setFinishDisable();
        } else {
            setFinishEnable();
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    public void setFinishEnable() {
        mFinishTv.setEnabled(true);
    }

    public void setFinishDisable() {
        mFinishTv.setEnabled(false);
    }
}
