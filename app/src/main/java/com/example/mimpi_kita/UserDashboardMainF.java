package com.example.mimpi_kita;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.cardview.widget.CardView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
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

public class UserDashboardMainF extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    /*HomeFragmentD homeFragmentD = new HomeFragmentD();
    ChatFragmentD chatFragmentD = new ChatFragmentD();
    UserFragmentD userFragmentD = new UserFragmentD();*/

    /*SimpleAdapter adapter;
    HashMap<String,String> map;
    ArrayList<HashMap<String,String>> mylist;
    String[] nama;
    String[] regist;
    String[] waktu;
    String[] lokasi;
    String[] jam;
    String[] user;
    String[] desc;
    String[] ben1;
    String[] ben2;
    String[] cash;
    String[] tiket;
    Integer[] gambar;*/

    private Context mContext;
    ListView listView;
    SearchView searchViewCari;
    CardView cardView, cardViewEvent,cardViewSertifikat,cardViewSemua;
    TextView tvemail, TVnama;
    CircleImageView IVgambar;
    ProgressDialog progressDialog;
    SwipeRefreshLayout swipeRefreshLayout;
    ArrayList<String> array_nama_ev,array_tanggal_ev,array_lokasi_ev,array_jadwal_ev,array_mulai_ev,
            array_akhir_ev, array_owner_ev, array_deskripsi_ev, array_benefit1_ev,
            array_benefit2_ev, array_harga_ev, array_tiket_ev, array_gambar_ev, array_jenis_ev;
    String email, nama, nomor, foto, pass, alm, tgl;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_dashboard_main_f);

        bottomNavigationView = findViewById(R.id.BTNNav);
        searchViewCari = findViewById(R.id.idETcari);
         cardView = findViewById(R.id.idCARDuser);
         cardViewEvent = findViewById(R.id.idEvent);
        cardViewSertifikat = findViewById(R.id.idSertifikat);
        cardViewSemua = findViewById(R.id.idSemua);
        tvemail  = findViewById(R.id.idTV2);
         swipeRefreshLayout = findViewById(R.id.swipe_container);
        listView = findViewById(R.id.idLV);
        TVnama   =findViewById(R.id.idTV2);
        IVgambar   =findViewById(R.id.IVgambar);
        progressDialog = new ProgressDialog(this);

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
                params.put("namaps_ev", nama);
                return params;
            }
        };
        queue.add(stringRequest1);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //scrollRefresh();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
        swipeRefreshLayout.setColorSchemeColors(
                getResources().getColor(android.R.color.holo_blue_bright),
                getResources().getColor(android.R.color.holo_green_light),
                getResources().getColor(android.R.color.holo_orange_light),
                getResources().getColor(android.R.color.holo_red_light)

        );
        //scrollRefresh();

        bottomNavigationView.setSelectedItemId(R.id.action_home);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener(){
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.action_home:

                        return true;

                    case R.id.action_chat:
                        nama = getIntent().getStringExtra("nama");
                        email = getIntent().getStringExtra("email");
                        nomor = getIntent().getStringExtra("no_hp");
                        pass = getIntent().getStringExtra("password");
                        alm = getIntent().getStringExtra("alamat");
                        tgl = getIntent().getStringExtra("tanggal_lahir");
                        foto = getIntent().getExtras().getString("foto");

                        Intent Back;
                        Back = new Intent(UserDashboardMainF.this,b_chat_user.class);
                        Back.putExtra("email", email);
                        Back.putExtra("nama", nama);
                        Back.putExtra("no_hp", nomor);
                        Back.putExtra("password", pass);
                        Back.putExtra("alamat", alm);
                        Back.putExtra("tanggal_lahir", tgl);
                        Back.putExtra("foto", foto);
                        startActivity(Back);
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.action_user:
                        nama = getIntent().getStringExtra("nama");
                        email = getIntent().getStringExtra("email");
                        nomor = getIntent().getStringExtra("no_hp");
                        pass = getIntent().getStringExtra("password");
                        alm = getIntent().getStringExtra("alamat");
                        tgl = getIntent().getStringExtra("tanggal_lahir");
                        foto = getIntent().getExtras().getString("foto");

                        Intent Back2;
                        Back2 = new Intent(UserDashboardMainF.this,b_infouser_user.class);
                        Back2.putExtra("email", email);
                        Back2.putExtra("nama", nama);
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

        /*nama = new String[]{
                "Lomba Esai Nasional Pioneer 2022","Pekan Nasional ADIKSI Batch IV","English Competition Indonesian Youth","Indonesia Challange On Ecomomic Ideas 2022","UNP Mathematics Challange"
        };
        regist = new String[]{
                "17 Oktober 2022", "25 September 2022","08 November 2022","06 November 2022","03 November 2022"

        };
        lokasi = new String[]{
                "Online","Online","Offline","Offline","Online"
        };
        jam = new String[]{
                "00.01-23.59 WIB","13.00-12.00 WIB","13.00-15.00 WIB","20.00-22.00 WIB","12.00-14.00 WIB"
        };
        user = new String[]{
                "PIONEER 2022","PENADIKSI UIN","Ayu Kartika Ndari","FSDE FEB UGM","UMC 2022"
        };
        waktu = new String[]{
                "29 November 2022","21 November 2022","02-08 November 2022","02 November 2022","05 November 2022"
        };
        desc = new String[]{
                "Lomba essai nasional pioneer 2022 tentang toxic atau kesehatan","Pekan nasional adiksi bagi teman kip kuliah","Lomba bahasa inggris untuk seluruh siswa sma/smk/ma","Event nasional tentang ide ekonomi untuk indonesia","Event matematika internasional bagi para mahasiswa"
        };
        ben1 = new String[]{
                "Relasi dan Pengalaman","Relasi dan Pengetahuan","Ilmu dan Relasi","Ilmu dan Relasi","Relasi dan Pengalaman"
        };
        ben2 = new String[]{
                "Free sertifikat","Free sertifikat","Free sertifikat dan akomodasi","Free sertifikat dan akomodasi","Free sertifikat dan buku matematika"
        };
        cash = new String[]{
                "25000","free","35000","free","free"
        };
        tiket = new String[]{
                "150","unlimited","350","170","unlimited"
        };
        gambar = new Integer[]{
                R.drawable.event1, R.drawable.event2,R.drawable.event3,R.drawable.event4,R.drawable.event5
        };

        mylist = new ArrayList<HashMap<String, String>>();
        for (int i = 0; i < nama.length; i++) {
            map = new HashMap<String, String>();
            map.put("Nama", nama[i]);
            map.put("Regist", regist[i]);
            map.put("Waktu", waktu[i]);
            map.put("Lokasi", lokasi[i]);
            map.put("Jam", jam[i]);
            map.put("User", user[i]);
            map.put("Desc", user[i]);
            map.put("Ben1", ben1[i]);
            map.put("Ben2", ben2[i]);
            map.put("Cash", cash[i]);
            map.put("Tiket", tiket[i]);
            map.put("Gambar", gambar[i].toString());
            mylist.add(map);
        }


        adapter = new SimpleAdapter(UserDashboardMainF.this, mylist, R.layout.list_item,
                new String[]{"Nama","Regist", "Waktu", "Gambar","Lokasi","Jam","User","Desc","Ben1","Ben2","Cash","Tiket"}, new int[]{R.id.idTXTNama,
                (R.id.idTXTregist),(R.id.idTXTwaktu),(R.id.idLVGambar)});

        listView.setAdapter(adapter);*/

        /*searchViewCari.clearFocus();
        searchViewCari.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                adapter.getFilter().filter(s.toString());
                return false;
            }
        });*/


        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                nama = getIntent().getStringExtra("nama");
                email = getIntent().getStringExtra("email");
                nomor = getIntent().getStringExtra("no_hp");
                foto = getIntent().getStringExtra("foto");
                pass = getIntent().getStringExtra("password");
                alm = getIntent().getStringExtra("alamat");
                tgl = getIntent().getStringExtra("tanggal_lahir");

                Intent Back;
                Back = new Intent(UserDashboardMainF.this,UserMenu.class);
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

        cardViewEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                nama = getIntent().getStringExtra("nama");
                Intent Back;
                Back = new Intent(UserDashboardMainF.this,HalamanEvent.class);
                Back.putExtra("nama", nama);
                startActivity(Back);



            }
        });

        cardViewSertifikat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                nama = getIntent().getStringExtra("nama");
                Intent Back;
                Back = new Intent(UserDashboardMainF.this,HalamanSertifikat.class);
                Back.putExtra("nama", nama);
                startActivity(Back);

            }
        });


        cardViewSemua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                nama = getIntent().getStringExtra("nama");
                Intent Back;
                Back = new Intent(UserDashboardMainF.this,HalamanSemua.class);
                Back.putExtra("nama", nama);
                kirimData();
                startActivity(Back);


            }
        });


        /*listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {



                Intent page = new Intent(UserDashboardMainF.this,EventResult1.class);
                page.putExtra("Nama", nama[position]);
                page.putExtra("Regist", regist[position]);
                page.putExtra("Waktu", waktu[position]);
                page.putExtra("Lokasi", lokasi[position]);
                page.putExtra("Jam", jam[position]);
                page.putExtra("User", user[position]);
                page.putExtra("Desc", desc[position]);
                page.putExtra("Ben1", ben1[position]);
                page.putExtra("Ben2", ben2[position]);
                page.putExtra("Cash", cash[position]);
                page.putExtra("Tiket",tiket[position]);
                page.putExtra("Gambar",gambar[position]);
                startActivity(page);
            }
        });*/
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
        array_tanggal_ev     = new ArrayList<String>();
        array_lokasi_ev     = new ArrayList<String>();
        array_jadwal_ev      = new ArrayList<String>();
        array_mulai_ev     = new ArrayList<String>();
        array_akhir_ev      = new ArrayList<String>();
        array_owner_ev     = new ArrayList<String>();
        array_deskripsi_ev  = new ArrayList<String>();
        array_benefit1_ev  = new ArrayList<String>();
        array_benefit2_ev  = new ArrayList<String>();
        array_harga_ev  = new ArrayList<String>();
        array_tiket_ev  = new ArrayList<String>();
        array_gambar_ev      = new ArrayList<String>();
        array_jenis_ev      = new ArrayList<String>();

        array_nama_ev.clear();
        array_tanggal_ev.clear();
        array_lokasi_ev.clear();
        array_jadwal_ev.clear();
        array_mulai_ev.clear();
        array_akhir_ev.clear();
        array_owner_ev.clear();
        array_deskripsi_ev.clear();
        array_benefit1_ev.clear();
        array_benefit2_ev.clear();
        array_harga_ev.clear();
        array_tiket_ev.clear();
        array_gambar_ev.clear();
        array_jenis_ev.clear();


    }

    public void getData(){
        initializeArray();
        AndroidNetworking.get("https://tkjbpnup.com/kelompok_9/mimpikita/getData1.php")
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
                                    array_tanggal_ev.add(jo.getString("tanggal_ev"));
                                    array_lokasi_ev.add(jo.getString("lokasi_ev"));
                                    array_jadwal_ev.add(jo.getString("jadwal_ev"));
                                    array_mulai_ev.add(jo.getString("mulai_ev"));
                                    array_akhir_ev.add(jo.getString("akhir_ev"));
                                    array_owner_ev.add(jo.getString("owner_ev"));
                                    array_deskripsi_ev.add(jo.getString("deskripsi_ev"));
                                    array_benefit1_ev.add(jo.getString("benefit1_ev"));
                                    array_benefit2_ev.add(jo.getString("benefit2_ev"));
                                    array_harga_ev.add(jo.getString("harga_ev"));
                                    array_tiket_ev.add(jo.getString("tiket_ev"));
                                    array_gambar_ev.add(jo.getString("gambar_ev"));
                                    array_jenis_ev.add(jo.getString("jenis_ev"));

                                }

                                //Menampilkan data berbentuk adapter menggunakan class CLVDataUser
                                final CLV_dataevent2 adapter = new CLV_dataevent2(UserDashboardMainF.this,array_nama_ev,array_tanggal_ev,array_lokasi_ev,array_jadwal_ev,array_mulai_ev,
                                        array_akhir_ev,array_owner_ev,array_deskripsi_ev,array_benefit1_ev,array_benefit2_ev,array_harga_ev,array_tiket_ev,array_jenis_ev,array_gambar_ev);
                                //Set adapter to list
                                listView.setAdapter(adapter);
