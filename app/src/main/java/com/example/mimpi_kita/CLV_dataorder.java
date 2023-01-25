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

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class CLV_dataorder extends ArrayAdapter<String> {
    private final Activity context;
    private ArrayList<String> vnama_ev;
    private ArrayList<String> vlokasi_ev;
    private ArrayList<String> vjadwal_ev;
    private ArrayList<String> vmulai_ev;
    private ArrayList<String> vakhir_ev;
    private ArrayList<String> vnamaps_ev;
    private ArrayList<String> vharga_ev;
    private ArrayList<String> vtiket_ev;
    private ArrayList<String> vowner_ev;
    private ArrayList<String> vtotal_ev;
    private ArrayList<String> vwaktu_tr;
    private ArrayList<String> vkode_tr;

    public CLV_dataorder(Activity context, ArrayList<String> nama_ev, ArrayList<String> lokasi_ev, ArrayList<String> jadwal_ev, ArrayList<String> mulai_ev,
                         ArrayList<String> akhir_ev,ArrayList<String> namaps_ev,ArrayList<String> harga_ev,ArrayList<String> tiket_ev,ArrayList<String> owner_ev,ArrayList<String> total_ev,ArrayList<String> waktu_tr,ArrayList<String> kode_tr)
    {
        super(context, R.layout.list_item_order,nama_ev);
        this.context          = context;
        this.vnama_ev         = nama_ev;
        this.vlokasi_ev       = lokasi_ev;
        this.vjadwal_ev       = jadwal_ev;
        this.vmulai_ev        = mulai_ev;
        this.vakhir_ev        = akhir_ev;
        this.vnamaps_ev       = namaps_ev;
        this.vharga_ev        = harga_ev;
        this.vtiket_ev        = tiket_ev;
        this.vowner_ev        = owner_ev;
        this.vtotal_ev       = total_ev;
        this.vwaktu_tr        = waktu_tr;
        this.vkode_tr       = kode_tr;

    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        //Load Custom Layout untuk list
        View rowView= inflater.inflate(R.layout.list_item_order, null, true);

        //Declarasi komponen
        TextView nama_ev       = rowView.findViewById(R.id.idTXTNama);
        TextView tiket_ev    =  rowView.findViewById(R.id.idTXTregist);
        TextView jadwal_ev      = rowView.findViewById(R.id.idTXTwaktu);
        TextView total_ev     = rowView.findViewById(R.id.idTXTtotal);

        //Set Parameter Value sesuai widget textview
        nama_ev.setText(vnama_ev.get(position));
        tiket_ev.setText(vtiket_ev.get(position));
        jadwal_ev.setText(vjadwal_ev.get(position));
        //total_ev.setText(vtotal_ev.get(position));

        String resultRupiah = formatRupiah(Double.parseDouble(vtotal_ev.get(position)));
        total_ev.setText(resultRupiah);



        //Load the animation from the xml file and set it to the row
        //load animasi untuk listview
        Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.drop_from_top);
        animation.setDuration(500);
        rowView.startAnimation(animation);



        return rowView;
    }

    private String formatRupiah(Double number){
        Locale localeID = new Locale("in", "ID");
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
        return formatRupiah.format(number);
    }
}
