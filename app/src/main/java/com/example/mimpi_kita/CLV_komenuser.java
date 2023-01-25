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

public class CLV_komenuser extends ArrayAdapter<String> {

    private final Activity context;
    private ArrayList<String> vnama_ev;
    private ArrayList<String> vnamaps_ev;
    private ArrayList<String> vowner_ev;
    private ArrayList<String> vkomen_ev;
    private ArrayList<String> vwaktu_km;

    public CLV_komenuser(Activity context, ArrayList<String> nama_ev, ArrayList<String> namaps_ev, ArrayList<String> owner_ev,
                         ArrayList<String> komen_ev, ArrayList<String> waktu_km)
    {
        super(context, R.layout.list_item_komen_user,nama_ev);
        this.context          = context;
        this.vnama_ev         = nama_ev;
        this.vnamaps_ev    = namaps_ev;
        this.vowner_ev    = owner_ev;
        this.vkomen_ev    = komen_ev;
        this.vwaktu_km    = waktu_km;

    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        //Load Custom Layout untuk list
        View rowView= inflater.inflate(R.layout.list_item_komen_user, null, true);

        TextView nama       = rowView.findViewById(R.id.idTXTNama);
        TextView waktu_ev       =  rowView.findViewById(R.id.idTXTregist);
        TextView nama_ev      = rowView.findViewById(R.id.idTXTwaktu);
        TextView komen_ev     = rowView.findViewById(R.id.idTXTtotal);

        //Set Parameter Value sesuai widget textview
        nama_ev.setText(vnama_ev.get(position));
        waktu_ev.setText(vwaktu_km.get(position));
        nama.setText(vnamaps_ev.get(position));
        komen_ev.setText(vkomen_ev.get(position));


        //Load the animation from the xml file and set it to the row
        //load animasi untuk listview
        Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.drop_from_top);
        animation.setDuration(500);
        rowView.startAnimation(animation);

        return rowView;
    }
}
