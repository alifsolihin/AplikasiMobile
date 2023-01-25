package com.example.mimpi_kita;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.Collator;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    EditText ETemail, ETpass;
    String email, pass;
    Button BTlgn, BTNregis;
    String URL = "https://tkjbpnup.com/kelompok_9/mimpikita/logintes2.php";
    String str;
    /*SharedPreferences sharedPreferences;

    private static final String NAMA_PREF = "my_pr";
    private static final String EMAIL_PREF = "email_pr";
    private static final String PASS_PREF = "pass_pr";*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ETemail = findViewById(R.id.idETemail);
        ETpass  = findViewById(R.id.idETpass);
        BTlgn   = findViewById(R.id.idBTNlg);
        BTNregis = findViewById(R.id.idBTNdft);

        //sharedPreferences = getSharedPreferences(NAMA_PREF,MODE_PRIVATE);
        //SharedPreferences session = getSharedPreferences("session_login", Context.MODE_PRIVATE);


        /*BTlgn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

        String emailKey = ETemail.getText().toString();
        String passwordKey = ETpass.getText().toString();

        if (emailKey.equals("user24@gmail.com") && passwordKey.equals("12345678")) {

            Intent intent = new Intent(MainActivity.this, UserDashboardMainF.class);
            MainActivity.this.startActivity(intent);
            finish();

        }else if (emailKey.equals("admin24@gmail.com") && passwordKey.equals("12345678")){

                Intent intent = new Intent(MainActivity.this, AdminDashboard.class);
                MainActivity.this.startActivity(intent);
                finish();

            } else {

            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setMessage("Email atau Password Anda salah!")
                    .setNegativeButton("Kembali", null).create().show();
        }
    }

    });*/

        /*BTlgn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = ETemail.getText().toString().trim();
                pass = ETpass.getText().toString().trim();
                str = ETemail.getText().toString();
                if(!email.equals("")&& !pass.equals("")){
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            if (response.equals("user")) {
                                Intent intent = new Intent(MainActivity.this, UserDashboardMainF.class);
                                startActivity(intent);
                                finish();
                                Toast.makeText(MainActivity.this, "Login User Berhasil", Toast.LENGTH_SHORT).show();
                            } else if (response.equals("admin")){
                                //String email2 = sharedPreferences.getString(NAMA_PREF, null);
                                SharedPreferences.Editor editor =sharedPreferences.edit();
                                editor.putString(EMAIL_PREF,ETemail.getText().toString());
                                editor.putString(PASS_PREF,ETpass.getText().toString());
                                editor.apply();

                                Intent intent = new Intent(MainActivity.this, AdminDashboard.class);
                                intent.putExtra("email", str);
                                startActivity(intent);
                                finish();
                                Toast.makeText(MainActivity.this, "Login Admin Berhasil", Toast.LENGTH_SHORT).show();
                            } else if (response.equals("gagal")) {
                                Toast.makeText(MainActivity.this, "Email atau Password Anda Salah", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(MainActivity.this, "Cek Koneksi Internet Anda", Toast.LENGTH_SHORT).show();
                        }
                    }){
                        @Nullable
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> data = new HashMap<>();
                            data.put("email",email);
                            data.put("password", pass);
                            return data;
                        }
                    };
                    RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                    requestQueue.add(stringRequest);
                }else{
                    Toast.makeText(MainActivity.this,"Data tidak boleh kosong", Toast.LENGTH_SHORT).show();
                }

            }
        });*/


        /*BTlgn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = ETemail.getText().toString().trim();
                pass = ETpass.getText().toString().trim();
                str = ETemail.getText().toString();
                if (!email.equals("") && !pass.equals("")) {
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try{
                                JSONObject jsonObject = new JSONObject(response);
                                String email = jsonObject.getString("email");
                                String nama = jsonObject.getString("nama");
                                String level = jsonObject.getString("level");
                                String no = jsonObject.getString("no_hp");
                                Boolean status = jsonObject.getBoolean("status");
                                //String pesan = jsonObject.getString("result");
                                if (level.equals("user")) {
                                    Intent page = new Intent(MainActivity.this, UserDashboardMainF.class);
                                    startActivity(page);
                                    finish();
                                    Toast.makeText(MainActivity.this, "Login user berhasil", Toast.LENGTH_SHORT).show();
                                } else if (level.equals("admin")){
                                    Intent page = new Intent(MainActivity.this, AdminDashboard.class);
                                    page.putExtra("nama", nama);
                                    page.putExtra("no_hp", no);
                                    page.putExtra("email", email);
                                    startActivity(page);
                                    finish();
                                    Toast.makeText(MainActivity.this, "Login admin berhasil", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(MainActivity.this, "Username atau password anda salah", Toast.LENGTH_SHORT).show();
                                }

                            }catch (JSONException e){
                                Toast.makeText(MainActivity.this, "Cek Koneksi Internet", Toast.LENGTH_SHORT).show();
                            }

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(MainActivity.this, "Data tidak boleh kosong", Toast.LENGTH_SHORT).show();
                        }
                    }) {
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> data = new HashMap<>();
                            data.put("email", email);
                            data.put("password", pass);
                            return data;
                        }
                    };
                    RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                    requestQueue.add(stringRequest);
                } else {
                    Toast.makeText(MainActivity.this,"Data tidak boleh kosong", Toast.LENGTH_SHORT).show();
                }
            }
        });*/


        BTlgn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = ETemail.getText().toString().trim();
                pass = ETpass.getText().toString().trim();
                kirimData();
            }
        });


        BTNregis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                MainActivity.this.startActivity(intent);

            }
        });

    }

    void kirimData(){
        //SharedPreferences.Editor session = getSharedPreferences("session_login", MODE_PRIVATE).edit();
        AndroidNetworking.post("https://tkjbpnup.com/kelompok_9/mimpikita/logintes3.php")
                .addBodyParameter("email",""+email)
                .addBodyParameter("password",""+pass)
                .setPriority(Priority.MEDIUM)
                .setTag("Tambah Data")
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        email = ETemail.getText().toString().trim();
                        pass = ETpass.getText().toString().trim();
                        Log.d("cekTambah",""+response);
                        try {
                            Boolean status = response.getBoolean("status");
                            String pesan = response.getString("result");
                            String level = response.getString("level");
                            String nama = response.getString("nama");
                            String no = response.getString("no_hp");
                            String alamat = response.getString("alamat");
                            String tgl = response.getString("tanggal_lahir");
                            String foto = response.getString("foto");
                            String pass2 = response.getString("password");
                            Toast.makeText(MainActivity.this, ""+pesan, Toast.LENGTH_SHORT).show();
                            Log.d("status",""+status);
                            if(!email.equals("") && !pass.equals("")) {
                                if (level.equals("admin")) {
                                    Intent page;
                                    page = new Intent(MainActivity.this, AdminDashboard.class);
                                    //session.putString("email",ETemail.getText().toString());
                                    page.putExtra("nama", nama);
                                    page.putExtra("no_hp", no);
                                    page.putExtra("alamat", alamat);
                                    page.putExtra("tanggal_lahir", tgl);
                                    page.putExtra("email", email);
                                    page.putExtra("password", pass2);
                                    page.putExtra("foto", foto);
                                    startActivity(page);
                                } else if (level.equals("user")) {
                                    Intent page;
                                    page = new Intent(MainActivity.this, UserDashboardMainF.class);
                                    page.putExtra("nama", nama);
                                    page.putExtra("no_hp", no);
                                    page.putExtra("alamat", alamat);
                                    page.putExtra("tanggal_lahir", tgl);
                                    page.putExtra("email", email);
                                    page.putExtra("password", pass2);
                                    page.putExtra("foto", foto);
                                    startActivity(page);
                                }
                                else if (level.equals("owner")) {
                                    Intent page;
                                    page = new Intent(MainActivity.this, AdminOnwerDashboard.class);
                                    page.putExtra("nama", nama);
                                    page.putExtra("no_hp", no);
                                    page.putExtra("alamat", alamat);
                                    page.putExtra("tanggal_lahir", tgl);
                                    page.putExtra("email", email);
                                    page.putExtra("password", pass2);
                                    page.putExtra("foto", foto);
                                    startActivity(page);
                                }
                                else {
                                    Toast.makeText(MainActivity.this,"Username atau password Anda salah", Toast.LENGTH_SHORT).show();
                                }
                            }
                            else{
                                Toast.makeText(MainActivity.this,"Data tidak boleh kosong", Toast.LENGTH_SHORT).show();

                            }
                        }catch (Exception e){
                            Toast.makeText(MainActivity.this,"Username atau Password Anda Salah",Toast.LENGTH_SHORT).show();
                        }


                    }

                    @Override
                    public void onError(ANError anError) {
                        Toast.makeText(MainActivity.this,"Cek Koneksi Internet", Toast.LENGTH_SHORT).show();
                    }
                });
    }

}