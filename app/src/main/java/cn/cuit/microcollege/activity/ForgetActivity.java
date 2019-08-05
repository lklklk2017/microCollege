package cn.cuit.microcollege.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import cn.cuit.microcollege.R;
import cn.cuit.microcollege.base.BaseActivity;
import cn.cuit.microcollege.contract.ForgetActivityContract;
import cn.cuit.microcollege.presenter.ForgetActivityPresenter;
import cn.cuit.microcollege.widget.CustomVertifyCodeView;

public class ForgetActivity extends BaseActivity<ForgetActivityPresenter> implements ForgetActivityContract.View, CustomVertifyCodeView.TextWatcher, TextWatcher, CustomVertifyCodeView.onSendSMS {

    @BindView(R.id.back)
    ImageButton mBackImgBtn;
    @BindView(R.id.forget_telContent_csv)
    CustomVertifyCodeView mTelContentCsv;
    @BindView(R.id.forget_vertifycode_et)
    EditText mForgetVertifyCodeEt;
    @BindView(R.id.forget_next_tv)
    TextView mNextTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget);
    }

    @Override
    public void initView() {

    }

    @Override
    public ForgetActivityPresenter initPresenter() {
        return new ForgetActivityPresenter(this);
    }

    @Override
    public void bindListener() {
        mTelContentCsv.addTextChangedListener(this);
        mForgetVertifyCodeEt.addTextChangedListener(this);
        mTelContentCsv.setSendListener(this);
    }

    @OnClick({R.id.back, R.id.forget_telContent_csv, R.id.forget_vertifycode_et, R.id.forget_next_tv})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.forget_telContent_csv:

                break;
            case R.id.forget_vertifycode_et:

                break;
            case R.id.forget_next_tv:
                getPresent().checkCodeIsEqualsLocal();
                break;
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (TextUtils.isEmpty(mTelContentCsv.getTelContent()) || TextUtils.isEmpty(mForgetVertifyCodeEt.getText())) {
            mNextTv.setEnabled(false);
        } else {
            mNextTv.setEnabled(true);
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    @Override
    public CustomVertifyCodeView getTelView() {
        return mTelContentCsv;
    }

    @Override
    public EditText getCodeView() {
        return mForgetVertifyCodeEt;
    }

    //开始发送
    @Override
    public void onSend() {
        //1.生成本地code
        getPresent().getCodeTask();
    }
}
