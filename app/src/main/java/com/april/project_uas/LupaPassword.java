package com.april.project_uas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LupaPassword extends AppCompatActivity {

    private TextInputEditText txtNim_code, txtPassbaru_code, txtPasslama_code;
    private Button btnUbahpwd_code, btnHapusakun;
    private ImageView Lupaback_code;
    private TextView txtStatus_code;

    private String URLUpdate = "http://192.168.100.39/uasMobile/update.php";
    private String URLDelete = "http://192.168.100.39/uasMobile/delete.php";

    private StringRequest stringRequest;
    private RequestQueue requestQueue;
    private String nim, passbaru, passlama;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lupa_password);

        Lupaback_code = (ImageView)findViewById(R.id.Lupapasback);

        txtNim_code = (TextInputEditText)findViewById(R.id.txtNim);
        txtPassbaru_code = (TextInputEditText)findViewById(R.id.txtPassbaru);
        txtPasslama_code = (TextInputEditText)findViewById(R.id.txtPasslama);

        btnUbahpwd_code = (Button)findViewById(R.id.btnUbahpwd);
        btnHapusakun = (Button)findViewById(R.id.btnHapusakun);

        txtStatus_code = (TextView)findViewById(R.id.txtStatus);


        Lupaback_code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pindah = new Intent(LupaPassword.this, Login.class);
                startActivity(pindah);
            }
        });

        btnUbahpwd_code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nim = txtNim_code.getText().toString().trim();
                passbaru = txtPassbaru_code.getText().toString().trim();
                if (nim.equals("") || passbaru.equals("")){
                    Toast.makeText(getApplicationContext(), "Harap semua diisi", Toast.LENGTH_SHORT).show();
                }else {

                    stringRequest = new StringRequest(Request.Method.POST, URLUpdate, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
//                            txtStatus_code.setText(response);
                            Intent pindah = new Intent(LupaPassword.this, Login.class);
                            startActivity(pindah);
                                Toast.makeText(getApplicationContext(), "Berhasil Ubah", Toast.LENGTH_SHORT).show();
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
                            data.put("password", passbaru);
                            return data;
                        }

                    };
                    //request ke Volley
                    requestQueue = Volley.newRequestQueue(getApplicationContext());
                    requestQueue.add(stringRequest);

                }
            }
        });

        btnHapusakun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nim = txtNim_code.getText().toString().trim();
                passlama = txtPasslama_code.getText().toString().trim();

                if (nim.equals("") || passlama.equals("")){
                    Toast.makeText(getApplicationContext(), "Harap semua diisi", Toast.LENGTH_SHORT).show();
                }else {

                    stringRequest = new StringRequest(Request.Method.POST, URLDelete, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            Intent pindah = new Intent(LupaPassword.this, Login.class);
                            startActivity(pindah);
                            Toast.makeText(getApplicationContext(), "Berhasil dihapus", Toast.LENGTH_SHORT).show();
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
                            data.put("password", passlama);
                            return data;
                        }

                    };
                    //request ke Volley
                    requestQueue = Volley.newRequestQueue(getApplicationContext());
                    requestQueue.add(stringRequest);

                }
            }
        });
    }
}