package cn.cuit.microcollege.utils;

import cn.cuit.microcollege.entity.HttpResultEntity.NewResultBean;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * @Author: Rod Eden
 * @Date: 2019/5/4
 * @Description: 新闻资讯相关api
 */
public interface NewAPIservice {

    /**
     * http://apicloud.mob.com/wx/article/search
     */
    String HOME_PATH = "http://apicloud.mob.com";


    @GET("/wx/article/search")
    Observable<NewResultBean> getNew(@Query("key") String key, @Query("cid") String cid,
                                        @Query("page") Integer page, @Query("size") Integer size);
}