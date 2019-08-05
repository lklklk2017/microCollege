package cn.cuit.microcollege.widget;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.cuit.microcollege.R;

/**
 * @Author: Created by Rod Eden
 * @Date: 2019/4/20
 * @Descirption:
 */
public class CustomVertifyCodeView extends RelativeLayout implements TextWatcher {
    private static final String TAG = "来自自定义控件：CustomVertifyCodeView";
    private Context context;
    private int defTimeDuration = 60;//设置默认间隔时间
    @BindView(R.id.custom_telinput_telContent_et)
    EditText mTelContentET;
    @BindView(R.id.custom_text_getcode_iv)
    TextView mGetCodeTv;

    private CustomVertifyCodeView.TextWatcher mTextWatcher;

    public void setSendListener(onSendSMS sendListener) {
        this.sendListener = sendListener;
    }

    private CustomVertifyCodeView.onSendSMS sendListener;
    private boolean isSending = false;
    private Timer timer = null;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            int second = msg.arg1;
            if (second == 0) {
                timer.cancel();
                timer = null;
                defTimeDuration = 60;
                mGetCodeTv.setText("重新获取");
                isSending = false;
                setEnable();
                return;
            }
            mGetCodeTv.setText(second + "s");
        }
    };

    public CustomVertifyCodeView(Context context) {
        this(context, null);
    }

    public CustomVertifyCodeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public void addTextChangedListener(CustomVertifyCodeView.TextWatcher listener) {
        mTextWatcher = listener;
    }

    private void init(Context context, AttributeSet attrs) {
        this.context = context;
        LayoutInflater.from(context).inflate(R.layout.custom_vertifycodeview, this);
        ButterKnife.bind(this);

        mTelContentET.addTextChangedListener(this);
    }

    @OnClick(R.id.custom_text_getcode_iv)
    public void onClick() {
        // TODO 回调中 1.请求获取code  2.发送code到短信 提示发送完毕即可
        if (sendListener != null) {
            sendListener.onSend();
        }
    }

    /**
     * 开始计时
     */
    public void startSend(){
        timer = new Timer("短信发送间隔");
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Message message = Message.obtain();
                message.arg1 = defTimeDuration--;
                handler.sendMessage(message);
//                setDisable();
                isSending = true;
            }
        }, 0, 1000);
    }

    /**
     * 获取tel
     *
     * @return
     */
    public String getTelContent() {

        Editable text = mTelContentET.getText();
        boolean empty = TextUtils.isEmpty(text);
        if (empty) {
            return null;
        }
        return text.toString();
    }


    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        if (mTextWatcher != null) {
            mTextWatcher.beforeTextChanged(s, start, count, after);
        }
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (s.toString().equals("")) {
            setDisable();
        } else {
            setEnable();
        }
        if (mTextWatcher != null) {
            mTextWatcher.onTextChanged(s, start, before, count);
        }
    }

    @Override
    public void afterTextChanged(Editable s) {
        if (mTextWatcher != null) {
            mTextWatcher.afterTextChanged(s);
        }
    }

    public void setEnable() {
        if (!isSending) {
            mGetCodeTv.setEnabled(true);
            mGetCodeTv.setTextColor(getResources().getColor(R.color.colorLightPrimary));
        }

    }

    public void setDisable() {
        if (!isSending) {
            mGetCodeTv.setEnabled(false);
            mGetCodeTv.setTextColor(getResources().getColor(R.color.regist_input_border_color));
        }
    }

    public interface TextWatcher {
        void beforeTextChanged(CharSequence s, int start, int count, int after);

        void onTextChanged(CharSequence s, int start, int before, int count);

        void afterTextChanged(Editable s);
    }

    public interface onSendSMS {
        void onSend();
    }
}
