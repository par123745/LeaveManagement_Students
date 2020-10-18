package com.isha.leavemanagementsystemforstudents;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.net.URI;

public class registration extends AppCompatActivity {
    EditText e1,e2,e3,e4,e5,e6;
    String name,email,pass,hod_email,gender,warden_mob;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        e1=findViewById(R.id.editText9);
        e2=findViewById(R.id.editText10);
        e3=findViewById(R.id.editText11);
        e4=findViewById(R.id.editText12);
        e5=findViewById(R.id.editText13);
        e6=findViewById(R.id.editText17);


    }

    public void submit(View view) {
        name = e1.getText().toString();
        email=e2.getText().toString();
        pass=e3.getText().toString();
        hod_email=e4.getText().toString();
        gender=e5.getText().toString();
        warden_mob=e6.getText().toString();

        RequestQueue rq= Volley.newRequestQueue(registration.this);
        String url= "http://malnirisha.xyz/leave/student_reg.php?n="+name+"&e="+email+"&p="+pass+"&h="+hod_email+"&g="+gender+"&w="+warden_mob;
        url = url.replace(" ", "%20");
        Log.d("url",url);
        StringRequest sr=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.trim().equals("1")){
                    Toast.makeText(registration.this, "Success", Toast.LENGTH_SHORT).show();
                    Intent i =new Intent(registration.this,login.class);
                    startActivity(i);
                    finish();
                }
                else if(response.trim().equals("0")){
                    Toast.makeText(registration.this, "Email Exist", Toast.LENGTH_SHORT).show();
                }
              
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(registration.this, "No Internet", Toast.LENGTH_SHORT).show();
            }
        });
        sr.setShouldCache(false);
        sr.setRetryPolicy(new DefaultRetryPolicy(20*1000,0,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        rq.add(sr);

    }
}
