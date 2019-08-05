package cn.cuit.microcollege.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.cuit.microcollege.R;
import cn.cuit.microcollege.base.BaseActivity;
import cn.cuit.microcollege.contract.FeedBackActivityContract;
import cn.cuit.microcollege.presenter.FeedBackActivityPresenter;
import cn.cuit.microcollege.utils.SPUtil;
import cn.cuit.microcollege.widget.actionBar.CustomDefaultActionBar;

public class FeedBackActivity extends BaseActivity<FeedBackActivityPresenter> implements FeedBackActivityContract.View, CustomDefaultActionBar.setBarListener {

    @BindView(R.id.feedback_actionBar)
    CustomDefaultActionBar mBar;
    @BindView(R.id.feedback_item_one_radioBtn)
    RadioButton mOneRb;
    @BindView(R.id.feedback_item_two_radioBtn)
    RadioButton mTwoRb;
    @BindView(R.id.feedback_item_three_radioBtn)
    RadioButton mThreeRb;
    @BindView(R.id.feedback_item_four_radioBtn)
    RadioButton mFourRb;
    @BindView(R.id.feedback_rgp)
    RadioGroup mRgp;
    @BindView(R.id.feedback_content_tv)
    EditText mContentEt;
    @BindView(R.id.feedback_submit_tv)
    TextView mSubmitTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back);
        ButterKnife.bind(this);
    }

    @Override
    public void initView() {
        mBar.setBarListener(this);
    }

    @Override
    public FeedBackActivityPresenter initPresenter() {
        return new FeedBackActivityPresenter(this);
    }

    @Override
    public void bindListener() {

    }

    @OnClick(R.id.feedback_submit_tv)
    public void onClick() {
        String type = "";
        //1.init type
        int checkedRadioButtonId = mRgp.getCheckedRadioButtonId();
        switch (checkedRadioButtonId) {
            case R.id.feedback_item_one_radioBtn:
                type = "#建议";
                break;
            case R.id.feedback_item_two_radioBtn:
                type = "#故障";
                break;
            case R.id.feedback_item_three_radioBtn:
                type = "#账号问题";
                break;
            case R.id.feedback_item_four_radioBtn:
                type = "#其他";
                break;
        }

        //2.content
        String content = mContentEt.getText().toString();
        if (TextUtils.isEmpty(content)) {
            toast("请填写关于" + type + "的内容", false);
            return;
        }
        String submitContent = type + " " + content;

        //TODO开始请求
        int userId = SPUtil.getmUserSp().getInt("userId", -1);
        if (userId == -1) {
            return;
        }
        showDialog(true);
        getPresent().getM().addFeedback(userId, submitContent, new FeedBackActivityContract.Model.AddFeedBackHttpResult() {
            @Override
            public void success() {
                showDialog(false);
                toast("提交成功", false);
                finish();
            }

            @Override
            public void error(String error) {
                showDialog(false);
                toast(error, false);
            }
        });
    }

    @Override
    public void onBack() {
        finish();
    }
}
