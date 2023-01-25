package com.example.mimpi_kita;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class ChatFragmentD extends Fragment {

    SimpleAdapter adapter;
    HashMap<String,String> map;
    ArrayList<HashMap<String,String>> mylist;
    String[] nama;
    String[] chat;
    String[] waktu;
    Integer[] gambar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_chat_d, container, false);
        ListView listView = (ListView) v.findViewById(R.id.idLV);
        CardView cardView = (CardView) v.findViewById(R.id.idCARDuser);

        nama = new String[]{
                "GB Peserta Pionner 22","GB Peserta English Competetion","GB Peserta MTK Challange"
        };
        chat = new String[]{
                "Kalau ada kendala tanyakan kaka aja 😊🙈","Okay Alright 🥰🥰🥰","Soalnya nanti ada kalkulus ya 😥😫🤯"

        };
        waktu = new String[]{
                "07:22","11:00","18.12"
        };
        gambar = new Integer[]{
                R.drawable.event1,R.drawable.event3,R.drawable.event5
        };

        mylist = new ArrayList<HashMap<String, String>>();
        for (int i=0; i<nama.length; i++) {
            map = new HashMap<String, String>();
            map.put("Nama", nama[i]);
            map.put("Chat", chat[i]);
            map.put("Waktu", waktu[i]);
            map.put("Gambar", gambar[i].toString());
            mylist.add(map);
        }
        adapter = new SimpleAdapter(getActivity(), mylist, R.layout.list_item2,
                new String[]{"Nama","Chat", "Waktu", "Gambar"}, new int[]{R.id.idTXTNama,
                (R.id.idTXTchat),(R.id.idTXTwaktu),(R.id.idLVGambar)});

        listView.setAdapter(adapter);

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent Back;
                Back = new Intent(getActivity(),UserMenu.class);
                startActivity(Back);

            }
        });

        return v;




    }
}