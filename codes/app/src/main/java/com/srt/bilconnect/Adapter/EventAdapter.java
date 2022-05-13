package com.srt.bilconnect.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
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
    private FirebaseFirestore firebaseFirestore;
    private FirebaseAuth auth;
    private int pos;

    public EventAdapter(ArrayList<Event> events) {
        super();
        this.list = events;
    }

    @NonNull
    @Override
    public EventHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
         binding = PastEventsProfileRecyclerViewBinding
                .inflate(LayoutInflater.from(parent.getContext()), parent, false);
         firebaseFirestore = FirebaseFirestore.getInstance();
         auth = FirebaseAuth.getInstance();
        return new EventHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull EventHolder holder, int position) {
        pos = position;
        holder.getBinding().eventNameText.setText(this.list.get(pos).getTitle());
        holder.getBinding().userNameText.setText(this.list.get(pos).getHost().getUsername());
        holder.getBinding().quotaText.setText(this.list.get(pos).getAttendees().size() + "/" + this.list.get(pos).getQuota());
        holder.getBinding().detailsText.setText(this.list.get(pos).getDescription());
        holder.getBinding().dateTextRow.setText(this.list.get(pos).getDate());
        holder.getBinding().timeTextRow.setText(this.list.get(pos).getTime());
        Picasso.get().load(this.list.get(pos).getHost().getProfilePhotoURL()).into(holder.getBinding().userPP);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.itemView.getContext(), EventPageActivity.class);
                intent.putExtra("event", list.get(holder.getAdapterPosition()));
                holder.itemView.getContext().startActivity(intent);
            }
        });

        holder.getBinding().registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!list.get(holder.getBindingAdapterPosition()).getHost().getUserID().equals(auth.getCurrentUser().getUid())) {
                    firebaseFirestore.collection("UserData").document(auth.getCurrentUser().getUid()).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                            User user = documentSnapshot.toObject(User.class);
                            firebaseFirestore.collection("EventData").document(list.get(holder.getBindingAdapterPosition()).getEventDocumentPlace()).update("attendees", FieldValue.arrayUnion(user));
                            firebaseFirestore.collection("UserData").document(auth.getCurrentUser().getUid()).update("registeredEvents", FieldValue.arrayUnion(list.get(holder.getBindingAdapterPosition())));
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(v.getContext(), e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    Toast.makeText(v.getContext(), "You are the creator of the event", Toast.LENGTH_SHORT).show();
                }
                
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