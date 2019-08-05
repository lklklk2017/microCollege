package cn.cuit.microcollege.activity.about;

import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.cuit.microcollege.R;
import cn.cuit.microcollege.base.BaseActivity;
import cn.cuit.microcollege.contract.AboutActivityContract;
import cn.cuit.microcollege.presenter.AboutActivityPresenter;
import cn.cuit.microcollege.widget.actionBar.CustomDefaultActionBar;

public class AboutActivity extends BaseActivity<AboutActivityPresenter> implements AboutActivityContract.View, CustomDefaultActionBar.setBarListener {

    @BindView(R.id.about_actionBar)
    CustomDefaultActionBar mBar;
    @BindView(R.id.about_logo)
    ImageView mLogo;
    @BindView(R.id.about_update_cardv)
    CardView mUpdateCardv;
    @BindView(R.id.about_agreement_cardv)
    CardView mAgreementCardv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        ButterKnife.bind(this);
    }

    @Override
    public void initView() {
    }

    @Override
    public AboutActivityPresenter initPresenter() {
        return new AboutActivityPresenter(this);
    }

    @Override
    public void bindListener() {
        mBar.setBarListener(this);
    }

    @OnClick({R.id.about_update_cardv, R.id.about_agreement_cardv})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.about_update_cardv:
                toast("当前已是最新版本", false);
                break;
            case R.id.about_agreement_cardv:
                toPage(AgreementActivity.class);
                break;
        }
    }

    @Override
    public void onBack() {
        finish();
    }
}
