package com.example.mercadoesclavo.view.fragment;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.mercadoesclavo.R;
import com.example.mercadoesclavo.adapter.FavoritosAdapter;
import com.example.mercadoesclavo.controller.MercadoEsclavoController;
import com.example.mercadoesclavo.dto.DetalleProducto;
import com.example.mercadoesclavo.utils.ResultListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavoritosFragment extends Fragment implements FavoritosAdapter.FavoritosAdapterListener {


    private FirebaseAuth mAuth;
    private notificadorFavoritos notificadorFavoritos;
    @BindView(R.id.progressBarFullScreen)
    ProgressBar progressBar;
    private FavoritosAdapter mAdapter;

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
        ButterKnife.bind(this, view);
        mAuth = FirebaseAuth.getInstance();
        final FirebaseFirestore db = FirebaseFirestore.getInstance();
        // getFavoritos(view, db);

        getFavoritosList(view, db);


        db.collection(mAuth.getUid())
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value,
                                        @Nullable FirebaseFirestoreException e) {
                        getFavoritosList(view, db);
                        if (e != null) {
                            return;
                        }
                    }
                });
        return view;
    }

    private void getFavoritosList(final View view, FirebaseFirestore db) {
        final MercadoEsclavoController mercadoEsclavoController = new MercadoEsclavoController(getContext());
        mercadoEsclavoController.getFavoritos(new ResultListener<List<DetalleProducto>>() {
            @Override
            public void onFinish(List<DetalleProducto> result) {
                List<DetalleProducto> detalleProductoList = new ArrayList<>();
                detalleProductoList = result;
                RecyclerView recyclerView = view.findViewById(R.id.recyclerViewFavoritos);
                LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
                recyclerView.setLayoutManager(layoutManager);
                FavoritosAdapter favoritosAdapter = new FavoritosAdapter(detalleProductoList, FavoritosFragment.this);


                ItemTouchHelper.Callback callback =
                        new ItemMoveCallback(favoritosAdapter);
                ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
                touchHelper.attachToRecyclerView(recyclerView);


                recyclerView.setAdapter(favoritosAdapter);
                progressBar.setVisibility(View.INVISIBLE);
            }
        }, db);
    }


    /*private void getFavoritos(final View view, FirebaseFirestore db) {
        progressBar.setVisibility(View.VISIBLE);
        db.collection(mAuth.getCurrentUser().getUid())
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        final List<DetalleProducto> detalleProductoList = new ArrayList<>();
                        for (QueryDocumentSnapshot queryDocumentSnapshot : queryDocumentSnapshots) {
                            DetalleProducto detalleProducto = queryDocumentSnapshot.toObject(DetalleProducto.class);
                            detalleProductoList.add(detalleProducto);
                            RecyclerView recyclerView = view.findViewById(R.id.recyclerViewFavoritos);
                            LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
                            recyclerView.setLayoutManager(layoutManager);
                            FavoritosAdapter favoritosAdapter = new FavoritosAdapter(detalleProductoList, FavoritosFragment.this);


                            ItemTouchHelper.Callback callback =
                                    new ItemMoveCallback(favoritosAdapter);
                            ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
                            touchHelper.attachToRecyclerView(recyclerView);

                            recyclerView.setAdapter(favoritosAdapter);
                            progressBar.setVisibility(View.INVISIBLE);


                        }
                    }
                });
    }
*/
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
