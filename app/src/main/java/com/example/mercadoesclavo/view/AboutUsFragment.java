package com.example.mercadoesclavo.view;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.mercadoesclavo.R;


import android.widget.TextView;

import com.example.mercadoesclavo.R;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;


public class AboutUsFragment extends Fragment {

    @BindView(R.id.historiaTituloAboutUsFragment)
    TextView historiaTituloAboutUsFragment;
    @BindView(R.id.textViewHistoriaAboutUsFragment)
    TextView textViewHistoriaAboutUsFragment;
    @BindView(R.id.misionTituloAboutUsFragment)
    TextView misionTituloAboutUsFragment;
    @BindView(R.id.textViewMisionAboutUsFragment)
    TextView textViewMisionAboutUsFragment;
    @BindView(R.id.visionTituloAboutUsFragment)
    TextView visionTituloAboutUsFragment;
    @BindView(R.id.textViewVisionAboutUsFragment)
    TextView textViewVisionAboutUsFragment;
    @BindView(R.id.valoresTituloAboutUsFragment)
    TextView valoresTituloAboutUsFragment;
    @BindView(R.id.textViewValoresAboutUsFragment)
    TextView textViewValoresAboutUsFragment;
    @BindView(R.id.creadorTituloAboutUsFragment)
    TextView creadorTituloAboutUsFragment;
    @BindView(R.id.textViewNombreAboutUsFragment)
    TextView textViewNombreAboutUsFragment;
    @BindView(R.id.textViewEdadAboutUsFragment)
    TextView textViewEdadAboutUsFragment;
    @BindView(R.id.textViewEstudioAboutUsFragment)
    TextView textViewEstudioAboutUsFragment;


    public AboutUsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment



        View view = inflater.inflate(R.layout.fragment_about_us, container, false);
        ButterKnife.bind(this, view);
        botonHistoria();
        botonMision();
        botonValores();
        botonVision();
        botonCreador();
        return view;
    }


    public void botonHistoria() {
        historiaTituloAboutUsFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                visibility(textViewHistoriaAboutUsFragment);
            }

        });
    }

    public void botonMision() {
        misionTituloAboutUsFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                visibility(textViewMisionAboutUsFragment);
            }

        });
    }

    public void botonVision() {
        visionTituloAboutUsFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                visibility(textViewVisionAboutUsFragment);
            }
        });
    }

    public void botonValores() {
        valoresTituloAboutUsFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                visibility(textViewValoresAboutUsFragment);
            }
        });
    }

    public void botonCreador() {
        creadorTituloAboutUsFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                visibility(textViewNombreAboutUsFragment);
                visibility(textViewEdadAboutUsFragment);
                visibility(textViewEstudioAboutUsFragment);
            }
        });
    }

    public void visibility(View view) {
        if (view.getVisibility() == View.GONE)
            view.setVisibility(View.VISIBLE);
        else view.setVisibility(View.GONE);
    }

}
