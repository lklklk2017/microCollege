package cn.cuit.microcollege.activity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.cuit.microcollege.R;
import cn.cuit.microcollege.base.BaseActivity;
import cn.cuit.microcollege.base.BasePresenter;
import cn.cuit.microcollege.widget.actionBar.CustomDefaultCenterActionBar;

public class NewDetailActivity extends BaseActivity implements CustomDefaultCenterActionBar.setBarListener {

    @BindView(R.id.activity_new_detail_actionbar)
    CustomDefaultCenterActionBar mBar;
    @BindView(R.id.activity_new_detail_webview)
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_detail);
        ButterKnife.bind(this);
    }

    @Override
    protected void preStatusForOther() {
        getIntent();
    }

    @Override
    public void initView() {
        //web view
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        webView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        //缩放
        webView.getSettings().setSupportZoom(true);
        webView.getSettings().setBuiltInZoomControls(true);

        //load
        Intent intent = getIntent();
        if (intent == null) {
            toast("没有获取到数据", true);
            return;
        }

        Bundle data = intent.getBundleExtra("data");

        if (data == null) {
            toast("没有获取到数据", true);
            return;
        }

        String url = data.getString("url");
        if (url != null) {
            webView.loadUrl(url);
        } else {
            toast("地址获取失败", true);
        }
    }

    @Override
    public BasePresenter initPresenter() {
        return null;
    }

    @Override
    public void bindListener() {
        mBar.setBarListener(this);
    }

    @Override
    public void onLeftBtnClick() {
        finish();

    }
}
