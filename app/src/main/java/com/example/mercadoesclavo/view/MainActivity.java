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
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainFragment.notificadorMain {

    @BindView(R.id.navigationViewMain)
    NavigationView navigationView;
    @BindView(R.id.drawerLayoutMain)
    DrawerLayout drawerLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

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

    private void configurarNavigationView() {
        this.navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
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
}

