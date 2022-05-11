package com.srt.bilconnect.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.srt.bilconnect.Model.Event;
import com.srt.bilconnect.View.EventPageActivity;
import com.srt.bilconnect.databinding.MapEventsRecyclerViewBinding;

import java.util.ArrayList;

public class MapEventAdapter extends RecyclerView.Adapter<MapEventHolder> {

    private ArrayList<Event> list;
    MapEventsRecyclerViewBinding binding;

    public MapEventAdapter(ArrayList<Event> events) {
        super();
        this.list = events;
    }

    @NonNull
    @Override
    public MapEventHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = MapEventsRecyclerViewBinding
                .inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new MapEventHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MapEventHolder holder, int position) {
        holder.getBinding().eventNameText.setText(this.list.get(position).getTitle());
        holder.getBinding().userNameText.setText(this.list.get(position).getHost().getUsername());
        holder.getBinding().quotaText.setText("katılımcı/" + this.list.get(position).getQuota());
        holder.getBinding().detailsText.setText(this.list.get(position).getDescription());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.itemView.getContext(), EventPageActivity.class);
                intent.putExtra("mapevents", list.get(holder.getAdapterPosition()));
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}

class MapEventHolder extends RecyclerView.ViewHolder {

    private MapEventsRecyclerViewBinding binding;

    public MapEventHolder(MapEventsRecyclerViewBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public MapEventsRecyclerViewBinding getBinding() {
        return this.binding;
    }
}