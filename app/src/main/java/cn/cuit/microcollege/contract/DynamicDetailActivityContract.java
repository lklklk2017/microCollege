package cn.cuit.microcollege.contract;

import android.support.v7.widget.RecyclerView;
import android.widget.EditText;

import java.util.List;
import java.util.Map;

import cn.cuit.microcollege.adapter.CommentAdapter;
import cn.cuit.microcollege.adapter.DynamicDetailContentAdapter;
import cn.cuit.microcollege.base.BaseModel;
import cn.cuit.microcollege.entity.HttpResultEntity.GetCommentResultBean;
import cn.cuit.microcollege.entity.HttpResultEntity.GetDynamicResultBean;

/**
 * @Author: Created by Rod Eden
 * @Date: 2019/5/10
 * @Descirption:
 */
public interface DynamicDetailActivityContract {
    interface Model extends BaseModel {
        void getComment(Map<String, Object> data, CommentHttpResult result);

        interface CommentHttpResult {
            void onSuccess(List<GetCommentResultBean.CommentsBean> comments);

            void onError(String error);
        }

        /**
         * 添加评论
         *
         * @param data
         * @param result
         */
        void addComment(Map<String, Object> data, addCommentHttpResult result);

        interface addCommentHttpResult {
            void onSuccess();

            void onError(String error);
        }

        void addLike(String tid, DynamicDetailActivityContract.Model.AddLikeHttpResult result);

        interface AddLikeHttpResult {
            void success();

            void error(String error);
        }

        void deleteComment(String comId, DeleteCommentHttpResult result);

        interface DeleteCommentHttpResult {
            void success();

            void error(String error);
        }
    }

    interface View {

        int getCurrToId();

        void setCurrToId(int currToId);

        int getCurrCommentType();

        void setCurrCommentType(int type);

        DynamicDetailContentAdapter getContentAdapter();

        CommentAdapter getCommentAdapter();

        RecyclerView getContentRcy();

        RecyclerView getCommentRcy();

        EditText getCommentEt();

        int getCurrentPage();

        int getDynamicId();

        int setCurrentPage(int page);

        void showNoComment();

        void showHasComment();

        GetDynamicResultBean.TrendsBean getTrendsBean();

        GetCommentResultBean.CommentsBean getCommentBean();
    }

    interface Presenter {

        void getDynamicTask(int DynamicId);

        void initDynamicTask();

        void getCommetTask(int mCurrPage);

        void getCommetAddListTask(int mCurrPage);

        void addCommetTask(int type);

        void addLike(String tid);

        void deleteComment();
    }
}
