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

public class PengolahanUser extends AppCompatActivity {

    Toolbar BTNBack;
    SwipeRefreshLayout srl_main;
    private Context mContext;
    ProgressDialog progressDialog;
    android.widget.ListView listView;

    ArrayList<String> array_nama, array_email, array_no_hp, array_foto, array_password,
            arrray_alamat, array_tanggal_lahir, array_level;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pengolahan_user);

        listView = findViewById(R.id.idLVPE);
        srl_main = findViewById(R.id.swipe_containerPE);
        progressDialog = new ProgressDialog(this);

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
                Back = new Intent(PengolahanUser.this,AdminDashboard.class);
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
        array_nama       = new ArrayList<String>();
        array_email     = new ArrayList<String>();
        array_no_hp     = new ArrayList<String>();
        array_foto      = new ArrayList<String>();
        array_password     = new ArrayList<String>();
        arrray_alamat     = new ArrayList<String>();
        array_tanggal_lahir      = new ArrayList<String>();
        array_level     = new ArrayList<String>();


        array_nama.clear();
        array_email.clear();
        array_no_hp.clear();
        array_foto.clear();
        array_password.clear();
        arrray_alamat.clear();
        array_tanggal_lahir.clear();
        array_level.clear();

    }


    public void getData(){
        initializeArray();
        AndroidNetworking.get("https://tkjbpnup.com/kelompok_9/mimpikita/getDataUser.php")
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

                                    array_nama.add(jo.getString("nama"));
                                    array_email.add(jo.getString("email"));
                                    array_no_hp.add(jo.getString("no_hp"));
                                    array_foto.add(jo.getString("foto"));
                                    array_password.add(jo.getString("password"));
                                    arrray_alamat.add(jo.getString("alamat"));
                                    array_tanggal_lahir.add(jo.getString("tanggal_lahir"));
                                    array_level.add(jo.getString("level"));


                                }

                                //Menampilkan data berbentuk adapter menggunakan class CLVDataUser
                                final CLV_datauser1 adapter = new CLV_datauser1(PengolahanUser.this,array_nama,array_email,array_no_hp,array_foto,array_password,arrray_alamat,array_tanggal_lahir,array_level);
                                //Set adapter to list
                                listView.setAdapter(adapter);
//edit and delete
                                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                        Log.d("TestKlik",""+array_nama.get(position));
                                        Toast.makeText(PengolahanUser.this, array_nama.get(position), Toast.LENGTH_SHORT).show();

                                        //Setelah proses koneksi keserver selesai, maka aplikasi akan berpindah class
                                        //DataActivity.class dan membawa/mengirim data-data hasil query dari server.
                                        Intent i = new Intent(PengolahanUser.this, EditUser.class);
                                        i.putExtra("nama",array_nama.get(position));
                                        i.putExtra("email",array_email.get(position));
                                        i.putExtra("no_hp",array_no_hp.get(position));
                                        i.putExtra("foto",array_foto.get(position));
                                        i.putExtra("password",array_password.get(position));
                                        i.putExtra("alamat",arrray_alamat.get(position));
                                        i.putExtra("tanggal_lahir",array_tanggal_lahir.get(position));
                                        i.putExtra("level",array_level.get(position));
                                        startActivity(i);
                                    }
                                });


                            }else{
                                Toast.makeText(PengolahanUser.this, "Gagal Mengambil Data", Toast.LENGTH_SHORT).show();

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
            Intent i = new Intent(PengolahanUser.this,AddUser.class);
            startActivityForResult(i,1);
        }
        return super.onOptionsItemSelected(item);
    }
}