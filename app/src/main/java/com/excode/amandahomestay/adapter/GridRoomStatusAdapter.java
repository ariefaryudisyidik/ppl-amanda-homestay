package com.excode.amandahomestay.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class GridRoomStatusAdapter extends RecyclerView.Adapter<GridRoomStatusAdapter.GridViewHolder> {
    private ArrayList<Tenant> listRoomStatus;

    public GridRoomStatusAdapter(ArrayList<Tenant> list) {
        this.listRoomStatus = list;
    }

    @NonNull
    @Override
    public GridViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_grid_room_status, viewGroup, false);
        return new GridViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GridViewHolder holder, int position) {
        Tenant roomStatus = listRoomStatus.get(position);
        holder.tvRoomNumber.setText(roomStatus.getRoomNumber());
    }

    @Override
    public int getItemCount() {
        return listRoomStatus.size();
    }

    public class GridViewHolder extends RecyclerView.ViewHolder {
        TextView tvRoomNumber;

        public GridViewHolder(@NonNull View itemView) {
            super(itemView);
            tvRoomNumber = itemView.findViewById(R.id.tv_item_tenant_name);
        }
    }

}

