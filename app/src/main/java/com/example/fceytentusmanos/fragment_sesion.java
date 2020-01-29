package com.example.fceytentusmanos;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;


public class fragment_sesion extends Fragment {
    Button btnSes;
    EditText nomUsu;
    EditText contUsu;
    CheckBox cRecor;
    SharedPreferences sp;
    SharedPreferences.Editor editor;
    public fragment_sesion() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_sesion, container, false);
        btnSes=(Button)view.findViewById(R.id.btnSes);
        nomUsu=(EditText)view.findViewById(R.id.nomUsu);
        contUsu=(EditText)view.findViewById(R.id.contUsu);
        cRecor=(CheckBox)view.findViewById(R.id.cRecor);
        sp=getActivity().getSharedPreferences("Login",Context.MODE_PRIVATE);
        editor=sp.edit();
        btnSes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
                Toast.makeText(getContext(),"Sesion Iniciada con Exito",Toast.LENGTH_SHORT).show();
            }
        });
        boolean crecord=sp.getBoolean("cRecor",true);
        if(crecord==true){
            nomUsu.setText(sp.getString("nomUsu",null));
            contUsu.setText(sp.getString("contUsu",null));
        }
        return view;
    }
    public void login(){
        String nomusu=nomUsu.getText().toString();
        String contu=contUsu.getText().toString();
        if(cRecor.isChecked()){
            editor.putBoolean("cRecor",true);
            editor.putString("nomUsu",nomusu);
            editor.putString("contUsu",contu);
            editor.commit();
        }
    }

}
