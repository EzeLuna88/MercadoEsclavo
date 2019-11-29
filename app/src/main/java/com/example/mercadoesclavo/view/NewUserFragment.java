package com.example.mercadoesclavo.view;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mercadoesclavo.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import butterknife.BindView;
import butterknife.ButterKnife;

import static androidx.constraintlayout.widget.Constraints.TAG;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewUserFragment extends Fragment {

    private FirebaseAuth mAuth;

    @BindView(R.id.email)
    EditText emailLogin;
    @BindView(R.id.password)
    EditText passwordLogin;
    @BindView(R.id.nombre)
    EditText nombreLogin;
    @BindView(R.id.apellido)
    EditText apellidoLogin;
    @BindView(R.id.edad)
    EditText edadLogin;
    @BindView(R.id.telefono)
    EditText telefonoLogin;
    @BindView(R.id.botonRegistrarse)
    Button botonRegistrarse;

    public NewUserFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_new_user, container, false);

        mAuth = FirebaseAuth.getInstance();

        ButterKnife.bind(this, view);

        botonRegistrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Register();
            }
        });


        return view;
    }

    private void Register() {
        String email = emailLogin.getText().toString();
        String password = passwordLogin.getText().toString();

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            String mensaje = user.getEmail() + " fue registrado";
                            Toast.makeText(getContext(), mensaje, Toast.LENGTH_SHORT).show();

                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(getContext(), "Error en el Registro", Toast.LENGTH_SHORT).show();

                        }

                        // ...
                    }
                });
    }


}
