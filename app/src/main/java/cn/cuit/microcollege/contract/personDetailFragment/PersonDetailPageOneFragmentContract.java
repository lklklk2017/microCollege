package cn.cuit.microcollege.contract.personDetailFragment;

import java.util.List;
import java.util.Map;

import cn.cuit.microcollege.adapter.HomePageOneAdapter;
import cn.cuit.microcollege.base.BaseModel;
import cn.cuit.microcollege.entity.HttpResultEntity.GetDynamicResultBean;

/**
 * @Author: Created by Rod Eden
 * @Date: 2019/5/24
 * @Descirption:
 */
public interface PersonDetailPageOneFragmentContract {
    interface Model extends BaseModel {
        void getPersonDynamic(Map<String, Object> map, PersonDynamicHttpResult result);

        interface PersonDynamicHttpResult {
            void onSuccess(List<GetDynamicResultBean.TrendsBean> trends);

            void onError(String error);
        }

        void deletePersonDyanmic(String tid, DeleteDynamicHttpResult result);

        interface DeleteDynamicHttpResult {
            void onSuccess();

            void onError(String error);
        }

        /**
         * 点赞
         * @param tid
         * @param result
         */
        void addLike(String tid, PersonDetailPageOneFragmentContract.Model.AddLikeHttpResult result);

        interface AddLikeHttpResult {
            void success();

            void error(String error);
        }
    }

    interface View {
        HomePageOneAdapter getAdapter();
        void setDynamicCount(int count);

        void showResult();

        void showNoResult();


        int getPageSize();

        int getCurrentPage();

        int setCurrentPage(int currentPage);//设置并返回当前页面
    }

    interface Presenter {
        void personDynamicTask(int page);
        void deleteDyanmicTask(int dynamicId, int tid);
        void addLike(String tid);
    }
}
