package com.april.project_uas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;

import java.util.HashMap;
import java.util.Map;

public class registrasi extends AppCompatActivity {

    private TextInputEditText txtNim_code;
    public TextInputEditText txtNama_code;
    private TextInputEditText txtPass_code;
    private Button btnMasuk_code;
    private TextView txtStatus_code;
    static final String ambilnama = "";

    //deklar var
    private String URL = "http://192.168.100.39/uasMobile/register.php";

    //Stringrequest salah satu library valley untuk menangkap data
    private StringRequest stringRequest;
    private RequestQueue requestQueue;
    private String nim, nama, pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_registrasi);

        txtNim_code = (TextInputEditText)findViewById(R.id.txtNim);
        txtNama_code = (TextInputEditText)findViewById(R.id.txtNama);
        txtPass_code = (TextInputEditText)findViewById(R.id.txtPass);
        btnMasuk_code = (Button)findViewById(R.id.btndaftar);

        txtStatus_code = (TextView)findViewById(R.id.txtStatus);

        btnMasuk_code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nim = txtNim_code.getText().toString().trim();
                nama = txtNama_code.getText().toString().trim();
                pass = txtPass_code.getText().toString().trim();

                if (nim.equals("") || nama.equals("") || pass.equals("")){
                    Toast.makeText(getApplicationContext(), "Harap semua diisi", Toast.LENGTH_SHORT).show();
                } else {
                    //instance of class
                    stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            txtStatus_code.setText(response);
                            Toast.makeText(getApplicationContext(), "Berhasil Daftar", Toast.LENGTH_SHORT).show();
                            Intent pindah = new Intent(registrasi.this, Login.class);
                            pindah.putExtra(ambilnama, txtNama_code.getText().toString());
                            startActivity(pindah);
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
//                            Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
                        }
                    })
                    {
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {

                            Map<String, String> data = new HashMap <>();
                            data.put("nim", nim);
                            data.put("nama", nama);
                            data.put("password", pass);
                            return data;
                        }

                    };


                    //req volley
                    requestQueue = Volley.newRequestQueue(getApplicationContext());
                    requestQueue.add(stringRequest);
                }
            }


        });
    }
}