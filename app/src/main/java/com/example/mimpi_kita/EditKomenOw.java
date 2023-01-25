package com.example.mimpi_kita;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

public class EditKomenOw extends AppCompatActivity {

    TextInputEditText EtnamaEV, ETnamaus, ETowner, ETkomen, ETbalas;
    ProgressDialog progressDialog;
    private Context mContext;
    Button BTNsaveData;

    String nama,komen, nama2, owner, wkttr, balas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_komen_ow);

        EtnamaEV = findViewById(R.id.ETNamaEV);
        ETnamaus = findViewById(R.id.ETNamaUser);
        ETowner = findViewById(R.id.ETOwnerEV);
        ETkomen = findViewById(R.id.ETKomenEv);
        ETbalas = findViewById(R.id.ETKomenBalasEv);
        BTNsaveData = findViewById(R.id.BTNNextDT);
        progressDialog = new ProgressDialog(this);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarDE);
        setSupportActionBar(toolbar);

        toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_ios_24);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                nama = getIntent().getStringExtra("nama");
                Intent Back;
                Back = new Intent(EditKomenOw.this,PengolahanKomenUser.class);
                Back.putExtra("nama", nama);
                startActivity(Back);
                finish();
            }

        });

        nama = getIntent().getExtras().getString("nama_ev");
        nama2 = getIntent().getExtras().getString("namaps_ev");
        owner = getIntent().getExtras().getString("owner_ev");
        komen = getIntent().getExtras().getString("komen_ev");

        EtnamaEV.setText(nama);
        ETnamaus.setText(nama2);
        ETowner.setText(owner);
        ETkomen.setText(komen);

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy 'Jam:' HH:mm:ss");
        String currentDateandTime = sdf.format(new Date());

        wkttr = (currentDateandTime).toString();

        BTNsaveData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.setMessage("Menambahkan Komentar...");
                progressDialog.setCancelable(false);
                progressDialog.show();

                nama = getIntent().getStringExtra("nama_ev");
                owner = getIntent().getStringExtra("owner_ev");
                nama2 = getIntent().getStringExtra("namaps_ev");
                komen = getIntent().getStringExtra("komen_ev");
                balas = ETbalas.getText().toString();
                wkttr = (currentDateandTime).toString();

                validasiData();
            }
        });
    }
    void validasiData(){
        if(ETbalas.equals("")){
            progressDialog.dismiss();
            Toast.makeText(EditKomenOw.this, "Periksa kembali data yang anda masukkan !", Toast.LENGTH_SHORT).show();
        }else {
            kirimData();
        }
    }

    void kirimData(){
        AndroidNetworking.post("https://tkjbpnup.com/kelompok_9/mimpikita/editKomen.php")
                .addBodyParameter("nama_ev",""+nama)
                .addBodyParameter("owner_ev",""+owner)
                .addBodyParameter("namaps_ev",""+nama2)
                .addBodyParameter("komen_ev",""+komen)
                .addBodyParameter("waktu_km",""+wkttr)
                .addBodyParameter("balas_km",""+balas)
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
                            Toast.makeText(EditKomenOw.this, ""+pesan, Toast.LENGTH_SHORT).show();
                            Log.d("status",""+status);
                            if(status){
                                new AlertDialog.Builder(EditKomenOw.this)
                                        .setMessage("Berhasil Mengirim Komentar !")
                                        .setCancelable(false)
                                        .setPositiveButton("Kembali", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                Intent page;
                                                page = new Intent(EditKomenOw.this,PengolahanKomenUser.class);
                                                startActivity(page);
                                                finish();
                                            }
                                        })
                                        .show();
                            }
                            else{
                                new AlertDialog.Builder(EditKomenOw.this)
                                        .setMessage("Gagal Mengirim Komentar !")
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
    }
}