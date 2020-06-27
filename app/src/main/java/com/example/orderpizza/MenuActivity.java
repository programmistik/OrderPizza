package com.example.orderpizza;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigation;
    Profile User;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        User = (Profile) getIntent().getSerializableExtra("USER");

        openFragment(MenuFragment.newInstance("", ""));
        bottomNavigation = findViewById(R.id.bottom_navigation);

        bottomNavigation.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.navigation_menu:
                                openFragment(MenuFragment.newInstance("", ""));
                                return true;
                            case R.id.navigation_profile:
                                openFragment(ProfileFragment.newInstance("", ""));
                                return true;
                            case R.id.navigation_contacts:
                                openFragment(ContactsFragment.newInstance("", ""));
                                return true;
                            case R.id.navigation_cart:
                                openFragment(CartFragment.newInstance("", ""));
                                return true;
                        }
                        return false;
                    }
                });
    }
    public void openFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }


    public void onClickAdd(View view) {
        EditText ed = (EditText) findViewById(R.id.addrDesc);
        EditText ed2 = (EditText) findViewById(R.id.addr);
        User.AddAddress(new Address(ed.getText().toString(), ed2.getText().toString()));

        ListView addressList = (ListView) findViewById(R.id.addressList);
        AddressAdapter adapter = new AddressAdapter(getApplicationContext(), R.layout.address_item, User.getAddresses());
        addressList.setAdapter(adapter);

        ed.setText("");
        ed2.setText("");

    }

    public void ShowOnMap(View view) {
     /*   Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);  */
    }
}