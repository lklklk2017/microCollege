package cn.cuit.microcollege.presenter;

import android.text.TextUtils;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.cuit.microcollege.R;
import cn.cuit.microcollege.activity.CommentDetailActivity;
import cn.cuit.microcollege.adapter.CommentAdapter;
import cn.cuit.microcollege.application.MyApplication;
import cn.cuit.microcollege.base.BasePresenter;
import cn.cuit.microcollege.contract.CommentDetailActivityContract;
import cn.cuit.microcollege.entity.HttpResultEntity.GetCommentResultBean;
import cn.cuit.microcollege.model.CommentDetailActivityModel;
import cn.cuit.microcollege.utils.LogU;
import cn.cuit.microcollege.utils.SPUtil;
import cn.cuit.microcollege.utils.TransFormUtil;

/**
 * @Author: Created by Rod Eden
 * @Date: 2019/5/12
 * @Descirption:
 */
public class CommentDetailActivityPresenter extends BasePresenter<CommentDetailActivity, CommentDetailActivityModel> implements CommentDetailActivityContract.Presenter {
    public CommentDetailActivityPresenter(CommentDetailActivity view) {
        super(view);
    }

    @Override
    public void initModel() {
        bindModel(new CommentDetailActivityModel());
    }

    /**
     * 请求评论
     *
     * @param mCurrPage
     */
    @Override
    public void getCommetTask(int mCurrPage) {

        GetCommentResultBean.CommentsBean commentBean = getV().getCommentBean();
        if (commentBean == null) {
            LogU.i("参数获取失败: commentBean", this.getClass().getName());
            return;
        }

        GetCommentResultBean.CommentsBean.TrendBean trend = commentBean.getTrend();

        if (trend == null) {
            LogU.i("参数获取失败: trend", this.getClass().getName());
            return;
        }

        int dynamicId = trend.getDynamicId();

        if (dynamicId == -1) {
            LogU.i("参数获取失败: dynamic id", this.getClass().getName());
            return;
        }

        int currToId = commentBean.getUser().getUserId();
        if (currToId == -1) {
            LogU.i("参数获取失败 currToId", this.getClass().getName());
            return;
        }

        //获取数据
        Map<String, Object> map = new HashMap<>();
        map.put("commParams.userId", "");
        map.put("commParams.dynamicId", dynamicId + "");
        map.put("commParams.commentContent", "");
        map.put("commParams.commentType", "1");
        map.put("commParams.fromTime", "");
        map.put("commParams.toTime", "");
        map.put("commParams.toUserId", currToId);
        map.put("commParams.page", mCurrPage);
        map.put("commParams.pageSize", 10);

        getM().getComment(map, new CommentDetailActivityContract.Model.CommentHttpResult() {
            @Override
            public void onSuccess(List<GetCommentResultBean.CommentsBean> comments) {
                if (getV() == null) {
                    return;
                }
                int commCount = getV().getCommentAdapter().getItemCount();
                if (comments == null || comments.size() == 0) {
                    if (commCount == 0) {
                        getV().showNoComment();
                    } else {
                        getV().toast("没有更多评论啦~", false);
                        getV().setCurrentPage(getV().getCurrentPage() - 1);
                    }
                    return;
                }

                getV().showHasComment();
                CommentAdapter commentAdapter = getV().getCommentAdapter();
                commentAdapter.changeList(comments);
                getV().getCommentCountTv().setText("" + commentAdapter.getmList().size());
            }

            @Override
            public void onError(String error) {
                if (getV() == null) {
                    return;
                }
                int itemCount = getV().getCommentAdapter().getItemCount();
                if (itemCount == 0) {
                    getV().showNoComment();
                }
            }
        });
    }

