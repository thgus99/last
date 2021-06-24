package com.example.tag;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {

    Button mbtn_url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Button savebtn = (Button)findViewById(R.id.savebtn);
        Button listbtn = (Button)findViewById(R.id.listbtn);
        Button addbtn = (Button)findViewById(R.id.addbtn);
        Button mapbtn = (Button)findViewById(R.id.mapbtn);
        mbtn_url = findViewById(R.id.btn_url);
        Button lonbtn = (Button)findViewById(R.id.lonbtn);

        savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent saveIntent = new Intent(MenuActivity.this, SaveActivity.class);
                MenuActivity.this.startActivity(saveIntent);
            }
        });

        listbtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent listIntent = new Intent(MenuActivity.this, ListActivity.class);
                MenuActivity.this.startActivity(listIntent);
            }
        });

        addbtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent uploadIntent = new Intent(MenuActivity.this, UploadActivity.class);
                MenuActivity.this.startActivity(uploadIntent);
            }
        });

        mapbtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent mapIntent = new Intent(MenuActivity.this, MapActivity.class);
                MenuActivity.this.startActivity(mapIntent);
            }
        });

        mbtn_url.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent urlintent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://smart-teg.com"));
                startActivity(urlintent);
            }
        });

        lonbtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent lonIntent = new Intent(MenuActivity.this, lonlist.class);
                MenuActivity.this.startActivity(lonIntent);
            }
        });
    }
}