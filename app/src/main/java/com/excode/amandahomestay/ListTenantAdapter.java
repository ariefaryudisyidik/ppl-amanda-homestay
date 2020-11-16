package com.excode.amandahomestay;

import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

public class ListTenantAdapter extends RecyclerView.Adapter<ListTenantAdapter.ListViewHolder> {
    private ArrayList<Tenant> listTenant;

    public ListTenantAdapter(ArrayList<Tenant> list) {
        this.listTenant = list;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_row_tenant, viewGroup, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        Tenant tenant = listTenant.get(position);
        Glide.with(holder.itemView.getContext()).load(tenant.getPhoto()).apply(new RequestOptions().override(55, 55)).into(holder.imgPhoto);
        holder.tvName.setText(tenant.getName());
        holder.tvDetail.setText(tenant.getDetail());
    }

    @Override
    public int getItemCount() {
        return listTenant.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPhoto;
        TextView tvName, tvDetail;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPhoto = itemView.findViewById(R.id.img_item_photo);
            tvName = itemView.findViewById(R.id.tv_item_name);
            tvDetail = itemView.findViewById(R.id.tv_item_detail);
        }
    }
}
