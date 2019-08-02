package com.kimbrian.sunrise.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.kimbrian.sunrise.R;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.kimbrian.sunrise.adapter.MenuAdapter;
import com.kimbrian.sunrise.models.MenuModel;

import java.util.ArrayList;
import java.util.List;

public class RestActivity extends AppCompatActivity {

    TextView nameRest, timeRest, locRest;
    String name, time, loc;
    RecyclerView menuRecycler;
    private DatabaseReference mDatabase;
    FirebaseDatabase dbb;
    List<MenuModel> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rest);

        menuRecycler = findViewById(R.id.recycler_menu);
        nameRest = findViewById(R.id.nameeRest);
        timeRest = findViewById(R.id.timeeRest);
        locRest = findViewById(R.id.loccRest);


        Intent i = getIntent();
        name = i.getStringExtra("restName");
        time = i.getStringExtra("restTime");
        loc = i.getStringExtra("restLoc");


        nameRest.setText(name);
        timeRest.setText(time);
        locRest.setText(loc);


        dbb = FirebaseDatabase.getInstance();
        mDatabase = dbb.getReference("Restaurants").child(name).child("Menu");
        mDatabase.keepSynced(true);
        final MenuAdapter restaurantAdapter = new MenuAdapter(list,RestActivity.this);
        menuRecycler.setHasFixedSize(true);
        menuRecycler.setLayoutManager(new LinearLayoutManager(this));
        menuRecycler.setItemAnimator( new DefaultItemAnimator());


        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                list = new ArrayList<MenuModel>();
                list = new ArrayList<>();
                for (DataSnapshot userSnapshot : dataSnapshot.getChildren())
                {
                    String dish = userSnapshot.getKey();
                    String price = userSnapshot.getValue().toString();

                    MenuModel listdata = new MenuModel();

                    listdata.setDish(dish);
                    listdata.setPrice(price);
                    list.add(listdata);

                }
                MenuAdapter recyclerAdapter = new MenuAdapter(list,RestActivity.this);
                RecyclerView.LayoutManager layoutmanager = new LinearLayoutManager(RestActivity.this);
                menuRecycler.setLayoutManager(layoutmanager);
                menuRecycler.setItemAnimator( new DefaultItemAnimator());
                menuRecycler.setAdapter(recyclerAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
