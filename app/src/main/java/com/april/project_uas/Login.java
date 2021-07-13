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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {

    private TextInputEditText txtNim_code;
    private TextInputEditText txtPass_code;
    private TextView txtLupa_code, txtDaftar_code;
    private Button btnLogin_code;

    private String URL = "http://192.168.100.39/uasMobile/login.php";

    private StringRequest stringRequest;
    private RequestQueue requestQueue;
    private String nim, pass;
    static final String ambilnim = "";

    private JSONObject jsonObj, jsonData; //digunakan u/ proses
    private JSONArray jsonLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        txtNim_code = (TextInputEditText)findViewById(R.id.txtNim);
        txtPass_code = (TextInputEditText)findViewById(R.id.txtPass);
        txtLupa_code = (TextView)findViewById(R.id.lupapas);
        txtDaftar_code = (TextView)findViewById(R.id.txtDaftar);
        btnLogin_code =  (Button)findViewById(R.id.btnLogin);

        btnLogin_code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nim = txtNim_code.getText().toString().trim();
                pass = txtPass_code.getText().toString().trim();

                if (nim.equals("")||pass.equals("")){
                    Toast.makeText(getApplicationContext(), "Harap semua diisi", Toast.LENGTH_SHORT).show();
                } else {
                    stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            try {
                                //instance of class JSONObj
                                jsonObj = new JSONObject(response);

                                String cek= jsonObj.getString("status");
                                String message = jsonObj.getString("message");
                                if(cek.equals("true")){
                                    String nama = jsonObj.getString("data");
                                    session ambil = new session(getApplicationContext());
                                    ambil.setNim(nim);
                                    Intent pindah = new Intent(Login.this, Dashboard.class);
                                    pindah.putExtra(ambilnim, txtNim_code.getText().toString());
                                    startActivity(pindah);
                                }
                                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }



                        }

                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(getApplicationContext(), "Anda belum terdaftar", Toast.LENGTH_SHORT).show();
                        }
                    })
                    {
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {

                            Map<String, String> data = new HashMap<>();
                            data.put("nim", nim);
                            data.put("password", pass);
                            return data;

                        }
                    };
                    //request ke Volley
                    requestQueue = Volley.newRequestQueue(getApplicationContext());
                    requestQueue.add(stringRequest);
                }
            }
        });


        txtDaftar_code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pindah = new Intent(Login.this, registrasi.class);
                pindah.putExtra(nim, txtNim_code.getText().toString());
                startActivity(pindah);
            }
        });

        txtLupa_code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pindah = new Intent(Login.this, LupaPassword.class);
                pindah.putExtra(nim, txtNim_code.getText().toString());
                startActivity(pindah);
            }
        });
    }
}