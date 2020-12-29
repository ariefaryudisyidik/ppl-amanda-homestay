package com.excode.amandahomestay.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.excode.amandahomestay.R;
import com.excode.amandahomestay.database.BookkeepingDatabase;
import com.excode.amandahomestay.model.Bookkeeping;

import java.util.ArrayList;


public class GridRoomStatusAdapter extends RecyclerView.Adapter<GridRoomStatusAdapter.GridViewHolder> {
    private ArrayList<Bookkeeping> listRoomStatus;
    private Context context;
    private BookkeepingDatabase database;

    public GridRoomStatusAdapter(ArrayList<Bookkeeping> list, Context context) {
        this.listRoomStatus = list;
        this.context = context;

        database = Room.databaseBuilder(context.getApplicationContext(),
                BookkeepingDatabase.class, "bookkeeping_db").allowMainThreadQueries().build();
    }

    @NonNull
    @Override
    public GridViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_grid_room_status, viewGroup, false);
        return new GridViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GridViewHolder holder, int position) {
        final String roomNumber = listRoomStatus.get(position).getNomorKamar();
        holder.tvRoomNumber.setText(roomNumber);
    }

    @Override
    public int getItemCount() {
        return listRoomStatus.size();
    }

    public class GridViewHolder extends RecyclerView.ViewHolder {
        TextView tvRoomNumber;

        public GridViewHolder(@NonNull View itemView) {
            super(itemView);
            tvRoomNumber = itemView.findViewById(R.id.tv_item_booking_name);
        }
    }

}

