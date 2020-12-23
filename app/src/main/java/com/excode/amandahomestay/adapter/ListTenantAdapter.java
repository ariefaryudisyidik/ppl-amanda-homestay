package com.excode.amandahomestay.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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
import com.excode.amandahomestay.database.BookkeepingDatabase;
import com.excode.amandahomestay.model.Bookkeeping;

import java.util.ArrayList;

public class ListTenantAdapter extends RecyclerView.Adapter<ListTenantAdapter.ListViewHolder> {
    private ArrayList<Bookkeeping> listTenant;
    private Context context;
    private BookkeepingDatabase database;

    public ListTenantAdapter(ArrayList<Bookkeeping> list, Context context) {
        this.listTenant = list;
        this.context = context;

        database = Room.databaseBuilder(context.getApplicationContext(),
                BookkeepingDatabase.class, "bookkeeping_db").allowMainThreadQueries().build();
    }

    @NonNull
    @Override
    public ListTenantAdapter.ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_tenant, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListTenantAdapter.ListViewHolder holder, int position) {
        final String tenantName = listTenant.get(position).getNamaPenyewa();
        final String roomNumber = listTenant.get(position).getNomorKamar();
        final String phoneNumber = listTenant.get(position).getNomorTelepon();
        holder.tvItemTenantName.setText(tenantName);
        holder.tvItemRoomNumber.setText("Nomor Kamar\t\t: " + roomNumber);
        holder.tvItemPhoneNumber.setText("Nomor Telepon\t: " + phoneNumber);
        holder.imgItemTenant.setImageResource(R.drawable.ic_tenant_data);
    }

    @Override
    public int getItemCount() {
        return listTenant.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener, MenuItem.OnMenuItemClickListener {
        CardView cvItemtenant;
        ImageView imgItemTenant, ivBgNotFound;
        TextView tvItemTenantName, tvItemRoomNumber, tvItemPhoneNumber;
        Button btnAddData;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            cvItemtenant = itemView.findViewById(R.id.cv_item_tenant);
            imgItemTenant = itemView.findViewById(R.id.img_item_tenant_photo);
            tvItemTenantName = itemView.findViewById(R.id.tv_item_tenant_name);
            tvItemRoomNumber = itemView.findViewById(R.id.tv_item_room_number);
            tvItemPhoneNumber = itemView.findViewById(R.id.tv_item_phone_number);

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
                    onEditTenant(getAdapterPosition());
                    break;
                case 2:
                    onDeleteTenant(getAdapterPosition());
            }
            return false;
        }
    }

    private void onEditTenant(int position) {
        context.startActivity(new Intent((Activity) context, BookkeepingActivity.class).putExtra("DATA", listTenant.get(position)));
    }

    public void onDeleteTenant(int position) {
        database.bookkeepingDao().deleteBookkeeping(listTenant.get(position));
        listTenant.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeRemoved(position, listTenant.size());
        Toast.makeText(context, "Data telah dihapus", Toast.LENGTH_SHORT).show();
    }
}
