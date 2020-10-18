package com.isha.leavemanagementsystemforstudents;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class welcome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
    }

    public void apl(View view) {
        Intent i= new Intent(welcome.this,MainActivity.class);
        startActivity(i);

    }

    public void ls(View view) {
        Intent i= new Intent(welcome.this,view.class);
        startActivity(i);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.op1){
            SharedPreferences sp = getSharedPreferences("user", Context.MODE_PRIVATE);
            SharedPreferences.Editor spe = sp.edit();
            spe.clear();
            spe.commit();
            Intent i=new Intent(welcome.this,login.class);
            startActivity(i);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
