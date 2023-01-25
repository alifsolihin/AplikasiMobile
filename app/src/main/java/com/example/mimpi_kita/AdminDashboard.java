package com.example.mimpi_kita;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import de.hdodenhof.circleimageview.CircleImageView;

public class AdminDashboard extends AppCompatActivity {

    TextView TVJmlEv, TVJmlUs, TVnama, TVjmlSt, TVjmlBy, Tvjam, Tvtgl, tvjm1, tvjm2, tvjm3;
    CardView CARDuser,CARDpe, CARDpu, CARDsu, CARDmb;
    String email, nama, nomor, foto, pass, alm, tgl;
    CircleImageView IVgambar;

    /*SharedPreferences sharedPreferences;

    private static final String NAMA_PREF = "my_pr";
    private static final String EMAIL_PREF = "email_pr";
    private static final String PASS_PREF = "pass_pr";*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);

        CARDuser =findViewById(R.id.idCARDuser);
        CARDsu  = findViewById(R.id.idSU);
        CARDpe   =findViewById(R.id.idPE);
        CARDpu   =findViewById(R.id.idPU);
        CARDmb   =findViewById(R.id.idMB);
        TVJmlEv   =findViewById(R.id.idTVJmlEV);
        TVJmlUs   =findViewById(R.id.idTVJmlUS);
        TVjmlSt   =findViewById(R.id.idTVJmlST);
        TVjmlBy   =findViewById(R.id.idTVJmlBY);
        TVnama   =findViewById(R.id.idTV2);
        Tvjam   =findViewById(R.id.idjam);
        Tvtgl   =findViewById(R.id.idtgl);
        tvjm1 = findViewById(R.id.idTVJmlUSr);
        tvjm2 = findViewById(R.id.idTVJmlAD);
        tvjm3 = findViewById(R.id.idTVJmlOwn);
        IVgambar   =findViewById(R.id.IVgambar);

        email = getIntent().getExtras().getString("email");
        nama = getIntent().getExtras().getString("nama");
        nomor = getIntent().getExtras().getString("no_hp");
        foto = getIntent().getExtras().getString("foto");
        pass = getIntent().getExtras().getString("password");
        alm = getIntent().getExtras().getString("alamat");
        tgl = getIntent().getExtras().getString("tanggal_lahir");
        //nomor = getIntent().getExtras().getString("email");


        //sharedPreferences = getSharedPreferences(NAMA_PREF,MODE_PRIVATE);
        //String nama2 = sharedPreferences.getString("nama", null);
        //String nama = sharedPreferences.getString(EMAIL_PREF, null);
        //final SharedPreferences session = getSharedPreferences("session_login", Context.MODE_PRIVATE);
        //TVnama.setText(session.getString("email", null));
        TVnama.setText(nama);

        if (foto != null)
        {
            Picasso.get().load("https://tkjbpnup.com/kelompok_9/mimpikita/image/"+getIntent().getStringExtra("foto")).into(IVgambar);

        }
        else
        {
            Picasso.get().load("https://tkjbpnup.com/kelompok_9/mimpikita/image/noimage.png").into(IVgambar);
        }


        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://tkjbpnup.com/kelompok_9/mimpikita/countData1.php";
        String url2 = "https://tkjbpnup.com/kelompok_9/mimpikita/countData2.php";
        String url3 = "https://tkjbpnup.com/kelompok_9/mimpikita/countData3.php";
        String url4 = "https://tkjbpnup.com/kelompok_9/mimpikita/countData4.php";
        String url5 = "https://tkjbpnup.com/kelompok_9/mimpikita/countDataJM1.php";
        String url6 = "https://tkjbpnup.com/kelompok_9/mimpikita/countDataJM2.php";
        String url7 = "https://tkjbpnup.com/kelompok_9/mimpikita/countDataJM3.php";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        TVJmlEv.setText(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        queue.add(stringRequest);

        StringRequest stringRequest2 = new StringRequest(Request.Method.GET, url2,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        TVJmlUs.setText(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        queue.add(stringRequest2);

        StringRequest stringRequest3 = new StringRequest(Request.Method.GET, url3,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        TVjmlSt.setText(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        queue.add(stringRequest3);

        StringRequest stringRequest4 = new StringRequest(Request.Method.GET, url4,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        TVjmlBy.setText(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        queue.add(stringRequest4);


        StringRequest stringRequest5 = new StringRequest(Request.Method.GET, url5,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        tvjm1.setText(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        queue.add(stringRequest5);

        StringRequest stringRequest6 = new StringRequest(Request.Method.GET, url6,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        tvjm2.setText(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        queue.add(stringRequest6);

        StringRequest stringRequest7 = new StringRequest(Request.Method.GET, url7,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        tvjm3.setText(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        queue.add(stringRequest7);

        Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                @SuppressLint("SimpleDateFormat")
                DateFormat jamformat = new SimpleDateFormat("HH:mm:ss");

                @SuppressLint("SimpleDateFormat")
                DateFormat tglformat = new SimpleDateFormat("EEEE, dd MMMM yyyy");

                Tvjam.setText(jamformat.format(new Date()));
                Tvtgl.setText(tglformat.format(new Date()));

                handler.postDelayed(this,1000);
            }
        });




        CARDuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                nama = getIntent().getStringExtra("nama");
                email = getIntent().getStringExtra("email");
                nomor = getIntent().getStringExtra("no_hp");
                pass = getIntent().getStringExtra("password");
                alm = getIntent().getStringExtra("alamat");
                tgl = getIntent().getStringExtra("tanggal_lahir");
                foto = getIntent().getStringExtra("foto");

                Intent Back;
                Back = new Intent(AdminDashboard.this,UserMenu.class);
                Back.putExtra("email", email);
                Back.putExtra("nama", nama);
                Back.putExtra("no_hp", nomor);
                Back.putExtra("password", pass);
                Back.putExtra("alamat", alm);
                Back.putExtra("tanggal_lahir", tgl);
                Back.putExtra("foto",foto);
                startActivity(Back);

            }
        });

        CARDpe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent Back;
                Back = new Intent(AdminDashboard.this,PengolahanEvent.class);
                startActivity(Back);

            }
        });

        CARDpu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent Back;
                Back = new Intent(AdminDashboard.this,PengolahanUser.class);
                startActivity(Back);

            }
        });

        CARDsu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent Back;
                Back = new Intent(AdminDashboard.this,PengolahanSertifikat.class);
                startActivity(Back);

            }
        });

        CARDmb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent Back;
                Back = new Intent(AdminDashboard.this,PengolahanBiaya.class);
                startActivity(Back);

            }
        });
    }
}