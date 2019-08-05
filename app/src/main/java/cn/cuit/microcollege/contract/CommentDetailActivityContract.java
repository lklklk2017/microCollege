package cn.cuit.microcollege.contract;

import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

import cn.cuit.microcollege.adapter.CommentAdapter;
import cn.cuit.microcollege.base.BaseModel;
import cn.cuit.microcollege.entity.HttpResultEntity.GetCommentResultBean;

/**
 * @Author: Created by Rod Eden
 * @Date: 2019/5/12
 * @Descirption:
 */
public interface CommentDetailActivityContract {

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
    }

    interface View {

        GetCommentResultBean.CommentsBean getCommentBean();

        void setCommentBean(GetCommentResultBean.CommentsBean bean);

        ImageView getPortait();

        TextView getNameTv();

        TextView getDateTv();

        TextView getCommentCountTv();

        TextView getMainCommentTv();

        int getCurrToId();

        void setCurrToId(int currToId);

        int getCurrCommentType();

        CommentAdapter getCommentAdapter();

        RecyclerView getCommentRcy();

        EditText getCommentEt();

        int getCurrentPage();

        int setCurrentPage(int page);

        void showNoComment();

        void showHasComment();
    }

    interface Presenter {

        //获取评论
        void getCommetTask(int mCurrPage);

        //获取评论
        void getCommetAddListTask(int mCurrPage);

        //添加评论
        void addCommetTask(int type);

        //初始化 main comment
        void initMainComment();
    }
}
