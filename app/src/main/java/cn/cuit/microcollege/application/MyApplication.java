package cn.cuit.microcollege.application;

import android.app.Application;
import android.content.Context;


/**
 * @Author: Created by Rod Eden
 * @Date: 2019/4/14
 * @Descirption: aplication
 */
public class MyApplication extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this.getApplicationContext();
        //mob
//        MobSDK.init(this);

    }


    public static Context getMyContext() {
        return context;
    }

}
