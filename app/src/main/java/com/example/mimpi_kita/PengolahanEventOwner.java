package com.example.mimpi_kita;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
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
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class PengolahanEventOwner extends AppCompatActivity {

    Toolbar BTNBack;
    SwipeRefreshLayout srl_main;
    private Context mContext;
    ProgressDialog progressDialog;
    ArrayList<String> array_nama_ev,array_tanggal_ev,array_lokasi_ev,array_jadwal_ev,array_mulai_ev,
            array_akhir_ev, array_owner_ev, array_deskripsi_ev, array_benefit1_ev,
            array_benefit2_ev, array_harga_ev, array_tiket_ev, array_gambar_ev, array_jenis_ev;
    android.widget.ListView listView;

    String nama;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pengolahan_event_owner);

        listView = findViewById(R.id.idLVPE);
        srl_main = findViewById(R.id.swipe_containerPE);
        progressDialog = new ProgressDialog(this);

        nama = getIntent().getStringExtra("nama");

        Toolbar toolbar = (Toolbar) findViewById(R.id.idToolbarPE);
        setSupportActionBar(toolbar);

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

        toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_ios_24);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent Back;
                Back = new Intent(PengolahanEventOwner.this,AdminOnwerDashboard.class);
                startActivity(Back);
                finish();
            }

        });
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
        array_jenis_ev      = new ArrayList<String>();
        array_gambar_ev      = new ArrayList<String>();


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
        array_jenis_ev.clear();
        array_gambar_ev.clear();



    }


    public void getData(){
        initializeArray();
        AndroidNetworking.post("https://tkjbpnup.com/kelompok_9/mimpikita/getDataEventOw.php")
                .addBodyParameter("owner_ev",""+nama)
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
                                    array_jenis_ev.add(jo.getString("jenis_ev"));
                                    array_gambar_ev.add(jo.getString("gambar_ev"));


                                }

                                //Menampilkan data berbentuk adapter menggunakan class CLVDataUser
                                final CLV_dataevent2 adapter = new CLV_dataevent2(PengolahanEventOwner.this,array_nama_ev,array_tanggal_ev,array_lokasi_ev,array_jadwal_ev,array_mulai_ev,
                                        array_akhir_ev,array_owner_ev,array_deskripsi_ev,array_benefit1_ev,array_benefit2_ev,array_harga_ev,array_tiket_ev,array_jenis_ev,array_gambar_ev);
                                //Set adapter to list
                                listView.setAdapter(adapter);
//edit and delete
                                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                        Log.d("TestKlik",""+array_nama_ev.get(position));
                                        Toast.makeText(PengolahanEventOwner.this, array_nama_ev.get(position), Toast.LENGTH_SHORT).show();

                                        //Setelah proses koneksi keserver selesai, maka aplikasi akan berpindah class
                                        //DataActivity.class dan membawa/mengirim data-data hasil query dari server.
                                        Intent i = new Intent(PengolahanEventOwner.this, EditEventOw.class);
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
                                Toast.makeText(PengolahanEventOwner.this, "Gagal Mengambil Data", Toast.LENGTH_SHORT).show();

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
        getMenuInflater().inflate(R.menu.main,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id==R.id.action_add){
            nama = getIntent().getStringExtra("nama");
            Intent Back;
            Back = new Intent(PengolahanEventOwner.this,AddEventOw.class);
            Back.putExtra("nama", nama);
            startActivity(Back);
        }
        return super.onOptionsItemSelected(item);
    }
}