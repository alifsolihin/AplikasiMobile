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

public class CLV_dataevent1 extends ArrayAdapter<String> {

    private final Activity context;
    private ArrayList<String> vnama;
    private ArrayList<String> vtgl;
    private ArrayList<String> vwaktu;
    private ArrayList<String> vfoto;

    public CLV_dataevent1(Activity context, ArrayList<String> nama, ArrayList<String> tgl, ArrayList<String> waktu, ArrayList<String> foto)
    {
        super(context, R.layout.list_item,nama);
        this.context    = context;
        this.vnama        = nama;
        this.vtgl         = tgl;
        this.vwaktu       = waktu;
        this.vfoto       = foto;

    }



    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        //Load Custom Layout untuk list
        View rowView= inflater.inflate(R.layout.list_item, null, true);

        //Declarasi komponen
        TextView nama       = rowView.findViewById(R.id.idTXTNama);
        TextView tgl        =  rowView.findViewById(R.id.idTXTregist);
        TextView waktu      = rowView.findViewById(R.id.idTXTwaktu);
        ImageView foto     = rowView.findViewById(R.id.idLVGambar);

        //Set Parameter Value sesuai widget textview
        nama.setText(vnama.get(position));
        tgl.setText(vtgl.get(position));
        waktu.setText(vwaktu.get(position));

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
