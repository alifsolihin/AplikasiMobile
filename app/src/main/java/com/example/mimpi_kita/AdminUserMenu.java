package com.example.mimpi_kita;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ablanco.zoomy.TapListener;
import com.ablanco.zoomy.Zoomy;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class AdminUserMenu extends AppCompatActivity {

    Button BTNBack,BTNInfo,BTNEdit;
    String email, nama, nom, foto;
    TextView TVemail, TVnama, TVno;
    CircleImageView IVgambar;
    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_user_menu);

        BTNBack = findViewById(R.id.idBTNBack);
        BTNInfo = findViewById(R.id.idBTNinfo);
        TVemail = findViewById(R.id.idTVemail);
        BTNEdit = findViewById(R.id.idBTNedit);
        TVnama = findViewById(R.id.idTVnama);
        TVno = findViewById(R.id.idTVnomor);
        IVgambar = findViewById(R.id.IVgambar);

        email = getIntent().getStringExtra("email");
        nama = getIntent().getStringExtra("nama");
        nom = getIntent().getStringExtra("no_hp");
        foto = getIntent().getStringExtra("foto");

        TVemail.setText(email);
        TVnama.setText(nama);
        TVno.setText(nom);

        builder = new AlertDialog.Builder(this);

        Zoomy.Builder builder = new Zoomy.Builder(this)
                .target(IVgambar)
                .animateZooming(true)
                .enableImmersiveMode(false)
                .tapListener(new TapListener() {
                    @Override
                    public void onTap(View v) {

                    }
                });

        builder.register();

        if (foto != null)
        {
            Picasso.get().load("https://tkjbpnup.com/kelompok_9/mimpikita/image/"+getIntent().getStringExtra("foto")).into(IVgambar);

        }
        else
        {
            Picasso.get().load("https://tkjbpnup.com/kelompok_9/mimpikita/image/noimage.png").into(IVgambar);
        }

        BTNBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Back;
                Back = new Intent(AdminUserMenu.this,MainActivity.class);
                startActivity(Back);
            }
        });
        BTNInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Back;
                Back = new Intent(AdminUserMenu.this,InfoMenu.class);
                startActivity(Back);
            }
        });
        BTNEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                foto = getIntent().getStringExtra("foto");
                Intent Back;
                Back = new Intent(AdminUserMenu.this,EditUserMenu.class);
                Back.putExtra("foto", foto);
                startActivity(Back);
            }
        });
    }
}