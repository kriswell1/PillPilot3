package com.example.pillpilot.ui.main;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.ViewGroup;
import com.example.pillpilot.R;
import com.example.pillpilot.Pill;

import java.util.List;

public class PillListAdapter extends RecyclerView.Adapter<PillListAdapter.ViewHolder>{
    private final int pillItemLayout;
    private List<Pill> pillList;
    public PillListAdapter(int layoutId) {
        pillItemLayout = layoutId;
    }
    public void setillList(List<Pill> pills) {
        pillList = pills;
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        return pillList == null ? 0 : pillList.size();
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(pillItemLayout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int listPosition) {
        TextView item = holder.item;
        item.setText(pillList.get(listPosition).getName());
    }
    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView item;
        ViewHolder(View itemView) { super(itemView);
            item = itemView.findViewById(R.id.pill_row);
        }
    }

}//class
