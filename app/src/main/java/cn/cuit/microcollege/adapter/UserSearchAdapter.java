package cn.cuit.microcollege.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView.ViewHolder;
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
import cn.cuit.microcollege.entity.HttpResultEntity.GetUserBaseInfoResultBean;
import cn.cuit.microcollege.utils.TransFormUtil;

/**
 * @Author: Created by Rod Eden
 * @Date: 2019/4/29
 * @Descirption: MyCircleActivit 我的圈子的适配器
 */
public class UserSearchAdapter extends BaseRcyAdapter<GetUserBaseInfoResultBean.UserListBean, UserSearchAdapter.MyCircleViewHolder> {
    private static AdapterClickListener listener;

    public void setAdapterClickListener(AdapterClickListener listener) {
        this.listener = listener;
    }

    public UserSearchAdapter() {
        super();
    }

    public UserSearchAdapter(List<GetUserBaseInfoResultBean.UserListBean> list) {
        super(list);
    }

    @NonNull
    @Override
    public MyCircleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = getInflater(parent).inflate(R.layout.fragment_search_detail_user_item, parent, false);
        return new MyCircleViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyCircleViewHolder holder, int position) {
        //data

        GetUserBaseInfoResultBean.UserListBean userBean = getmList().get(position);
        String name = userBean.getUsername();//name
        String url = userBean.getAvatarImgUrl();//img url
        String sign = userBean.getSign();//sign
        String gender = userBean.getGender();//性别


        holder.mUserNameTv.setText(name);
        if (url == null) {
            Glide.with(MyApplication.getMyContext())
                    .load(R.drawable.logo_temp)
                    .apply(RequestOptions.bitmapTransform(new CircleCrop())
                            .error(R.drawable.error))
                    .into(holder.mUserPortraitImg);
        } else {
            Glide.with(MyApplication.getMyContext())
                    .load(TransFormUtil.getLocalImageUrl(url))
                    .apply(RequestOptions.bitmapTransform(new CircleCrop())
                            .error(R.drawable.error))
                    .into(holder.mUserPortraitImg);
        }

        //sign
        holder.mUserSignTv.setText(sign);
        //gender
        if (gender != null && gender.length() != 0) {
            if (gender.equals("男")) {
                holder.mUserGender.setImageResource(R.drawable.man_black);
            } else {
                holder.mUserGender.setImageResource(R.drawable.woman_pink);
            }
        }

        //click
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onItemClick(holder.getAdapterPosition());
                }
            }
        });

    }

    class MyCircleViewHolder extends ViewHolder {
        @BindView(R.id.fragment_search_detail_user_item_portrait)
        ImageView mUserPortraitImg;
        @BindView(R.id.fragment_search_detail_user_item_user_name)
        TextView mUserNameTv;
        @BindView(R.id.fragment_search_detail_user_item_user_sign)
        TextView mUserSignTv;
        @BindView(R.id.fragment_search_detail_user_item_user_gender)
        ImageView mUserGender;
        View itemView;

        public MyCircleViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            ButterKnife.bind(this, itemView);
        }
    }

    public interface AdapterClickListener {
        void onItemClick(int position);
    }
}
