package cn.cuit.microcollege.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @Author: Rod Eden
 * @Date: 2019/5/4
 * @Description: Created by Administrator on 2018/3/20.
 * 网络管理器(RxJava+Retrofit)
 * 单例模式
 */
public class RetrofitManager {

    private static Retrofit mRetrofit = null;
    private static Retrofit mMainRetrofit = null;
    private static Retrofit mAminRetrofit = null;
    private static Retrofit mEmsRetrofit = null;
    private static final OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(50, TimeUnit.SECONDS)
            .readTimeout(50, TimeUnit.SECONDS)
            .writeTimeout(50, TimeUnit.SECONDS)
            .build();

    private RetrofitManager() throws Exception {
        throw new Exception("can not invoke it by this method");
    }

    /**
     * 获取retrofit实体类型
     * <p>
     * 新闻
     *
     * @return
     */
    private static Retrofit getNewInstence() {

        if (mRetrofit == null) {
            /*加锁原因：防止在多线程的情况下进行多次实例化*/
            synchronized (RetrofitManager.class) {
                if (mRetrofit == null) {
                    return mRetrofit = new Retrofit.Builder()
                            .client(client)
                            /*指定根路径*/
                            .baseUrl(NewAPIservice.HOME_PATH)
                            /*指定实体解析类型*/
                            .addConverterFactory(GsonConverterFactory.create())
                            /*指定响应模式为observable类,支持RxJava*/
                            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                            .build();
                }
            }
        }
        return mRetrofit;
    }

    /**
     * 获取retrofit实体类型
     * <p>
     * admin
     *
     * @return
     */
    private static Retrofit getAdminInstence() {

        if (mAminRetrofit == null) {
            /*加锁原因：防止在多线程的情况下进行多次实例化*/
            synchronized (RetrofitManager.class) {
                if (mAminRetrofit == null) {
                    return mAminRetrofit = new Retrofit.Builder()
                            .client(client)
                            /*指定根路径*/
                            .baseUrl(AdminAPIservice.HOME_PATH)
                            /*指定实体解析类型*/
                            .addConverterFactory(GsonConverterFactory.create())
                            /*指定响应模式为observable类,支持RxJava*/
                            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                            .build();
                }
            }
        }
        return mAminRetrofit;
    }

    /**
     * 获取Userretrofit实体类型
     * <p>
     * 用户
     *
     * @return
     */
    private static Retrofit getMainInstence() {

        GsonBuilder gsonBuilder = new GsonBuilder();
        GsonBuilder gsonBuilder1 = gsonBuilder.setDateFormat("yyyy-MM-dd");
        Gson gson = gsonBuilder1.create();
        if (mMainRetrofit == null) {
            /*加锁原因：防止在多线程的情况下进行多次实例化*/
            synchronized (RetrofitManager.class) {

                if (mMainRetrofit == null) {
                    return mMainRetrofit = new Retrofit.Builder()
                            .client(client)
                            /*指定根路径*/
                            .baseUrl(MainAPIservice.HOME_PATH)
                            /*指定实体解析类型*/
                            .addConverterFactory(GsonConverterFactory.create(gson))
                            /*指定响应模式为observable类,支持RxJava*/
                            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                            .build();
                }
            }
        }
        return mMainRetrofit;
    }

    /**
     * 获取Userretrofit实体类型
     * <p>
     * 短信
     *
     * @return
     */
    private static Retrofit getSMSInstence() {

        if (mEmsRetrofit == null) {
            /*加锁原因：防止在多线程的情况下进行多次实例化*/
            synchronized (RetrofitManager.class) {

                if (mEmsRetrofit == null) {
                    return mEmsRetrofit = new Retrofit.Builder()
                            .client(client)
                            /*指定根路径*/
                            .baseUrl(SmsAPIservice.HOME_PATH)
                            /*指定实体解析类型*/
                            .addConverterFactory(GsonConverterFactory.create())
                            /*指定响应模式为observable类,支持RxJava*/
                            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                            .build();
                }
            }
        }
        return mEmsRetrofit;
    }


    /**
     * 获取 news ApiService
     * 新闻接口
     *
     * @return
     */
    public static NewAPIservice getNewApiService() {
        return getNewInstence().create(NewAPIservice.class);
    }

    /**
     * 获取 news ApiService
     * 本地接口
     *
     * @return
     */
    public static MainAPIservice getMainApiService() {
        return getMainInstence().create(MainAPIservice.class);
    }

    /**
     * 获取 admin ApiService
     * 本地接口
     *
     * @return
     */
    public static AdminAPIservice getAdminApiService() {
        return getAdminInstence().create(AdminAPIservice.class);
    }

    /**
     * 获取 sms ApiService
     * 短信接口
     *
     * @return
     */
    public static SmsAPIservice getEmsApiService() {
        return getSMSInstence().create(SmsAPIservice.class);
    }
}
