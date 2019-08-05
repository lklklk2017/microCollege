package cn.cuit.microcollege.base;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: Created by Rod Eden
 * @Date: 2019/4/25
 * @Descirption: 传参即可初始化
 */
public abstract class BaseRcyAdapter<T, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {

    private List<T> mList = new ArrayList<>(0);

    private Map<Integer, RecyclerView.ViewHolder> vhMap = new HashMap<>(0);

    public RecyclerView.ViewHolder getmVH(Integer key) {

        RecyclerView.ViewHolder vh = vhMap.get(key);
        if (vh != null) {
            return vh;
        } else {
            throw new RuntimeException("can't find view holder-key:" + key + ", be sure to invoked setVH(VH mVH) and try again");
        }
    }

    public void setmVH(Integer key, RecyclerView.ViewHolder mVH) {
        if (mVH != null && key != null) {
            vhMap.put(key, mVH);
        } else {
            throw new NullPointerException("key or vVH both can't be null!");
        }
    }

    /**
     * get default vh ：only have one viewholder
     *
     * @return
     */
    public VH getDefaultVH() {
        VH viewHolder = (VH) getmVH(0);
        if (viewHolder != null) {
            return viewHolder;
        } else {
            throw new RuntimeException("can't find view holder-key:" + 0 + ", be sure to invoked setDefaultVH(VH mVH) and try again");
        }
    }

    /**
     * set default vh ：only have one viewholder
     *
     * @return
     */
    public void setDefaultVH(VH mVH) {
        if (mVH != null) {
            setmVH(0, mVH);
        } else {
            throw new NullPointerException("mVH both can't be null!");
        }
    }

    public BaseRcyAdapter() {
        this(null);
    }

    public BaseRcyAdapter(List<T> list) {
        initList(list);
    }

    protected void initList(List<T> list) {
        if (list != null) {
            this.mList = list;
        }
    }

    public void changeList(List<T> list) {
        if (list != null) {
            this.mList = list;
            notifyDataSetChanged();
        }
    }

    /**
     * 新数据添加在后面
     *
     * @param list
     */
    public void addList(List<T> list) {
        if (list != null) {
            this.mList.addAll(list);
            notifyDataSetChanged();
        }
    }

    public void removeAll() {
        getmList().clear();
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return getmList().size();
    }

    public List<T> getmList() {
        if (mList == null) {
            throw new RuntimeException("adapter 中的list为null,请先初始化后再使用");
        }
        return mList;
    }

    public LayoutInflater getInflater(ViewGroup parent) {
        return LayoutInflater.from(parent.getContext());
    }

    public void removeItem(int currItemPosition) {
        if (currItemPosition < 0 || currItemPosition > getmList().size()) {
            throw new RuntimeException("recycleView item 在删除的时候 传入的position非法");
        }
        getmList().remove(getmList().get(currItemPosition));
        notifyItemRemoved(currItemPosition);
    }
}
