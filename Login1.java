package com.example.map_project;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;

import java.util.HashMap;
import java.util.Map;

public class Login1 extends AppCompatActivity {

    EditText mEmail,mpassword;
    Button mLoginBtn;
    TextView mCreateBtn;

    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login1);

        mEmail = findViewById(R.id.emailTxt);
        mpassword = findViewById(R.id.enterPwd);
        mLoginBtn = findViewById(R.id.loginbtn);
        mCreateBtn = findViewById(R.id.createtext);

        fAuth = FirebaseAuth.getInstance();

        mCreateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Login1.this, "Welcome User", Toast.LENGTH_SHORT).show();
                Intent i1=new Intent(Login1.this,Register.class);
                startActivity(i1);

            }
        });

        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mEmail.getText().toString();
                String password = mpassword.getText().toString();

                if (TextUtils.isEmpty(email)) {
                    mEmail.setError("Email is Required");
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    mpassword.setError("Password is Required");
                    return;
                }

                if (password.length() < 6) {
                    mpassword.setError("Password Must Be >= 6 character");
                    return;
                }


                fAuth.signInWithEmailAndPassword(email,password)
                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                Toast.makeText(Login1.this, "Welcome User", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent("com.example.map_project.MainActivity");
                                Bundle b=new Bundle();
                                b.putString("email",email);
                                i.putExtra("b",b);
                                startActivity(i);
                            }
                        })
                                .
                        addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(Login1.this, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });

    }
}