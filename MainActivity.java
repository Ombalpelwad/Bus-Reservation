package com.example.map_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Spinner s1;
    Spinner s2;
    Button b1;
    String str1 = "";
    String str2 = "";
    String[] des={"Pune","Mumbai"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        Intent i=getIntent();
        Bundle b=i.getBundleExtra("b");
        String email=b.getString("email");

        s1=findViewById(R.id.spinner1);
        s2=findViewById(R.id.spinner2);
        b1=findViewById(R.id.search_buses);

        ArrayAdapter<String> a=new ArrayAdapter<>(this,android.R.layout.simple_dropdown_item_1line,des);
        s1.setAdapter(a);
        s2.setAdapter(a);

        b1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if((s1.getSelectedItem().toString()) != (s2.getSelectedItem().toString())){
                    str1 = s1.getSelectedItem().toString();
                    str2 = s2.getSelectedItem().toString();
                    Log.d("helli", str1);
                    Log.d("helli", str2);

                    Intent i=new Intent("com.example.map_project.MainActivity2");
                    Bundle bundle = new Bundle();
                    bundle.putString("key1", str1);
                    bundle.putString("key2", str2);
                    bundle.putString("email",email);
                    i.putExtra("b",bundle);
                    startActivity(i);
                }
                else{
                    Toast.makeText(MainActivity.this, "Please select valid locations!",Toast.LENGTH_SHORT).show();
                }
            }


        });


    }
}