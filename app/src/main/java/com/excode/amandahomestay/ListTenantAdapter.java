package com.excode.amandahomestay;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

public class ListTenantAdapter extends RecyclerView.Adapter<ListTenantAdapter.ListViewHolder> {
    private ArrayList<GetterSetter> listGetterSetter;

    public ListTenantAdapter(ArrayList<GetterSetter> list) {
        this.listGetterSetter = list;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_row_tenant, viewGroup, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        GetterSetter getterSetter = listGetterSetter.get(position);
        Glide.with(holder.itemView.getContext()).load(getterSetter.getTenantPhoto()).apply(new RequestOptions().override(55, 55)).into(holder.imgPhoto);
        holder.tvName.setText(getterSetter.getTenantName());
        holder.tvDetail.setText(getterSetter.getTenantDetail());
    }

    @Override
    public int getItemCount() {
        return listGetterSetter.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPhoto;
        TextView tvName, tvDetail;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPhoto = itemView.findViewById(R.id.img_item_tenant_photo);
            tvName = itemView.findViewById(R.id.tv_item_room_number);
            tvDetail = itemView.findViewById(R.id.tv_item_tenant_detail);
        }
    }
}
