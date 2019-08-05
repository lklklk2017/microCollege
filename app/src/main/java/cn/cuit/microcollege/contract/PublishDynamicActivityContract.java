package cn.cuit.microcollege.contract;

import java.util.List;
import java.util.Map;

import cn.cuit.microcollege.adapter.ImageCollectorAdapter;
import cn.cuit.microcollege.base.BaseModel;
import cn.cuit.microcollege.entity.HttpResultEntity.GetMyCircleResultBean;
import cn.cuit.microcollege.entity.HttpResultEntity.GetUploadFilesResultBean;
import okhttp3.RequestBody;

/**
 * @Author: Created by Rod Eden
 * @Date: 2019/5/1
 * @Descirption:
 */
public interface PublishDynamicActivityContract {
    interface Model extends BaseModel {
        //多图片上传
        void doUpload(Map<String, RequestBody> map, UploadHttpResult result);

        interface UploadHttpResult {
            void onSuccess(GetUploadFilesResultBean bean);

            void onError(String error);
        }

        //发布
        void doPublish(Map<String, Object> map, PublishHttpResult result);

        interface PublishHttpResult {
            void onSuccess();

            void onError(String error);
        }
    }

    interface View {
        GetMyCircleResultBean.CirclesBean getCircleBean();

        String getLocal();

        ImageCollectorAdapter getImgAdapter();

        String getPublishContent();
    }

    interface Presenter {
        void publishTask();
        void addTrend(int type, List<String> imgUrl);
    }
}
