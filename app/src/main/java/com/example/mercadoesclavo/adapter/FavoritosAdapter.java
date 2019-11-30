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
import com.example.mercadoesclavo.model.DetalleProducto;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FavoritosAdapter extends RecyclerView.Adapter {

    private List<DetalleProducto> detalleProductoList;
    private FavoritosAdapterListener listener;

    public FavoritosAdapter(List<DetalleProducto> detalleProductoList, FavoritosAdapterListener listener) {
        this.detalleProductoList = detalleProductoList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.row_favoritos, parent, false);
        FavoritosViewHolder favoritosViewHolder = new FavoritosViewHolder(view);
        return favoritosViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
    DetalleProducto detalleProductoDeLaLista = this.detalleProductoList.get(position);
    FavoritosViewHolder favoritosViewHolder = (FavoritosViewHolder) holder;
    favoritosViewHolder.bindFavoritos(detalleProductoDeLaLista);
    }

    @Override
    public int getItemCount() {

        return this.detalleProductoList.size();
    }

    class FavoritosViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imageViewCardViewFavoritosRow)
        ImageView imageViewCardViewFavoritosRow;
        @BindView(R.id.textViewViewCardNombreFavoritosRow)
        TextView textViewViewCardNombreFavoritosRow;
        @BindView(R.id.textViewViewCardPrecioFavoritosRow)
        TextView textViewViewCardPrecioFavoritosRow;
        private DetalleProducto detalleProducto;

        public FavoritosViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Integer adapterPosition = getAdapterPosition();
                    DetalleProducto detalleProducto = detalleProductoList.get(adapterPosition);
                    listener.informarSeleccionFavoritos(adapterPosition, detalleProducto);
                }
            });
        }

        public void bindFavoritos(DetalleProducto detalleProductoDeLaCelda) {
            this.detalleProducto = detalleProductoDeLaCelda;
            Glide.with(itemView).load(this.detalleProducto.getThumbnail()).into(imageViewCardViewFavoritosRow);
            textViewViewCardNombreFavoritosRow.setText(this.detalleProducto.getTitle());
            textViewViewCardPrecioFavoritosRow.setText("$ " + this.detalleProducto.getPrice().toString());

        }
    }



    public interface FavoritosAdapterListener {
        void informarSeleccionFavoritos(Integer posicion, DetalleProducto detalleProducto);
    }
}
