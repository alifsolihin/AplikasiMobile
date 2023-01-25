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

public class PengolahanSertifikatOw extends AppCompatActivity {

    Toolbar BTNBack;
    SwipeRefreshLayout srl_main;
    private Context mContext;
    ProgressDialog progressDialog;
    android.widget.ListView listView;

    ArrayList<String> array_nama_serti,array_nama_user,array_gambar_serti, array_nama_owner;

    String nama;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pengolahan_sertifikat_ow);

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
                Back = new Intent(PengolahanSertifikatOw.this,AdminOnwerDashboard.class);
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


    void initializeArray() {
        array_nama_serti = new ArrayList<String>();
        array_nama_user= new ArrayList<String>();
        array_nama_owner = new ArrayList<String>();
        array_gambar_serti = new ArrayList<String>();


        array_nama_serti.clear();
        array_nama_user.clear();
        array_nama_owner.clear();
        array_gambar_serti.clear();
    }

    public void getData(){
        initializeArray();
        AndroidNetworking.post("https://tkjbpnup.com/kelompok_9/mimpikita/getDataSertifikatOw.php")
                .addBodyParameter("nama_owner",""+nama)
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

                                    array_nama_serti.add(jo.getString("nama_serti"));
                                    array_nama_user.add(jo.getString("nama_user"));
                                    array_nama_owner.add(jo.getString("nama_owner"));
                                    array_gambar_serti.add(jo.getString("gambar_serti"));

                                }

                                //Menampilkan data berbentuk adapter menggunakan class CLVDataUser
                                final CLV_dataSertiAd adapter = new CLV_dataSertiAd(PengolahanSertifikatOw.this,array_nama_serti,array_nama_user,array_nama_owner,array_gambar_serti);
                                //Set adapter to list
                                listView.setAdapter(adapter);


//edit and delete
                                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                        Log.d("TestKlik",""+array_nama_serti.get(position));
                                        Toast.makeText(PengolahanSertifikatOw.this, array_nama_serti.get(position), Toast.LENGTH_SHORT).show();

                                        //Setelah proses koneksi keserver selesai, maka aplikasi akan berpindah class
                                        //DataActivity.class dan membawa/mengirim data-data hasil query dari server.
                                        Intent i = new Intent(PengolahanSertifikatOw.this, EditSertifikat.class);
                                        i.putExtra("nama_serti",array_nama_serti.get(position));
                                        i.putExtra("nama_user",array_nama_user.get(position));
                                        i.putExtra("nama_owner",array_nama_owner.get(position));
                                        i.putExtra("gambar_serti",array_gambar_serti.get(position));
                                        startActivity(i);
                                    }
                                });


                            }else{
                                Toast.makeText(PengolahanSertifikatOw.this, "Gagal Mengambil Data", Toast.LENGTH_SHORT).show();

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
            Back = new Intent(PengolahanSertifikatOw.this,AddSertifikat.class);
            Back.putExtra("nama", nama);
            startActivity(Back);
        }
        return super.onOptionsItemSelected(item);
    }
}