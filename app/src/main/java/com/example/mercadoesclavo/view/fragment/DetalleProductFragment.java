package com.example.mercadoesclavo.view.fragment;


import android.media.Image;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
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
import com.example.mercadoesclavo.controller.CategoriesController;
import com.example.mercadoesclavo.model.Description;
import com.example.mercadoesclavo.model.DetalleProducto;
import com.example.mercadoesclavo.model.Geolocation;
import com.example.mercadoesclavo.model.Pictures;
import com.example.mercadoesclavo.model.Results;
import com.example.mercadoesclavo.utils.ResultListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetalleProductFragment extends Fragment {

    public static final String KEY_DETALLE_PRODUCT = "detalle producto";

    @BindView(R.id.textViewTitleDetalleProductFragment)
    TextView textViewTitleDetalleProductFragment;
    /*@BindView(R.id.imageViewCardViewDetalleProductFragment)
    ImageView imageViewCardViewDetalleProductFragment;*/
    @BindView(R.id.textViewPriceDetalleProductFragment)
    TextView textViewPriceDetalleProductFragment;
    @BindView(R.id.textViewCantidadDetalleProductFragment)
    TextView textViewCantidadDetalleProductFragment;
    @BindView(R.id.textViewDisponibleDetalleProductFragment)
    TextView textViewDisponibleDetalleProductFragment;
    @BindView(R.id.textViewTipoDeCompraDetalleProductFragment)
    TextView textViewTipoDeCompraDetalleProductFragment;
    @BindView(R.id.textViewCondicionDetalleProductFragment)
    TextView textViewCondicionDetalleProductFragment;
    @BindView(R.id.textViewUbicacionDetalleProductFragment)
    TextView textViewUbicacionDetalleProductFragment;
    @BindView(R.id.textViewGarantiaDetalleProductFragment)
    TextView textViewGarantiaDetalleProductFragment;
    @BindView(R.id.textViewDescripcionDetalleProductFragment)
    TextView textViewDescripcionDetalleProductFragment;
    @BindView(R.id.floatingButton)
    FloatingActionButton floatingActionButton;
    @BindView(R.id.progressBarFullScreen)
    ProgressBar progressBar;
    private FirebaseAuth mAuth;
    private DetalleProducto detalleProducto;
    private Description description;
    private List<Fragment> fragmentList = new ArrayList<>();
    private ViewPager pager;
    private ViewPagerImagenProductoAdapter pagerAdapter;


    public DetalleProductFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_detalle_product, container, false);
        ButterKnife.bind(this, view);
        mAuth = FirebaseAuth.getInstance();
        progressBar.setVisibility(View.VISIBLE);

        final FirebaseFirestore db = FirebaseFirestore.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();

        Bundle bundle = getArguments();
        Results results = (Results) bundle.getSerializable(KEY_DETALLE_PRODUCT);

        final String id = results.getId();
        getDetalleProductos(view, id);


        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseUser currentUser = mAuth.getCurrentUser();
                if (currentUser != null) {
                    String userUid = mAuth.getCurrentUser().getUid();
                    FirebaseFirestore db = FirebaseFirestore.getInstance();
                    db = FirebaseFirestore.getInstance();


                    db.collection(userUid).document(detalleProducto.getId()).set(detalleProducto);
                    floatingActionButton.setImageResource(R.drawable.ic_favorite_white_24dp);
                    Toast.makeText(getContext(), "fue agregado a favoritos", Toast.LENGTH_SHORT).show();
                }
            }
        });


        DocumentReference documentReference = db.collection(mAuth.getUid()).document(id);
        documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()) {
                    floatingActionButton.setImageResource(R.drawable.ic_favorite_white_24dp);
                }
            }
        });


        return view;


    }

    private void getDetalleProductos(final View view, String id) {
        CategoriesController categoriesController = new CategoriesController();
        categoriesController.getDetalleProducto(new ResultListener<DetalleProducto>() {
            @Override
            public void onFinish(DetalleProducto result) {

                detalleProducto = result;
//                Glide.with(view).load(detalleProducto.getSecureThumbnail()).into(imageViewCardViewDetalleProductFragment);
                textViewTitleDetalleProductFragment.setText(detalleProducto.getTitle());
                textViewPriceDetalleProductFragment.setText(detalleProducto.getPrice().toString());
                textViewDisponibleDetalleProductFragment.setText(detalleProducto.getAvailableQuantity().toString());
                textViewCantidadDetalleProductFragment.setText(detalleProducto.getInitialQuantity().toString());
                textViewTipoDeCompraDetalleProductFragment.setText(detalleProducto.getBuyingMode());
                textViewCondicionDetalleProductFragment.setText(detalleProducto.getCondition());
                String ubicacion = detalleProducto.getSellerAddress().getCity().getName() + ", " + detalleProducto.getSellerAddress().getState().getName() +
                        ", " + detalleProducto.getSellerAddress().getCountry().getName();
                textViewUbicacionDetalleProductFragment.setText(ubicacion);
                textViewGarantiaDetalleProductFragment.setText(detalleProducto.getWarranty());
                pager = view.findViewById(R.id.ViewPagerImageDetalleProductFragment);
                List<Pictures> picturesList = detalleProducto.getPictures();
                cargarImagenes(picturesList);

                MapsFragment mapsFragment = new MapsFragment();
                Bundle bundle = new Bundle();
                bundle.putSerializable(MapsFragment.KEY_POSICION, detalleProducto);
                mapsFragment.setArguments(bundle);
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.contenedorDeMapa, mapsFragment).commit();
            }
        }, id);

        categoriesController.getDescription(new ResultListener<Description>() {
            @Override
            public void onFinish(Description result) {
                description = result;
                textViewDescripcionDetalleProductFragment.setText(description.getPlaintText());
            }
        }, id);

    }


    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();


    }

    public void cargarImagenes(List<Pictures> picturesList) {
        for (Pictures pictures : picturesList) {
            ImagenDetalleProductoFragment imagenDetalleProductoFragment = ImagenDetalleProductoFragment.getInstance(pictures);
            fragmentList.add(imagenDetalleProductoFragment);
        }
        pagerAdapter = new ViewPagerImagenProductoAdapter(getActivity().getSupportFragmentManager(), fragmentList);
        pager.setAdapter(pagerAdapter);
        progressBar.setVisibility(View.INVISIBLE);
    }

    /*public void getMap(){
        MapsFragment mapsFragment = new MapsFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(MapsFragment.KEY_POSICION, detalleProducto);
        mapsFragment.setArguments(bundle);
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.contenedorDeMapa, mapsFragment).commit();



    }*/
}
