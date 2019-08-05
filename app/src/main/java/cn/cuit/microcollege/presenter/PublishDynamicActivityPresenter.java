package cn.cuit.microcollege.presenter;

import java.io.File;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import cn.cuit.microcollege.activity.PublishDynamicActivity;
import cn.cuit.microcollege.adapter.ImageCollectorAdapter;
import cn.cuit.microcollege.base.BasePresenter;
import cn.cuit.microcollege.contract.PublishDynamicActivityContract;
import cn.cuit.microcollege.entity.DataEntity.ImageDataBean;
import cn.cuit.microcollege.entity.HttpResultEntity.GetMyCircleResultBean;
import cn.cuit.microcollege.entity.HttpResultEntity.GetUploadFilesResultBean;
import cn.cuit.microcollege.model.PublishDynamicActivityModel;
import cn.cuit.microcollege.utils.SPUtil;
import cn.cuit.microcollege.utils.TransFormUtil;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * @Author: Created by Rod Eden
 * @Date: 2019/5/1
 * @Descirption:
 */
public class PublishDynamicActivityPresenter extends BasePresenter<PublishDynamicActivity, PublishDynamicActivityModel> implements PublishDynamicActivityContract.Presenter {

    public PublishDynamicActivityPresenter(PublishDynamicActivity view) {
        super(view);
    }

    @Override
    public void initModel() {
        bindModel(new PublishDynamicActivityModel());
    }

    @Override
    public void publishTask() {
        //get data
        ImageCollectorAdapter imgAdapter = getV().getImgAdapter();//照片适配器
//        String local = getV().getLocal();//获取地址

        //validate
        if (imgAdapter == null) {
            getV().toast("发送失败", false);
            return;
        }

        //imgs path
        List<ImageDataBean> imgList = imgAdapter.getmList();
        if (imgList == null) {
            getV().toast("imgs 获取失败", false);
            return;
        }

        int size = imgList.size();//img count
        int type = 0;

        if (size < 9 && size > 0) {
            size -= 1;
        }

        if (size == 9) {
            if (imgList.get(8).getImgUrl().toString().equals("2131165276")) {
                size -= 1;
            }
        }

        if (size == 0) {
            type = 0;
        } else if (size == 1) {
            type = 1;
        } else if (size == 2) {
            type = 2;
        } else if (size == 3) {
            type = 3;
        } else if (size == 4) {
            type = 4;
        } else if (size == 5) {
            type = 5;
        } else if (size == 6) {
            type = 6;
        } else if (size == 7) {
            type = 7;
        } else if (size == 8) {
            type = 8;
        } else if (size == 9) {
            type = 9;
        }

        if (type == 0) {//不需要上传
            addTrend(0, null);
        } else {
            //先执行图片上传
            //在执行文字上传

            //init param ：files
            HashMap<String, RequestBody> dataMap = new HashMap<>();
            for (int i = 0; i < size; i++) {
                ImageDataBean imageDataBean = imgList.get(i);
                File file = new File(imageDataBean.getImgUrl().toString());
                dataMap.put("upload\";filename=\"" + file.getName(), RequestBody.create(MediaType.parse("image/png"), file));
            }

            //开始发布
            //1.upload imgs
            getV().showDialog(true);
            final int finalType = type;
            getM().doUpload(dataMap, new PublishDynamicActivityContract.Model.UploadHttpResult() {
                @Override
                public void onSuccess(GetUploadFilesResultBean bean) {
                    getV().showDialog(false);
                    getV().toast("上传成功", false);
                    //发布到动态
                    List<String> imgUrlArr = bean.getImgUrlArr();
                    addTrend(finalType, imgUrlArr);
                }

                @Override
                public void onError(String error) {
                    getV().showDialog(false);
                    getV().toast("上传失败：" + error, false);
                }
            });
        }
    }

    @Override
    public void addTrend(int type, List<String> imgUrl) {

        //type
        int currType = type;
        //userId
        int userId = SPUtil.getmUserSp().getInt("userId", -1);
        if (userId == -1) {
            getV().toast("获取参数失败", false);
            return;
        }

        //circleId
        int circleId = 0;
        GetMyCircleResultBean.CirclesBean circleBean = getV().getCircleBean();
        if (circleBean != null) {
            circleId = circleBean.getCircleId();
        } else {
            circleId = 0;
        }

        //userImg
        String avatarImgUrl = SPUtil.getmUserSp().getString("avatarImgUrl", "");
        if (avatarImgUrl == null) {
            getV().toast("参数获取失败:avatarImgUrl", false);
            return;
        }

        //userName
        String username = SPUtil.getmUserSp().getString("username", null);
        if (username == null) {
            getV().toast("参数获取失败:username", false);
            return;
        }

        //content
        String publishContent = getV().getPublishContent();

        //imagList
        List<String> imgs = new ArrayList<>();
        if (imgUrl != null) {
            imgs = imgUrl;
        } else {

        }

        //map
        HashMap<String, Object> map = new HashMap<>();
        map.put("addTrendParams.userId", userId);
        map.put("addTrendParams.circleId", circleId);
        map.put("addTrendParams.dynamicUserAvaterUrl", TransFormUtil.getRemoteImageUrl(avatarImgUrl));
        map.put("addTrendParams.dynamicUserName", username);
        map.put("addTrendParams.dynamicType", currType);
        map.put("addTrendParams.dynamicContent", publishContent);
        map.put("addTrendParams.publishTime", new Timestamp(new Date().getTime()));
        map.put("addTrendParams.imageList", imgs);

        getV().showDialog(true);
        getM().doPublish(map, new PublishDynamicActivityContract.Model.PublishHttpResult() {
            @Override
            public void onSuccess() {
                //发布成功，全部结束
                getV().showDialog(false);
                getV().toast("发布成功", false);
                getV().finish();
            }

            @Override
            public void onError(String error) {
                getV().showDialog(false);
                getV().toast("发布失败：" + error, false);
            }
        });
    }
}
