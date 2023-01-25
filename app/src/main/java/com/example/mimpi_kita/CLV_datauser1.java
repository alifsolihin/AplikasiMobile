package com.example.mimpi_kita;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CLV_datauser1 extends ArrayAdapter<String> {

    private final Activity context;
    private ArrayList<String> vnama;
    private ArrayList<String> vemail;
    private ArrayList<String> vno_hp;
    private ArrayList<String> vfoto;
    private ArrayList<String> vpassword;
    private ArrayList<String> valamat;
    private ArrayList<String> vtanggal_lahir;
    private ArrayList<String> vlevel;


    public CLV_datauser1(Activity context, ArrayList<String> nama, ArrayList<String> email, ArrayList<String> no_hp, ArrayList<String> foto, ArrayList<String> password,
                         ArrayList<String> alamat, ArrayList<String> tanggal_lahir, ArrayList<String> level)
    {
        super(context, R.layout.list_item_pengolaan_user,nama);
        this.context        = context;
        this.vnama          = nama;
        this.vemail         = email;
        this.vno_hp         = no_hp;
        this.vfoto          = foto;
        this.vpassword      = password;
        this.valamat        = alamat;
        this.vtanggal_lahir = tanggal_lahir;
        this.vlevel = level;

    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        //Load Custom Layout untuk list
        View rowView= inflater.inflate(R.layout.list_item_pengolaan_user, null, true);

        //Declarasi komponen
        TextView nama       = rowView.findViewById(R.id.idTXTNama);
        TextView email        =  rowView.findViewById(R.id.idTXTemail);
        TextView level      = rowView.findViewById(R.id.idTXTlv);
        ImageView foto     = rowView.findViewById(R.id.idLVGambar);

        //Set Parameter Value sesuai widget textview
        nama.setText(vnama.get(position));
        email.setText(vemail.get(position));
        level.setText(vlevel.get(position));

        if (vfoto.get(position).equals(""))
        {
            Picasso.get().load("https://tkjbpnup.com/kelompok_9/mimpikita/image/noimage.png").into(foto);
        }
        else
        {
            Picasso.get().load("https://tkjbpnup.com/kelompok_9/mimpikita/image/"+vfoto.get(position)).into(foto);
        }

        //Load the animation from the xml file and set it to the row
        //load animasi untuk listview
        Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.drop_from_top);
        animation.setDuration(500);
        rowView.startAnimation(animation);

        return rowView;
    }
}
