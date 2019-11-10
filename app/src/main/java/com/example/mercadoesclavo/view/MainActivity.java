package com.example.mercadoesclavo.view;

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
import com.google.android.material.navigation.NavigationView;

import butterknife.BindView;

public class MainActivity extends AppCompatActivity implements MainFragment.notificadorMain {

    @BindView(R.id.navigationViewMain)
    NavigationView navigationView;
    @BindView(R.id.drawerLayoutMain)
    DrawerLayout drawerLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MainFragment mainFragment = new MainFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.contenedorDeFragment, mainFragment);
        fragmentTransaction.commit();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarMainActivity);
        setSupportActionBar(toolbar);

        configurarNavigationView();

    }


    @Override
    public void hicieronClickMain() {

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

                drawerLayout.closeDrawers();

                return false;
            }
        });
    }
}

