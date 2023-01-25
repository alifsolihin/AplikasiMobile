package com.example.mimpi_kita;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.Locale;

public class HasilTransaksiUser extends AppCompatActivity {

    TextView TVnamaus, Tvnamaev, Tvlokasiev, Tvhari, Tvmulai, Tvakhir, TvjmlTk, TvhrgTK, Tvwkt, Tvtotal, Tvowner, Tvrandom;
    String namaus, namaev, lokasiev, hariev, mulaiev, akhirev, jmltk, hrgtk, total, owner, wkttr, kodetr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hasil_transaksi_user);

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
        hariev = getIntent().getExtras().getString("jadwal_ev");
        mulaiev = getIntent().getExtras().getString("mulai_ev");
        akhirev = getIntent().getExtras().getString("akhir_ev");
        jmltk = getIntent().getExtras().getString("tiket_ev");
        hrgtk = getIntent().getExtras().getString("harga_ev");
        owner = getIntent().getExtras().getString("owner_ev");
        total = getIntent().getExtras().getString("total_ev");
        wkttr = getIntent().getExtras().getString("waktu_tr");
        kodetr = getIntent().getExtras().getString("kode_tr");

        Tvnamaev.setText(namaev);
        TVnamaus.setText(namaus);
        Tvlokasiev.setText(lokasiev);
        Tvhari.setText(hariev);
        Tvmulai.setText(mulaiev);
        Tvakhir.setText(akhirev);
        TvjmlTk.setText(jmltk);
        TvhrgTK.setText(hrgtk);
        Tvowner.setText(owner);
        Tvwkt.setText(wkttr);
        Tvrandom.setText(kodetr);

        String resultRupiah = formatRupiah(Double.parseDouble(total));
        Tvtotal.setText(resultRupiah);


    }

    private String formatRupiah(Double number){
        Locale localeID = new Locale("in", "ID");
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
        return formatRupiah.format(number);
    }
}