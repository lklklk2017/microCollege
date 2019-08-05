package cn.cuit.microcollege.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
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
import cn.cuit.microcollege.entity.NativeResultEntity.ChatListFromNativeResultBean;

/**
 * @Author: Created by Rod Eden
 * @Date: 2019/5/4
 * @Descirption:
 */
public class ChatListAdapter extends BaseRcyAdapter<ChatListFromNativeResultBean, ChatListAdapter.ChatListViewHolder> {

    private static OnItemClickListener mListener;


    public ChatListAdapter() {
    }

    public ChatListAdapter(List<ChatListFromNativeResultBean> list) {
        super(list);
    }

    public void setmListener(OnItemClickListener mListener) {
        ChatListAdapter.mListener = mListener;
    }

    @NonNull
    @Override
    public ChatListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_explore_chatlist_item, parent, false);
        return new ChatListViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatListViewHolder holder, int position) {
        ChatListFromNativeResultBean dataBean = getmList().get(position);

        //data
        //1.image
        Glide.with(MyApplication.getMyContext())
                .load(R.drawable.logo)
                .apply(RequestOptions.bitmapTransform(new CircleCrop())
                        .error(R.drawable.error))
                .into(holder.mImg);
        holder.mNameTv.setText("微校管理员");
        holder.mContentTv.setText("聊天内容...");
        holder.mDataTv.setText("4-28 1:30");

        //listener
    }

    public static class ChatListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.chat_list_img)
        ImageView mImg;
        @BindView(R.id.chat_list_name_tv)
        TextView mNameTv;
        @BindView(R.id.chat_list_content_tv)
        TextView mContentTv;
        @BindView(R.id.chat_list_data_tv)
        TextView mDataTv;

        public ChatListViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mListener != null) {
                mListener.onItemClick(getAdapterPosition());
            }
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

}
