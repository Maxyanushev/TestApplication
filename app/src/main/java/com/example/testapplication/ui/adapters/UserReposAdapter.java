package com.example.testapplication.ui.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testapplication.R;
import com.example.testapplication.data.models.UserRepos;

import java.util.ArrayList;
import java.util.List;

public class UserReposAdapter extends RecyclerView.Adapter<UserReposAdapter.ViewHolder> {

    private List<UserRepos> userReposList = new ArrayList();

    @SuppressLint("NotifyDataSetChanged")
    public void setList(List<UserRepos> userReposList) {
        this.userReposList = userReposList;
        notifyDataSetChanged();
    }

    // 5. методы Адаптера:
    @NonNull
    @Override
    // 5.1. делает из list_item.xml обычную View и передает ее в качестве параметра ViewHolder(view)
    public UserReposAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_user_repos, parent, false);
        return new UserReposAdapter.ViewHolder(view);
    }

    @Override
    // 5.2. делает привязку ViewHolder к определенной позиции (как-то так)
    public void onBindViewHolder(UserReposAdapter.ViewHolder holder, int position) {
        UserRepos userRepos = userReposList.get(position); // получает объект GitUser из List'а согласно переданной позиции
        // и берет из него данные для их присвоения элементам Holder'а
        holder.repoView.setText(userRepos.getName());
    }

    @Override
    // 5.3. возвращает кол-во позиций
    public int getItemCount() {
        return userReposList.size();
    }

    // 1. создает Holder на основе view'шек из list_item.xml
    public static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView repoView;

        ViewHolder(View view) {
            super(view);
            repoView = (TextView) view.findViewById(R.id.tvRepo);
        }
    }
}
