package cn.cuit.microcollege.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import cn.cuit.microcollege.R;
import cn.cuit.microcollege.base.BaseRcyAdapter;

/**
 * @Author: Created by Rod Eden
 * @Date: 2019/4/25
 * @Descirption:
 */
public class SearchFragmentHistoryAdapter extends BaseRcyAdapter<String, SearchFragmentHistoryAdapter.SearchHistoryViewHolder> {
    private onItemClickListener listener;

    public void setListener(onItemClickListener listener) {
        this.listener = listener;
    }

    public SearchFragmentHistoryAdapter(List<String> list) {
        super(list);
    }

    @NonNull
    @Override
    public SearchHistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_rcyitem_history, parent, false);
        return new SearchHistoryViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull final SearchHistoryViewHolder holder, final int position) {
        final String recordName = getmList().get(position);
        holder.mHistoryName.setText(recordName);
        holder.mHistoryName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null && !TextUtils.isEmpty(recordName)) {
                    listener.onItemClick(recordName);
                }
            }
        });

    }

    static class SearchHistoryViewHolder extends RecyclerView.ViewHolder {
        TextView mHistoryName;

        public SearchHistoryViewHolder(View itemView) {
            super(itemView);
            mHistoryName = ((TextView) itemView.findViewById(R.id.search_fragment_history_item_text));
        }
    }

    public interface onItemClickListener {

        void onItemClick(String recordName);
    }

}
