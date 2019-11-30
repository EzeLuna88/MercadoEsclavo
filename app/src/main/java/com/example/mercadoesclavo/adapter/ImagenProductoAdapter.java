package com.example.mercadoesclavo.adapter;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mercadoesclavo.model.Pictures;

public class ImagenProductoAdapter extends RecyclerView.Adapter {
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }


    class ImagenProductoViewHolder extends RecyclerView.ViewHolder {
        private Pictures picture;

        public ImagenProductoViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
