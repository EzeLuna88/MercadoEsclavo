package com.example.mercadoesclavo.view;


import android.content.Intent;
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

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {

    private FirebaseAuth mAuth;
    @BindView(R.id.botonRegistrarseLoginFragment)
    Button botonRegistrarseLoginFragment;
    @BindView(R.id.botonIniciarSesionLoginFragment)
    Button botonIniciarSesionLoginFragment;
    @BindView(R.id.emailLoginFragment)
    EditText emailLoginFragment;
    @BindView(R.id.passwordLoginFragment)
    EditText passwordLoginFragment;

    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        mAuth = FirebaseAuth.getInstance();
        ButterKnife.bind(this, view);

        botonIniciarSesionLoginFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Login();
            }
        });

        botonRegistrarseLoginFragment.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                NewUserFragment newUserFragment = new NewUserFragment();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.contenedorDeFragment, newUserFragment)
                        .addToBackStack(null)
                        .commit();
            }
        });


        return view;
    }

    private void Login() {

        String email = emailLoginFragment.getText().toString();
        String password = passwordLoginFragment.getText().toString();

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(getContext(), "Bienvenido " + user.getEmail(), Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getContext(), "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });
    }


    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();

    }


}