//edit and delete
                                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                        Log.d("TestKlik",""+array_nama_ev.get(position));
                                        Toast.makeText(UserDashboardMainF.this, array_nama_ev.get(position), Toast.LENGTH_SHORT).show();

                                        //Setelah proses koneksi keserver selesai, maka aplikasi akan berpindah class
                                        //DataActivity.class dan membawa/mengirim data-data hasil query dari server.
                                        Intent i = new Intent(UserDashboardMainF.this, EventResult1.class);
                                        i.putExtra("nama", nama);
                                        i.putExtra("nama_ev",array_nama_ev.get(position));
                                        i.putExtra("tanggal_ev",array_tanggal_ev.get(position));
                                        i.putExtra("lokasi_ev",array_lokasi_ev.get(position));
                                        i.putExtra("jadwal_ev",array_jadwal_ev.get(position));
                                        i.putExtra("mulai_ev",array_mulai_ev.get(position));
                                        i.putExtra("akhir_ev",array_akhir_ev.get(position));
                                        i.putExtra("owner_ev",array_owner_ev.get(position));
                                        i.putExtra("deskripsi_ev",array_deskripsi_ev.get(position));
                                        i.putExtra("benefit1_ev",array_benefit1_ev.get(position));
                                        i.putExtra("benefit2_ev",array_benefit2_ev.get(position));
                                        i.putExtra("harga_ev",array_harga_ev.get(position));
                                        i.putExtra("tiket_ev",array_tiket_ev.get(position));
                                        i.putExtra("jenis_ev",array_jenis_ev.get(position));
                                        i.putExtra("gambar_ev",array_gambar_ev.get(position));
                                        startActivity(i);
                                    }
                                });


                            }else{
                                Toast.makeText(UserDashboardMainF.this, "Gagal Mengambil Data", Toast.LENGTH_SHORT).show();

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

    void kirimData(){
        AndroidNetworking.post("https://tkjbpnup.com/kelompok_9/mimpikita/getDataTes2.php")
                .addBodyParameter("namaps_ev",""+nama)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        progressDialog.dismiss();
                        Log.d("cekTambah",""+response);
                        try {
                            Boolean status = response.getBoolean("status");
                            String pesan = response.getString("result");
                            Log.d("status",""+status);
                            if(status){

                            }
                            else{

                            }
                        }catch (Exception e){
                            e.printStackTrace();
                        }


                    }

                    @Override
                    public void onError(ANError anError) {
                    }
                });
    }
}