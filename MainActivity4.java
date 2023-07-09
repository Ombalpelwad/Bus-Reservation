package com.example.map_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity4 extends AppCompatActivity {
    TextView t1;
    TextView t2;
    TextView t3;
    TextView t4;
    TextView t5;
    TextView t6;
    TextView t7;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        t1 = findViewById(R.id.email1txt);
        t2 = findViewById(R.id.qty1);
        t3 = findViewById(R.id.price1);
        t4 = findViewById(R.id.src1txt);
        t5 = findViewById(R.id.dst1txt);
        t6 = findViewById(R.id.bntxt);
        t7 = findViewById(R.id.bptxt);
        Intent i=getIntent();
        Bundle b1=i.getBundleExtra("b");
        String email=b1.getString("email");
        String source=b1.getString("src");
        String destination=b1.getString("dst");
        String busname1=b1.getString("bn");
        int busprice1=b1.getInt("bp");
        int quantity=b1.getInt("qty");
        int finalprice = quantity*busprice1;
        String price1 = "Rs."+String.valueOf(busprice1);
        String price ="Rs. " + String.valueOf(finalprice);
        String quantity1= String.valueOf(quantity);
        t1.setText(email);
        t2.setText(quantity1);
        t3.setText(price);
        t4.setText(source);
        t5.setText(destination);
        t6.setText(busname1);
        t7.setText(price1);

    }
}