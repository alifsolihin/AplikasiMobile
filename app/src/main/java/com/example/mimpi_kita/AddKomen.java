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

import java.text.SimpleDateFormat;
import java.util.Date;

public class AddKomen extends AppCompatActivity {

    TextInputEditText EtnamaEV, ETnamaus, ETowner, ETkomen;
    ProgressDialog progressDialog;
    private Context mContext;
    Button BTNsaveData;

    String nama,komen, nama2, owner, wkttr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_komen);

        EtnamaEV = findViewById(R.id.ETNamaEV);
        ETnamaus = findViewById(R.id.ETNamaUser);
        ETowner = findViewById(R.id.ETOwnerEV);
        ETkomen = findViewById(R.id.ETKomenEv);
        BTNsaveData = findViewById(R.id.BTNSdataEV);
        progressDialog = new ProgressDialog(this);



        nama = getIntent().getExtras().getString("nama_ev");
        nama2 = getIntent().getExtras().getString("nama");
        owner = getIntent().getExtras().getString("owner_ev");

        EtnamaEV.setText(nama);
        ETnamaus.setText(nama2);
        ETowner.setText(owner);

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
                nama2 = getIntent().getStringExtra("nama");
                komen = ETkomen.getText().toString();
                wkttr = (currentDateandTime).toString();

                validasiData();
            }
        });
    }

    void validasiData(){
        if(komen.equals("")){
            progressDialog.dismiss();
            Toast.makeText(AddKomen.this, "Periksa kembali data yang anda masukkan !", Toast.LENGTH_SHORT).show();
        }else {
            kirimData();
        }
    }

    void kirimData(){
        AndroidNetworking.post("https://tkjbpnup.com/kelompok_9/mimpikita/addDataKomen.php")
                .addBodyParameter("nama_ev",""+nama)
                .addBodyParameter("owner_ev",""+owner)
                .addBodyParameter("namaps_ev",""+nama2)
                .addBodyParameter("komen_ev",""+komen)
                .addBodyParameter("waktu_km",""+wkttr)
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
                            Toast.makeText(AddKomen.this, ""+pesan, Toast.LENGTH_SHORT).show();
                            Log.d("status",""+status);
                            if(status){
                                new AlertDialog.Builder(AddKomen.this)
                                        .setMessage("Berhasil Menambahkan Komentar !")
                                        .setCancelable(false)
                                        .setPositiveButton("Kembali", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                Intent page;
                                                page = new Intent(AddKomen.this,UserDashboardMainF.class);
                                                startActivity(page);
                                                finish();
                                            }
                                        })
                                        .show();
                            }
                            else{
                                new AlertDialog.Builder(AddKomen.this)
                                        .setMessage("Gagal Menambahkan Komentar !")
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