package com.example.mercadoesclavo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mercadoesclavo.model.Producto;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductoAdapter extends RecyclerView.Adapter {

    private List<Producto> productoList;

    public ProductoAdapter(List<Producto> productoList) {
        this.productoList = productoList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.celda_main, parent, false);
        ProductoViewHolder productoViewHolder = new ProductoViewHolder(view);
        return productoViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Producto productoDeLaLista = this.productoList.get(position);
        ProductoViewHolder productoViewHolder = (ProductoViewHolder) holder;
        productoViewHolder.bindProducto(productoDeLaLista);
    }

    @Override
    public int getItemCount() {
        return this.productoList.size();
    }

    class ProductoViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imageViewCardViewMain)
        ImageView imageViewCardViewMain;
        @BindView(R.id.textViewViewCardNombreMain)
        TextView textViewCardViewNombreMain;
        @BindView(R.id.textViewViewCardPrecioMain)
        TextView textViewCardViewPrecioMain;
        private Producto producto;


        public ProductoViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bindProducto(Producto productoDeLaCelda) {
            this.producto = productoDeLaCelda;
            this.textViewCardViewNombreMain.setText(this.producto.getNombre());
            this.textViewCardViewPrecioMain.setText(this.producto.getPrecio());
            this.imageViewCardViewMain.setImageResource(this.producto.getImagen());
        }
    }


}

