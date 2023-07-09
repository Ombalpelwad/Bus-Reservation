package com.example.map_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
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
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {

    public static final String TAG = "TAG";
    EditText mFullname,mEmail,mPassword,mPhone;
    Button mRegistorBtn;
    TextView mLoginBtn;

    FirebaseFirestore fstore;
    String userID;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mFullname =findViewById(R.id.fullname);
        mEmail = findViewById(R.id.Email);
        mPassword = findViewById(R.id.password);
        mPhone = findViewById(R.id.phone);
        mRegistorBtn = findViewById(R.id.registerbtn);
        mLoginBtn = findViewById(R.id.createtext);

        fAuth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();

//        if ((fAuth.getCurrentUser()!= null)){
//            startActivity(new Intent(getApplicationContext(),MainActivity.class));
//            finish();
//        }


        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Login1.class));
            }
        });

        mRegistorBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = mEmail.getText().toString().trim();
                String password = mPassword.getText().toString().trim();
                final String fullname = mFullname.getText().toString().trim();
                final String phone = mPhone.getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                    mEmail.setError("Email is Required");
                }

                if(TextUtils.isEmpty(password)){
                    mPassword.setError("Password is Required");
                }

                if (password.length() < 6){
                    mPassword.setError("Password Must Be >= 6 character");
                    return;
                }


                fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){

                            FirebaseUser fuser = fAuth.getCurrentUser();
                            fuser.sendEmailVerification()

                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Toast.makeText(getApplicationContext(),"Register Succesfully",Toast.LENGTH_SHORT).show();
                                }
                            })
                                    .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.d(TAG,"Onfailure: Email Not Send" + e.getMessage());
                                }
                            });
                            Toast.makeText(getApplicationContext(),"User Created",Toast.LENGTH_SHORT).show();
                            userID = fAuth.getCurrentUser().getUid();
                            DocumentReference documentReference = fstore.collection("user").document(userID);
                            Map<String,Object> user = new HashMap<>();
                            user.put("Name",fullname);
                            user.put("email",email);
                            user.put("phone",phone);
                            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Log.d(TAG,"onsucess: user profile is created for " + userID);
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.d(TAG,"onfailure: "+e.toString());
                                }
                            });

                        }
                        else{
                            Toast.makeText(Register.this,"Error" + task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });





    }
}