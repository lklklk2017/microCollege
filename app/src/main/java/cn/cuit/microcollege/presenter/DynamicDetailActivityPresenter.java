package cn.cuit.microcollege.presenter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.cuit.microcollege.activity.DynamicDetailActivity;
import cn.cuit.microcollege.adapter.CommentAdapter;
import cn.cuit.microcollege.base.BasePresenter;
import cn.cuit.microcollege.contract.DynamicDetailActivityContract;
import cn.cuit.microcollege.entity.HttpResultEntity.GetCommentResultBean;
import cn.cuit.microcollege.entity.HttpResultEntity.GetDynamicResultBean;
import cn.cuit.microcollege.model.DynamicDetailActivityModel;
import cn.cuit.microcollege.utils.SPUtil;
import cn.cuit.microcollege.utils.TransFormUtil;

/**
 * @Author: Created by Rod Eden
 * @Date: 2019/5/10
 * @Descirption:
 */
public class DynamicDetailActivityPresenter extends BasePresenter<DynamicDetailActivity, DynamicDetailActivityModel> implements DynamicDetailActivityContract.Presenter {


    public DynamicDetailActivityPresenter(DynamicDetailActivity view) {
        super(view);
    }

    @Override
    public void initModel() {
        bindModel(new DynamicDetailActivityModel());
    }

    @Override
    public void getDynamicTask(int DynamicId) {

    }

    /**
     * 初始化动态信息
     * 非网络访问
     */
    @Override
    public void initDynamicTask() {
        GetDynamicResultBean.TrendsBean trendsBean = getV().getTrendsBean();
        if (trendsBean != null) {
            ArrayList<GetDynamicResultBean.TrendsBean> list = new ArrayList<>();
            list.add(trendsBean);
            getV().getContentAdapter().changeList(list);
        }
    }

    /***
     * 获取评论
     * 请求
     * @param mCurrPage
     */
    @Override
    public void getCommetTask(int mCurrPage) {

        int dynamicId = getV().getDynamicId();
        if (dynamicId == -1) {
            getV().toast("参数获取失败", false);
            return;
        }

        //获取数据
        Map<String, Object> map = new HashMap<>();
        map.put("commParams.userId", "");
        map.put("commParams.dynamicId", dynamicId + "");
        map.put("commParams.commentContent", "");
        map.put("commParams.commentType", "0");
        map.put("commParams.fromTime", "");
        map.put("commParams.toTime", "");
        map.put("commParams.page", mCurrPage);
        map.put("commParams.pageSize", 10);

        getM().getComment(map, new DynamicDetailActivityContract.Model.CommentHttpResult() {
            @Override
            public void onSuccess(List<GetCommentResultBean.CommentsBean> comments) {

                if (getV() == null) {
                    return;
                }

                CommentAdapter adapter = getV().getCommentAdapter();

                if (adapter == null) {
                    return;
                }

                int commCount = adapter.getItemCount();
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
                adapter.changeList(comments);

            }

            @Override
            public void onError(String error) {
                if (getV() == null) {
                    return;
                }
                CommentAdapter adapter = getV().getCommentAdapter();
                if (adapter == null) {
                    return;
                }

                int itemCount = adapter.getItemCount();
                if (itemCount == 0) {
                    getV().showNoComment();
                }
            }
        });
    }

    /***
     * 获取评论
     * 请求
     * @param mCurrPage
     */
    @Override
    public void getCommetAddListTask(int mCurrPage) {
        int dynamicId = getV().getDynamicId();
        if (dynamicId == -1) {
            getV().toast("参数获取失败", false);
            return;
        }

        //获取数据
        Map<String, Object> map = new HashMap<>();
        map.put("commParams.userId", "");
        map.put("commParams.dynamicId", dynamicId + "");
        map.put("commParams.commentContent", "");
        map.put("commParams.commentType", "0");
        map.put("commParams.fromTime", "");
        map.put("commParams.toTime", "");
        map.put("commParams.page", mCurrPage);
        map.put("commParams.pageSize", 10);

        getM().getComment(map, new DynamicDetailActivityContract.Model.CommentHttpResult() {
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
        getV().hideInput();
        //user id
        int userId = SPUtil.getmUserSp().getInt("userId", -1);
        if (userId == -1) {
            getV().toast("user id 获取失败", false);
            return;
        }
        //dynamic id
        int dynamicId = getV().getDynamicId();
        if (dynamicId == -1) {
            getV().toast("dynamic id 获取失败", false);
            return;
        }

        //to id
        int toid;
        if (type == 0) {
            toid = 0;//随意 这个值会被后台覆盖为动态发布者id
        } else if (type == 1) {
            toid = getV().getCurrToId();//获取评论列表中的评论者id
        } else {
            throw new RuntimeException("comment type 必须为1 或者 0");
        }

        // commentContent
        String commentContent = getV().getCommentEt().getText().toString();

        //获取数据
        Map<String, Object> map = new HashMap<>();
        map.put("addCommParams.userId", userId);
        map.put("addCommParams.dynamicId", dynamicId + "");
        map.put("addCommParams.toId", toid);
        map.put("addCommParams.commentContent", TransFormUtil.getEncodeWithUTF(commentContent));
        map.put("addCommParams.commentType", type + "");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        map.put("addCommParams.commentTime", sdf.format(new Date()).toString());

        getM().addComment(map, new DynamicDetailActivityContract.Model.addCommentHttpResult() {
            @Override
            public void onSuccess() {
                getV().toast("评论已添加", false);
                getV().getCommentEt().setText("");
                //请求所有comments
                getCommetTask(1);
            }

            @Override
            public void onError(String error) {
                getV().toast("评论添加失败:" + error, false);
            }
        });
    }

    @Override
    public void addLike(String tid) {
        getM().addLike(tid, new DynamicDetailActivityContract.Model.AddLikeHttpResult() {
            @Override
            public void success() {

            }

            @Override
            public void error(String error) {

            }
        });
    }

    @Override
    public void deleteComment() {
        GetCommentResultBean.CommentsBean commentBean = getV().getCommentBean();
        if (commentBean == null) {
            return;
        }
        int comId = commentBean.getCommentId();

        int userId = commentBean.getUser().getUserId();//评论者id
        int userNative = SPUtil.getmUserSp().getInt("userId", -1);//本地的
        if (userId == -1) {
            return;
        }

        if (userId != userNative) {
            getV().toast("只能删除自己的评论..", false);
            return;
        }

        getM().deleteComment(String.valueOf(comId), new DynamicDetailActivityContract.Model.DeleteCommentHttpResult() {
            @Override
            public void success() {
                if (getV() == null) {
                    return;
                }
                getV().updateView();
            }

            @Override
            public void error(String error) {
                if (getV() == null) {
                    return;
                }
                getV().toast(error, false);
            }
        });
    }
}
