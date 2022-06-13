package com.example.crud;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DialogForm extends DialogFragment {
    String agent, skills, key, pilih;
    DatabaseReference database = FirebaseDatabase.getInstance().getReference();

    public DialogForm(String agent, String skills, String key, String pilih) {
        this.agent = agent;
        this.skills = skills;
        this.key = key;
        this.pilih = pilih;
    }


    TextView eAgent, eSkiils;
    Button btn_simpan;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final  View view = inflater.inflate(R.layout.activity_tambah, container, false);
        eAgent = view.findViewById(R.id.input_agent);
        eSkiils = view.findViewById(R.id.input_skiils);
        btn_simpan = view.findViewById(R.id.btn_simpan);


        eAgent.setText(agent);
        eSkiils.setText(skills);
        btn_simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String aAgent = eAgent.getText().toString();
                String aSkiils = eSkiils.getText().toString();


                if(pilih.equals("Ubah")) {
                    database.child("Agent").child(key).setValue(new ModelAgent(aAgent, aSkiils)).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(view.getContext(), "Berhasil di Update", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(view.getContext(), "Gagal di Update", Toast.LENGTH_SHORT).show();

                        }
                    });
                }

            }
        });

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();

        if(dialog != null) {
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }
    }
}
