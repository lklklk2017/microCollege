package cn.cuit.microcollege.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.cuit.microcollege.R;
import cn.cuit.microcollege.application.MyApplication;
import cn.cuit.microcollege.base.BaseRcyAdapter;
import cn.cuit.microcollege.entity.HttpResultEntity.GetMyCircleResultBean;
import cn.cuit.microcollege.utils.TransFormUtil;

/**
 * @Author: Created by Rod Eden
 * @Date: 2019/4/29
 * @Descirption:  MyCircleActivit 我的圈子的适配器
 */
public class MyCircleAdapter extends BaseRcyAdapter<GetMyCircleResultBean.CirclesBean, MyCircleAdapter.MyCircleViewHolder> {

    private static AdapterClickListener listener;

    public void setAdapterClickListener(AdapterClickListener listener) {
        this.listener = listener;
    }

    public MyCircleAdapter() {
        super();
    }

    public MyCircleAdapter(List<GetMyCircleResultBean.CirclesBean> list) {
        super(list);
    }

    @NonNull
    @Override
    public MyCircleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = getInflater(parent).inflate(R.layout.activity_mycircle_circle_item, parent, false);
        return new MyCircleViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyCircleViewHolder holder, int position) {
        //data

        GetMyCircleResultBean.CirclesBean circlesBean = getmList().get(position);
        String circleAvaterUrl = circlesBean.getCircleAvaterUrl();
        String circleName = circlesBean.getCircleName();

        holder.mCircleNameTv.setText(circleName);
        if (circleAvaterUrl == null) {
            Glide.with(MyApplication.getMyContext())
                    .load(R.drawable.logo_temp)
                    .apply(new RequestOptions()
                            .centerCrop())
                    .into(holder.mPortraitImg);
        } else {
            Glide.with(MyApplication.getMyContext())
                    .load(TransFormUtil.getLocalImageUrl(circleAvaterUrl))
                    .apply(new RequestOptions()
                            .placeholderOf(R.drawable.logo_temp)
                            .centerCrop()
                            .error(R.drawable.error)
                    )
                    .into(holder.mPortraitImg);
        }

        holder.mCircleTitleTv.setText(circlesBean.getCircleTitle());
        //click
        holder.mCircleOptionImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onItemOptionClick(holder.getAdapterPosition());
                }
            }
        });

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
        @BindView(R.id.activity_my_circle_rcy_item_portrait)
        ImageView mPortraitImg;
        @BindView(R.id.activity_my_circle_rcy_item_circle_name)
        TextView mCircleNameTv;
        @BindView(R.id.activity_my_circle_rcy_item_circle_title)
        TextView mCircleTitleTv;
        @BindView(R.id.activity_my_circle_rcy_item_circle_option)
        ImageView mCircleOptionImg;
        View itemView;

        public MyCircleViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            ButterKnife.bind(this, itemView);
        }
    }

    public interface AdapterClickListener {
        void onItemClick(int position);

        void onItemOptionClick(int position);
    }
}
