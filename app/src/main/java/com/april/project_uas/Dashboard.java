package com.april.project_uas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.card.MaterialCardView;

public class Dashboard extends AppCompatActivity {

    private MaterialCardView cardEvent_code, cardPengurus_code, cardGaleri_code, cardAspirasi_code;
    private TextView txtMenuNama_code;
    private ImageView profilHMSI_code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        cardEvent_code = (MaterialCardView)findViewById(R.id.Dash_Event);
        cardGaleri_code = (MaterialCardView)findViewById(R.id.Dash_Galeri);
        cardPengurus_code = (MaterialCardView)findViewById(R.id.Dash_Pengurus);
        cardAspirasi_code = (MaterialCardView)findViewById(R.id.Dash_Aspirasi);

        txtMenuNama_code = (TextView) findViewById(R.id.txtMenuNama);

        cardEvent_code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pindah = new Intent(Dashboard.this, MenuEvent.class);
                startActivity(pindah);
            }
        });

        cardPengurus_code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pindah = new Intent(Dashboard.this, MenuPengurus.class);
                startActivity(pindah);
            }
        });

        cardGaleri_code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pindah = new Intent(Dashboard.this, MenuGaleri.class);
                startActivity(pindah);
            }
        });

        cardAspirasi_code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pindah = new Intent(Dashboard.this, MenuAspirasi.class);
                startActivity(pindah);
            }
        });

        Intent terima = getIntent();
        String terimaNama = terima.getStringExtra(Login.ambilnim);
        txtMenuNama_code.setText("Hi " + terimaNama);
    }
}