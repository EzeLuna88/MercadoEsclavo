package com.example.mercadoesclavo.view.fragment;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mercadoesclavo.R;
import com.example.mercadoesclavo.adapter.FavoritosAdapter;
import com.example.mercadoesclavo.model.DetalleProducto;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavoritosFragment extends Fragment implements FavoritosAdapter.FavoritosAdapterListener{



    private FirebaseAuth mAuth;

    private notificadorFavoritos notificadorFavoritos;

    public FavoritosFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.notificadorFavoritos = (notificadorFavoritos) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_favoritos, container, false);
        mAuth = FirebaseAuth.getInstance();
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection(mAuth.getCurrentUser().getUid())
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        List<DetalleProducto> detalleProductoList = new ArrayList<>();
                        for (QueryDocumentSnapshot queryDocumentSnapshot : queryDocumentSnapshots) {
                            DetalleProducto detalleProducto = queryDocumentSnapshot.toObject(DetalleProducto.class);
                            detalleProductoList.add(detalleProducto);
                            RecyclerView recyclerView = view.findViewById(R.id.recyclerViewFavoritos);
                            LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
                            recyclerView.setLayoutManager(layoutManager);
                            FavoritosAdapter favoritosAdapter = new FavoritosAdapter(detalleProductoList, FavoritosFragment.this);
                            recyclerView.setAdapter(favoritosAdapter);
                        }
                    }
                });







        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();


    }

    @Override
    public void informarSeleccionFavoritos(Integer posicion, DetalleProducto detalleProducto) {
        notificadorFavoritos.enviarNotificacionFavoritos(detalleProducto, posicion);
    }

    public interface notificadorFavoritos {
        public void enviarNotificacionFavoritos(DetalleProducto detalleProducto, Integer posicion);
    }
}
