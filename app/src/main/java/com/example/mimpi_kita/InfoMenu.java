package com.example.mimpi_kita;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.ablanco.zoomy.TapListener;
import com.ablanco.zoomy.Zoomy;

import de.hdodenhof.circleimageview.CircleImageView;

public class InfoMenu extends AppCompatActivity {

    CircleImageView circleImageView1, circleImageView2;

    AlertDialog.Builder builder, builder2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_menu);

        circleImageView1 = findViewById(R.id.idfoto1);
        circleImageView2 = findViewById(R.id.idfoto2);


        builder = new AlertDialog.Builder(this);
        builder2 = new AlertDialog.Builder(this);

        Zoomy.Builder builder = new Zoomy.Builder(this)
                .target(circleImageView1)
                .animateZooming(true)
                .enableImmersiveMode(false)
                .tapListener(new TapListener() {
                    @Override
                    public void onTap(View v) {

                    }
                });

        builder.register();

        Zoomy.Builder builder2 = new Zoomy.Builder(this)
                .target(circleImageView2)
                .animateZooming(true)
                .enableImmersiveMode(false)
                .tapListener(new TapListener() {
                    @Override
                    public void onTap(View v) {

                    }
                });

        builder2.register();

    }
}