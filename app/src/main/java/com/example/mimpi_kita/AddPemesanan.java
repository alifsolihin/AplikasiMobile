package com.example.mimpi_kita;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONObject;

public class AddPemesanan extends AppCompatActivity {

    TextInputEditText EtnamaEV,ETlokasiEV,EThariEV,ETmulaiEV,ETakhirEV, EThargaEv, ETtiketEv, ETnamaus, ETowner;
    TextView ETtotal;
    ProgressDialog progressDialog;
    private Context mContext;
    Button BTNsaveData;

    String nama,lokasi,hari,mulai,akhir,harga,tiket,namaus, total, nama2, owner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pemesanan);

        EtnamaEV = findViewById(R.id.ETNamaEV);
        ETlokasiEV = findViewById(R.id.ETLokasiEV);
        EThariEV = findViewById(R.id.ETHariEV);
        ETmulaiEV = findViewById(R.id.ETMulaiEV);
        ETakhirEV = findViewById(R.id.ETAkhirEV);
        ETnamaus = findViewById(R.id.ETNamaOnwer);
        EThargaEv = findViewById(R.id.ETHargaTK);
        ETtiketEv = findViewById(R.id.ETJumlahTK);
        ETowner = findViewById(R.id.ETOwnerEV);
        ETtotal = findViewById(R.id.TVjmlHrg);
        BTNsaveData = findViewById(R.id.BTNSdataEV);
        progressDialog = new ProgressDialog(this);

        nama = getIntent().getExtras().getString("nama_ev");
        nama2 = getIntent().getExtras().getString("nama");
        lokasi = getIntent().getExtras().getString("lokasi_ev");
        hari = getIntent().getExtras().getString("hari_ev");
        mulai = getIntent().getExtras().getString("mulai_ev");
        akhir = getIntent().getExtras().getString("akhir_ev");
        harga = getIntent().getExtras().getString("harga_ev");
        owner = getIntent().getExtras().getString("owner_ev");

        EtnamaEV.setText(nama);
        ETlokasiEV.setText(lokasi);
        EThariEV.setText(hari);
        ETnamaus.setText(nama2);
        ETmulaiEV.setText(mulai);
        ETakhirEV.setText(akhir);
        EThargaEv.setText(harga);
        ETowner.setText(owner);

        ETtiketEv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int angka1 = Integer.parseInt(ETtiketEv.getText().toString());
                int angka2 = Integer.parseInt(EThargaEv.getText().toString());
                int hsl = angka1 * angka2;
                ETtotal.setText(""+hsl);
            }
        });

        BTNsaveData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nama = getIntent().getExtras().getString("nama_ev");
                lokasi = getIntent().getExtras().getString("lokasi_ev");
                hari = getIntent().getExtras().getString("hari_ev");
                mulai = getIntent().getExtras().getString("mulai_ev");
                akhir = getIntent().getExtras().getString("akhir_ev");
                harga = getIntent().getExtras().getString("harga_ev");
                owner = getIntent().getExtras().getString("owner_ev");
                namaus        = ETnamaus.getText().toString();
                tiket        = ETtiketEv.getText().toString();
                total        = ETtotal.getText().toString();

                namaus = ETnamaus.getText().toString();
                nama = EtnamaEV.getText().toString();
                lokasi = ETlokasiEV.getText().toString();
                hari = EThariEV.getText().toString();
                mulai = ETmulaiEV.getText().toString();
                akhir = ETakhirEV.getText().toString();
                harga = EThargaEv.getText().toString();
                tiket = ETtiketEv.getText().toString();
                owner = ETowner.getText().toString();
                total = ETtotal.getText().toString();
                Intent page;
                page = new Intent(AddPemesanan.this,HasilTransaksi.class);
                page.putExtra("namaps_ev", namaus);
                page.putExtra("nama_ev", nama);
                page.putExtra("lokasi_ev", lokasi);
                page.putExtra("hari_ev", hari);
                page.putExtra("mulai_ev", mulai);
                page.putExtra("akhir_ev", akhir);
                page.putExtra("harga_ev", harga);
                page.putExtra("tiket_ev", tiket);
                page.putExtra("owner_ev", owner);
                page.putExtra("total_ev",total);
                startActivity(page);


                //validasiData();

            }
        });



        //getDataIntent();
    }

    /*void validasiData(){
        if(ETnamaus.equals("")){
            progressDialog.dismiss();
            Toast.makeText(AddPemesanan.this, "Periksa kembali data yang anda masukkan !", Toast.LENGTH_SHORT).show();
        }else {
            kirimData();
        }
    }

    void kirimData(){
        AndroidNetworking.post("https://tkjbpnup.com/kelompok_9/mimpikita/addDataBeli.php")
                .addBodyParameter("nama_ev",""+nama)
                .addBodyParameter("lokasi_ev",""+lokasi)
                .addBodyParameter("jadwal_ev",""+hari)
                .addBodyParameter("mulai_ev",""+mulai)
                .addBodyParameter("akhir_ev",""+akhir)
                .addBodyParameter("namaps_ev",""+namaus)
                .addBodyParameter("harga_ev",""+harga)
                .addBodyParameter("tiket_ev",""+tiket)
                .addBodyParameter("owner_ev",""+owner)
                .addBodyParameter("total_ev",""+total)
                .setPriority(Priority.MEDIUM)
                .setTag("Tambah Data")
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        progressDialog.dismiss();
                        Log.d("cekTambah",""+response);
                        try {
                            Boolean status = response.getBoolean("status");
                            String pesan = response.getString("result");
                            Toast.makeText(AddPemesanan.this, ""+pesan, Toast.LENGTH_SHORT).show();
                            Log.d("status",""+status);
                            if(status){
                                new AlertDialog.Builder(AddPemesanan.this)
                                        .setMessage("Berhasil Menambahkan Data !")
                                        .setCancelable(false)
                                        .setPositiveButton("Cetak Transaksi", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                namaus = ETnamaus.getText().toString();
                                                nama = EtnamaEV.getText().toString();
                                                lokasi = ETlokasiEV.getText().toString();
                                                hari = EThariEV.getText().toString();
                                                mulai = ETmulaiEV.getText().toString();
                                                akhir = ETakhirEV.getText().toString();
                                                harga = EThargaEv.getText().toString();
                                                tiket = ETtiketEv.getText().toString();
                                                owner = ETowner.getText().toString();
                                                total = ETtotal.getText().toString();
                                                Intent page;
                                                page = new Intent(AddPemesanan.this,HasilTransaksi.class);
                                                page.putExtra("namaps_ev", namaus);
                                                page.putExtra("nama_ev", nama);
                                                page.putExtra("lokasi_ev", lokasi);
                                                page.putExtra("hari_ev", hari);
                                                page.putExtra("mulai_ev", mulai);
                                                page.putExtra("akhir_ev", akhir);
                                                page.putExtra("harga_ev", harga);
                                                page.putExtra("tiket_ev", tiket);
                                                page.putExtra("owner_ev", owner);
                                                page.putExtra("total_ev",total);
                                                startActivity(page);
                                            }
                                        })
                                        .show();
                            }
                            else{
                                new AlertDialog.Builder(AddPemesanan.this)
                                        .setMessage("Gagal Menambahkan Data !")
                                        .setPositiveButton("Kembali", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                //Intent i = getIntent();
                                                //setResult(RESULT_CANCELED,i);
                                                //add_mahasiswa.this.finish();
                                            }
                                        })
                                        .setCancelable(false)
                                        .show();
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
    }*/

}