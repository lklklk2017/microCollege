package cn.cuit.microcollege.activity;

import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.WindowManager;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.cuit.microcollege.R;
import cn.cuit.microcollege.base.BaseActivity;
import cn.cuit.microcollege.base.BasePresenter;
import cn.cuit.microcollege.entity.HttpResultEntity.UserLoginResultBean;
import cn.cuit.microcollege.utils.RetrofitManager;
import cn.cuit.microcollege.utils.SPUtil;
import cn.cuit.microcollege.utils.apiService.CustomResultCallBack;
import cn.cuit.microcollege.widget.CustomCountDown;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class WelcomeActivity extends BaseActivity implements CustomCountDown.CountDownListener {

    @BindView(R.id.wel_count_down)
    CustomCountDown welCountDown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*沉浸式状态栏*/
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        setContentView(R.layout.activity_welcome);
        ButterKnife.bind(this);
    }

    @Override
    public void initView() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        welCountDown.start();
    }

    @Override
    public BasePresenter initPresenter() {
        return null;
    }

    @Override
    public void bindListener() {
        welCountDown.setListener(this);
    }

    @Override
    public void onEnd() {
//        toPage(LoginActivity.class);
//        finish();
        loginOrHomeTask();
    }

    @OnClick(R.id.wel_count_down)
    public void onClick() {
        welCountDown.stop();
        //login or home
        loginOrHomeTask();
    }

    private void loginOrHomeTask() {
        if (SPUtil.getmUserSp() == null) {
            //to login
            toPage(LoginActivity.class);
            finish();
        } else {
            String tel = SPUtil.getmUserSp().getString("tel", "");
            String password = SPUtil.getmUserSp().getString("password", "");
            if (TextUtils.isEmpty(tel) || TextUtils.isEmpty(password)) {
                //to login
                toPage(LoginActivity.class);
                finish();
            } else {
                Toast.makeText(this, "正在登陆..", Toast.LENGTH_SHORT).show();
                RetrofitManager.getMainApiService().login(tel, password)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new CustomResultCallBack<UserLoginResultBean>() {
                            @Override
                            public void onResultSuccess(UserLoginResultBean userLoginResultBean) {
                                int statusCode = userLoginResultBean.getStatusCode();
                                if (statusCode == 0) {
                                    // to login
                                    Toast.makeText(WelcomeActivity.this, userLoginResultBean.getMessage(), Toast.LENGTH_SHORT).show();
                                    toPage(LoginActivity.class);
                                    finish();
                                } else if (statusCode == 1) {
                                    //save and to home
                                    UserLoginResultBean.UserBean user = userLoginResultBean.getUser();
                                    SPUtil.saveUserBysp(user);
                                    Toast.makeText(WelcomeActivity.this, "登陆成功", Toast.LENGTH_SHORT).show();
                                    toPage(HomeActivity.class);
                                    finish();
                                }
                            }

                            @Override
                            public void onResultError(String e) {
                                toPage(LoginActivity.class);
                                Toast.makeText(WelcomeActivity.this, e, Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        });
            }
        }
    }


}
