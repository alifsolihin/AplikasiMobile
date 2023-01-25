package com.example.mimpi_kita;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ablanco.zoomy.DoubleTapListener;
import com.ablanco.zoomy.TapListener;
import com.ablanco.zoomy.Zoomy;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;


public class EventResult1 extends AppCompatActivity {

    //ImageView TVResultGambar;
    ImageView TVResultGambar;
    TextView TVResult1,TVResult2,TVResult3,TVResult4,TVResult4_2,TVResult4_3,TVResult5,TVResult6,TVResult7,TVResult8,TVResult9,TVResult10, Tvjenis;
    Button BTNCek;
    ImageButton IBkomen;
    AlertDialog dialog;


    String nama,waktu,lokasi,hari,mulai,akhir,user,desc,ben1,ben2,cash,tiket, nama2, jenis;
    int gambar;

    AlertDialog.Builder builder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_result1);

        TVResultGambar = findViewById(R.id.idTVResultGambar);
        BTNCek = findViewById(R.id.idBTNcek);
        TVResult1 = findViewById(R.id.idTVResult1);
        TVResult2 = findViewById(R.id.idTVResult2);
        TVResult3 = findViewById(R.id.idTVResult3);
        TVResult4 = findViewById(R.id.idTVResult4);
        TVResult4_2 = findViewById(R.id.idTVResult42);
        TVResult4_3 = findViewById(R.id.idTVResult43);
        TVResult5 = findViewById(R.id.idTVResult5);
        TVResult6 = findViewById(R.id.idTVResult6);
        TVResult7 = findViewById(R.id.idTVResult7);
        TVResult8 = findViewById(R.id.idTVResult8);
        TVResult9 = findViewById(R.id.idTVResult9);
        TVResult10 = findViewById(R.id.idTVResult10);
        Tvjenis = findViewById(R.id.idTVResultpilih);
        IBkomen = findViewById(R.id.idIBKomen);

        nama2 = getIntent().getStringExtra("nama");

        builder = new AlertDialog.Builder(this);

        Zoomy.Builder builder = new Zoomy.Builder(this)
                .target(TVResultGambar)
                .animateZooming(true)
                .enableImmersiveMode(false)
                .tapListener(new TapListener() {
                    @Override
                    public void onTap(View v) {

                    }
                });

        builder.register();

        nama = getIntent().getStringExtra("nama_ev");
        waktu = getIntent().getStringExtra("tanggal_ev");
        lokasi = getIntent().getStringExtra("lokasi_ev");
        hari = getIntent().getStringExtra("jadwal_ev");
        mulai = getIntent().getStringExtra("mulai_ev");
        akhir = getIntent().getStringExtra("akhir_ev");
        user = getIntent().getStringExtra("owner_ev");
        desc = getIntent().getStringExtra("deskripsi_ev");
        ben1 = getIntent().getStringExtra("benefit1_ev");
        ben2 = getIntent().getStringExtra("benefit2_ev");
        cash = getIntent().getStringExtra("harga_ev");
        tiket = getIntent().getStringExtra("tiket_ev");
        jenis = getIntent().getStringExtra("jenis_ev");
        Picasso.get().load("https://tkjbpnup.com/kelompok_9/mimpikita/image/"+getIntent().getStringExtra("gambar_ev")).into(TVResultGambar);




        TVResult1.setText(nama);
        TVResult2.setText(waktu);
        TVResult3.setText(lokasi);
        TVResult4.setText(hari);
        TVResult4_2.setText(mulai);
        TVResult4_3.setText(akhir);
        TVResult5.setText(user);
        TVResult6.setText(desc);
        TVResult7.setText(ben1);
        TVResult8.setText(ben2);
        TVResult9.setText(cash);
        TVResult10.setText(tiket);
        Tvjenis.setText(jenis);

        BTNCek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nama2 = getIntent().getStringExtra("nama");
                nama = getIntent().getStringExtra("nama_ev");
                lokasi = getIntent().getStringExtra("lokasi_ev");
                hari = getIntent().getStringExtra("jadwal_ev");
                mulai = getIntent().getStringExtra("mulai_ev");
                akhir = getIntent().getStringExtra("akhir_ev");
                cash = getIntent().getStringExtra("harga_ev");
                user = getIntent().getStringExtra("owner_ev");
                Intent Back;
                Back = new Intent(EventResult1.this,AddPemesanan.class);
                Back.putExtra("nama_ev", nama);
                Back.putExtra("nama", nama2);
                Back.putExtra("lokasi_ev", lokasi);
                Back.putExtra("hari_ev", hari);
                Back.putExtra("mulai_ev", mulai);
                Back.putExtra("akhir_ev", akhir);
                Back.putExtra("harga_ev",cash);
                Back.putExtra("owner_ev",user);
                startActivity(Back);

            }
        });

        IBkomen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nama2 = getIntent().getStringExtra("nama");
                nama = getIntent().getStringExtra("nama_ev");
                user = getIntent().getStringExtra("owner_ev");
                Intent Back;
                Back = new Intent(EventResult1.this,AddKomen.class);
                Back.putExtra("nama_ev", nama);
                Back.putExtra("nama", nama2);
                Back.putExtra("owner_ev",user);
                startActivity(Back);

            }
        });


    }

    /*private void addPesan() {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setTitle("Pemesanan Event");
        EditText d_namaev = new EditText(this);
        builder1.setView(d_namaev);
        builder1.setPositiveButton("Pesan", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {


                String nama_event = d_namaev.getText().toString().trim();
                Toast.makeText(EventResult1.this, "Anda Memilih"+nama_event+"Untuk Dipesan", Toast.LENGTH_SHORT).show();
            }
        });
        builder1.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(EventResult1.this, "Anda Membatalkan Pemesanan", Toast.LENGTH_SHORT).show();
            }
        });
        builder1.create().show();
    }*/

}