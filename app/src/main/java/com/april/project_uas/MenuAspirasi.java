package com.april.project_uas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
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

import java.util.HashMap;
import java.util.Map;

public class MenuAspirasi extends AppCompatActivity {

    private ImageView Aspirasiback_code;
    private TextInputEditText txtAspirasi_code, txtSolusi_code;
    private Button btnSubmit_code;

    String aspirasi, solusi;

    private JSONObject jsonObj, jsonData; //digunakan u/ proses
    private JSONArray jsonLogin;

    //deklar var
    private String URL = "http://192.168.100.39/uasMobile/aspirasi.php";

    //Stringrequest salah satu library valley untuk menangkap data
    private StringRequest stringRequest;
    private RequestQueue requestQueue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_aspirasi);

        Aspirasiback_code = (ImageView)findViewById(R.id.Aspirasiback);
        txtAspirasi_code = (TextInputEditText)findViewById(R.id.txtAspirasi);
        txtSolusi_code = (TextInputEditText)findViewById(R.id.txtSolusi);
        btnSubmit_code = (Button)findViewById(R.id.btnSubmitAspirasi);
        session ambil = new session(getApplicationContext());

        Aspirasiback_code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pindah = new Intent(MenuAspirasi.this, Dashboard.class);
                startActivity(pindah);
            }
        });

        btnSubmit_code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aspirasi= txtAspirasi_code.getText().toString().trim();
                solusi= txtSolusi_code.getText().toString().trim();

                if(aspirasi.equals("") || solusi.equals("")){


                }else{
                    stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Toast.makeText(getApplicationContext(),response , Toast.LENGTH_SHORT).show();

                            try {
                                //instance of class JSONObj
                                jsonObj = new JSONObject(response);

                                String message = jsonObj.getString("message");
                                Toast.makeText(getApplicationContext(),message , Toast.LENGTH_SHORT).show();

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(getApplicationContext(), "eror", Toast.LENGTH_SHORT).show();
                        }
                    })
                    {
                        @Override
                        protected Map<String,String> getParams() throws AuthFailureError {
                            Map<String,String> data = new HashMap<>();
                            data.put("saran",aspirasi);
                            data.put("solusi",solusi);
                            data.put("nim", ambil.getnim());
                            return data;
                        }
                    };
                    //request volley
                    requestQueue = Volley.newRequestQueue(getApplicationContext());
                    requestQueue.add(stringRequest);
                }

            }
        });


    }
}