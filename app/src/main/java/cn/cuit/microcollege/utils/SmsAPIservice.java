package cn.cuit.microcollege.utils;

import java.util.Map;

import cn.cuit.microcollege.entity.HttpResultEntity.SMSResultBean;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * @Author: Created by Rod Eden
 * @Date: 2019/5/7
 * @Descirption:
 */
public interface SmsAPIservice {
    /**
     * http://v.juhe.cn/sms/send?mobile=手机号码&tpl_id=短信模板ID&tpl_value=%23code%23%3D654654&key=
     * <p>
     * 短信验证 模板156331
     *
     * @Query("mobile") String tel,
     * @Query("tpl_id") int id,
     * @Query("tpl_value") String value,
     * @Query("key") String key,
     * @Query("dtype") String type)
     */
    String HOME_PATH = "http://v.juhe.cn/";

    @GET("sms/send")
    Observable<SMSResultBean> sendsms(
            @QueryMap Map<String, Object> map);
}