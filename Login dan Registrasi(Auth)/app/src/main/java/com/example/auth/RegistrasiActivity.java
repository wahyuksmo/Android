package com.example.auth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegistrasiActivity extends AppCompatActivity {

    TextView keLog;
    EditText inputEmail, pass1, pass2;
    String email, password1, password2;
    Button btn_regis;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrasi);
        mAuth = FirebaseAuth.getInstance();

        inputEmail = findViewById(R.id.regis_email);
        pass1  = findViewById(R.id.password1);
        pass2 = findViewById(R.id.password2);
        btn_regis = findViewById(R.id.btn_register);

        btn_regis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrasi();
            }
        });

        keLog = findViewById(R.id.keLog);
        keLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegistrasiActivity.this, MainActivity.class));
            }
        });


    }

    private void registrasi() {
        email =   inputEmail.getText().toString();
        password1 = pass1.getText().toString();
        password2 = pass2.getText().toString();


        if(TextUtils.isEmpty(email)) {
            inputEmail.setError("Tidak boleh kosong");
        }else if(TextUtils.isEmpty(password1)) {
            pass1.setError("Tidak boleh kosong");
        }else if(TextUtils.isEmpty(password2)) {
            pass2.setError("Tidak boleh kosong");
        } else if(!password2.equals(password1)) {
            pass2.setError("Password tidak sama");
        }else {
            mAuth.createUserWithEmailAndPassword(email,password2)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Toast.makeText(RegistrasiActivity.this, "Registrasi Sukses", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(RegistrasiActivity.this, MainActivity.class));
                            } else {
                                // If sign in fails, display a message to the user.
                                Toast.makeText(RegistrasiActivity.this, "Registrasi Gagal", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }




    }
}