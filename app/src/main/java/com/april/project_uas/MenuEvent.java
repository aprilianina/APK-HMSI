package com.april.project_uas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MenuEvent extends AppCompatActivity {

    private ImageView imgBack_code;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_event);

        imgBack_code = (ImageView)findViewById(R.id.imgBack);

        imgBack_code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pindah = new Intent(MenuEvent.this, Dashboard.class);
                startActivity(pindah);
            }
        });
    }
}