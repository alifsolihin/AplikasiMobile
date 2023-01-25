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

public class PengolahanKomenUser extends AppCompatActivity {

    Toolbar BTNBack;
    SwipeRefreshLayout srl_main;
    private Context mContext;
    ProgressDialog progressDialog;
    android.widget.ListView listView;

    ArrayList<String> array_nama_ev, array_namaps_ev, array_nama_owner, array_komen, array_waktu_km;
    String nama;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pengolahan_komen_user);

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
                Back = new Intent(PengolahanKomenUser.this,AdminOnwerDashboard.class);
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
        array_namaps_ev     = new ArrayList<String>();
        array_nama_owner     = new ArrayList<String>();
        array_komen      = new ArrayList<String>();
        array_waktu_km     = new ArrayList<String>();


        array_nama_ev.clear();
        array_namaps_ev.clear();
        array_nama_owner.clear();
        array_komen.clear();
        array_waktu_km.clear();


    }


    public void getData(){
        initializeArray();
        AndroidNetworking.post("https://tkjbpnup.com/kelompok_9/mimpikita/getDataKomenOw.php")
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
                                    array_namaps_ev.add(jo.getString("namaps_ev"));
                                    array_nama_owner.add(jo.getString("owner_ev"));
                                    array_komen.add(jo.getString("komen_ev"));
                                    array_waktu_km.add(jo.getString("waktu_km"));


                                }

                                //Menampilkan data berbentuk adapter menggunakan class CLVDataUser
                                final CLV_komenuser adapter = new CLV_komenuser(PengolahanKomenUser.this,array_nama_ev,array_namaps_ev,array_nama_owner,array_komen,array_waktu_km);
                                //Set adapter to list
                                listView.setAdapter(adapter);
//edit and delete
                                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                        Log.d("TestKlik",""+array_nama_ev.get(position));
                                        Toast.makeText(PengolahanKomenUser.this, array_nama_ev.get(position), Toast.LENGTH_SHORT).show();

                                        //Setelah proses koneksi keserver selesai, maka aplikasi akan berpindah class
                                        //DataActivity.class dan membawa/mengirim data-data hasil query dari server.
                                        Intent i = new Intent(PengolahanKomenUser.this, EditKomenOw.class);
                                        i.putExtra("nama_ev",array_nama_ev.get(position));
                                        i.putExtra("owner_ev",array_nama_owner.get(position));
                                        i.putExtra("namaps_ev",array_namaps_ev.get(position));
                                        i.putExtra("komen_ev",array_komen.get(position));
                                        i.putExtra("waktu_km",array_waktu_km.get(position));
                                        startActivity(i);
                                    }
                                });


                            }else{
                                Toast.makeText(PengolahanKomenUser.this, "Gagal Mengambil Data", Toast.LENGTH_SHORT).show();

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