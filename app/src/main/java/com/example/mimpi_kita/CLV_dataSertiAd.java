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

public class CLV_dataSertiAd extends ArrayAdapter<String> {

    private final Activity context;
    private ArrayList<String> vnama_serti;
    private ArrayList<String> vnama_user;
    private ArrayList<String> vnama_owner;
    private ArrayList<String> vgambar_serti;

    public CLV_dataSertiAd(Activity context, ArrayList<String> nama_serti, ArrayList<String> nama_user, ArrayList<String> nama_owner,ArrayList<String> gambar_serti)
    {
        super(context, R.layout.list_item_sertifikat,nama_serti);
        this.context          = context;
        this.vnama_serti         = nama_serti;
        this.vnama_user     = nama_user;
        this.vnama_owner     = nama_owner;
        this.vgambar_serti     = gambar_serti;



    }
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        //Load Custom Layout untuk list
        View rowView= inflater.inflate(R.layout.list_item_sertifikat, null, true);

        //Declarasi komponen
        TextView nama_serti       = rowView.findViewById(R.id.idTXTNama);
        TextView nama_user       = rowView.findViewById(R.id.idTXTemail);
        ImageView gambar_serti     = rowView.findViewById(R.id.idLVGambar);

        //Set Parameter Value sesuai widget textview
        nama_serti.setText(vnama_serti.get(position));
        nama_user.setText(vnama_user.get(position));

        if (vgambar_serti.get(position).equals(""))
        {
            Picasso.get().load("https://tkjbpnup.com/kelompok_9/mimpikita/image/noimage.png").into(gambar_serti);
        }
        else
        {
            Picasso.get().load("https://tkjbpnup.com/kelompok_9/mimpikita/image/"+vgambar_serti.get(position)).into(gambar_serti);
        }

        //Load the animation from the xml file and set it to the row
        //load animasi untuk listview
        Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.drop_from_top);
        animation.setDuration(500);
        rowView.startAnimation(animation);

        return rowView;
    }
}
