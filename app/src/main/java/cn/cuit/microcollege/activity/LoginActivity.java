package cn.cuit.microcollege.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.cuit.microcollege.R;
import cn.cuit.microcollege.base.BaseActivity;
import cn.cuit.microcollege.contract.LoginActivityContract;
import cn.cuit.microcollege.presenter.LoginActivityPresenter;
import cn.cuit.microcollege.widget.CustomTelInputView;

public class LoginActivity extends BaseActivity<LoginActivityPresenter> implements LoginActivityContract.View, CustomTelInputView.TextWatcher, TextWatcher {

    @BindView(R.id.login_telContent_csv)
    CustomTelInputView mTelContentCsv;
    @BindView(R.id.login_password_et)
    EditText mPwdEt;
    @BindView(R.id.login_login_tv)
    TextView mLoginTv;
    @BindView(R.id.login_forget_tv)
    TextView mForgetTv;
    @BindView(R.id.login_eye_iv)
    ImageView mEyeIv;
    @BindView(R.id.login_regist_tv)
    TextView mRegistTv;
    private boolean open = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        mPresenter.setmContext(this);
    }

    @Override
    public void initView() {
    }

    @Override
    public LoginActivityPresenter initPresenter() {
        return new LoginActivityPresenter(this);
    }

    @Override
    public void bindListener() {
        mTelContentCsv.addTextChangedListener(this);
        mPwdEt.addTextChangedListener(this);
    }

    @OnClick({R.id.login_telContent_csv, R.id.login_password_et, R.id.login_login_tv, R.id.login_forget_tv, R.id.login_eye_iv, R.id.login_regist_tv})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login_telContent_csv://tel
                break;
            case R.id.login_password_et://password
                break;
            case R.id.login_login_tv://login
                getPresent().loginTask();
                break;
            case R.id.login_forget_tv://forget
                toPage(ForgetActivity.class);
                break;
            case R.id.login_regist_tv://regist
                toPage(RegistActivity.class);
                break;
            case R.id.login_eye_iv://show password
                if (open) {
                    //不可见
                    mPwdEt.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    mEyeIv.setImageResource(R.drawable.eye_close);
                    open = false;
                } else {
                    //可见
                    mPwdEt.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    mEyeIv.setImageResource(R.drawable.eye_open);
                    open = true;
                }
                break;
        }
    }

    /**************************************tel的改变监听 start**********************************************/
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (TextUtils.isEmpty(mTelContentCsv.getTelContent()) || TextUtils.isEmpty(mPwdEt.getText())) {
            mLoginTv.setEnabled(false);
        } else {
            mLoginTv.setEnabled(true);
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    /**************************************tel的改变监听 end**********************************************/

    @Override
    public CustomTelInputView getTelView() {
        return mTelContentCsv;
    }

    @Override
    public EditText getPasswordView() {
        return mPwdEt;
    }

}
