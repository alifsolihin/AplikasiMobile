package com.example.mimpi_kita;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.util.Pair;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.timepicker.MaterialTimePicker;
import com.google.android.material.timepicker.TimeFormat;
import com.squareup.picasso.Picasso;

import java.util.Calendar;

public class EditEvent extends AppCompatActivity {

    Toolbar BTNBack;
    Button BTNnext;
    TextInputEditText ETnamaEV,ETtglrg,ETlokasiEV, EThariEV,ETmulaiEV,ETakhirEV,ETownerEV,ETdeskripsiEV;
    String namaEv, tglrgEV, lokasiEV, hariEV, mulaiEV, akhirEV, ownerEV, deskripsiEV, benefit1Ev, benefit2Ev, hargaEv, tiketEv, gambarEV;
    ProgressDialog progressDialog;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_event);

        BTNnext =findViewById(R.id.BTNNextDT);
        ETtglrg = findViewById(R.id.ETTglReg);
        ETnamaEV = findViewById(R.id.ETNamaEV);
        ETlokasiEV = findViewById(R.id.ETLokasiEV);
        EThariEV = findViewById(R.id.ETHariEV);
        ETmulaiEV = findViewById(R.id.ETMulaiEV);
        ETakhirEV = findViewById(R.id.ETAkhirEV);
        ETownerEV = findViewById(R.id.ETNamaOnwer);
        ETdeskripsiEV = findViewById(R.id.ETDeskEV);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarDE);
        setSupportActionBar(toolbar);

        progressDialog = new ProgressDialog(this);
        getDataIntent();

        toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_ios_24);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent Back;
                Back = new Intent(EditEvent.this,PengolahanEvent.class);
                startActivity(Back);
                finish();
            }

        });

        /*BTNnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Next;
                Next = new Intent(EditEvent.this,EditEvent2.class);
                startActivity(Next);
            }
        });*/

        MaterialDatePicker datePicker=MaterialDatePicker.Builder.dateRangePicker()
                .setSelection(Pair.create(MaterialDatePicker.thisMonthInUtcMilliseconds(),
                        MaterialDatePicker.todayInUtcMilliseconds())).build();

        MaterialDatePicker datePicker2 =MaterialDatePicker.Builder.dateRangePicker()
                .setSelection(Pair.create(MaterialDatePicker.thisMonthInUtcMilliseconds(),
                        MaterialDatePicker.todayInUtcMilliseconds())).build();

        ETtglrg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePicker.show(getSupportFragmentManager(),"Material_Range");
                datePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() {
                    @Override
                    public void onPositiveButtonClick(Object selection) {
                        ETtglrg.setText(datePicker.getHeaderText());
                    }
                });
            }
        });

        EThariEV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePicker2.show(getSupportFragmentManager(),"Material_Range");
                datePicker2.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() {
                    @Override
                    public void onPositiveButtonClick(Object selection) {
                        EThariEV.setText(datePicker2.getHeaderText());
                    }
                });
            }
        });

        Calendar calendar=Calendar.getInstance();
        MaterialTimePicker materialTimePicker=new MaterialTimePicker.Builder().setTimeFormat(TimeFormat.CLOCK_24H).
                setHour(calendar.get(Calendar.HOUR_OF_DAY))
                .setMinute(calendar.get(calendar.MINUTE))
                .build();

        ETmulaiEV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                materialTimePicker.show(getSupportFragmentManager(),"Time");
                materialTimePicker.addOnPositiveButtonClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int Hour=materialTimePicker.getHour();
                        int Minute=materialTimePicker.getMinute();
                        ETmulaiEV.setText(Hour+":"+Minute);

                    }
                });
            }
        });

        MaterialTimePicker materialTimePicker2 =new MaterialTimePicker.Builder().setTimeFormat(TimeFormat.CLOCK_24H).
                setHour(calendar.get(Calendar.HOUR_OF_DAY))
                .setMinute(calendar.get(calendar.MINUTE))
                .build();

        ETakhirEV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                materialTimePicker2.show(getSupportFragmentManager(),"Time");
                materialTimePicker2.addOnPositiveButtonClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int Hour=materialTimePicker2.getHour();
                        int Minute=materialTimePicker2.getMinute();
                        ETakhirEV.setText(Hour+":"+Minute);

                    }
                });
            }
        });

        BTNnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                namaEv = getIntent().getStringExtra("nama_ev");
                tglrgEV = getIntent().getStringExtra("tanggal_ev");
                lokasiEV = getIntent().getStringExtra("lokasi_ev");
                hariEV = getIntent().getStringExtra("jadwal_ev");
                mulaiEV = getIntent().getStringExtra("mulai_ev");
                akhirEV = getIntent().getStringExtra("akhir_ev");
                ownerEV = getIntent().getStringExtra("owner_ev");
                deskripsiEV = getIntent().getStringExtra("deskripsi_ev");
                benefit1Ev = getIntent().getStringExtra("benefit1_ev");
                benefit2Ev = getIntent().getStringExtra("benefit2_ev");
                hargaEv = getIntent().getStringExtra("harga_ev");
                tiketEv = getIntent().getStringExtra("tiket_ev");
                gambarEV = getIntent().getStringExtra("gambar_ev");

                namaEv = ETnamaEV.getText().toString();
                tglrgEV = ETtglrg.getText().toString();
                lokasiEV = ETlokasiEV.getText().toString();
                hariEV = EThariEV.getText().toString();
                mulaiEV = ETmulaiEV.getText().toString();
                akhirEV = ETakhirEV.getText().toString();
                ownerEV = ETownerEV.getText().toString();
                deskripsiEV = ETdeskripsiEV.getText().toString();
                Intent page;
                page = new Intent(EditEvent.this,EditEvent2.class);
                page.putExtra("nama_ev", namaEv);
                page.putExtra("tanggal_ev", tglrgEV);
                page.putExtra("lokasi_ev", lokasiEV);
                page.putExtra("jadwal_ev", hariEV);
                page.putExtra("mulai_ev", mulaiEV);
                page.putExtra("akhir_ev", akhirEV);
                page.putExtra("owner_ev", ownerEV);
                page.putExtra("deskripsi_ev", deskripsiEV);
                page.putExtra("benefit1_ev", benefit1Ev);
                page.putExtra("benefit2_ev", benefit2Ev);
                page.putExtra("harga_ev", hargaEv);
                page.putExtra("tiket_ev", tiketEv);
                page.putExtra("gambar_ev", gambarEV);
                startActivity(page);

                //TVResult.setText(name01+" bersama dengan "+name02+" pergi ke kampus");


            }
        });

    }

    void getDataIntent(){
        Bundle bundle = getIntent().getExtras();
        if(bundle!=null){
            ETnamaEV.setText(bundle.getString("nama_ev"));
            ETtglrg.setText(bundle.getString("tanggal_ev"));
            ETlokasiEV.setText(bundle.getString("lokasi_ev"));
            EThariEV.setText(bundle.getString("jadwal_ev"));
            ETmulaiEV.setText(bundle.getString("mulai_ev"));
            ETakhirEV.setText(bundle.getString("akhir_ev"));
            ETownerEV.setText(bundle.getString("owner_ev"));
            ETdeskripsiEV.setText(bundle.getString("deskripsi_ev"));
        }else{
            ETnamaEV.setText("");
            ETtglrg.setText("");
            ETlokasiEV.setText("");
            EThariEV.setText("");
            ETmulaiEV.setText("");
            ETakhirEV.setText("");
            ETownerEV.setText("");
            ETdeskripsiEV.setText("");
        }

    }
}