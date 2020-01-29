package com.example.fceytentusmanos.RedesSociales;

import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.fceytentusmanos.R;

public class RedesSociales extends Fragment {

    private RedesSocialesViewModel mViewModel;
    private Context C;
    public static RedesSociales newInstance() {
        return new RedesSociales();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.redes_sociales_fragment, container, false);
        C = getContext();
        LinearLayout layInstagram = (LinearLayout) view.findViewById(R.id.lay_instagram), layFacebook = (LinearLayout) view.findViewById(R.id.lay_facebook), layTwitter = (LinearLayout) view.findViewById(R.id.lay_twitter);
        layInstagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://www.instagram.com/fceytunse/");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
        layFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://www.facebook.com/Facultad-de-Ciencias-Exactas-y-Tecnolog%C3%ADas-UNSE-389558071235685/");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
        layTwitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://twitter.com/");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(RedesSocialesViewModel.class);
        // TODO: Use the ViewModel
    }
}
