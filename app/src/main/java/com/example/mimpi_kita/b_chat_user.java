package com.example.mimpi_kita;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
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
import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class b_chat_user extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    ListView listView;
    CardView cardView;
    CircleImageView IVgambar;
    TextView tvemail, TVnama;
    String email, namaj, nomor, foto, pass, alm, tgl;
    private Context mContext;
    ProgressDialog progressDialog;

    ArrayList<String> array_nama_ev, array_namaps_ev, array_nama_owner, array_komen, array_waktu_km, array_balas_km;
    String nama;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bchat_user);

        bottomNavigationView = findViewById(R.id.BTNNav);
        listView = findViewById(R.id.idLV);
        cardView = findViewById(R.id.idCARDuser);
        TVnama   =findViewById(R.id.idTV2);
        IVgambar   =findViewById(R.id.IVgambar);
        progressDialog = new ProgressDialog(this);
        //getSupportFragmentManager().beginTransaction().replace(R.id.container,homeFragmentD).commit();

        email = getIntent().getExtras().getString("email");
        namaj = getIntent().getExtras().getString("nama");
        nomor = getIntent().getExtras().getString("no_hp");
        foto = getIntent().getExtras().getString("foto");
        pass = getIntent().getExtras().getString("password");
        alm = getIntent().getExtras().getString("alamat");
        tgl = getIntent().getExtras().getString("tanggal_lahir");

        TVnama.setText(namaj);
        if (foto != null)
        {
            Picasso.get().load("https://tkjbpnup.com/kelompok_9/mimpikita/image/"+getIntent().getStringExtra("foto")).into(IVgambar);

        }
        else
        {
            Picasso.get().load("https://tkjbpnup.com/kelompok_9/mimpikita/image/noimage.png").into(IVgambar);
        }

        /*BadgeDrawable badgeDrawable = bottomNavigationView.getOrCreateBadge(R.id.action_chat);
        badgeDrawable.setVisible(true);*/
        //badgeDrawable.setNumber(3);

        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://tkjbpnup.com/kelompok_9/mimpikita/countDataChat.php";
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
                params.put("namaps_ev", namaj);
                return params;
            }
        };
        queue.add(stringRequest1);

        bottomNavigationView.setSelectedItemId(R.id.action_chat);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener(){
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.action_home:
                        namaj = getIntent().getStringExtra("nama");
                        email = getIntent().getStringExtra("email");
                        nomor = getIntent().getStringExtra("no_hp");
                        pass = getIntent().getStringExtra("password");
                        alm = getIntent().getStringExtra("alamat");
                        tgl = getIntent().getStringExtra("tanggal_lahir");
                        foto = getIntent().getExtras().getString("foto");

                        Intent Back;
                        Back = new Intent(b_chat_user.this,UserDashboardMainF.class);
                        Back.putExtra("email", email);
                        Back.putExtra("nama", namaj);
                        Back.putExtra("no_hp", nomor);
                        Back.putExtra("password", pass);
                        Back.putExtra("alamat", alm);
                        Back.putExtra("tanggal_lahir", tgl);
                        Back.putExtra("foto", foto);
                        startActivity(Back);
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.action_chat:

                        return true;

                    case R.id.action_user:
                        namaj = getIntent().getStringExtra("nama");
                        email = getIntent().getStringExtra("email");
                        nomor = getIntent().getStringExtra("no_hp");
                        pass = getIntent().getStringExtra("password");
                        alm = getIntent().getStringExtra("alamat");
                        tgl = getIntent().getStringExtra("tanggal_lahir");
                        foto = getIntent().getExtras().getString("foto");

                        Intent Back2;
                        Back2 = new Intent(b_chat_user.this,b_infouser_user.class);
                        Back2.putExtra("email", email);
                        Back2.putExtra("nama", namaj);
                        Back2.putExtra("no_hp", nomor);
                        Back2.putExtra("password", pass);
                        Back2.putExtra("alamat", alm);
                        Back2.putExtra("tanggal_lahir", tgl);
                        Back2.putExtra("foto", foto);
                        startActivity(Back2);
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });


        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                namaj = getIntent().getStringExtra("nama");
                email = getIntent().getStringExtra("email");
                nomor = getIntent().getStringExtra("no_hp");
                foto = getIntent().getStringExtra("foto");
                pass = getIntent().getStringExtra("password");
                alm = getIntent().getStringExtra("alamat");
                tgl = getIntent().getStringExtra("tanggal_lahir");

                Intent Back;
                Back = new Intent(b_chat_user.this,UserMenu.class);
                Back.putExtra("email", email);
                Back.putExtra("nama", namaj);
                Back.putExtra("no_hp", nomor);
                Back.putExtra("password", pass);
                Back.putExtra("alamat", alm);
                Back.putExtra("tanggal_lahir", tgl);
                Back.putExtra("foto", foto);
                startActivity(Back);

            }
        });

        /*srl_main.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                scrollRefresh();
                srl_main.setRefreshing(false);
            }
        });

        // Scheme colors for animation
        srl_main.setColorSchemeColors(
                getResources().getColor(android.R.color.holo_blue_bright),
                getResources().getColor(android.R.color.holo_green_light),
                getResources().getColor(android.R.color.holo_orange_light),
                getResources().getColor(android.R.color.holo_red_light)

        );
        scrollRefresh();*/

        //scrollRefresh();
        getData();


    }
    /*public void scrollRefresh(){
        progressDialog.setTitle("Mohon Tunggu ☺");
        progressDialog.setMessage("Mengambil Data.....");
        progressDialog.setCancelable(false);
        progressDialog.show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                getData();
            }
        },2000);
    }*/

    void initializeArray(){
        array_nama_ev       = new ArrayList<String>();
        array_namaps_ev     = new ArrayList<String>();
        array_nama_owner     = new ArrayList<String>();
        array_komen      = new ArrayList<String>();
        array_waktu_km     = new ArrayList<String>();
        array_balas_km     = new ArrayList<String>();


        array_nama_ev.clear();
        array_namaps_ev.clear();
        array_nama_owner.clear();
        array_komen.clear();
        array_waktu_km.clear();
        array_balas_km.clear();


    }


    public void getData(){
        initializeArray();
        AndroidNetworking.post("https://tkjbpnup.com/kelompok_9/mimpikita/getDataKomenUs.php")
                .addBodyParameter("namaps_ev",""+namaj)
                .setTag("Get Data")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        progressDialog.dismiss();

                        try{
                            Boolean status = response.getBoolean("status");
                            if(status){
                                JSONArray ja = response.getJSONArray("result");
                                Log.d("respon",""+ja);
                                for(int i = 0 ; i < ja.length() ; i++){
                                    JSONObject jo = ja.getJSONObject(i);

                                    array_nama_ev.add(jo.getString("nama_ev"));
                                    array_namaps_ev.add(jo.getString("namaps_ev"));
                                    array_nama_owner.add(jo.getString("owner_ev"));
                                    array_komen.add(jo.getString("komen_ev"));
                                    array_waktu_km.add(jo.getString("waktu_km"));
                                    array_balas_km.add(jo.getString("balas_km"));


                                }

                                //Menampilkan data berbentuk adapter menggunakan class CLVDataUser
                                final CLV_balaskomen adapter = new CLV_balaskomen(b_chat_user.this,array_nama_ev,array_namaps_ev,array_nama_owner,array_komen,array_waktu_km,array_balas_km);
                                //Set adapter to list
                                listView.setAdapter(adapter);
//edit and delete
                                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                        Log.d("TestKlik",""+array_nama_ev.get(position));
                                        Toast.makeText(b_chat_user.this, array_nama_ev.get(position), Toast.LENGTH_SHORT).show();

                                        //Setelah proses koneksi keserver selesai, maka aplikasi akan berpindah class
                                        //DataActivity.class dan membawa/mengirim data-data hasil query dari server.
                                        Intent i = new Intent(b_chat_user.this, b_chat_user.class);
                                        i.putExtra("nama_ev",array_nama_ev.get(position));
                                        i.putExtra("owner_ev",array_nama_owner.get(position));
                                        i.putExtra("namaps_ev",array_namaps_ev.get(position));
                                        i.putExtra("komen_ev",array_komen.get(position));
                                        i.putExtra("waktu_km",array_waktu_km.get(position));
                                        startActivity(i);
                                    }
                                });


                            }else{
                                Toast.makeText(b_chat_user.this, "Gagal Mengambil Data", Toast.LENGTH_SHORT).show();

                            }

                        }
                        catch (Exception e){
                            e.printStackTrace();
                        }

                    }


                    @Override
                    public void onError(ANError anError) {

                    }
                });
    }
}