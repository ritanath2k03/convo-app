package com.example.chatsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.chatsapp.databinding.ActivityMainBinding;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.ktx.Firebase;

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;
    ActivityMainBinding binding;
    Toolbar toolbar;

    FirebaseAuth auth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        navigationView = findViewById(R.id.Top_Nav_Bar_Navigation_View);
        drawerLayout = findViewById(R.id.Drawer_Layout);
        Toolbar toolbar1 = findViewById(R.id.Top_Nav_Bar_Toolbar);
//
//        toggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar1,R.string.navigration_open,R.string.navigration_close);
//        drawerLayout.addDrawerListener(toggle);
//        toggle.syncState();
//                if(getSupportActionBar()!=null) {
//            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
//        }
        getSupportFragmentManager().beginTransaction().replace(R.id.Chat_Fragment_Container, new Chat_Fragment()).commit();

  binding.MainBottomNavView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
      @Override
      public boolean onNavigationItemSelected(@NonNull MenuItem item) {
          switch (item.getItemId()) {

              case R.id.Chat_Section:
                  Log.d("Tag","hi");
                  getSupportFragmentManager().beginTransaction().replace(R.id.Chat_Fragment_Container, new Chat_Fragment()).commit();
                  break;
              case R.id.Status_Section:
                  Log.d("Tag","hi");
                  getSupportFragmentManager().beginTransaction().replace(R.id.Chat_Fragment_Container, new Status_Fragment()).commit();
                  break;
              case R.id.Call_Section:
                  Log.d("Tag","hi");
                  getSupportFragmentManager().beginTransaction().replace(R.id.Chat_Fragment_Container, new Calls_Fragment()).commit();
                  break;
          }
          return true;
      }
  });

    }

    @Override
    public boolean onCreateOptionsMenu(@NonNull Menu menu) {
        new MenuInflater(this).inflate(R.menu.top_side_nav_bar, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.Settings:
                Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
                break;
            case R.id.Add_Contacts:
                break;
            case R.id.New_Group:


                break;
            case R.id.Logout:
                auth.signOut();
                startActivity(new Intent(MainActivity.this, SignInActivity.class));
                break;
        }
        return true;
    }
}