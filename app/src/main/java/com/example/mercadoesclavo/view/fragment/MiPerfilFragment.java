package com.example.mercadoesclavo.view.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mercadoesclavo.R;
import com.example.mercadoesclavo.dto.UserMercadoEsclavo;
import com.google.android.gms.tasks.OnSuccessListener;
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
public class MiPerfilFragment extends Fragment {

    @BindView(R.id.textViewEmailPerfil)
    TextView textViewEmailPerfil;
    @BindView(R.id.textViewNombrePerfil)
    TextView textViewNombrePerfil;
    @BindView(R.id.textViewEdadPerfil)
    TextView textViewEdadPerfil;
    @BindView(R.id.textViewTelefonoPerfil)
    TextView textViewTelefonoPerfil;
    private FirebaseAuth mAuth;
    @BindView(R.id.progressBarFullScreen)
    ProgressBar progressBar;
    @BindView(R.id.imageViewImageViewPerfil)
    ImageView imageViewPerfil;

    public MiPerfilFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_mi_perfil, container, false);
        ButterKnife.bind(this, view);
        progressBar.setVisibility(View.VISIBLE);
        mAuth = FirebaseAuth.getInstance();
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        textViewNombrePerfil.setText(mAuth.getCurrentUser().getDisplayName());
        if (textViewNombrePerfil.length() > 0) {
            Glide.with(view).load(mAuth.getCurrentUser().getPhotoUrl()).into(imageViewPerfil);
            textViewEmailPerfil.setText(mAuth.getCurrentUser().getEmail());
            textViewTelefonoPerfil.setText(mAuth.getCurrentUser().getPhoneNumber().toString());
            progressBar.setVisibility(View.INVISIBLE);
        } else {
            final DocumentReference documentReference = db.collection("usuariosMercadoEsclavo").document(mAuth.getCurrentUser().getUid());
            documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                @Override
                public void onSuccess(DocumentSnapshot documentSnapshot) {
                    UserMercadoEsclavo userMercadoEsclavo = documentSnapshot.toObject(UserMercadoEsclavo.class);
                    textViewEmailPerfil.setText(mAuth.getCurrentUser().getEmail());
                    textViewNombrePerfil.setText(userMercadoEsclavo.getNombre() + " " + userMercadoEsclavo.getApellido());
                    textViewEdadPerfil.setText(userMercadoEsclavo.getEdad());
                    textViewTelefonoPerfil.setText(userMercadoEsclavo.getTelefono());
                    progressBar.setVisibility(View.INVISIBLE);
                }
            });
        }

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();

    }

}
