package cn.cuit.microcollege.utils;

import java.util.Date;
import java.util.Map;

import cn.cuit.microcollege.entity.HttpResultEntity.AddFeedBackResultBean;
import cn.cuit.microcollege.entity.HttpResultEntity.DeleteDynamicResultBean;
import cn.cuit.microcollege.entity.HttpResultEntity.DeleteResultBean;
import cn.cuit.microcollege.entity.HttpResultEntity.GetAddCommentResultBean;
import cn.cuit.microcollege.entity.HttpResultEntity.GetAddTrendResultBean;
import cn.cuit.microcollege.entity.HttpResultEntity.GetCommentResultBean;
import cn.cuit.microcollege.entity.HttpResultEntity.GetDynamicResultBean;
import cn.cuit.microcollege.entity.HttpResultEntity.GetDynamicWithCircleResultBean;
import cn.cuit.microcollege.entity.HttpResultEntity.GetMyCircleResultBean;
import cn.cuit.microcollege.entity.HttpResultEntity.GetUserBaseInfoResultBean;
import cn.cuit.microcollege.entity.HttpResultEntity.GetUserInCircleCheckResultBean;
import cn.cuit.microcollege.entity.HttpResultEntity.JoinInCircleResultBean;
import cn.cuit.microcollege.entity.HttpResultEntity.LikeResultBean;
import cn.cuit.microcollege.entity.HttpResultEntity.QuitFromCircleResultBean;
import cn.cuit.microcollege.entity.HttpResultEntity.UserRegistResultBean;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * @Author: Created by Rod Eden
 * @Date: 2019/5/7
 * @Descirption:
 */
public interface AdminAPIservice {
    /**
     * http://localhost:8080/microCollege/adminApi/addUser.action
     */
//    String HOME_PATH = "http://192.168.1.2:8080/microCollege/adminApi/";
    String HOME_PATH = "http://" + Config.BASE_HOST + ":8080/microCollege/adminApi/";

    /**
     * 注册
     *
     * @param name
     * @param pwd
     * @param gender
     * @param email
     * @param avatarUrl
     * @param tel
     * @return
     */
    @GET("addUser.action")
    Observable<UserRegistResultBean> regist(@Query("user.username") String name,
                                            @Query("user.userpassword") String pwd,
                                            @Query("user.gender") String gender,
                                            @Query("user.email") String email,
                                            @Query("user.avatarImgUrl") String avatarUrl,
                                            @Query("user.tel") String tel);


    /**
     * search circles by condictions
     *
     * @param circleId
     * @param creatorId
     * @param dynamicId
     * @param userJoinedId
     * @param circleName
     * @param createDateFrom
     * @param createDateTo
     * @param page
     * @param pageSize
     * @return
     */
    @GET("getAllCircles.action")
    Observable<GetMyCircleResultBean> getCircleByConditions(@Query("circleId") String circleId,
                                                            @Query("creatorId") String creatorId,
                                                            @Query("dynamicId") String dynamicId,
                                                            @Query("userJoinedId") String userJoinedId,
                                                            @Query("circleName") String circleName,
                                                            @Query("createDateFrom") Date createDateFrom,
                                                            @Query("createDateTo") Date createDateTo,
                                                            @Query("page") String page,
                                                            @Query("pageSize") String pageSize);

    /**
     * 多条件查询动态
     *
     * @param dataMap
     * @return
     */
    @FormUrlEncoded
    @POST("getAllDynamic.action")
    Observable<GetDynamicResultBean> getDynamicByCondiction(@FieldMap Map<String, Object> dataMap);


    @FormUrlEncoded
    @POST("getAllDynamicWithCircle.action")
    Observable<GetDynamicWithCircleResultBean> getDynamicWithCircleByCondiction(@FieldMap Map<String, Object> dataMap);

    @FormUrlEncoded
    @POST("getAllHotDynamicWithCircle.action")
    Observable<GetDynamicWithCircleResultBean> getHotDynamicWithCircleByCondiction(@FieldMap Map<String, Object> dataMap);

    @FormUrlEncoded
    @POST("getAllComments.action")
    Observable<GetCommentResultBean> getCommentByCondiction(@FieldMap Map<String, Object> dataMap);

    @FormUrlEncoded
    @POST("addComment.action")
    Observable<GetAddCommentResultBean> addComment(@FieldMap Map<String, Object> dataMap);

    /**
     * 发布动态
     *
     * @param dataMap
     * @return
     */
    @FormUrlEncoded
    @POST("addTrend.action")
    Observable<GetAddTrendResultBean> addTrend(@FieldMap Map<String, Object> dataMap);

    @FormUrlEncoded
    @POST("checkUserInCircle.action")
    Observable<GetUserInCircleCheckResultBean> checkUserInCircle(@FieldMap Map<String, Object> dataMap);

    /**
     * 多条件查询用户
     *
     * @param dataMap
     * @return
     */
    @FormUrlEncoded
    @POST("getAllUsers.action")
    Observable<GetUserBaseInfoResultBean> getUserInfoByCondiction(@FieldMap Map<String, Object> dataMap);

    /**
     * 搜索用户 (这里的user id 必填 ，代表搜索除去自已(user id)以外的数据)
     *
     * @param dataMap
     * @return
     */
    @FormUrlEncoded
    @POST("SearchUser.action")
    Observable<GetUserBaseInfoResultBean> searchUsers(@FieldMap Map<String, Object> dataMap);


    /**
     * 删除动态
     *
     * @param tid
     * @return
     */
    @FormUrlEncoded
    @POST("deleteDynamic.action")
    Observable<DeleteDynamicResultBean> deleteDyanmic(@Field("tid") String tid);

    /**
     * 点赞
     *
     * @param tid
     * @return
     */
    @FormUrlEncoded
    @POST("addLike.action")
    Observable<LikeResultBean> addLike(@Field("tid") String tid);

    /**
     * 取消点赞
     *
     * @param tid
     * @return
     */
    @FormUrlEncoded
    @POST("cancelLike.action")
    Observable<LikeResultBean> cancelLike(@Field("tid") String tid);

    /**
     * 加入圈子
     *
     * @param circleId
     * @param userId
     * @return
     */
    @FormUrlEncoded
    @POST("joinInCircle.action")
    Observable<JoinInCircleResultBean> joinInCircle(@Field("circleId") String circleId, @Field("userJoinedId") String userId);

    /**
     * 退出圈子
     *
     * @param circleId
     * @param userId
     * @return
     */
    @FormUrlEncoded
    @POST("quitFromCircle.action")
    Observable<QuitFromCircleResultBean> quitFromCircle(@Field("circleId") String circleId, @Field("userJoinedId") String userId);

    /**
     * 删除评论
     *
     * @return
     */
    @FormUrlEncoded
    @POST("deleteComment.action")
    Observable<DeleteResultBean> deleteComment(@Field("comId") String comId);

    /**
     * 提交反馈
     *
     * @return
     */
    @FormUrlEncoded
    @POST("addFeedBack.action")
    Observable<AddFeedBackResultBean> addFeedBack(@Field("uid") int uid, @Field("feedBackContent") String feedBackContent);
}
