package cn.cuit.microcollege.activity;

import android.os.Bundle;
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
import cn.cuit.microcollege.contract.RegistActivityContract;
import cn.cuit.microcollege.presenter.RegistActivityPresenter;
import cn.cuit.microcollege.widget.CustomTelInputView;

public class RegistActivity extends BaseActivity<RegistActivityPresenter>
        implements RegistActivityContract.View {

    @BindView(R.id.back)
    ImageButton mBackImgBtn;
    @BindView(R.id.regist_telContent_csv)
    CustomTelInputView mTelContentCsv;
    @BindView(R.id.regist_regist_tv)
    TextView mRegistTv;
    @BindView(R.id.regist_password_tv)
    TextView mPwdToLoginTv;
    @BindView(R.id.regist_password_et)
    EditText mPasswordEt;
    @BindView(R.id.regist_password_et_eye_iv)
    ImageView mPasswordEtEyeIv;
    @BindView(R.id.regist_email_et)
    EditText mEmailEt;
    private boolean open = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);
        ButterKnife.bind(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public void initView() {
    }

    @Override
    public RegistActivityPresenter initPresenter() {
        return new RegistActivityPresenter(this);
    }

    @Override
    public void bindListener() {
    }

    @OnClick({R.id.back, R.id.regist_regist_tv, R.id.regist_password_tv, R.id.regist_password_et_eye_iv})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.regist_regist_tv://注册
                getPresent().registTask();
                break;
            case R.id.regist_password_tv:
                finish();
                break;

            case R.id.regist_password_et_eye_iv:
                if (open) {
                    //不可见
                    mPasswordEt.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    mPasswordEtEyeIv.setImageResource(R.drawable.eye_close);
                    open = false;
                } else {
                    //可见
                    mPasswordEt.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    mPasswordEtEyeIv.setImageResource(R.drawable.eye_open);
                    open = true;
                }
                break;
        }
    }

    @Override
    public CustomTelInputView getTelView() {
        return mTelContentCsv;
    }

    @Override
    public EditText getPwdView() {

        return mPasswordEt;
    }

    @Override
    public EditText getEmailView() {
        return mEmailEt;
    }

    @Override
    public TextView getRegistView() {
        return mRegistTv;
    }

}
