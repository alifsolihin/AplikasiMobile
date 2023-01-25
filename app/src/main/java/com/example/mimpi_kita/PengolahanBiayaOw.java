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

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class PengolahanBiayaOw extends AppCompatActivity {

    Toolbar BTNBack;
    SwipeRefreshLayout srl_main;
    private Context mContext;
    ProgressDialog progressDialog;
    android.widget.ListView listView;

    ArrayList<String> array_nama_ev,array_lokasi_ev,array_jadwal_ev,array_mulai_ev,
            array_akhir_ev, array_namaps_ev, array_harga_ev, array_tiket_ev, array_total_ev, array_owner_ev, array_waktu_tr, array_kode_tr;

    String nama;
    TextView TVtotal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pengolahan_biaya_ow);

        listView = findViewById(R.id.idLVPE);
        srl_main = findViewById(R.id.swipe_containerPE);
        progressDialog = new ProgressDialog(this);
        TVtotal = findViewById(R.id.idTotalBY);
        nama = getIntent().getStringExtra("nama");

        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://tkjbpnup.com/kelompok_9/mimpikita/sumCashOwn.php";

        Toolbar toolbar = (Toolbar) findViewById(R.id.idToolbarPE);
        setSupportActionBar(toolbar);

        toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_ios_24);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent Back;
                Back = new Intent(PengolahanBiayaOw.this,AdminOnwerDashboard.class);
                startActivity(Back);
                finish();
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

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {

                            String resultRupiah = formatRupiah(Double.parseDouble(response));
                            TVtotal.setText(resultRupiah);
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        TVtotal.setText("0");

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
        queue.add(stringRequest);
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
        array_lokasi_ev     = new ArrayList<String>();
        array_jadwal_ev    = new ArrayList<String>();
        array_mulai_ev      = new ArrayList<String>();
        array_akhir_ev     = new ArrayList<String>();
        array_namaps_ev     = new ArrayList<String>();
        array_harga_ev     = new ArrayList<String>();
        array_tiket_ev     = new ArrayList<String>();
        array_owner_ev     = new ArrayList<String>();
        array_total_ev     = new ArrayList<String>();
        array_waktu_tr     = new ArrayList<String>();
        array_kode_tr    = new ArrayList<String>();


        array_nama_ev.clear();
        array_lokasi_ev.clear();
        array_jadwal_ev.clear();
        array_mulai_ev.clear();
        array_akhir_ev.clear();
        array_namaps_ev.clear();
        array_harga_ev.clear();
        array_tiket_ev.clear();
        array_owner_ev.clear();
        array_total_ev.clear();
        array_waktu_tr.clear();
        array_kode_tr.clear();

    }


    public void getData(){
        initializeArray();
        AndroidNetworking.post("https://tkjbpnup.com/kelompok_9/mimpikita/getDataOrderOw.php")
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
                                    array_lokasi_ev.add(jo.getString("lokasi_ev"));
                                    array_jadwal_ev.add(jo.getString("jadwal_ev"));
                                    array_mulai_ev.add(jo.getString("mulai_ev"));
                                    array_akhir_ev.add(jo.getString("akhir_ev"));
                                    array_namaps_ev.add(jo.getString("namaps_ev"));
                                    array_harga_ev.add(jo.getString("harga_ev"));
                                    array_tiket_ev.add(jo.getString("tiket_ev"));
                                    array_owner_ev.add(jo.getString("owner_ev"));
                                    array_total_ev.add(jo.getString("total_ev"));
                                    array_waktu_tr.add(jo.getString("waktu_tr"));
                                    array_kode_tr.add(jo.getString("kode_tr"));


                                }

                                //Menampilkan data berbentuk adapter menggunakan class CLVDataUser
                                final CLV_dataorderadmin adapter = new CLV_dataorderadmin(PengolahanBiayaOw.this,array_nama_ev,array_lokasi_ev,array_jadwal_ev,array_mulai_ev,array_akhir_ev,array_namaps_ev,
                                        array_harga_ev,array_tiket_ev,array_owner_ev,array_total_ev,array_waktu_tr,array_kode_tr);
                                //Set adapter to list
                                listView.setAdapter(adapter);
//edit and delete
                                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                        Log.d("TestKlik",""+array_nama_ev.get(position));
                                        Toast.makeText(PengolahanBiayaOw.this, array_nama_ev.get(position), Toast.LENGTH_SHORT).show();

                                        //Setelah proses koneksi keserver selesai, maka aplikasi akan berpindah class
                                        //DataActivity.class dan membawa/mengirim data-data hasil query dari server.
                                        Intent i = new Intent(PengolahanBiayaOw.this, HasilTransaksiUser.class);
                                        i.putExtra("nama_ev",array_nama_ev.get(position));
                                        i.putExtra("lokasi_ev",array_lokasi_ev.get(position));
                                        i.putExtra("jadwal_ev",array_jadwal_ev.get(position));
                                        i.putExtra("mulai_ev",array_mulai_ev.get(position));
                                        i.putExtra("akhir_ev",array_akhir_ev.get(position));
                                        i.putExtra("owner_ev",array_owner_ev.get(position));
                                        i.putExtra("namaps_ev",array_namaps_ev.get(position));
                                        i.putExtra("harga_ev",array_harga_ev.get(position));
                                        i.putExtra("tiket_ev",array_tiket_ev.get(position));
                                        i.putExtra("total_ev",array_total_ev.get(position));
                                        i.putExtra("waktu_tr",array_waktu_tr.get(position));
                                        i.putExtra("kode_tr",array_kode_tr.get(position));
                                        startActivity(i);

                                    }
                                });


                            }else{
                                Toast.makeText(PengolahanBiayaOw.this, "Gagal Mengambil Data", Toast.LENGTH_SHORT).show();

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

    private String formatRupiah(Double number){
        Locale localeID = new Locale("in", "ID");
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
        return formatRupiah.format(number);
    }
}