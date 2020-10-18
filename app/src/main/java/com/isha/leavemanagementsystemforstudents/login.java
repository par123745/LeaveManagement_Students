package com.isha.leavemanagementsystemforstudents;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

public class login extends AppCompatActivity {
EditText e1,e2;
String email,pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        e1=findViewById(R.id.editText14);
        e2=findViewById(R.id.editText15);
        SharedPreferences sp = getSharedPreferences("user", Context.MODE_PRIVATE);
        String status=sp.getString("status","0");
        if (status.equals("1")){
            Intent i=new Intent(login.this,welcome.class);
            startActivity(i);
            finish();
        }

    }

    public void login(View view) {

        email=e1.getText().toString();
        pass=e2.getText().toString();


        RequestQueue rq= Volley.newRequestQueue(login.this);
        String url= "http://malnirisha.xyz/leave/student_login.php?e="+email+"&p="+pass;
        url = url.replace(" ", "%20");
        Log.d("url",url);
        StringRequest sr=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.trim().equals("1"))
                {
                    SharedPreferences sp = getSharedPreferences("user", Context.MODE_PRIVATE);
                    SharedPreferences.Editor spe = sp.edit();
                    spe.putString("status","1");
                    spe.putString("email",email);
                    spe.commit();
                    Intent i=new Intent(login.this,welcome.class);
                    startActivity(i);
                    finish();
                }
                else
                {
                    Toast.makeText(login.this, "login details incorrect", Toast.LENGTH_SHORT).show();
                }

        }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(login.this, "No Internet", Toast.LENGTH_SHORT).show();
            }
        });
        sr.setShouldCache(false);
        sr.setRetryPolicy(new DefaultRetryPolicy(20*1000,0,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        rq.add(sr);

    }

    public void reg(View view) {
        Intent i =new Intent(login.this,registration.class);
        startActivity(i);
    }
}
