package com.example.pillpilot;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*EdgeToEdge.enable(this);*/
        setContentView(R.layout.activity_main);
       /* ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });*/
        //menü felolvasása az xml fájlból
        Toolbar toolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);
        setTitle("PillPilot");

        loadFragment(new HomeFragment(),"Főoldal",false);


        }//onCreate
        //hozzáillesztjük a Toolbarhoz a menüt
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }
    //fragmentek betöltése és a fragment veremhez csatlakoztatása
    private void loadFragment(Fragment fragment,String tag, boolean addToBackStack){
    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
    transaction.replace(R.id.fragment_container,fragment,tag);
    if(addToBackStack){
        transaction.addToBackStack(tag);
    }

    transaction.commit();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case 1000009:     //ez az R.id.action_home
                loadFragment(new HomeFragment(),"Főoldal",true);
                return true;

            case 1000002:    //ez az R.id.action_manipulation
                loadFragment(new ManipulationFragment(),"Adatmanipuláció",true);
                return true;
        }

        return true;
    }
}//mainactivity