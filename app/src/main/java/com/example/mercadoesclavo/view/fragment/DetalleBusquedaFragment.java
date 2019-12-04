package com.example.mercadoesclavo.view.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.mercadoesclavo.R;
import com.example.mercadoesclavo.adapter.ViewPagerImagenProductoAdapter;
import com.example.mercadoesclavo.dto.Results;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetalleBusquedaFragment extends Fragment {

    public static final String KEY_RESULTS = "results";

    @BindView(R.id.textViewTitleDetalleBusquedaFragment)
    TextView textViewTitleDetalleBusquedaFragment;
    @BindView(R.id.textViewPriceDetalleBusquedaFragment)
    TextView textViewPriceDetalleBusquedaFragment;
    @BindView(R.id.textViewCantidadDetalleBusquedaFragment)
    TextView textViewCantidadDetalleBusquedaFragment;
    @BindView(R.id.textViewDisponibleDetalleBusquedaFragment)
    TextView textViewDisponibleDetalleBusquedaFragment;
    @BindView(R.id.textViewTipoDeCompraDetalleBusquedaFragment)
    TextView textViewTipoDeCompraDetalleBusquedaFragment;
    @BindView(R.id.textViewCondicionDetalleBusquedaFragment)
    TextView textViewCondicionDetalleBusquedaFragment;
    @BindView(R.id.textViewUbicacionDetalleBusquedaFragment)
    TextView textViewUbicacionDetalleBusquedaFragment;
    @BindView(R.id.textViewGarantiaDetalleBusquedaFragment)
    TextView textViewGarantiaDetalleBusquedaFragment;
    @BindView(R.id.textViewDescripcionDetalleBusquedaFragment)
    TextView textViewDescripcionDetalleBusquedaFragment;
    @BindView(R.id.floatingButton)
    FloatingActionButton floatingActionButton;
    @BindView(R.id.ImagenDetalleBusquedaFragment)
    ImageView imagenDetalleBusquedaFragment;
    @BindView(R.id.progressBarFullScreen)
    ProgressBar progressBar;
    private FirebaseAuth mAuth;
    private ViewPager pager;
    private ViewPagerImagenProductoAdapter pagerAdapter;

    public DetalleBusquedaFragment() {
        // Required empty public constructor
    }

    public static DetalleBusquedaFragment getInstance(Results results) {
        DetalleBusquedaFragment detalleBusquedaFragment = new DetalleBusquedaFragment();
        Bundle args = new Bundle();
        args.putSerializable(KEY_RESULTS, results);
        detalleBusquedaFragment.setArguments(args);
        return detalleBusquedaFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detalle_busqueda, container, false);

        ButterKnife.bind(this, view);
        mAuth = FirebaseAuth.getInstance();
        progressBar.setVisibility(View.VISIBLE);

        final FirebaseFirestore db = FirebaseFirestore.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();

        Bundle bundle = getArguments();
        final Results results = (Results) bundle.getSerializable(KEY_RESULTS);

        final String id = results.getId();
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseUser currentUser = mAuth.getCurrentUser();
                if (currentUser != null) {
                    String userUid = mAuth.getCurrentUser().getUid();
                    FirebaseFirestore db = FirebaseFirestore.getInstance();
                    db = FirebaseFirestore.getInstance();


                    db.collection(userUid).document(results.getId()).set(results);
                    floatingActionButton.setImageResource(R.drawable.ic_favorite_white_24dp);
                    Toast.makeText(getContext(), "fue agregado a favoritos", Toast.LENGTH_SHORT).show();
                }
            }
        });
        agregarAFavoritos(db, currentUser, id);


        textViewTitleDetalleBusquedaFragment.setText(results.getTitle());
        textViewPriceDetalleBusquedaFragment.setText(results.getPrice().toString());
        textViewDisponibleDetalleBusquedaFragment.setText(results.getAvailableQuantity().toString());
                textViewTipoDeCompraDetalleBusquedaFragment.setText(results.getBuyingMode());
        textViewCondicionDetalleBusquedaFragment.setText(results.getCondition());
        String ubicacion = results.getSellerAddress().getCity().getName() + ", " + results.getSellerAddress().getState().getName() +
                ", " + results.getSellerAddress().getCountry().getName();
        //textViewUbicacionDetalleBusquedaFragment.setText(results.ubicacion);
        textViewGarantiaDetalleBusquedaFragment.setText(results.getWarranty());
        Glide.with(view).load(results.getThumbnail()).into(imagenDetalleBusquedaFragment);
        pager = view.findViewById(R.id.ViewPagerImageDetalleProductFragment);
        progressBar.setVisibility(View.INVISIBLE);


        return view;
    }


    private void agregarAFavoritos(FirebaseFirestore db, FirebaseUser currentUser, String id) {
        if (currentUser != null) {
            DocumentReference documentReference = db.collection(mAuth.getUid()).document(id);
            documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                @Override
                public void onSuccess(DocumentSnapshot documentSnapshot) {
                    if (documentSnapshot.exists()) {
                        floatingActionButton.setImageResource(R.drawable.ic_favorite_white_24dp);
                    }
                }
            });

        }
    }


}
