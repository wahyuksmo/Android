package com.example.crud;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class TambahActivity extends AppCompatActivity {

    EditText agent, skills;
    Button btn_simpan;
    DatabaseReference database = FirebaseDatabase.getInstance().getReference();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah);


        agent = findViewById(R.id.input_agent);
        skills = findViewById(R.id.input_skiils);
        btn_simpan = findViewById(R.id.btn_simpan);

        btn_simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getAgent = agent.getText().toString();
                String getSkill = skills.getText().toString();

                if(getAgent.isEmpty()) {
                    agent.setError("Masukan nama agent....");
                }else if(getSkill.isEmpty()){
                    skills.setError("Masukan skiil...");
                }else {
                    database.child("Agent").push().setValue(new ModelAgent(getAgent, getSkill)).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(TambahActivity.this, "Data berhasil di simpan", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(TambahActivity.this, MainActivity.class));
                            finish();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(TambahActivity.this, "Data gagal di simpan", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });







    }
}