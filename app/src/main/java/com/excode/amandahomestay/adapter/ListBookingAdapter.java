package com.excode.amandahomestay.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.excode.amandahomestay.BookkeepingActivity;
import com.excode.amandahomestay.R;
import com.excode.amandahomestay.database.BookingDatabase;
import com.excode.amandahomestay.model.Booking;

import java.util.ArrayList;

public class ListBookingAdapter extends RecyclerView.Adapter<ListBookingAdapter.ListViewHolder> {
    private ArrayList<Booking> listBooking;
    private Context context;
    private BookingDatabase database;

    public ListBookingAdapter(ArrayList<Booking> list, Context context) {
        this.listBooking = list;
        this.context = context;

        database = Room.databaseBuilder(context.getApplicationContext(),
                BookingDatabase.class, "booking_db").allowMainThreadQueries().build();
    }

    @NonNull
    @Override
    public ListBookingAdapter.ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_booking, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListBookingAdapter.ListViewHolder holder, int position) {
        final String bookingName = listBooking.get(position).getNamaPenyewa();
        final String roomNumber = listBooking.get(position).getNomorKamar();
        final String phoneNumber = listBooking.get(position).getNomorTelepon();
        final String bookingDate = listBooking.get(position).getTanggalMasuk();
        holder.tvItemBookingName.setText(bookingName);
        holder.tvItemRoomNumber.setText("Nomor Kamar\t\t: " + roomNumber);
        holder.tvItemPhoneNumber.setText("Nomor Telepon\t: " + phoneNumber);
        holder.tvItemBookingDate.setText("Booked on\n"+bookingDate);
        holder.imgItemBooking.setImageResource(R.drawable.ic_booking);
    }

    @Override
    public int getItemCount() {
        return listBooking.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener, MenuItem.OnMenuItemClickListener {
        CardView cvItemBooking;
        ImageView imgItemBooking, ivBgNotFound;
        TextView tvItemBookingName, tvItemRoomNumber, tvItemPhoneNumber, tvItemBookingDate;
        Button btnAddData;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            cvItemBooking = itemView.findViewById(R.id.cv_item_booking);
            imgItemBooking = itemView.findViewById(R.id.img_item_booking_photo);
            tvItemBookingName = itemView.findViewById(R.id.tv_item_booking_name);
            tvItemRoomNumber = itemView.findViewById(R.id.tv_item_room_number);
            tvItemPhoneNumber = itemView.findViewById(R.id.tv_item_phone_number);
            tvItemBookingDate = itemView.findViewById(R.id.tv_item_booking_date);

            ivBgNotFound = itemView.findViewById(R.id.iv_bg_not_found);
            btnAddData = itemView.findViewById(R.id.btn_add_data);

            itemView.setOnCreateContextMenuListener(this);
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            MenuItem edit = menu.add(Menu.NONE, 1, 1, "Edit");
            MenuItem delete = menu.add(Menu.NONE, 2, 2, "Delete");

            edit.setOnMenuItemClickListener(this);
            delete.setOnMenuItemClickListener(this);
        }

        @Override
        public boolean onMenuItemClick(MenuItem item) {
            switch (item.getItemId()) {
                case 1:
                    onEditBooking(getAdapterPosition());
                    break;
                case 2:
                    onDeleteBooking(getAdapterPosition());
            }
            return false;
        }
    }

    private void onEditBooking(int position) {
        context.startActivity(new Intent((Activity) context, BookkeepingActivity.class).putExtra("DATA_BOOKING", listBooking.get(position)));
    }

    public void onDeleteBooking(int position) {
        database.bookingDao().deleteBooking(listBooking.get(position));
        listBooking.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeRemoved(position, listBooking.size());
        Toast.makeText(context, "Data telah dihapus", Toast.LENGTH_SHORT).show();
    }
}
