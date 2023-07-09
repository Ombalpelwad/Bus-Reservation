package com.example.map_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {
    CardView c1;
    CardView c2;
    CardView c3;
    TextView t1;
    TextView t2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
          t1 = findViewById(R.id.textView1);
          t2 = findViewById(R.id.textView2);
          c1 = findViewById(R.id.card1);
          c2= findViewById(R.id.card2);
          c3 = findViewById(R.id.card3);

        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("b");
        String source = bundle.getString("key1");
        String destination = bundle.getString("key2");
        String email=bundle.getString("email");
        t1.setText(source);
        t2.setText(destination);

        c1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent("com.example.map_project.MainActivity3");
                Bundle b=new Bundle();
                if(source.equals("Pune")  && destination.equals("Mumbai")){
                    b.putString("d","bus1_pm");
                    b.putString("email",email);
                    b.putString("src",source);
                    b.putString("bus_name","ABC bus service");
                    b.putInt("bus_price",500);
                    b.putString("dst",destination);
                }
                else if(source.equals("Mumbai") && destination.equals("Pune")){
                    b.putString("d","bus1_mp");
                    b.putString("email",email);
                    b.putString("src",source);
                    b.putString("dst",destination);
                    b.putString("bus_name","ABC bus service");
                    b.putInt("bus_price",500);
                }
                i.putExtra("b",b);
                startActivity(i);
            }
        });

        c2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent("com.example.map_project.MainActivity3");
                Bundle b=new Bundle();

                if(source.equals("Pune")  && destination.equals("Mumbai")){
                    b.putString("d","bus2_pm");
                    b.putString("email",email);
                    b.putString("src",source);
                    b.putString("bus_name","XYZ bus service");
                    b.putInt("bus_price",750);
                    b.putString("dst",destination);
                }
                else if(source.equals("Mumbai") && destination.equals("Pune")){
                    b.putString("d","bus2_mp");
                    b.putString("email",email);
                    b.putString("src",source);
                    b.putString("bus_name","XYZ bus service");
                    b.putInt("bus_price",750);
                    b.putString("dst",destination);
                }
                i.putExtra("b",b);
                startActivity(i);
            }
        });
        c3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent("com.example.map_project.MainActivity3");
                Bundle b=new Bundle();
                if(source.equals("Pune")  && destination.equals("Mumbai")){
                    b.putString("d","bus3_pm");
                    b.putString("email",email);
                    b.putString("bus_name","PQR bus service");
                    b.putInt("bus_price",800);
                    b.putString("src",source);
                    b.putString("dst",destination);
                }
                else if(source.equals("Mumbai") && destination.equals("Pune")){
                    b.putString("d","bus3_mp");
                    b.putString("email",email);
                    b.putString("bus_name","PQR bus service");
                    b.putInt("bus_price",800);
                    b.putString("src",source);
                    b.putString("dst",destination);
                }
                i.putExtra("b",b);
                startActivity(i);
            }
        });
    }
}