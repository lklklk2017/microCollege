package cn.cuit.microcollege.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import cn.cuit.microcollege.R;
import cn.cuit.microcollege.application.MyApplication;
import cn.cuit.microcollege.base.BaseRcyAdapter;
import cn.cuit.microcollege.entity.HttpResultEntity.GetMyCircleResultBean;
import cn.cuit.microcollege.utils.TransFormUtil;

/**
 * @Author: Created by Rod Eden
 * @Date: 2019/4/23
 * @Descirption:  主页-HomeFragment-mycircle-list-adapter
 */
public class MyCircleHomeAdapter extends BaseRcyAdapter<GetMyCircleResultBean.CirclesBean, MyCircleHomeAdapter.NativeViewHolder> {

    public MyCircleHomeAdapter() {
        super();
    }

    public MyCircleHomeAdapter(List<GetMyCircleResultBean.CirclesBean> list) {
        super(list);
    }

    @NonNull
    @Override
    public NativeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(MyApplication.getMyContext()).inflate(R.layout.home_rcyitem_circle, parent, false);
        return new NativeViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull final NativeViewHolder holder, final int position) {
        GetMyCircleResultBean.CirclesBean circlesBean = getmList().get(position);
        String circleAvaterUrl = circlesBean.getCircleAvaterUrl();
        String circleName = circlesBean.getCircleName();

        holder.mCircleName.setText(circleName);
        if (circleAvaterUrl == null) {
            Glide.with(MyApplication.getMyContext())
                    .load(R.drawable.logo_temp)
                    .apply(new RequestOptions()
                            .centerCrop())
                    .into(holder.mImg);
        } else {
            Glide.with(MyApplication.getMyContext())
                    .load(TransFormUtil.getLocalImageUrl(circleAvaterUrl))
                    .apply(new RequestOptions()
                            .placeholderOf(R.drawable.logo_temp)
                            .centerCrop()
                            .error(R.drawable.error)
                    )
                    .into(holder.mImg);
        }

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onItemClick(getmList().get(holder.getAdapterPosition()));
                }
            }
        });
    }

    static class NativeViewHolder extends RecyclerView.ViewHolder {
        public ImageView mImg;
        public TextView mCircleName;
        public View view;

        public NativeViewHolder(View itemView) {
            super(itemView);
            this.view = itemView;
            mImg = (ImageView) itemView.findViewById(R.id.home_fragment_circleItem_img);
            mCircleName = (TextView) itemView.findViewById(R.id.home_fragment_circleItem_tv);
        }
    }

    private CircleItemClickListener listener;

    public void setListener(CircleItemClickListener listener) {
        this.listener = listener;
    }

    public interface CircleItemClickListener {
        void onItemClick(GetMyCircleResultBean.CirclesBean circlesBean);
    }

}