    @Override
    public void getCommetAddListTask(int mCurrPage) {

        GetCommentResultBean.CommentsBean commentBean = getV().getCommentBean();
        if (commentBean == null) {
            LogU.i("参数获取失败: commentBean", this.getClass().getName());
            return;
        }

        GetCommentResultBean.CommentsBean.TrendBean trend = commentBean.getTrend();

        if (trend == null) {
            LogU.i("参数获取失败: trend", this.getClass().getName());
            return;
        }

        int dynamicId = trend.getDynamicId();

        if (dynamicId == -1) {
            LogU.i("参数获取失败: dynamic id", this.getClass().getName());
            return;
        }

        int currToId = commentBean.getUser().getUserId();
        if (currToId == -1) {
            LogU.i("参数获取失败 currToId", this.getClass().getName());
            return;
        }

        //获取数据
        Map<String, Object> map = new HashMap<>();
        map.put("commParams.userId", "");
        map.put("commParams.dynamicId", dynamicId + "");
        map.put("commParams.commentContent", "");
        map.put("commParams.commentType", "1");
        map.put("commParams.fromTime", "");
        map.put("commParams.toTime", "");
        map.put("commParams.toUserId", currToId);
        map.put("commParams.page", mCurrPage);
        map.put("commParams.pageSize", 10);

        getM().getComment(map, new CommentDetailActivityContract.Model.CommentHttpResult() {
            @Override
            public void onSuccess(List<GetCommentResultBean.CommentsBean> comments) {
                if (getV() == null) {
                    return;
                }
                int commCount = getV().getCommentAdapter().getItemCount();
                if (comments == null || comments.size() == 0) {
                    if (commCount == 0) {
                        getV().showNoComment();
                    } else {
                        getV().toast("没有更多评论啦~", false);
                        getV().setCurrentPage(getV().getCurrentPage() - 1);
                    }
                    return;
                }

                getV().showHasComment();
                CommentAdapter commentAdapter = getV().getCommentAdapter();
                commentAdapter.addList(comments);
            }

            @Override
            public void onError(String error) {
                if (getV() == null) {
                    return;
                }
                int itemCount = getV().getCommentAdapter().getItemCount();
                if (itemCount == 0) {
                    getV().showNoComment();
                } else {
                    getV().setCurrentPage(getV().getCurrentPage() - 1);
                }
            }
        });
    }

    @Override
    public void addCommetTask(int type) {

        GetCommentResultBean.CommentsBean commentBean = getV().getCommentBean();
        if (commentBean == null) {
            LogU.i("参数获取失败: commentBean", this.getClass().getName());
            return;
        }

        GetCommentResultBean.CommentsBean.TrendBean trend = commentBean.getTrend();

        if (trend == null) {
            LogU.i("参数获取失败: trend", this.getClass().getName());
            return;
        }

        int dynamicId = trend.getDynamicId();

        if (dynamicId == -1) {
            LogU.i("参数获取失败: dynamic id", this.getClass().getName());
            return;
        }

        int currToId = commentBean.getUser().getUserId();
        if (currToId == -1) {
            LogU.i("参数获取失败 currToId", this.getClass().getName());
            return;
        }


        getV().hideInput();
        //user id
        int userId = SPUtil.getmUserSp().getInt("userId", -1);
        if (userId == -1) {
            getV().toast("user id 获取失败", false);
            return;
        }

        // commentContent
        String commentContent = getV().getCommentEt().getText().toString();

        if (TextUtils.isEmpty(commentContent)) {
            getV().toast("评论内容不能为空哦~", false);
            return;
        }

        //获取数据
        Map<String, Object> map = new HashMap<>();
        map.put("addCommParams.userId", userId);
        map.put("addCommParams.dynamicId", dynamicId + "");
        map.put("addCommParams.toId", currToId);
        map.put("addCommParams.commentContent", TransFormUtil.getEncodeWithUTF(commentContent));
        map.put("addCommParams.commentType", "1");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        map.put("addCommParams.commentTime", sdf.format(new Date()));

        getM().addComment(map, new CommentDetailActivityContract.Model.addCommentHttpResult() {
            @Override
            public void onSuccess() {
                if (getV() == null) {
                    return;
                }
                getV().toast("评论已添加", false);
                getV().getCommentEt().setText("");


                //请求所有comments
                getCommetTask(1);
            }

            @Override
            public void onError(String error) {
                if (getV() == null) {
                    return;
                }
                getV().toast("评论添加失败:" + error, false);
            }
        });
    }

    @Override
    public void initMainComment() {

        //获取数据
        GetCommentResultBean.CommentsBean commentBean = getV().getCommentBean();
        if (commentBean == null) {
            getV().toast("初始化详情失败", false);
            return;
        }

        //name
        GetCommentResultBean.CommentsBean.UserBean user = commentBean.getUser();
        if (user == null) {
            LogU.i("user bean is null", this.getClass().getName());
            return;
        }

        String username = user.getUsername();
        String avatarImgUrl = user.getAvatarImgUrl();

        if (username != null) {
            getV().getNameTv().setText(username);
        }

        //date
        String commentTime = commentBean.getCommentTime();
        if (commentTime != null) {
            getV().getDateTv().setText(commentTime.replace("T", " "));
        }

        //content
        String commentContent = commentBean.getCommentContent();
        if (commentContent != null) {
            getV().getMainCommentTv().setText(TransFormUtil.getDecodeWithUTF(commentContent));
        }

        //user imgUrl
        if (avatarImgUrl != null) {
            Glide.with(MyApplication.getMyContext())
                    .load(TransFormUtil.getLocalImageUrl(avatarImgUrl))
                    .apply(RequestOptions.bitmapTransform(new CircleCrop())
                            .placeholder(R.drawable.logo_temp)
                            .error(R.drawable.logo_temp))
                    .into(getV().getPortait());
        }
    }
}
