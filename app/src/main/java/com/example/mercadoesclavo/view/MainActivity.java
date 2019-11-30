package com.example.mercadoesclavo.view;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.util.Base64;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.mercadoesclavo.R;
import com.example.mercadoesclavo.model.Categories;
import com.example.mercadoesclavo.model.Results;
import com.example.mercadoesclavo.view.fragment.ProductsFragment;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import butterknife.BindView;

import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity implements ProductsFragment.notificadorProducto, CategoriesFragment.notificadorCategories {

    @BindView(R.id.navigationViewMain)
    NavigationView navigationView;
    @BindView(R.id.drawerLayoutMain)
    DrawerLayout drawerLayout;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        ButterKnife.bind(this);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        obtenerKeyHash();
        CategoriesFragment categoriesFragment = new CategoriesFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.contenedorDeFragment, categoriesFragment);
        fragmentTransaction.commit();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarMainActivity);
        setSupportActionBar(toolbar);

        configurarNavigationView();

    }


    public void metodoNavigationView(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.navigationViewMenuPerfil:
                Toast.makeText(MainActivity.this, "voy a mi perfil", Toast.LENGTH_SHORT).show();
                break;
            case R.id.navigationViewMenuFavoritos:
                Toast.makeText(MainActivity.this, "voy a favoritos", Toast.LENGTH_SHORT).show();
                break;
            case R.id.navigationViewMenuCerrarSesion:
                Toast.makeText(MainActivity.this, "cierro mi sesion", Toast.LENGTH_SHORT).show();
                break;
        }
    }


    private void configurarNavigationView() {
        this.navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                metodoNavigationView(menuItem);
                switch (menuItem.getItemId()) {
                    case R.id.navigationViewMenuPerfil:
                        Toast.makeText(MainActivity.this, "voy a mi perfil", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.navigationViewMenuFavoritos:
                        Toast.makeText(MainActivity.this, "voy a favoritos", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.navigationViewMenuCerrarSesion:
                        Toast.makeText(MainActivity.this, "cierro mi sesion", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.navigationViewAboutUs:
                        IrAAboutUs();
                        break;
                    case R.id.navigationViewMenuIniciarSesion:
                        GoToLoginFragment();
                }


                drawerLayout.closeDrawers();

                return false;
            }
        });
    }


    public void IrAAboutUs() {
        AboutUsFragment aboutUsFragment = new AboutUsFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.contenedorDeFragment, aboutUsFragment);
        fragmentTransaction.commit();
    }

    public void GoToLoginFragment() {
        LoginFragment loginFragment = new LoginFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.contenedorDeFragment, loginFragment);
        fragmentTransaction.commit();
    }

    @Override
    public void enviarNotificacionCategories(Categories categories, Integer position) {
        ProductsFragment productsFragment = new ProductsFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.contenedorDeFragment, productsFragment).commit();

        Bundle bundle = new Bundle();
        bundle.putSerializable(ProductsFragment.KEY_CATEGORIES, categories);
        productsFragment.setArguments(bundle);


    }

    @Override
    public void enviarNotificacionProducto(Results results, Integer posicion) {
        DetalleProductFragment detalleProductFragment = new DetalleProductFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.contenedorDeFragment, detalleProductFragment).commit();

        Bundle bundle = new Bundle();
        bundle.putSerializable(DetalleProductFragment.KEY_DETALLE_PRODUCT, results);
        detalleProductFragment.setArguments(bundle);

    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        //TODO si esta logueado o no que haga algo
    }

    public void obtenerKeyHash() {
        String keyHash = "";
        try {
            PackageInfo info = getPackageManager().getPackageInfo(getPackageName(), PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                keyHash = Base64.encodeToString(md.digest(), Base64.DEFAULT);
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        System.out.println("MI KEY HASH: " + keyHash);
    }
}

