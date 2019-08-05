package cn.cuit.microcollege.widget;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.cuit.microcollege.R;

/**
 * Created by Administrator on 2018/6/15.
 * function：
 * 圆形倒转计时器
 */

public class CustomCountDown extends LinearLayout {

    private static final String TAG = "CustomCountDown";
    private int defaultCount = 5;
    private Timer mTimer;
    private boolean isSending = false;

    public void setListener(CountDownListener listener) {
        this.listener = listener;
    }

    private CountDownListener listener;
    @BindView(R.id.custom_count_down_num)
    TextView mNum;

    private Handler mHander = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            int count = msg.arg1;
            switch (msg.what) {
                case 0: //结束

                    break;

                case 1://开始
                    mNum.setText(count + "");
                    if (count == 0) {
                        mTimer.cancel();
                        mTimer = null;
                        defaultCount = 5;
                        isSending = false;
                        if (listener != null) {
                            listener.onEnd();
                        }
                    }
                    break;
            }
        }
    };


    public CustomCountDown(Context context) {
        this(context, null);
    }

    public CustomCountDown(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.custom_count_down, this, true);
        ButterKnife.bind(this);
        mNum.setText(defaultCount + "");
    }

    public void start() {
        if (!isSending) {
            mTimer = new Timer();
            mTimer.schedule(new TimerTask() {
                @Override
                public void run() {
                    Message message = mHander.obtainMessage();
                    message.arg1 = defaultCount--;
                    message.what = 1;
                    mHander.sendMessage(message);
                    isSending = true;
                }
            }, 0, 1000);
        }
    }

    public void stop() {
        mTimer.cancel();
        defaultCount = 5;
        isSending = false;
    }

    public interface CountDownListener {
        void onEnd();
    }
}
