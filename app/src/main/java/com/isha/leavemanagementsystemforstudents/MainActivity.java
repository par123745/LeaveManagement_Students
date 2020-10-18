package com.isha.leavemanagementsystemforstudents;

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

public class MainActivity extends AppCompatActivity {
EditText e1,e2,e3,e4,e5,e6,e7,e8,e9,e10,e11;

String usn,student_email,student_name,date,reason,address,stu_mob,parents_mob,date_issue,warden,hod_email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        e1=findViewById(R.id.editText);
        e2=findViewById(R.id.editText2);
        e3=findViewById(R.id.editText3);
        e4=findViewById(R.id.editText4);
        e5=findViewById(R.id.editText5);
        e6=findViewById(R.id.editText6);
        e7=findViewById(R.id.editText7);
        e8=findViewById(R.id.editText8);
        e9=findViewById(R.id.editText1);
        e10=findViewById(R.id.editText9);
        e11=findViewById(R.id.editText16);

    }

    public void go(View view) {
        student_name = e1.getText().toString();
       student_email =e9.getText().toString();
        usn=e2.getText().toString();
        date=e3.getText().toString();
        reason=e4.getText().toString();
        address=e5.getText().toString();
        stu_mob=e6.getText().toString();
        parents_mob=e7.getText().toString();
        date_issue=e8.getText().toString();
        warden=e10.getText().toString();
        hod_email=e11.getText().toString();

        RequestQueue rq= Volley.newRequestQueue(MainActivity.this);
        String url= "http://malnirisha.xyz/leave/leave_request.php?s1="+student_name+"&w="+warden+"&s2="+student_email+"&s3="+usn+"&s4="+date+"&s5="+reason+"&s6="+address+"&s7="+stu_mob+"&s8="+parents_mob+"&s9="+date_issue+"&s10="+hod_email;
        url = url.replace(" ", "%20");
        Log.d("url",url);
        StringRequest sr=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(MainActivity.this, response, Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "No Internet", Toast.LENGTH_SHORT).show();
            }
        });
        sr.setShouldCache(false);
        sr.setRetryPolicy(new DefaultRetryPolicy(20*1000,0,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        rq.add(sr);



    }
}
