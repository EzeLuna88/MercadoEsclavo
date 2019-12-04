package com.example.mercadoesclavo.view.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.mercadoesclavo.R;
import com.example.mercadoesclavo.dto.Pictures;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class ImagenDetalleProductoFragment extends Fragment {

    @BindView(R.id.imagenDetalleProductoFragment)
    ImageView imagenDetalleProductoFragment;

    public static final String KEY_IMAGEN = "imagen";

    public ImagenDetalleProductoFragment() {
        // Required empty public constructor
    }


    public static ImagenDetalleProductoFragment getInstance(Pictures pictures) {
        ImagenDetalleProductoFragment imagenDetalleProductoFragment = new ImagenDetalleProductoFragment();
        Bundle args = new Bundle();
        args.putSerializable(KEY_IMAGEN, pictures);
        imagenDetalleProductoFragment.setArguments(args);
        return imagenDetalleProductoFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_imagen_detalle_producto, container, false);
        ButterKnife.bind(this, view);

        Bundle bundle = getArguments();
        Pictures pictures = (Pictures)bundle.getSerializable(KEY_IMAGEN);

        Glide.with(ImagenDetalleProductoFragment.this).load(pictures.getSecureUrl()).into(imagenDetalleProductoFragment);

        return view;
    }


}
