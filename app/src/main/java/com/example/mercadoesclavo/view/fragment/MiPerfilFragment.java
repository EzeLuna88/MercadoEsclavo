package com.example.mercadoesclavo.view.fragment;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mercadoesclavo.R;
import com.example.mercadoesclavo.model.UserMercadoEsclavo;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.auth.User;

import org.w3c.dom.Text;

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
    @BindView(R.id.textViewApellidoPerfil)
    TextView textViewApellidoPerfil;
    @BindView(R.id.textViewEdadPerfil)
    TextView textViewEdadPerfil;
    @BindView(R.id.textViewTelefonoPerfil)
    TextView textViewTelefonoPerfil;
    private FirebaseAuth mAuth;

    public MiPerfilFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_mi_perfil, container, false);
        ButterKnife.bind(this, view);
        mAuth = FirebaseAuth.getInstance();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        textViewEmailPerfil.setText(mAuth.getCurrentUser().getEmail());
        if (mAuth.getCurrentUser().getDisplayName() != null) {
            textViewNombrePerfil.setText(mAuth.getCurrentUser().getDisplayName());
        } else {
            final DocumentReference documentReference = db.collection("usuariosMercadoEsclavo").document(mAuth.getCurrentUser().getUid());
            documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                @Override
                public void onSuccess(DocumentSnapshot documentSnapshot) {
                    UserMercadoEsclavo userMercadoEsclavo = documentSnapshot.toObject(UserMercadoEsclavo.class);
                    textViewNombrePerfil.setText(userMercadoEsclavo.getNombre());
                    textViewApellidoPerfil.setText(userMercadoEsclavo.getApellido());
                    textViewEdadPerfil.setText(userMercadoEsclavo.getEdad());
                    textViewTelefonoPerfil.setText(userMercadoEsclavo.getTelefono());
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
