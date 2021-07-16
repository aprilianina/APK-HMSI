package com.april.project_uas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Developer extends AppCompatActivity {

    private ImageView imgBack_code, Instagram_code, Linkedin_code, Github_code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_developer);

        imgBack_code = (ImageView)findViewById(R.id.imgBack);
        Instagram_code = (ImageView)findViewById(R.id.imgInsta);
        Linkedin_code = (ImageView)findViewById(R.id.imgLink);
        Github_code = (ImageView)findViewById(R.id.imgGit);

        imgBack_code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pindah = new Intent(Developer.this, ProfilHmsi.class);
                startActivity(pindah);
            }
        });

        Instagram_code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pindah = new Intent();
                pindah.setAction(Intent.ACTION_VIEW);
                pindah.setData(Uri.parse("https://instagram.com/aprilia.ni"));
                startActivity(pindah);
            }
        });

        Linkedin_code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pindah = new Intent();
                pindah.setAction(Intent.ACTION_VIEW);
                pindah.setData(Uri.parse("https://www.linkedin.com/in/apriliani-nur-afifah-8b4a39157"));
                startActivity(pindah);
            }
        });

        Github_code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pindah = new Intent();
                pindah.setAction(Intent.ACTION_VIEW);
                pindah.setData(Uri.parse("https://github.com/aprilianina.github.io"));
                startActivity(pindah);
            }
        });
    }
}