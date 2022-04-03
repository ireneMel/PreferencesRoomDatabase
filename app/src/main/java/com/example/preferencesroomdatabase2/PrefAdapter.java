package com.example.preferencesroomdatabase2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PrefAdapter extends RecyclerView.Adapter<PrefAdapter.PrefViewHolder> {

    private final LayoutInflater inflater;
    private List<TblPrefs> prefList; //cached copy

    public PrefAdapter(Context context) {
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public PrefViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PrefViewHolder(inflater.inflate(R.layout.recyclerview_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PrefViewHolder holder, int position) {
        if(prefList != null) {
            TblPrefs current = prefList.get(position);
            holder.view.setText(new StringBuilder().append(current.getPrefKey()).append(" : ").append(current.getPrefValue()).toString());
        } else
            holder.view.setText("no data");
    }

    void setPrefs(List<TblPrefs> prefList){
        this.prefList = prefList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if(prefList != null) return prefList.size();
        return 0;
    }

    static class PrefViewHolder extends RecyclerView.ViewHolder{
        private final TextView view;
        public PrefViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView.findViewById(R.id.rec_item);
        }
    }
}
