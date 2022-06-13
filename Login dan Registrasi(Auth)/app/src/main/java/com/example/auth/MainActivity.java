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

public class MainActivity extends AppCompatActivity {

    EditText inputEmail, inputPassword;
    String email, password;
    Button btnLogin;
    TextView keReg;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();


        inputEmail = findViewById(R.id.email);
        inputPassword = findViewById(R.id.password);
        btnLogin = findViewById(R.id.btn_login);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cekLogin();
            }
        });








        keReg = findViewById(R.id.keReg);
        keReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, RegistrasiActivity.class));
            }
        });






    }

    private void cekLogin() {
       email =   inputEmail.getText().toString();
       password = inputPassword.getText().toString();


       if(TextUtils.isEmpty(email)) {
           inputEmail.setError("Tidak boleh kosong");
       } else if(TextUtils.isEmpty(password)) {
           inputPassword.setError("Tidak boleh kosong");
       }else {
           mAuth.signInWithEmailAndPassword(email, password)
                   .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                       @Override
                       public void onComplete(@NonNull Task<AuthResult> task) {
                           if (task.isSuccessful()) {
                               // Sign in success, update UI with the signed-in user's information
                               Toast.makeText(MainActivity.this, "Login Sukses", Toast.LENGTH_SHORT).show();
                               startActivity(new Intent(MainActivity.this, LogoutActivity.class));
                           } else {
                               // If sign in fails, display a message to the user.
                               Toast.makeText(MainActivity.this, "Login Gagal", Toast.LENGTH_SHORT).show();
                           }
                       }
                   });

       }




    }
}