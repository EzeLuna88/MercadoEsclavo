package com.example.mercadoesclavo.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mercadoesclavo.R;
import com.example.mercadoesclavo.model.Producto;
import com.example.mercadoesclavo.model.Results;
import com.example.mercadoesclavo.utils.ResultListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BusquedaAdapter extends RecyclerView.Adapter {

    private Producto producto;
    private List<Results> resultsList;
    private BusquedaAdapterListener listener;

    public BusquedaAdapter(List<Results> resultsList, BusquedaAdapterListener listener) {
        this.resultsList = resultsList;
        this.listener = listener;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.row_products, parent, false);
        BusquedaViewHolder busquedaViewHolder = new BusquedaViewHolder(view);
        return busquedaViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Results resultsDeLaLista = this.resultsList.get(position);
        BusquedaViewHolder productoViewHolder = (BusquedaViewHolder) holder;
        productoViewHolder.bindBusqueda(resultsDeLaLista);
    }

    @Override
    public int getItemCount() {
        return this.resultsList.size();
    }

    class BusquedaViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imageViewCardViewProductsRow)
        ImageView imageViewCardViewMain;
        @BindView(R.id.textViewViewCardNombreProductsRow)
        TextView textViewCardViewNombreMain;
        @BindView(R.id.textViewViewCardPrecioMain)
        TextView textViewCardViewPrecioMain;
        private Results results;


        public BusquedaViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Integer adapterPosition = getAdapterPosition();
                    Results results = resultsList.get(adapterPosition);
                    listener.informarSeleccionBusqueda(adapterPosition);
                }
            });
        }


        public void bindBusqueda(Results resultsDeLaCelda) {
            this.results = resultsDeLaCelda;
            Glide.with(itemView).

                    load(this.results.getThumbnail()).

                    into(imageViewCardViewMain);
            textViewCardViewNombreMain.setText(this.results.getTitle());
            textViewCardViewPrecioMain.setText("$ " + this.results.getPrice().
                    toString());

        }

    }

    public interface BusquedaAdapterListener {
        void informarSeleccionBusqueda(Integer posicion);
    }

}
