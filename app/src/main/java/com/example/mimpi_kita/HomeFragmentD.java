package com.example.mimpi_kita;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class HomeFragmentD extends Fragment {

    SimpleAdapter adapter;
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
    Integer[] gambar;
    String email;

    ArrayList<String> array_nama_ev,array_tanggal_ev,array_lokasi_ev,array_jadwal_ev,array_mulai_ev,
            array_akhir_ev, array_owner_ev, array_deskripsi_ev, array_benefit1_ev,
            array_benefit2_ev, array_harga_ev, array_tiket_ev, array_gambar_ev, array_jenis_ev;
    ProgressDialog progressDialog;
    private Context mContext;
    ListView listView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home_d, container, false);
        SearchView searchViewCari             = (SearchView) v.findViewById(R.id.idETcari);
        CardView cardView                     = (CardView) v.findViewById(R.id.idCARDuser);
        CardView cardViewEvent                = (CardView) v.findViewById(R.id.idEvent);
        CardView cardViewSertifikat           = (CardView) v.findViewById(R.id.idSertifikat);
        CardView cardViewSemua                = (CardView) v.findViewById(R.id.idSemua);
        TextView tvemail                     = (TextView) v.findViewById(R.id.idTV2);
        SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) v.findViewById(R.id.swipe_container);
        listView = (ListView) v.findViewById(R.id.idLV);


        tvemail.setText(email);

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





        nama = new String[]{
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


        adapter = new SimpleAdapter(getActivity(), mylist, R.layout.list_item,
                new String[]{"Nama","Regist", "Waktu", "Gambar","Lokasi","Jam","User","Desc","Ben1","Ben2","Cash","Tiket"}, new int[]{R.id.idTXTNama,
                (R.id.idTXTregist),(R.id.idTXTwaktu),(R.id.idLVGambar)});

        listView.setAdapter(adapter);

       searchViewCari.clearFocus();
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
       });


       cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent Back;
                Back = new Intent(getActivity(),UserMenu.class);
                startActivity(Back);

            }
        });

        cardViewEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent Back;
                Back = new Intent(getActivity(),HalamanEvent.class);
                startActivity(Back);


            }
        });

        cardViewSertifikat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent Back;
                Back = new Intent(getActivity(),HalamanSertifikat.class);
                startActivity(Back);

            }
        });

        cardViewSemua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent Back;
                Back = new Intent(getActivity(),HalamanSemua.class);
                startActivity(Back);

            }
        });


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {



                Intent page = new Intent(getActivity(),EventResult1.class);
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
        });
        //getData();


        return v;
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

                                }

                                //Menampilkan data berbentuk adapter menggunakan class CLVDataUser
                                final CLV_dataevent2 adapter = new CLV_dataevent2(getActivity(),array_nama_ev,array_tanggal_ev,array_lokasi_ev,array_jadwal_ev,array_mulai_ev,
                                        array_akhir_ev,array_owner_ev,array_deskripsi_ev,array_benefit1_ev,array_benefit2_ev,array_harga_ev,array_tiket_ev,array_gambar_ev,array_jenis_ev);
                                //Set adapter to list
                                listView.setAdapter(adapter);
//edit and delete
                                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                        Log.d("TestKlik",""+array_nama_ev.get(position));
                                        Toast.makeText(getActivity(), array_nama_ev.get(position), Toast.LENGTH_SHORT).show();

                                        //Setelah proses koneksi keserver selesai, maka aplikasi akan berpindah class
                                        //DataActivity.class dan membawa/mengirim data-data hasil query dari server.
                                        Intent i = new Intent(getActivity(), EventResult1.class);
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
                                        i.putExtra("gambar_ev",array_gambar_ev.get(position));
                                        startActivity(i);
                                    }
                                });


                            }else{
                                Toast.makeText(getActivity(), "Gagal Mengambil Data", Toast.LENGTH_SHORT).show();

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