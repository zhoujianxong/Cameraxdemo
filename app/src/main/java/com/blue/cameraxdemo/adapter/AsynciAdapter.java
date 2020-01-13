package com.blue.cameraxdemo.adapter;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.AsyncListDiffer;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.blue.cameraxdemo.R;

import java.util.List;

public class AsynciAdapter extends RecyclerView.Adapter<AsynciAdapter.ViewHolderView> {

    private AsyncListDiffer<String> mDiffer;

    private DiffUtil.ItemCallback<String> diffCallback = new DiffUtil.ItemCallback<String>() {
        @Override
        public boolean areItemsTheSame(@NonNull String oldItem, @NonNull String newItem) {
            return false;
        }

        @Override
        public boolean areContentsTheSame(@NonNull String oldItem, @NonNull String newItem) {
            return false;
        }
    };

    public AsynciAdapter() {
        mDiffer = new AsyncListDiffer<>(this, diffCallback);
    }

    public String getItem(int position){
        return mDiffer.getCurrentList().get(position);
    }
    public void submitList(List<String> data) {
        mDiffer.submitList(data);
    }

    @NonNull
    @Override
    public ViewHolderView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv, parent, false);
        return new ViewHolderView(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderView holder, int position) {
        holder.name_tv.setText(getItem(position));
    }

    @Override
    public int getItemCount() {
        return mDiffer.getCurrentList().size();
    }

    class ViewHolderView extends RecyclerView.ViewHolder {
        private TextView name_tv;

        public ViewHolderView(@NonNull View itemView) {
            super(itemView);
            name_tv = itemView.findViewById(R.id.item_name);
        }
    }
}
