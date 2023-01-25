package com.example.mimpi_kita;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class b_infouser_user extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    CardView cardView;
    CircleImageView IVgambar, IVgambar2;
    TextView tvemail, TVnama, TVnama2, TVnama3, TVnama4, TVnama5;
    Button BTNedit;
    String email, nama, nomor, foto, pass, alm, tgl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_binfouser_user);
        bottomNavigationView = findViewById(R.id.BTNNav);
        cardView = findViewById(R.id.idCARDuser);
        TVnama   =findViewById(R.id.idTV2);
        IVgambar   =findViewById(R.id.IVgambar);
        IVgambar2   =findViewById(R.id.IVgambar2);
        TVnama2   =findViewById(R.id.idTVResultnm);
        TVnama3   =findViewById(R.id.idTVResultus);
        TVnama4   =findViewById(R.id.idTVJmlEV);
        TVnama5   =findViewById(R.id.idTVJmlST);
        BTNedit =findViewById(R.id.idBTNedit);

        email = getIntent().getExtras().getString("email");
        nama = getIntent().getExtras().getString("nama");
        nomor = getIntent().getExtras().getString("no_hp");
        foto = getIntent().getExtras().getString("foto");
        pass = getIntent().getExtras().getString("password");
        alm = getIntent().getExtras().getString("alamat");
        tgl = getIntent().getExtras().getString("tanggal_lahir");

        TVnama.setText(nama);
        TVnama2.setText(nama);
        TVnama3.setText(email);
        if (foto != null)
        {
            Picasso.get().load("https://tkjbpnup.com/kelompok_9/mimpikita/image/"+getIntent().getStringExtra("foto")).into(IVgambar);

        }
        else
        {
            Picasso.get().load("https://tkjbpnup.com/kelompok_9/mimpikita/image/noimage.png").into(IVgambar);
        }
        if (foto != null)
        {
            Picasso.get().load("https://tkjbpnup.com/kelompok_9/mimpikita/image/"+getIntent().getStringExtra("foto")).into(IVgambar2);

        }
        else
        {
            Picasso.get().load("https://tkjbpnup.com/kelompok_9/mimpikita/image/noimage.png").into(IVgambar2);
        }



        //getSupportFragmentManager().beginTransaction().replace(R.id.container,homeFragmentD).commit();

        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://tkjbpnup.com/kelompok_9/mimpikita/countDataChat.php";
        String url2 = "https://tkjbpnup.com/kelompok_9/mimpikita/countDatates5.php";
        String url3 = "https://tkjbpnup.com/kelompok_9/mimpikita/countDatates6.php";


        StringRequest stringRequest1 = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        BadgeDrawable badgeDrawable = bottomNavigationView.getOrCreateBadge(R.id.action_chat);
                        badgeDrawable.setVisible(true);
                        badgeDrawable.setNumber(Integer.parseInt(response));

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
                params.put("namaps_ev", nama);
                return params;
            }
        };
        queue.add(stringRequest1);


        StringRequest stringRequest2 = new StringRequest(Request.Method.POST, url2,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        TVnama4.setText(response);

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
                params.put("namaps_ev", nama);
                return params;
            }
        };
        queue.add(stringRequest2);


        StringRequest stringRequest3 = new StringRequest(Request.Method.POST, url3,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        TVnama5.setText(response);

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
                params.put("nama_user", nama);
                return params;
            }
        };
        queue.add(stringRequest3);



        bottomNavigationView.setSelectedItemId(R.id.action_user);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener(){
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.action_home:
                        nama = getIntent().getStringExtra("nama");
                        email = getIntent().getStringExtra("email");
                        nomor = getIntent().getStringExtra("no_hp");
                        pass = getIntent().getStringExtra("password");
                        alm = getIntent().getStringExtra("alamat");
                        tgl = getIntent().getStringExtra("tanggal_lahir");
                        foto = getIntent().getStringExtra("foto");

                        Intent Back;
                        Back = new Intent(b_infouser_user.this,UserDashboardMainF.class);
                        Back.putExtra("email", email);
                        Back.putExtra("nama", nama);
                        Back.putExtra("no_hp", nomor);
                        Back.putExtra("password", pass);
                        Back.putExtra("alamat", alm);
                        Back.putExtra("tanggal_lahir", tgl);
                        Back.putExtra("foto", foto);
                        startActivity(Back);
                        overridePendingTransition(0,0);
                        //getSupportFragmentManager().beginTransaction().replace(R.id.container,homeFragmentD).commit();
                        return true;

                    case R.id.action_chat:
                        nama = getIntent().getStringExtra("nama");
                        email = getIntent().getStringExtra("email");
                        nomor = getIntent().getStringExtra("no_hp");
                        pass = getIntent().getStringExtra("password");
                        alm = getIntent().getStringExtra("alamat");
                        tgl = getIntent().getStringExtra("tanggal_lahir");
                        foto = getIntent().getStringExtra("foto");
                        Intent Back2;
                        Back2 = new Intent(b_infouser_user.this,b_chat_user.class);
                        Back2.putExtra("email", email);
                        Back2.putExtra("nama", nama);
                        Back2.putExtra("no_hp", nomor);
                        Back2.putExtra("password", pass);
                        Back2.putExtra("alamat", alm);
                        Back2.putExtra("tanggal_lahir", tgl);
                        Back2.putExtra("foto", foto);
                        startActivity(Back2);
                        overridePendingTransition(0,0);
                        //getSupportFragmentManager().beginTransaction().replace(R.id.container,chatFragmentD).commit();
                        return true;

                    case R.id.action_user:
                        return true;
                }
                return false;
            }
        });

        cardView.setOnClickListener(new View.OnClickListener() {
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
                Back = new Intent(b_infouser_user.this,UserMenu.class);
                Back.putExtra("email", email);
                Back.putExtra("nama", nama);
                Back.putExtra("no_hp", nomor);
                Back.putExtra("password", pass);
                Back.putExtra("alamat", alm);
                Back.putExtra("tanggal_lahir", tgl);
                Back.putExtra("foto", foto);
                startActivity(Back);

            }
        });


        BTNedit.setOnClickListener(new View.OnClickListener() {
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
                Back = new Intent(b_infouser_user.this,EditUserMenu.class);
                Back.putExtra("email", email);
                Back.putExtra("nama", nama);
                Back.putExtra("no_hp", nomor);
                Back.putExtra("password", pass);
                Back.putExtra("alamat", alm);
                Back.putExtra("tanggal_lahir", tgl);
                Back.putExtra("foto", foto);
                startActivity(Back);

            }
        });









    }
}