package com.example.fceytentusmanos;


import android.Manifest;
import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.QuickContactBadge;
import android.widget.Toast;


public class fragment_du extends Fragment {
    LinearLayout lagenda;
    LinearLayout lhorarios;
    LinearLayout lsiu;
    LinearLayout lcalendario;
    private static final int PERMISSION_STORAGE_CODE=1000;
    public fragment_du() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view=inflater.inflate(R.layout.fragment_du, container, false);
        lagenda=(LinearLayout)view.findViewById(R.id.lagen);
        lagenda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(),agenda.class));
            }
        });
        lhorarios=(LinearLayout) view.findViewById(R.id.lhorarios);
        lhorarios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(),horarios.class));
            }
        });
        lsiu=(LinearLayout) view.findViewById(R.id.lsiu);
        lsiu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(),siu.class));
            }
        });
        lcalendario=(LinearLayout) view.findViewById(R.id.lcalendario);
        lcalendario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
                    if(getContext().checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)==
                            PackageManager.PERMISSION_DENIED){
                            String[] permissions={Manifest.permission.WRITE_EXTERNAL_STORAGE};
                            requestPermissions(permissions,PERMISSION_STORAGE_CODE);
                    }
                    else{
                            iniciarDescarga();                    }
                }
                else {
                            iniciarDescarga();
                }
            }
        });
        return view;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch(requestCode){
            case PERMISSION_STORAGE_CODE:{
                if(grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    iniciarDescarga();
                }
                else{
                    Toast.makeText(getContext(),"Permiso Denegado...!!!",Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    public void iniciarDescarga(){
        DownloadManager.Request request=new DownloadManager.Request(Uri.parse("http://fce.unse.edu.ar/sites/default/files/ingreso/ResHCDN220-18-CalendarioAcademico2019.pdf"));
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE);
        request.setTitle("Calendario Academico");
        request.setDescription("Descargando Documento...");
        request.setAllowedOverMetered(true);
        request.setAllowedOverRoaming(true);
        request.allowScanningByMediaScanner();
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,"CalendarioAcademico.pdf");
        DownloadManager dm=(DownloadManager)getContext().getSystemService(Context.DOWNLOAD_SERVICE);
        dm.enqueue(request);
    }

}
