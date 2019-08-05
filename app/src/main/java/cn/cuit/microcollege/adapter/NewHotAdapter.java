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

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.cuit.microcollege.R;
import cn.cuit.microcollege.application.MyApplication;
import cn.cuit.microcollege.base.BaseRcyAdapter;
import cn.cuit.microcollege.entity.HttpResultEntity.NewResultBean;

/**
 * @Author: Created by Rod Eden
 * @Date: 2019/5/4
 * @Descirption: NewHotResultBean.ResultBean.ListBean
 */
public class NewHotAdapter extends BaseRcyAdapter<NewResultBean.ResultBean.ListBean, NewHotAdapter.NewListViewHolder> {

    private OnItemClickListener mListener;


    public NewHotAdapter() {
    }

    public NewHotAdapter(List<NewResultBean.ResultBean.ListBean> list) {
        super(list);
    }

    public void setmListener(OnItemClickListener mListener) {
        this.mListener = mListener;
    }

    @NonNull
    @Override
    public NewListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_new_hot_item, parent, false);
        return new NewListViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull final NewListViewHolder holder, int position) {
        NewResultBean.ResultBean.ListBean listBean = getmList().get(position);

        holder.mSubTitleTv.setText(listBean.getSubTitle());
        holder.mTitleTv.setText(listBean.getTitle());
        holder.mCountTv.setText(listBean.getHitCount() + " 浏览");
        holder.mDateTv.setText(listBean.getPubTime());
        Glide.with(MyApplication.getMyContext())
                .load(listBean.getThumbnails())
                .apply(new RequestOptions().centerCrop().error(R.drawable.error))
                .into(holder.mBgImg);

        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onItemClick(holder.getAdapterPosition());
                }
            }
        });
    }

    class NewListViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.fragment_new_hot_subTitle_tv)
        TextView mSubTitleTv;
        @BindView(R.id.fragment_new_hot_bg_img)
        ImageView mBgImg;
        @BindView(R.id.fragment_new_hot_title_tv)
        TextView mTitleTv;
        @BindView(R.id.fragment_new_hot_count_tv)
        TextView mCountTv;
        @BindView(R.id.fragment_new_hot_date_tv)
        TextView mDateTv;
        View item;

        public NewListViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            item = view;
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }
}
