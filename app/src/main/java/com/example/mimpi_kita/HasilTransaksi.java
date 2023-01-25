package com.example.mimpi_kita;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONObject;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

public class HasilTransaksi extends AppCompatActivity {

    TextView TVnamaus, Tvnamaev, Tvlokasiev, Tvhari, Tvmulai, Tvakhir, TvjmlTk, TvhrgTK, Tvwkt, Tvtotal, Tvowner, Tvrandom;
    String namaus, namaev, lokasiev, hariev, mulaiev, akhirev, jmltk, hrgtk, total, owner, wkttr, kodetr;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hasil_transaksi);

        TVnamaus = findViewById(R.id.idNamaUs);
        Tvnamaev = findViewById(R.id.idTVnama_ev);
        Tvlokasiev = findViewById(R.id.idTVlokasi_ev);
        Tvhari = findViewById(R.id.idTVhari_ev);
        Tvmulai = findViewById(R.id.idTVmulai_ev);
        Tvakhir = findViewById(R.id.idTVakhir_ev);
        TvjmlTk = findViewById(R.id.idTVjml_tkt);
        TvhrgTK = findViewById(R.id.idTVhrg_tkt);
        Tvowner = findViewById(R.id.idTVOwner_ev);
        Tvwkt = findViewById(R.id.idTVWaktu);
        Tvrandom = findViewById(R.id.idTVrandom);
        Tvtotal = findViewById(R.id.idTVtotal);


        namaus = getIntent().getExtras().getString("namaps_ev");
        namaev = getIntent().getExtras().getString("nama_ev");
        lokasiev = getIntent().getExtras().getString("lokasi_ev");
        hariev = getIntent().getExtras().getString("hari_ev");
        mulaiev = getIntent().getExtras().getString("mulai_ev");
        akhirev = getIntent().getExtras().getString("akhir_ev");
        jmltk = getIntent().getExtras().getString("tiket_ev");
        hrgtk = getIntent().getExtras().getString("harga_ev");
        owner = getIntent().getExtras().getString("owner_ev");
        total = getIntent().getExtras().getString("total_ev");

        Tvnamaev.setText(namaev);
        TVnamaus.setText(namaus);
        Tvlokasiev.setText(lokasiev);
        Tvhari.setText(hariev);
        Tvmulai.setText(mulaiev);
        Tvakhir.setText(akhirev);
        TvjmlTk.setText(jmltk);
        TvhrgTK.setText(hrgtk);
        Tvowner.setText(owner);



        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy 'Jam:' HH:mm:ss");
        String currentDateandTime = sdf.format(new Date());
        Tvwkt.setText(currentDateandTime);

        String resultRupiah = formatRupiah(Double.parseDouble(total));
        Tvtotal.setText(resultRupiah);

         wkttr = Tvwkt.getText().toString();


        GENERATE();
        kirimData();

    }

    private String formatRupiah(Double number){
        Locale localeID = new Locale("in", "ID");
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
        return formatRupiah.format(number);
    }

    private void GENERATE(){
        RandomString randomString= new RandomString();

        String result=randomString.generateAlphaNumeric(20);
        Tvrandom.setText(result);
        kodetr = Tvrandom.getText().toString();
    }

    void kirimData(){
        AndroidNetworking.post("https://tkjbpnup.com/kelompok_9/mimpikita/addDataBeli2.php")
                .addBodyParameter("nama_ev",""+namaev)
                .addBodyParameter("lokasi_ev",""+lokasiev)
                .addBodyParameter("jadwal_ev",""+hariev)
                .addBodyParameter("mulai_ev",""+mulaiev)
                .addBodyParameter("akhir_ev",""+akhirev)
                .addBodyParameter("namaps_ev",""+namaus)
                .addBodyParameter("harga_ev",""+hrgtk)
                .addBodyParameter("tiket_ev",""+jmltk)
                .addBodyParameter("owner_ev",""+owner)
                .addBodyParameter("total_ev",""+total)
                .addBodyParameter("waktu_tr",""+wkttr)
                .addBodyParameter("kode_tr",""+kodetr)
                .setPriority(Priority.MEDIUM)
                .setTag("Tambah Data")
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Boolean status = response.getBoolean("status");
                            String pesan = response.getString("result");
                            if(status){
                                Toast.makeText(HasilTransaksi.this, "Berhasil Melakukan Transaksi", Toast.LENGTH_SHORT).show();
                            }
                            else{

                                Intent page;
                                page = new Intent(HasilTransaksi.this,AddPemesanan.class);
                                startActivity(page);
                                Toast.makeText(HasilTransaksi.this, "Gagal Anda Sudah Melakukan Transaksi Ini", Toast.LENGTH_SHORT).show();
                            }
                        }catch (Exception e){
                            e.printStackTrace();
                        }


                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.d("ErrorTambahData",""+anError.getErrorBody());
                    }
                });
    }
}