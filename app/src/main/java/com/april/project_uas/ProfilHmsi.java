package com.april.project_uas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class ProfilHmsi extends AppCompatActivity {

    private ImageView imgBack_code;
    private Button btnDev_code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil_hmsi);

        imgBack_code = (ImageView)findViewById(R.id.imgBack);
        btnDev_code = (Button)findViewById(R.id.btnDev);

        imgBack_code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pindah = new Intent(ProfilHmsi.this, Dashboard.class);
                startActivity(pindah);
            }
        });

        btnDev_code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pindah = new Intent(ProfilHmsi.this, Developer.class);
                startActivity(pindah);
            }
        });
    }
}