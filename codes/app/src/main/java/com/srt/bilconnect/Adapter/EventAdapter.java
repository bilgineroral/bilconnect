package com.srt.bilconnect.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.squareup.picasso.Picasso;
import com.srt.bilconnect.Model.Event;
import com.srt.bilconnect.Model.User;
import com.srt.bilconnect.View.EventPageActivity;
import com.srt.bilconnect.View.MainPageActivity;
import com.srt.bilconnect.databinding.PastEventsProfileRecyclerViewBinding;

import java.util.ArrayList;

public class EventAdapter extends RecyclerView.Adapter<EventHolder> {

    private ArrayList<Event> list;
    PastEventsProfileRecyclerViewBinding binding;

    public EventAdapter(ArrayList<Event> events) {
        super();
        this.list = events;
    }

    @NonNull
    @Override
    public EventHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
         binding = PastEventsProfileRecyclerViewBinding
                .inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new EventHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull EventHolder holder, int position) {
        holder.getBinding().eventNameText.setText(this.list.get(position).getTitle());
        holder.getBinding().userNameText.setText(this.list.get(position).getHost().getUsername());
        holder.getBinding().quotaText.setText(this.list.get(position).getAttendees().size() + "/" + this.list.get(position).getQuota());
        holder.getBinding().detailsText.setText(this.list.get(position).getDescription());
        holder.getBinding().dateTextRow.setText(this.list.get(position).getDate());
        holder.getBinding().timeTextRow.setText(this.list.get(position).getTime());
        Picasso.get().load(this.list.get(position).getHost().getProfilePhotoURL()).into(holder.getBinding().userPP);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.itemView.getContext(), EventPageActivity.class);
                intent.putExtra("event", list.get(holder.getAdapterPosition()));
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}

class EventHolder extends RecyclerView.ViewHolder {

    private PastEventsProfileRecyclerViewBinding binding;

    public EventHolder(PastEventsProfileRecyclerViewBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }



    public PastEventsProfileRecyclerViewBinding getBinding() {
        return this.binding;
    }
}