package com.example.mimpi_kita;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class AdminOnwerDashboard extends AppCompatActivity {

    TextView TVJmlEv, TVJmlUs, TVnama, TVjmlSt, TVjmlBy, Tvjam, Tvtgl, Tvcash;
    CardView CARDuser,CARDpe, CARDpu, CARDsu, CARDmb;
    String email, nama, nomor, foto, pass, alm, tgl;
    CircleImageView IVgambar;
    ArrayList<String> count1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_onwer_dashboard);

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
        //Tvcash = findViewById(R.id.idTVcash);
        IVgambar   =findViewById(R.id.IVgambar);

        email = getIntent().getExtras().getString("email");
        nama = getIntent().getExtras().getString("nama");
        nomor = getIntent().getExtras().getString("no_hp");
        foto = getIntent().getExtras().getString("foto");
        pass = getIntent().getExtras().getString("password");
        alm = getIntent().getExtras().getString("alamat");
        tgl = getIntent().getExtras().getString("tanggal_lahir");

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
        String url = "https://tkjbpnup.com/kelompok_9/mimpikita/countDatates2.php";
        String url2 = "https://tkjbpnup.com/kelompok_9/mimpikita/countDatates4.php";
        String url3 = "https://tkjbpnup.com/kelompok_9/mimpikita/countDatates.php";
        String url4 = "https://tkjbpnup.com/kelompok_9/mimpikita/countDatates3.php";
        String url5 = "https://tkjbpnup.com/kelompok_9/mimpikita/sumCashOwn.php";


        StringRequest stringRequest1 = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        TVJmlEv.setText(response);
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("owner_ev", nama);
                return params;
            }
        };
        queue.add(stringRequest1);

        StringRequest stringRequest2 = new StringRequest(Request.Method.POST, url2,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        TVjmlSt.setText(response);
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("nama_owner", nama);
                return params;
            }
        };
        queue.add(stringRequest2);

        StringRequest stringRequest4 = new StringRequest(Request.Method.POST, url3,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        TVJmlUs.setText(response);
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("owner_ev", nama);
                return params;
            }
        };
        queue.add(stringRequest4);

        StringRequest stringRequest3 = new StringRequest(Request.Method.POST, url4,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        TVjmlBy.setText(response);
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("owner_ev", nama);
                return params;
            }
        };
        queue.add(stringRequest3);



       /* StringRequest stringRequest5 = new StringRequest(Request.Method.POST, url5,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {

                        String resultRupiah = formatRupiah(Double.parseDouble(response));
                        Tvcash.setText(resultRupiah);
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("owner_ev", nama);
                return params;
            }
        };
        queue.add(stringRequest5);*/


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
                Back = new Intent(AdminOnwerDashboard.this,UserMenu.class);
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

                nama = getIntent().getStringExtra("nama");
                Intent Back;
                Back = new Intent(AdminOnwerDashboard.this,PengolahanEventOwner.class);
                Back.putExtra("nama", nama);
                startActivity(Back);

            }
        });

        CARDpu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                nama = getIntent().getStringExtra("nama");
                Intent Back;
                Back = new Intent(AdminOnwerDashboard.this,PengolahanKomenUser.class);
                Back.putExtra("nama", nama);
                startActivity(Back);

            }
        });

        CARDsu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                nama = getIntent().getStringExtra("nama");
                Intent Back;
                Back = new Intent(AdminOnwerDashboard.this,PengolahanSertifikatOw.class);
                Back.putExtra("nama", nama);
                startActivity(Back);

            }
        });

        CARDmb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                nama = getIntent().getStringExtra("nama");
                Intent Back;
                Back = new Intent(AdminOnwerDashboard.this,PengolahanBiayaOw.class);
                Back.putExtra("nama", nama);
                startActivity(Back);

            }
        });


    }

    private String formatRupiah(Double number){
        Locale localeID = new Locale("in", "ID");
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
        return formatRupiah.format(number);
    }
}