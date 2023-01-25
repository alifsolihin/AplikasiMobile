package com.example.mimpi_kita;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ablanco.zoomy.TapListener;
import com.ablanco.zoomy.Zoomy;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class UserMenu extends AppCompatActivity {

    /*SharedPreferences sharedPreferences;

    private static final String NAMA_PREF = "my_pr";
    private static final String EMAIL_PREF = "email_pr";
    private static final String PASS_PREF = "pass_pr";*/

    Button BTNBack,BTNInfo, BTNEdit;
    String email, nama, nom, foto, alm, tgl, pass;
    TextView TVemail, TVnama, TVno;
    CircleImageView IVgambar;

    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_menu);

        BTNBack = findViewById(R.id.idBTNBack);
        BTNEdit = findViewById(R.id.idBTNedit);
        BTNInfo = findViewById(R.id.idBTNinfo);
        TVemail = findViewById(R.id.idTVemail);
        TVnama = findViewById(R.id.idTVnama);
        TVno = findViewById(R.id.idTVnomor);
        IVgambar = findViewById(R.id.IVgambar);

        email = getIntent().getStringExtra("email");
        nama = getIntent().getStringExtra("nama");
        nom = getIntent().getStringExtra("no_hp");
        pass = getIntent().getStringExtra("password");
        alm = getIntent().getStringExtra("alamat");
        tgl = getIntent().getStringExtra("tanggal_lahir");
        foto = getIntent().getExtras().getString("foto");

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

        TVemail.setText(email);
        TVnama.setText(nama);
        TVno.setText(nom);

        if (foto != null)
        {
            Picasso.get().load("https://tkjbpnup.com/kelompok_9/mimpikita/image/"+getIntent().getStringExtra("foto")).into(IVgambar);

        }
        else
        {
            Picasso.get().load("https://tkjbpnup.com/kelompok_9/mimpikita/image/noimage.png").into(IVgambar);
        }

        //sharedPreferences = getSharedPreferences(NAMA_PREF,MODE_PRIVATE);

        BTNBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.commit();
                finish();*/

                Intent Back;
                Back = new Intent(UserMenu.this,MainActivity.class);
                startActivity(Back);
            }
        });
        BTNInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Back;
                Back = new Intent(UserMenu.this,InfoMenu.class);
                startActivity(Back);
            }
        });

        BTNEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = getIntent().getStringExtra("email");
                nama = getIntent().getStringExtra("nama");
                nom = getIntent().getStringExtra("no_hp");
                foto = getIntent().getStringExtra("foto");
                pass = getIntent().getStringExtra("password");
                alm = getIntent().getStringExtra("alamat");
                tgl = getIntent().getStringExtra("tanggal_lahir");
                Intent Back;
                Back = new Intent(UserMenu.this,EditUserMenu.class);
                Back.putExtra("email", email);
                Back.putExtra("nama", nama);
                Back.putExtra("no_hp", nom);
                Back.putExtra("foto", foto);
                Back.putExtra("alamat", alm);
                Back.putExtra("password", pass);
                Back.putExtra("tanggal_lahir", tgl);
                Back.putExtra("foto", foto);
                startActivity(Back);
            }
        });
    }
}