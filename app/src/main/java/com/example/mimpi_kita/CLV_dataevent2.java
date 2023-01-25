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

public class CLV_dataevent2 extends ArrayAdapter<String> {

    private final Activity context;
    private ArrayList<String> vnama_ev;
    private ArrayList<String> vtanggal_ev;
    private ArrayList<String> vlokasi_ev;
    private ArrayList<String> vjadwal_ev;
    private ArrayList<String> vmulai_ev;
    private ArrayList<String> vakhir_ev;
    private ArrayList<String> vowner_ev;
    private ArrayList<String> vdeskripsi_ev;
    private ArrayList<String> vbenefit1_ev;
    private ArrayList<String> vbenefit2_ev;
    private ArrayList<String> vharga_ev;
    private ArrayList<String> vtiket_ev;
    private ArrayList<String> vjenis_ev;
    private ArrayList<String> vgambar_ev;

    public CLV_dataevent2(Activity context, ArrayList<String> nama_ev, ArrayList<String> tanggal_ev, ArrayList<String> lokasi_ev, ArrayList<String> jadwal_ev,
                          ArrayList<String> mulai_ev, ArrayList<String> akhir_ev, ArrayList<String> owner_ev, ArrayList<String> deskripsi_ev, ArrayList<String> benefit1_ev,
                          ArrayList<String> benefit2_ev, ArrayList<String> harga_ev, ArrayList<String> tiket_ev, ArrayList<String> jenis_ev, ArrayList<String> gambar_ev)
    {
        super(context, R.layout.list_item_pengolaan_item,nama_ev);
        this.context          = context;
        this.vnama_ev         = nama_ev;
        this.vtanggal_ev      = tanggal_ev;
        this.vlokasi_ev       = lokasi_ev;
        this.vjadwal_ev       = jadwal_ev;
        this.vmulai_ev        = mulai_ev;
        this.vakhir_ev        = akhir_ev;
        this.vowner_ev        = owner_ev;
        this.vdeskripsi_ev    = deskripsi_ev;
        this.vbenefit1_ev     = benefit1_ev;
        this.vbenefit2_ev     = benefit2_ev;
        this.vharga_ev        = harga_ev;
        this.vtiket_ev        = tiket_ev;
        this.vjenis_ev        = jenis_ev;
        this.vgambar_ev       = gambar_ev;

    }



    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        //Load Custom Layout untuk list
        View rowView= inflater.inflate(R.layout.list_item_pengolaan_item, null, true);

        //Declarasi komponen
        TextView nama_ev       = rowView.findViewById(R.id.idTXTNama);
        TextView tanggal_ev     =  rowView.findViewById(R.id.idTXTregist);
        TextView jadwal_ev      = rowView.findViewById(R.id.idTXTwaktu);
        TextView jenis_ev      = rowView.findViewById(R.id.idTXTJenis);
        ImageView gambar_ev     = rowView.findViewById(R.id.idLVGambar);

        //Set Parameter Value sesuai widget textview
        nama_ev.setText(vnama_ev.get(position));
        tanggal_ev.setText(vtanggal_ev.get(position));
        jenis_ev.setText(vjenis_ev.get(position));
        jadwal_ev.setText(vjadwal_ev.get(position));

        if (vgambar_ev.get(position).equals(""))
        {
            Picasso.get().load("https://tkjbpnup.com/kelompok_9/mimpikita/image/noimage.png").into(gambar_ev);
        }
        else
        {
            Picasso.get().load("https://tkjbpnup.com/kelompok_9/mimpikita/image/"+vgambar_ev.get(position)).into(gambar_ev);
        }

        //Load the animation from the xml file and set it to the row
        //load animasi untuk listview
        Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.drop_from_top);
        animation.setDuration(500);
        rowView.startAnimation(animation);

        return rowView;
    }
}
