package cn.cuit.microcollege.utils;

import java.util.Map;

import cn.cuit.microcollege.entity.HttpResultEntity.GetUploadFilesResultBean;
import cn.cuit.microcollege.entity.HttpResultEntity.UserCheckTelExistResultBean;
import cn.cuit.microcollege.entity.HttpResultEntity.UserLoginResultBean;
import cn.cuit.microcollege.entity.HttpResultEntity.UserReSetPwdResultBean;
import okhttp3.RequestBody;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PartMap;
import retrofit2.http.Query;
import rx.Observable;

/**
 * @Author: Rod Eden
 * @Date: 2019/5/4
 * @Description: 新闻资讯相关api
 */
public interface MainAPIservice {

    /**
     * http://localhost:8080/microCollege/userApi/UserLogin.action?tel=15008476607&userpassword=123456
     */
//    String HOME_PATH = "http://127.0.0.1:8080/microCollege/userApi/";
//    String HOME_PATH = "http://192.168.43.185:8080/microCollege/userApi/";
//    String HOME_PATH = "http://192.168.1.2:8080/microCollege/userApi/";

    String HOME_PATH = "http://"+Config.BASE_HOST+":8080/microCollege/userApi/";

    /**
     * 用户登录
     *
     * @param tel
     * @param pwd
     * @return
     */
    @GET("UserLogin.action")
    Observable<UserLoginResultBean> login(@Query("tel") String tel, @Query("userpassword") String pwd);

    /**
     * 检验tel是否存在
     *
     * @param tel
     * @return
     */
    @GET("validateTelIsExist.action")
    Observable<UserCheckTelExistResultBean> checkTel(@Query("tel") String tel);

    /**
     * 更改密码
     *
     * @param tel
     * @param pwd
     * @return
     */
    @GET("retSetPwd.action")
    Observable<UserReSetPwdResultBean> resetPwd(@Query("tel") String tel, @Query("userpassword") String pwd);

    /**
     * 上传多文件
     * @param dataMap
     * @return
     */
    @Multipart
    @POST("uploads/uploadFiles.action")
    Observable<GetUploadFilesResultBean> uploadFiles(@PartMap Map<String, RequestBody> dataMap);
}