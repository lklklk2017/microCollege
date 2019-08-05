package cn.cuit.microcollege.activity.about;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.cuit.microcollege.R;
import cn.cuit.microcollege.base.BaseActivity;
import cn.cuit.microcollege.base.BasePresenter;
import cn.cuit.microcollege.widget.actionBar.CustomDefaultActionBar;

public class AgreementActivity extends BaseActivity implements CustomDefaultActionBar.setBarListener {

    @BindView(R.id.agreement_actionBar)
    CustomDefaultActionBar mBar;
    @BindView(R.id.agreement_content_tv)
    TextView mContentTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agreement);
        ButterKnife.bind(this);
    }

    @Override
    public void initView() {

//        readAgreementFile();
    }

    private void readAgreementFile() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    StringBuffer content = new StringBuffer();
                    BufferedReader br = new BufferedReader(new InputStreamReader(getResources().openRawResource(R.raw.agreement), "GBK"));

                    String s = "";
                    while ((s = br.readLine()) != null) {
                        content.append(s);
                    }
                    Message message = handler.obtainMessage();
                    message.obj = content;
                    message.what = 0;
                    handler.sendMessage(message);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public BasePresenter initPresenter() {
        return null;
    }

    @Override
    public void bindListener() {
        mBar.setBarListener(this);
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    String content = (String) msg.obj;
                    if (content != null) {
                        mContentTv.setText(content);
                    }
                    break;
            }
        }
    };

    @Override
    public void onBack() {
        finish();
    }
}
