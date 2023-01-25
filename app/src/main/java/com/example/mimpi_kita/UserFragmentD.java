package com.example.mimpi_kita;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class UserFragmentD extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_user_d, container, false);
        CardView cardView = (CardView) v.findViewById(R.id.idCARDuser);
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