package com.example.mercadoesclavo.view;


import android.media.Image;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mercadoesclavo.R;
import com.example.mercadoesclavo.controller.CategoriesController;
import com.example.mercadoesclavo.model.Description;
import com.example.mercadoesclavo.model.DetalleProducto;
import com.example.mercadoesclavo.model.Results;
import com.example.mercadoesclavo.utils.ResultListener;

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

    public DetalleProductFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_detalle_product, container, false);
        ButterKnife.bind(this, view);

        Bundle bundle = getArguments();
        Results results = (Results) bundle.getSerializable(KEY_DETALLE_PRODUCT);

        String id = results.getId();
        CategoriesController categoriesController = new CategoriesController();
        categoriesController.getDetalleProducto(new ResultListener<DetalleProducto>() {
            @Override
            public void onFinish(DetalleProducto result) {
                DetalleProducto detalleProducto = result;
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

            }
        }, id);

        categoriesController.getDescription(new ResultListener<Description>() {
            @Override
            public void onFinish(Description result) {
                Description description = result;
                textViewDescripcionDetalleProductFragment.setText(description.getPlaintText());
            }
        }, id);

        return view;
    }


}
