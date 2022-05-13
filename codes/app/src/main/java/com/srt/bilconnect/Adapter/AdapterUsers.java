package com.srt.bilconnect.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.srt.bilconnect.Model.User;
import com.srt.bilconnect.R;
import com.srt.bilconnect.databinding.FragmentUsersBinding;
import com.srt.bilconnect.databinding.PastEventsProfileRecyclerViewBinding;
import com.srt.bilconnect.databinding.RowUsersBinding;

import java.util.ArrayList;

public class AdapterUsers extends RecyclerView.Adapter<MyHolder>{
    ArrayList<User> userList;
    RowUsersBinding binding;


    public AdapterUsers(ArrayList<User> users) {
       super();
        this.userList = users;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = RowUsersBinding
                .inflate(LayoutInflater.from(parent.getContext()), parent, false);

        return new MyHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {

        String userImage = this.userList.get(position).getProfilePhotoURL();
        String userName = this.userList.get(position).getUsername();
        String userEmail = this.userList.get(position).getEmail();

        holder.mNameTv.setText(userName);
        holder.mEmailTv.setText(userEmail);
        Picasso.get().load(userImage).into(holder.mavatarIv);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }


}
class MyHolder extends RecyclerView.ViewHolder {

    ImageView mavatarIv;
    TextView mNameTv, mEmailTv;
    private RowUsersBinding binding;

    public MyHolder(RowUsersBinding binding) {
        super(binding.getRoot());
        this.binding = binding;


        mavatarIv = itemView.findViewById(R.id.avatarIv);
        mNameTv = itemView.findViewById(R.id.nameTv);
        mEmailTv = itemView.findViewById(R.id.emailTv);
    }

    public RowUsersBinding getBinding() { return this.binding; }
}

