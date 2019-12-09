package com.example.mercadoesclavo.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mercadoesclavo.R;
import com.example.mercadoesclavo.dto.DetalleProducto;
import com.example.mercadoesclavo.view.fragment.ItemMoveCallback;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class  FavoritosAdapter extends RecyclerView.Adapter implements ItemMoveCallback.ItemTouchHelperContract {

    private List<DetalleProducto> detalleProductoList;
    private FavoritosAdapterListener listener;
    private FirebaseAuth mAuth;



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



    public class FavoritosViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imageViewCardViewFavoritosRow)
        ImageView imageViewCardViewFavoritosRow;
        @BindView(R.id.textViewViewCardNombreFavoritosRow)
        TextView textViewViewCardNombreFavoritosRow;
        @BindView(R.id.textViewViewCardPrecioFavoritosRow)
        TextView textViewViewCardPrecioFavoritosRow;
        private DetalleProducto detalleProducto;
        @BindView(R.id.floatingButtonQuitarFavorito)
        FloatingActionButton floatingButtonQuitarFavorito;
        View rowView;

        public FavoritosViewHolder(@NonNull View itemView) {
            super(itemView);
            rowView = itemView;
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

        public void bindFavoritos(final DetalleProducto detalleProductoDeLaCelda) {
            this.detalleProducto = detalleProductoDeLaCelda;
            Glide.with(itemView).load(this.detalleProducto.getThumbnail()).into(imageViewCardViewFavoritosRow);
            textViewViewCardNombreFavoritosRow.setText(this.detalleProducto.getTitle());
            textViewViewCardPrecioFavoritosRow.setText("$ " + this.detalleProducto.getPrice().toString());
            floatingButtonQuitarFavorito.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    mAuth = FirebaseAuth.getInstance();
                    FirebaseFirestore db = FirebaseFirestore.getInstance();
                    DocumentReference documentReference = db.collection(mAuth.getUid()).document(detalleProductoDeLaCelda.getId());
                    final FirebaseFirestore finalDb = db;
                    documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                            if (documentSnapshot.exists()) {
                                finalDb.collection(mAuth.getUid()).document(detalleProductoDeLaCelda.getId())
                                        .delete()
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {
                                                Toast.makeText(itemView.getContext(), "fue eliminado de favoritos", Toast.LENGTH_SHORT).show();

                                            }
                                        });

                            }
                        }
                    });


                }
            });

        }


    }

    @Override
    public void onRowMoved(int fromPosition, int toPosition) {
        if (fromPosition < toPosition) {
            for (int i = fromPosition; i < toPosition; i++) {
                Collections.swap(detalleProductoList, i, i + 1);
            }
        } else {
            for (int i = fromPosition; i > toPosition; i--) {
                Collections.swap(detalleProductoList, i, i - 1);
            }
        }
        notifyItemMoved(fromPosition, toPosition);
    }

    @Override
    public void onRowSelected(FavoritosAdapter.FavoritosViewHolder myViewHolder) {
        myViewHolder.rowView.setBackgroundColor(Color.GRAY);
    }

    @Override
    public void onRowClear(FavoritosAdapter.FavoritosViewHolder myViewHolder) {
        myViewHolder.rowView.setBackgroundColor(Color.WHITE);
    }


    public interface FavoritosAdapterListener {
        void informarSeleccionFavoritos(Integer posicion, DetalleProducto detalleProducto);
    }
}
