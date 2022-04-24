package com.example.testapplication.ui.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testapplication.R;
import com.example.testapplication.data.models.UserModel;

import java.util.ArrayList;
import java.util.List;

public class GitUserAdapter extends RecyclerView.Adapter<com.example.testapplication.ui.adapters.UserViewHolder>{

    //  // ОБРАБОТКА НАЖАТИЯ делаем слушатель для нажатия на Холдер
    public interface OnGitUserClickListener{
        // этот метод ждет выранный Холдер и его позицию
        void onGitUserClick(UserModel gitUser);
    }

    // // ОБРАБОТКА НАЖАТИЯ переменная для хранения объекта этого интерфейса
    private final OnGitUserClickListener listener;

    private List<UserModel> users = new ArrayList();

    // 3. в качестве параметров передаем Context - это активити, из которого вызывается адаптер и в котором будет отображаться наш RecyclerView
    //  // ОБРАБОТКА НАЖАТИЯ также передаем OnGitUserClickListener
    public GitUserAdapter( OnGitUserClickListener listener) {
        this.listener = listener;  // ОБРАБОТКА НАЖАТИЯ
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setUsers(List<UserModel> users){
        this.users = users;
        notifyDataSetChanged();
    }

    // 5. методы Адаптера:
    @NonNull
    @Override
    // 5.1. делает из list_item.xml обычную View и передает ее в качестве параметра ViewHolder(view)
    public com.example.testapplication.ui.adapters.UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new com.example.testapplication.ui.adapters.UserViewHolder(view);
    }

    @Override
    // 5.2. делает привязку ViewHolder к определенной позиции (как-то так)
    public void onBindViewHolder(com.example.testapplication.ui.adapters.UserViewHolder holder, int position) {

        holder.onBind(users.get(position), listener);
    }

    @Override
    // 5.3. возвращает кол-во позиций
    public int getItemCount() {
        return users.size();
    }

}
