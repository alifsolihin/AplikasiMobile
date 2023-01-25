package com.example.mimpi_kita;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class HalamanSertifikat extends AppCompatActivity {

    GridView grid1;


    ArrayList<String> array_nama_serti,array_nama_user,array_gambar_serti,array_nama_owner;
    ProgressDialog progressDialog;
    SwipeRefreshLayout srl_main, srl_main2;
    private Context mContext;

    String nama;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halaman_sertifikat);

        nama = getIntent().getStringExtra("nama");

        grid1 = findViewById(R.id.idgridview);
        srl_main = findViewById(R.id.swipe_container);
        progressDialog = new ProgressDialog(this);

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
        array_gambar_serti = new ArrayList<String>();

        array_nama_serti.clear();
        array_nama_user.clear();
        array_gambar_serti.clear();
    }

    public void getData(){
        initializeArray();
        AndroidNetworking.post("https://tkjbpnup.com/kelompok_9/mimpikita/getDataSertifikat.php")
                .addBodyParameter("nama_user",""+nama)
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
                                    array_nama_user.add(jo.getString("nama_owner"));
                                    array_gambar_serti.add(jo.getString("gambar_serti"));

                                }

                                //Menampilkan data berbentuk adapter menggunakan class CLVDataUser
                                final CLV_dataSerti adapter = new CLV_dataSerti(HalamanSertifikat.this,array_nama_serti,array_nama_user,array_nama_owner,array_gambar_serti);
                                //Set adapter to list
                                grid1.setAdapter(adapter);


//edit and delete
                                grid1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                        Log.d("TestKlik",""+array_nama_serti.get(position));
                                        Toast.makeText(HalamanSertifikat.this, array_nama_serti.get(position), Toast.LENGTH_SHORT).show();

                                        //Setelah proses koneksi keserver selesai, maka aplikasi akan berpindah class
                                        //DataActivity.class dan membawa/mengirim data-data hasil query dari server.
                                        Intent i = new Intent(HalamanSertifikat.this, SertifikatResult.class);
                                        i.putExtra("nama_serti",array_nama_serti.get(position));
                                        i.putExtra("nama_user",array_nama_user.get(position));
                                        i.putExtra("nama_owner",array_nama_user.get(position));
                                        i.putExtra("gambar_serti",array_gambar_serti.get(position));
                                        startActivity(i);
                                    }
                                });


                            }else{
                                Toast.makeText(HalamanSertifikat.this, "Gagal Mengambil Data", Toast.LENGTH_SHORT).show();

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