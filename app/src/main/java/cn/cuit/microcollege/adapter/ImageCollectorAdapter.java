package cn.cuit.microcollege.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.cuit.microcollege.R;
import cn.cuit.microcollege.base.BaseRcyAdapter;
import cn.cuit.microcollege.entity.DataEntity.ImageDataBean;

/**
 * @Author: Created by Rod Eden
 * @Date: 2019/5/1
 * @Descirption:
 */
public class ImageCollectorAdapter extends BaseRcyAdapter<ImageDataBean, ImageCollectorAdapter.ImageViewHolder> {

    private Context context;
    private OnItemClickListener listener;

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public ImageCollectorAdapter() {
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public ImageCollectorAdapter(List<ImageDataBean> list) {
        super(list);
    }

    /**
     * 0: +
     * 1:其他
     *
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        int type = 0;
        ImageDataBean imageDataBean = getmList().get(position);
        if (imageDataBean.getType() == 0) {
            type = 0;
        }
        if (imageDataBean.getType() == 1) {
            type = 1;
        }
        return type;
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = getInflater(parent).inflate(R.layout.publish_item, parent, false);
        ImageViewHolder mVH = new ImageViewHolder(inflate);
        setDefaultVH(mVH);
        return mVH;
    }

    @Override
    public void onBindViewHolder(@NonNull final ImageViewHolder holder, int position) {

        final ImageDataBean bean = getmList().get(position);

        final int type = bean.getType();

        if (type == 1) {
//            holder.img.setImageDrawable(context.getResources().getDrawable((Integer) bean.getImgUrl()));

            Glide.with(context)
                    .load(bean.getImgUrl())//按文件路径传入
                    .thumbnail(0.2f)
                    .apply(
                            new RequestOptions()
                                    .override(300, 300)
                                    .centerCrop()
                                    .error(R.drawable.error)
                    )
                    .into(holder.img);

            holder.img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.onItemClick(holder.getAdapterPosition());
                    }
                }
            });
            holder.img.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if (listener != null) {
                        listener.onItemLongClick(holder.getAdapterPosition());
                        return true;
                    }
                    return false;
                }
            });
        } else {
            //添加+
            if (context != null) {
                holder.img.setImageDrawable(context.getResources().getDrawable((Integer) bean.getImgUrl()));
                holder.img.setPadding(75, 75, 75, 75);
                holder.img.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (listener != null) {
                            listener.onAddItemClick(holder.getAdapterPosition());
                        }
                    }
                });
            }
        }
    }

    /**
     * 倒序添加 并且在第一个位置的地方插入
     *
     * @param imageDataBean
     */
    public void addItemDesc(ImageDataBean imageDataBean) {
        List<ImageDataBean> ts = getmList();
        Collections.reverse(ts);
//        String imgUrl = imageDataBean.getImgUrl().toString();
//        String[] split = imgUrl.split("\\.");
//        String finalName = split[0].trim() + UUID.randomUUID().toString().substring(0, 4) + "." + split[1].trim();
//        imageDataBean.setImgUrl(finalName);
        ts.add(imageDataBean);
        Collections.reverse(ts);
        initList(ts);
        notifyItemInserted(0);
    }

    /**
     * 正序添加  每次添加在后面
     *
     * @param t
     */
    public void addItemAse(ImageDataBean t) {
        List<ImageDataBean> ts = getmList();
        ts.add(t);
        initList(ts);
        notifyItemInserted(8);
    }

    static class ImageViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.publish_item_img)
        ImageView img;

        ImageViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int position);

        void onAddItemClick(int position);

        void onItemLongClick(int position);
    }

}
