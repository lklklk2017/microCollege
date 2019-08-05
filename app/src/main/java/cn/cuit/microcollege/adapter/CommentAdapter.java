package cn.cuit.microcollege.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.cuit.microcollege.R;
import cn.cuit.microcollege.application.MyApplication;
import cn.cuit.microcollege.base.BaseRcyAdapter;
import cn.cuit.microcollege.entity.HttpResultEntity.GetCommentResultBean;
import cn.cuit.microcollege.utils.TransFormUtil;

/**
 * @Author: Created by Rod Eden
 * @Date: 2019/5/4
 * @Descirption:
 */
public class CommentAdapter extends BaseRcyAdapter<GetCommentResultBean.CommentsBean, CommentAdapter.CommentListViewHolder> {

    private OnItemClickListener mListener;


    public CommentAdapter() {
    }

    public CommentAdapter(List<GetCommentResultBean.CommentsBean> list) {
        super(list);
    }

    public void setmListener(OnItemClickListener mListener) {
        this.mListener = mListener;
    }

    @NonNull
    @Override
    public CommentListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.dynamic_detail_rcy_item, parent, false);
        return new CommentListViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull final CommentListViewHolder holder, int position) {
        GetCommentResultBean.CommentsBean comment = getmList().get(position);

        //comment content
        String commentContent = comment.getCommentContent();
        if (!TextUtils.isEmpty(commentContent)) {
            holder.mContent.setText(TransFormUtil.getDecodeWithUTF(commentContent));
        } else {
            holder.mContent.setText("");
        }

        //time
        String commentTime = comment.getCommentTime();
        if (!TextUtils.isEmpty(commentTime)) {
            holder.mCommentDateTv.setText(commentTime.replace("T", " "));
        }

        //name and img
        GetCommentResultBean.CommentsBean.UserBean user = comment.getUser();
        if (user != null) {
            String avatarImgUrl = user.getAvatarImgUrl();
            String username = user.getUsername();
            if (username != null) {

                holder.mName.setText(username);
            } else {
                holder.mName.setText("这是个没名字的的家伙");
            }

            if (avatarImgUrl != null) {
                Glide.with(MyApplication.getMyContext())
                        .load(TransFormUtil.getLocalImageUrl(avatarImgUrl))
                        .apply(RequestOptions.bitmapTransform(new CircleCrop())
                                .placeholder(R.drawable.logo_temp)
                                .error(R.drawable.logo_temp))
                        .into(holder.mPortrait);
            } else {
                Glide.with(MyApplication.getMyContext())
                        .load(R.drawable.logo_temp)
                        .into(holder.mPortrait);
            }
        }

        //listener
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onItemClick(holder.getAdapterPosition());
                }
            }
        });
    }

    public static class CommentListViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.dynamic_detail_comment_item_protrait)
        ImageView mPortrait;
        @BindView(R.id.dynamic_detail_comment_item_name)
        TextView mName;
        @BindView(R.id.dynamic_detail_comment_item_content)
        TextView mContent;
        @BindView(R.id.dynamic_detail_comment_item_date)
        TextView mCommentDateTv;
        View view;

        public CommentListViewHolder(View itemView) {
            super(itemView);
            this.view = itemView;
            ButterKnife.bind(this, itemView);
        }
    }


    public interface OnItemClickListener {
        void onItemClick(int position);
    }
}
