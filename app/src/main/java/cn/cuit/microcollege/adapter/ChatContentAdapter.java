package cn.cuit.microcollege.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.cuit.microcollege.R;
import cn.cuit.microcollege.base.BaseRcyAdapter;

/**
 * @Author: Created by Rod Eden
 * @Date: 2019/5/5
 * @Descirption:
 */
public class ChatContentAdapter extends BaseRcyAdapter<Object, RecyclerView.ViewHolder> {

    public ChatContentAdapter() {
    }

    public ChatContentAdapter(List<Object> list) {
        super(list);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == 0) {
            View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_chatlist_left_item_txt_only, parent, false);
            return new ChatLeftItemTxtOnlyViewHolder(inflate);
        } else if (viewType == 1) {
            View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_chatlist_left_item_img_only, parent, false);
            return new ChatLeftItemImgOnlyViewHolder(inflate);
        } else if (viewType == 2) {
            View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_chatlist_right_item_txt_only, parent, false);
            return new ChatRightItemTxtOnlyViewHolder(inflate);
        } else if (viewType == 3) {
            View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_chatlist_right_item_img_only, parent, false);
            return new ChatRightItemImgOnlyViewHolder(inflate);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int itemViewType = getItemViewType(position);
        switch (itemViewType) {
            case 0://左文
                break;
            case 1://左图
                break;
            case 2://右文
                break;
            case 3://右图
                break;
        }
    }

    /**
     * 右布局 txt
     * 2
     */
    class ChatRightItemTxtOnlyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.chat_content_list_right_text_only_time)
        TextView mTime;
        @BindView(R.id.chat_content_list_right_text_only_portrait)
        ImageView mPortrait;
        @BindView(R.id.chat_content_list_right_text_only_txt)
        TextView mTxt;

        public ChatRightItemTxtOnlyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    /**
     * 右布局 img
     * 3
     */
    class ChatRightItemImgOnlyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.chat_content_list_right_img_only_time)
        TextView mTime;
        @BindView(R.id.chat_content_list_right_img_only_portrait)
        ImageView mPortrait;
        @BindView(R.id.chat_content_list_right_img_only_img)
        ImageView mImg;

        public ChatRightItemImgOnlyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    /**
     * 左布局 txt
     * 0
     */
    class ChatLeftItemTxtOnlyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.chat_content_list_left_text_only_time)
        TextView mTime;
        @BindView(R.id.chat_content_list_left_text_only_portrait)
        ImageView mPortrait;
        @BindView(R.id.chat_content_list_left_text_only_txt)
        TextView mTxt;

        ChatLeftItemTxtOnlyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    /**
     * 左布局 img
     * 1
     */
    class ChatLeftItemImgOnlyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.chat_content_list_left_img_only_time)
        TextView mTime;
        @BindView(R.id.chat_content_list_left_img_only_portrait)
        ImageView mPortrait;
        @BindView(R.id.chat_content_list_left_img_only_img)
        ImageView mImg;

        ChatLeftItemImgOnlyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}
