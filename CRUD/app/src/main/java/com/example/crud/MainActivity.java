package com.example.crud;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton tambah;
    AdapterData adapterData;
    DatabaseReference database = FirebaseDatabase.getInstance().getReference();
    ArrayList<ModelAgent> listData;
    RecyclerView tampil_data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tambah = findViewById(R.id.btn_tambah);
        tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TambahActivity.class);
                startActivity(intent);
            }
        });

        tampil_data = findViewById(R.id.tampil_data);
        RecyclerView.LayoutManager mLayout = new LinearLayoutManager(this);
        tampil_data.setLayoutManager(mLayout);
        tampil_data.setItemAnimator(new DefaultItemAnimator());

         MenampilkanData();
    }

    private void MenampilkanData() {

        database.child("Agent").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listData = new ArrayList<>();
                for (DataSnapshot item : snapshot.getChildren()) {
                    ModelAgent ag = item.getValue(ModelAgent.class);
                    ag.setKey(item.getKey());
                    listData.add(ag);
                }
                adapterData = new AdapterData(listData,MainActivity.this);
                tampil_data.setAdapter(adapterData);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}