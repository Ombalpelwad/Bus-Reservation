package com.example.map_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity3 extends AppCompatActivity {
    SQLiteDatabase db;
    ImageView s1;
    ImageView s2;
    ImageView s3;
    ImageView s4;
    ImageView s5;
    ImageView s6;
    ImageView s7;
    ImageView s8;
    ImageView s9;
    ImageView s10;
    ImageView s11;
    ImageView s12;
    ImageView s13;
    ImageView s14;
    ImageView s15;
    ImageView s16;
    ImageView s17;
    ImageView s18;
    TextView t1;
    TextView t2;
    TextView t3;
    TextView t4;

    Cursor c;
    Button b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db=openOrCreateDatabase("BusDB", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS bus1_pm(seatnumber String, i number,user String);");
        db.execSQL("CREATE TABLE IF NOT EXISTS bus2_pm(seatnumber String, i number,user String);");
        db.execSQL("CREATE TABLE IF NOT EXISTS bus3_pm(seatnumber String, i number,user String);");

        db.execSQL("CREATE TABLE IF NOT EXISTS bus1_mp(seatnumber String, i number,user String);");
        db.execSQL("CREATE TABLE IF NOT EXISTS bus2_mp(seatnumber String, i number,user String);");
        db.execSQL("CREATE TABLE IF NOT EXISTS bus3_mp(seatnumber String, i number,user String);");


        Intent i=getIntent();
        Bundle b1=i.getBundleExtra("b");
        String d=b1.getString("d");
        String email=b1.getString("email");
        String source=b1.getString("src");
        String destination=b1.getString("dst");
        String busname=b1.getString("bus_name");
        int busprice=b1.getInt("bus_price");
        String bp = "Rs."+busprice;

        t1=findViewById(R.id.srctxt);
        t2=findViewById(R.id.dsttxt);
        t3=findViewById(R.id.name);
        t4=findViewById(R.id.pricetxt);
        t1.setText(source);
        t2.setText(destination);
        t3.setText(busname);
        t4.setText(bp);

        Log.d("hey", email);

            c=db.rawQuery("SELECT * FROM "+d,null);
            if(c.getCount()==0) {
                // do nothing
            }
            else{
                while(c.moveToNext()){
                    String s12=c.getString(0);
                    String s13=c.getString(2);
                    if(s13.equals(email)){
                        int id=getResources().getIdentifier(s12,"id",this.getPackageName());
                        ImageView v=findViewById(id);
                        v.setTag("im2");
                        v.setImageDrawable(getResources().getDrawable(R.drawable.myseats));
                    }
                    else{
                        int id = getResources().getIdentifier(s12, "id", this.getPackageName());
                        ImageView v=findViewById(id);
                        v.setTag("im2");
                        v.setImageDrawable(getResources().getDrawable(R.drawable.booked_img));
                    }

                }
            }

        ArrayList<String> a=new ArrayList<>();


        s1=findViewById(R.id.s1);
        s2=findViewById(R.id.s2);
        s3=findViewById(R.id.s3);
        s4=findViewById(R.id.s4);
        s5=findViewById(R.id.s5);
        s6=findViewById(R.id.s6);
        s7=findViewById(R.id.s7);
        s8=findViewById(R.id.s8);
        s9=findViewById(R.id.s9);
        s10=findViewById(R.id.s10);
        s11=findViewById(R.id.s11);
        s12=findViewById(R.id.s12);
        s13=findViewById(R.id.s13);
        s14=findViewById(R.id.s14);
        s15=findViewById(R.id.s15);
        s16=findViewById(R.id.s16);
        s17=findViewById(R.id.s17);
        s18=findViewById(R.id.s18);

        b=findViewById(R.id.book);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                db.execSQL("Drop Table bus1_mp");
//                db.execSQL("Drop Table bus2_mp");
//                db.execSQL("Drop Table bus3_mp");
//                db.execSQL("Drop Table bus1_pm");
//                db.execSQL("Drop Table bus2_pm");
//                db.execSQL("Drop Table bus3_pm");

                for(String w:a){
                    String q=w.substring(1);
                    db.execSQL("INSERT INTO "+d+" VALUES('"+w+"','"+q+"','"+email+"')");
                }
                int n = a.size();
//                int bpr = Integer.valueOf(busprice);
                Intent i1 = new Intent("com.example.map_project.MainActivity4");
                Bundle bundle = new Bundle();
                bundle.putString("email", email);
                bundle.putString("src", source);
                bundle.putString("dst",destination);
                bundle.putString("bn",busname);
                bundle.putInt("bp",busprice);
                bundle.putInt("qty",n);
                i1.putExtra("b",bundle);
                startActivity(i1);
            }
        });

        s1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                c=db.rawQuery("SELECT * FROM "+ d+ " WHERE seatnumber = '"+"s1'", null);
                if(c.getCount()==0) {
                    if (String.valueOf(s1.getTag()).equals("im1")) {
                        s1.setImageDrawable(getResources().getDrawable(R.drawable.your_seat_img));
                        s1.setTag("im2");
                        a.add("s1");
                    } else {
                        s1.setImageDrawable(getResources().getDrawable(R.drawable.available_img));
                        s1.setTag("im1");
                        a.remove(String.valueOf("s1"));
                    }
                }
            }

        });
        s2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                c=db.rawQuery("SELECT * FROM "+ d+ " WHERE seatnumber = '"+"s2'", null);
                if(c.getCount()==0) {
                    if (String.valueOf(s2.getTag()).equals("im1")) {
                        s2.setImageDrawable(getResources().getDrawable(R.drawable.your_seat_img));
                        s2.setTag("im2");
                        a.add("s2");
                    }
                    else {
                        s2.setImageDrawable(getResources().getDrawable(R.drawable.available_img));
                        s2.setTag("im1");
                        a.remove(String.valueOf("s2"));
                    }
                }
            }
        });
        s3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                c=db.rawQuery("SELECT * FROM "+ d+ " WHERE seatnumber = '"+"s3'", null);
                if(c.getCount()==0) {
                    if (String.valueOf(s3.getTag()).equals("im1")) {
                        s3.setImageDrawable(getResources().getDrawable(R.drawable.your_seat_img));
                        s3.setTag("im2");
                        a.add("s3");
                    } else {
                        s3.setImageDrawable(getResources().getDrawable(R.drawable.available_img));
                        s3.setTag("im1");
                        a.remove(String.valueOf("s3"));
                    }
                }
            }
        });
        s4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                c = db.rawQuery("SELECT * FROM " + d + " WHERE seatnumber = '" + "s4'", null);
                if (c.getCount() == 0) {
                    if (String.valueOf(s4.getTag()).equals("im1")) {
                        s4.setImageDrawable(getResources().getDrawable(R.drawable.your_seat_img));
                        s4.setTag("im2");
                        a.add("s4");
                    } else {
                        s4.setImageDrawable(getResources().getDrawable(R.drawable.available_img));
                        s4.setTag("im1");
                        a.remove(String.valueOf("s4"));
                    }

                }
            }
        });
        s5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                c=db.rawQuery("SELECT * FROM "+ d+ " WHERE seatnumber = '"+"s5'", null);
                if(c.getCount()==0) {
                    if (String.valueOf(s5.getTag()).equals("im1")) {
                        s5.setImageDrawable(getResources().getDrawable(R.drawable.your_seat_img));
                        s5.setTag("im2");
                        a.add("s5");
                    } else {
                        s5.setImageDrawable(getResources().getDrawable(R.drawable.available_img));
                        s5.setTag("im1");
                        a.remove(String.valueOf("s5"));
                    }
                }
            }
        });
        s6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                c=db.rawQuery("SELECT * FROM "+ d+ " WHERE seatnumber = '"+"s6'", null);
                if(c.getCount()==0) {
                    if (String.valueOf(s6.getTag()).equals("im1")) {
                        s6.setImageDrawable(getResources().getDrawable(R.drawable.your_seat_img));
                        s6.setTag("im2");
                        a.add("s6");
                    } else {
                        s6.setImageDrawable(getResources().getDrawable(R.drawable.available_img));
                        s6.setTag("im1");
                        a.remove(String.valueOf("s6"));
                    }
                }
            }
        });
        s7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                c=db.rawQuery("SELECT * FROM "+ d+ " WHERE seatnumber = '"+"s7'", null);
                if(c.getCount()==0) {
                    if (String.valueOf(s7.getTag()).equals("im1")) {
                        s7.setImageDrawable(getResources().getDrawable(R.drawable.your_seat_img));
                        s7.setTag("im2");
                        a.add("s7");
                    } else {
                        s7.setImageDrawable(getResources().getDrawable(R.drawable.available_img));
                        s7.setTag("im1");
                        a.remove(String.valueOf("s7"));
                    }
                }
            }
        });
        s8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                c=db.rawQuery("SELECT * FROM "+ d+ " WHERE seatnumber = '"+"s8'", null);
                if(c.getCount()==0) {
                    if (String.valueOf(s8.getTag()).equals("im1")) {
                        s8.setImageDrawable(getResources().getDrawable(R.drawable.your_seat_img));
                        s8.setTag("im2");
                        a.add("s8");
                    } else {
                        s8.setImageDrawable(getResources().getDrawable(R.drawable.available_img));
                        s8.setTag("im1");
                        a.remove(String.valueOf("s8"));
                    }
                }
            }
        });
        s9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                c=db.rawQuery("SELECT * FROM "+ d+ " WHERE seatnumber = '"+"s9'", null);
                if(c.getCount()==0) {
                    if (String.valueOf(s9.getTag()).equals("im1")) {
                        s9.setImageDrawable(getResources().getDrawable(R.drawable.your_seat_img));
                        s9.setTag("im2");
                        a.add("s9");
                    } else {
                        s9.setImageDrawable(getResources().getDrawable(R.drawable.available_img));
                        s9.setTag("im1");
                        a.remove(String.valueOf("s9"));
                    }
                }
            }
        });
        s10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                c=db.rawQuery("SELECT * FROM "+ d+ " WHERE seatnumber = '"+"s10'", null);
                if(c.getCount()==0) {
                    if (String.valueOf(s10.getTag()).equals("im1")) {
                        s10.setImageDrawable(getResources().getDrawable(R.drawable.your_seat_img));
                        s10.setTag("im2");
                        a.add("s10");
                    } else {
                        s10.setImageDrawable(getResources().getDrawable(R.drawable.available_img));
                        s10.setTag("im1");
                        a.remove(String.valueOf("s10"));
                    }
                }
            }
        });
        s11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                c=db.rawQuery("SELECT * FROM "+ d+ " WHERE seatnumber = '"+"s11'", null);
                if(c.getCount()==0) {
                    if (String.valueOf(s11.getTag()).equals("im1")) {
                        s11.setImageDrawable(getResources().getDrawable(R.drawable.your_seat_img));
                        s11.setTag("im2");
                        a.add("s11");
                    } else {
                        s11.setImageDrawable(getResources().getDrawable(R.drawable.available_img));
                        s11.setTag("im1");
                        a.remove(String.valueOf("s11"));
                    }
                }
            }
        });
        s12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                c=db.rawQuery("SELECT * FROM "+ d+ " WHERE seatnumber = '"+"s12'", null);
                if(c.getCount()==0) {
                    if (String.valueOf(s12.getTag()).equals("im1")) {
                        s12.setImageDrawable(getResources().getDrawable(R.drawable.your_seat_img));
                        s12.setTag("im2");
                        a.add("s12");
                    } else {
                        s12.setImageDrawable(getResources().getDrawable(R.drawable.available_img));
                        s12.setTag("im1");
                        a.remove(String.valueOf("s12"));
                    }
                }
            }
        });
        s13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                c=db.rawQuery("SELECT * FROM "+ d+ " WHERE seatnumber = '"+"s13'", null);
                if(c.getCount()==0) {
                    if (String.valueOf(s13.getTag()).equals("im1")) {
                        s13.setImageDrawable(getResources().getDrawable(R.drawable.your_seat_img));
                        s13.setTag("im2");
                        a.add("s13");
                    } else {
                        s13.setImageDrawable(getResources().getDrawable(R.drawable.available_img));
                        s13.setTag("im1");
                        a.remove(String.valueOf("s13"));
                    }
                }
            }
        });
        s14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                c=db.rawQuery("SELECT * FROM "+ d+ " WHERE seatnumber = '"+"s14'", null);
                if(c.getCount()==0) {
                    if (String.valueOf(s14.getTag()).equals("im1")) {
                        s14.setImageDrawable(getResources().getDrawable(R.drawable.your_seat_img));
                        s14.setTag("im2");
                        a.add("s14");
                    } else {
                        s14.setImageDrawable(getResources().getDrawable(R.drawable.available_img));
                        s14.setTag("im1");
                        a.remove(String.valueOf("s14"));
                    }
                }
            }
        });
        s15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                c=db.rawQuery("SELECT * FROM "+ d+ " WHERE seatnumber = '"+"s15'", null);
                if(c.getCount()==0) {
                    if (String.valueOf(s15.getTag()).equals("im1")) {
                        s15.setImageDrawable(getResources().getDrawable(R.drawable.your_seat_img));
                        s15.setTag("im2");
                        a.add("s15");
                    } else {
                        s15.setImageDrawable(getResources().getDrawable(R.drawable.available_img));
                        s15.setTag("im1");
                        a.remove(String.valueOf("s15"));
                    }
                }
            }
        });
        s16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                c=db.rawQuery("SELECT * FROM "+ d+ " WHERE seatnumber = '"+"s16'", null);
                if(c.getCount()==0) {
                    if (String.valueOf(s16.getTag()).equals("im1")) {
                        s16.setImageDrawable(getResources().getDrawable(R.drawable.your_seat_img));
                        s16.setTag("im2");
                        a.add("s16");
                    } else {
                        s16.setImageDrawable(getResources().getDrawable(R.drawable.available_img));
                        s16.setTag("im1");
                        a.remove(String.valueOf("s16"));
                    }
                }
            }
        });
        s17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                c=db.rawQuery("SELECT * FROM "+ d+ " WHERE seatnumber = '"+"s17'", null);
                if(c.getCount()==0) {
                    if (String.valueOf(s17.getTag()).equals("im1")) {
                        s17.setImageDrawable(getResources().getDrawable(R.drawable.your_seat_img));
                        s17.setTag("im2");
                        a.add("s17");
                    } else {
                        s17.setImageDrawable(getResources().getDrawable(R.drawable.available_img));
                        s17.setTag("im1");
                        a.remove(String.valueOf("s17"));
                    }
                }
            }
        });
        s18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                c=db.rawQuery("SELECT * FROM "+ d+ " WHERE seatnumber = '"+"s18'", null);
                if(c.getCount()==0) {
                    if (String.valueOf(s18.getTag()).equals("im1")) {
                        s18.setImageDrawable(getResources().getDrawable(R.drawable.your_seat_img));
                        s18.setTag("im2");
                        a.add("s18");
                    } else {
                        s18.setImageDrawable(getResources().getDrawable(R.drawable.available_img));
                        s18.setTag("im1");
                        a.remove(String.valueOf("s18"));
                    }
                }
            }
        });



    }



}