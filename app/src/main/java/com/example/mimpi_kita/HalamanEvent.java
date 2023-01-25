package com.example.mimpi_kita;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;


import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HalamanEvent extends AppCompatActivity {

    Toolbar toolbar;
    ImageSlider imageSlider;
    android.widget.ListView listView, listView2;
    int imageList;

    CardView CARDse1, CARDse2;

    ArrayList<String> array_nama_ev,array_tanggal_ev,array_lokasi_ev,array_jadwal_ev,array_mulai_ev,
            array_akhir_ev, array_owner_ev, array_deskripsi_ev, array_benefit1_ev,
            array_benefit2_ev, array_harga_ev, array_tiket_ev, array_gambar_ev, array_jenis_ev;
    ProgressDialog progressDialog;
    SwipeRefreshLayout srl_main, srl_main2;
    String nama, nama2;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halaman_event);

        toolbar = findViewById(R.id.BTNNav);
        imageSlider = findViewById(R.id.idSlider);
        CARDse1 = findViewById(R.id.idSelengkap1);
        CARDse2 = findViewById(R.id.idSelengkap2);
        listView  = findViewById(R.id.idLVEv);
        listView2  = findViewById(R.id.idLVEv2);
        srl_main = findViewById(R.id.swipe_container);
        srl_main2 = findViewById(R.id.swipe_container2);
        progressDialog = new ProgressDialog(this);

        nama = getIntent().getStringExtra("nama");
        nama2 = getIntent().getStringExtra("nama");

        ArrayList<SlideModel> images = new ArrayList<>();
        images.add(new SlideModel(R.drawable.b1p, null));
        images.add(new SlideModel(R.drawable.b2p, null));
        images.add(new SlideModel(R.drawable.b3p, null));
        imageSlider.setImageList(images, ScaleTypes.FIT);



        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_ios_new_24_he);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onBackPressed();
            }

        });



        srl_main.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
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

        scrollRefresh();

        srl_main2.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                scrollRefresh();
                srl_main2.setRefreshing(false);
            }
        });
        // Scheme colors for animation
        srl_main2.setColorSchemeColors(
                getResources().getColor(android.R.color.holo_blue_bright),
                getResources().getColor(android.R.color.holo_green_light),
                getResources().getColor(android.R.color.holo_orange_light),
                getResources().getColor(android.R.color.holo_red_light)

        );

        scrollRefresh2();

    }
    public void scrollRefresh(){
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
    }

    public void scrollRefresh2(){
        progressDialog.setTitle("Mohon Tunggu ☺");
        progressDialog.setMessage("Mengambil Data.....");
        progressDialog.setCancelable(false);
        progressDialog.show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                getData2();
            }
        },2000);
    }

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

    void initializeArray2(){
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

        CARDse1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                nama = getIntent().getStringExtra("nama");
                Intent Back;
                Back = new Intent(HalamanEvent.this,Selengkapnya.class);
                Back.putExtra("nama", nama);
                startActivity(Back);

            }
        });

        CARDse2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                nama = getIntent().getStringExtra("nama");
                Intent Back;
                Back = new Intent(HalamanEvent.this,Selengkapnya.class);
                Back.putExtra("nama", nama);
                startActivity(Back);

            }
        });



    }



    public void getData(){
        initializeArray();
        AndroidNetworking.post("https://tkjbpnup.com/kelompok_9/mimpikita/getData1.php")
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
                                final CLV_dataevent2 adapter = new CLV_dataevent2(HalamanEvent.this,array_nama_ev,array_tanggal_ev,array_lokasi_ev,array_jadwal_ev,array_mulai_ev,
                                        array_akhir_ev,array_owner_ev,array_deskripsi_ev,array_benefit1_ev,array_benefit2_ev,array_harga_ev,array_tiket_ev,array_jenis_ev,array_gambar_ev);
                                //Set adapter to list
                                listView.setAdapter(adapter);


//edit and delete
                                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                        Log.d("TestKlik",""+array_nama_ev.get(position));
                                        Toast.makeText(HalamanEvent.this, array_nama_ev.get(position), Toast.LENGTH_SHORT).show();

                                        //Setelah proses koneksi keserver selesai, maka aplikasi akan berpindah class
                                        //DataActivity.class dan membawa/mengirim data-data hasil query dari server.
                                        Intent i = new Intent(HalamanEvent.this, EventResult1.class);
                                        i.putExtra("nama",nama);
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
                                Toast.makeText(HalamanEvent.this, "Gagal Mengambil Data", Toast.LENGTH_SHORT).show();

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

    public void getData2(){
        initializeArray2();
        AndroidNetworking.post("https://tkjbpnup.com/kelompok_9/mimpikita/getData1.php")
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
                                final CLV_dataevent2 adapter = new CLV_dataevent2(HalamanEvent.this,array_nama_ev,array_tanggal_ev,array_lokasi_ev,array_jadwal_ev,array_mulai_ev,
                                        array_akhir_ev,array_owner_ev,array_deskripsi_ev,array_benefit1_ev,array_benefit2_ev,array_harga_ev,array_tiket_ev,array_jenis_ev,array_gambar_ev);
                                //Set adapter to list
                                listView2.setAdapter(adapter);
//edit and delete
                                listView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                        Log.d("TestKlik",""+array_nama_ev.get(position));
                                        Toast.makeText(HalamanEvent.this, array_nama_ev.get(position), Toast.LENGTH_SHORT).show();

                                        //Setelah proses koneksi keserver selesai, maka aplikasi akan berpindah class
                                        //DataActivity.class dan membawa/mengirim data-data hasil query dari server.
                                        Intent i = new Intent(HalamanEvent.this, EventResult1.class);
                                        i.putExtra("nama",nama2);
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
                                Toast.makeText(HalamanEvent.this, "Gagal Mengambil Data", Toast.LENGTH_SHORT).show();

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.top_menu_event,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id==R.id.action_calender){
            Intent i;
            i = new Intent(HalamanEvent.this,Calender1.class);
            startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }
}