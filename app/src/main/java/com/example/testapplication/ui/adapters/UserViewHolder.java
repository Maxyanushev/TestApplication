package com.example.testapplication.ui.adapters;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testapplication.R;
import com.example.testapplication.data.models.UserModel;

public class UserViewHolder extends RecyclerView.ViewHolder {
    private final TextView tvLoginUser, tvChangesCount;

    public UserViewHolder(@NonNull View itemView) {
        super(itemView);
        tvChangesCount = itemView.findViewById(R.id.tvChangesCount);
        tvLoginUser = itemView.findViewById(R.id.tvLoginUser);
    }

    public void onBind(UserModel model, GitUserAdapter.OnGitUserClickListener listener){
        tvLoginUser.setText(model.getLogin());
        tvChangesCount.setText(String.valueOf(model.getId()));
        itemView.setOnClickListener(v-> listener.onGitUserClick(model));
    }
}
