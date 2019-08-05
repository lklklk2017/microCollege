package cn.cuit.microcollege.utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.text.SimpleDateFormat;

import cn.cuit.microcollege.application.MyApplication;
import cn.cuit.microcollege.entity.HttpResultEntity.UserLoginResultBean;

/**
 * @Author: Created by Rod Eden
 * @Date: 2019/5/6
 * @Descirption:
 */
public class SPUtil {

    private static final String TAG = "SPUtil";
    private static SPUtil instance;
    private static SharedPreferences mUserSp;
    private static SharedPreferences mParamSp;
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");


    private SPUtil() {
        mUserSp = MyApplication.getMyContext().getSharedPreferences("user", Context.MODE_PRIVATE);
        mParamSp = MyApplication.getMyContext().getSharedPreferences("params", Context.MODE_PRIVATE);
    }

    public static SPUtil getInstance() {

        if (instance == null) {
            synchronized (SPUtil.class) {
                if (instance == null) {
                    return instance = new SPUtil();
                }
            }
        }
        return instance;
    }


    /**
     * 用户状态
     *
     * @return
     */
    public static SharedPreferences getmUserSp() {
        getInstance();
        if (mUserSp != null) {
            return mUserSp;
        } else {
            return null;
        }
    }

    /**
     * 常规参数文件
     *
     * @return
     */
    public static SharedPreferences getmParamSp() {
        getInstance();
        if (mParamSp != null) {
            return mParamSp;
        } else {
            return null;
        }
    }

    public static SharedPreferences.Editor getmUserSpEt() {
        return getmUserSp().edit();
    }

    public static SharedPreferences.Editor getmParamSpEt() {
        return getmParamSp().edit();
    }

    /**
     * 用户保存用户简单信息
     *
     * @param user
     */
    public static void saveUserBysp(UserLoginResultBean.UserBean user) {

        if (user != null) {
            SharedPreferences.Editor editor = getmUserSpEt();
            editor.putInt("userId", user.getUserId());//id
            editor.putString("username", user.getUsername());//name
            editor.putString("password", user.getUserpassword());//password
            editor.putString("gender", user.getGender());//gender
            editor.putString("birthday", sdf.format(user.getBirthday()));//birth
            editor.putString("tel", user.getTel());//tel
            editor.putString("eamil", user.getEmail());//email
            editor.putString("userRegistTime", sdf.format(user.getUserRegistTime()));//registTime
            editor.putString("avatarImgUrl", TransFormUtil.getLocalImageUrl(user.getAvatarImgUrl()));//avatar
            editor.putString("coverImgUrl", TransFormUtil.getLocalImageUrl(user.getCoverImgUrl()));//coverImageUrl
            editor.putInt("circleCreatedCount", user.getCircleCreatedCount());//circleCreated
            editor.putInt("circleAddedCount", user.getCircleAddedCount());//circleAdded
            editor.putString("sign", user.getSign());//sign
            editor.putString("campus", user.getCampus());//campus
            editor.putString("nativePlace", user.getNativePlace());//nativePlace
            editor.putString("industry", user.getIndustry());//industry
            editor.putInt("loginState", user.getLoginState());//loginstate
            editor.putInt("freeze", user.getFreeze());//freeze
            editor.putString("token", user.getToken());//token
            editor.putString("ciphertext", user.getCiphertext());//ciphertext
            editor.commit();

            LogU.i("用户信息已保存!", TAG);
        } else {
            LogU.i("用户信息保存失败", TAG);
        }
    }


    /**
     * 用户保存用户简单信息
     *
     * @param
     */
//    public static UserLoginResultBean. getUserBysp() {
//
//        if (user != null) {
//            SharedPreferences.Editor editor = getmUserSpEt();
//            editor.putInt("userId", user.getUserId());//id
//            editor.putString("username", user.getUsername());//name
//            editor.putString("password", user.getUserpassword());//password
//            editor.putString("gender", user.getGender());//gender
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//            editor.putString("birthday", user.getBirthday());//birth
//            editor.putString("tel", user.getTel());//tel
//            editor.putString("eamil", user.getEmail());//email
//            editor.putString("userRegistTime", sdf.format(user.getUserRegistTime()));//registTime
//            editor.putString("avatarImgUrl", user.getAvatarImgUrl());//avatar
//            editor.putString("coverImgUrl", user.getCoverImgUrl());//coverImageUrl
//            editor.putInt("circleCreatedCount", user.getCircleCreatedCount());//circleCreated
//            editor.putInt("circleAddedCount", user.getCircleAddedCount());//circleAdded
//            editor.putString("sign", user.getSign());//sign
//            editor.putString("campus", user.getCampus());//campus
//            editor.putString("nativePlace", user.getNativePlace());//nativePlace
//            editor.putString("industry", user.getIndustry());//industry
//            editor.putInt("loginState", user.getLoginState());//loginstate
//            editor.putInt("freeze", user.getFreeze());//freeze
//            editor.putString("token", user.getToken());//token
//            editor.putString("ciphertext", user.getCiphertext());//ciphertext
//            editor.commit();
//
//            LogU.i("用户信息已保存!", TAG);
//        } else {
//            LogU.i("用户信息保存失败", TAG);
//        }
//    }
}
