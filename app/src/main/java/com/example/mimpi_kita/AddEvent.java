package com.example.mimpi_kita;

import androidx.appcompat.app.AlertDialog;
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

import java.util.Calendar;

public class AddEvent extends AppCompatActivity {

    Button BTNnext;
    TextInputEditText EtnamaEV,ETtglrg,ETlokasiEV,EThariEV,ETmulaiEV,ETakhirEV,ETownerEV,ETdeskripsiEV;
    String namaEv, tglrgEV, lokasiEV, jadwalEV, mulaiEV, akhirEV, ownerEV, deskripsiEV;
    ProgressDialog progressDialog;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);

        BTNnext =findViewById(R.id.BTNNextDT);
        EtnamaEV = findViewById(R.id.ETNamaEV);
        ETtglrg = findViewById(R.id.ETTglReg);
        ETlokasiEV = findViewById(R.id.ETLokasiEV);
        EThariEV = findViewById(R.id.ETHariEV);
        ETmulaiEV = findViewById(R.id.ETMulaiEV);
        ETakhirEV = findViewById(R.id.ETAkhirEV);
        ETownerEV = findViewById(R.id.ETNamaOnwer);
        ETdeskripsiEV = findViewById(R.id.ETDeskEV);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarDE);
        setSupportActionBar(toolbar);

        progressDialog = new ProgressDialog(this);

        toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_ios_24);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent Back;
                Back = new Intent(AddEvent.this,PengolahanEvent.class);
                startActivity(Back);
                finish();
            }

        });

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
                namaEv = EtnamaEV.getText().toString();
                tglrgEV = ETtglrg.getText().toString();
                lokasiEV = ETlokasiEV.getText().toString();
                jadwalEV = EThariEV.getText().toString();
                mulaiEV = ETmulaiEV.getText().toString();
                akhirEV = ETakhirEV.getText().toString();
                ownerEV = ETownerEV.getText().toString();
                deskripsiEV = ETdeskripsiEV.getText().toString();
                Intent Next;
                Next = new Intent(AddEvent.this,AddEvent2.class);
                Next.putExtra("nama_ev", namaEv);
                Next.putExtra("tanggal_ev", tglrgEV);
                Next.putExtra("lokasi_ev", lokasiEV);
                Next.putExtra("jadwal_ev", jadwalEV);
                Next.putExtra("mulai_ev", mulaiEV);
                Next.putExtra("akhir_ev", akhirEV);
                Next.putExtra("owner_ev", ownerEV);
                Next.putExtra("deskripsi_ev", deskripsiEV);
                startActivity(Next);
            }
        });





    }
}