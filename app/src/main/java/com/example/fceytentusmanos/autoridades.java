package com.example.fceytentusmanos;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import android.app.ListActivity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class autoridades extends Fragment{

    private List<Map<String, Object>> autoridades;
    public autoridades() {

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_autoridades, container, false);
        String[] datos = {"foto", "cargo","nombre", "telefono", "mail"};
        int[] vistas = {R.id.foto, R.id.cargo,R.id.nombre, R.id.capital, R.id.mail};

        SimpleAdapter adaptador =
                new SimpleAdapter(this.getContext(), listadoAutoridades(),
                        R.layout.item_auto, datos, vistas);
        ListView list=(ListView)view.findViewById(R.id.list);
        list.setAdapter(adaptador);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Map<String, Object> item = autoridades.get(position);
                Bundle extras=new Bundle();
                extras.putString("nombre",item.get("nombre").toString());
                Intent intencion=new Intent("ar.com.example.fceytentusmanos.agregarContacto");
                intencion.putExtras(extras);
                startActivity(intencion);
            }
        });
        return view;
    }
    private List<Map<String, Object>> listadoAutoridades() {
        autoridades = new ArrayList<Map<String, Object>>();
        Map<String, Object> item = new HashMap<String, Object>();
        item.put("foto", R.drawable.basualdo);
        item.put("cargo","Decano");
        item.put("nombre", "Ing. Pedro Juvenal Basualdo");
        item.put("telefono", "Telefono: 385 450 9560  Int 1831");
        item.put("mail", "basualdo@unse.edu.ar");
        autoridades.add(item);

        item = new HashMap<String, Object>();
        item.put("foto", R.drawable.juarez);
        item.put("cargo","Vicedecano");
        item.put("nombre", "Dr. Ing. Carlos Ramón Juárez");
        item.put("telefono", "Telefono: 385 450 9560  Int 1842");
        item.put("mail", "cjuarez@unse.edu.ar");
        autoridades.add(item);

        item = new HashMap<String, Object>();
        item.put("foto", R.drawable.mellano);
        item.put("cargo","Secretaría Académica");
        item.put("nombre", "Dra. María Fernanda Mellano");
        item.put("telefono", "Telefono: 385 450 9560  Int 1840");
        item.put("mail", "fermellano@unse.edu.ar");
        autoridades.add(item);

        item = new HashMap<String, Object>();
        item.put("foto", R.drawable.gallardo);
        item.put("cargo","Secretaría de Administración");
        item.put("nombre", "Lic. Juan Carlos Coronel Gallardo");
        item.put("telefono", "Telefono: 385 450 9560   Int 1833");
        item.put("mail", "jccgall@unse.edu.ar");
        autoridades.add(item);

        item = new HashMap<String, Object>();
        item.put("foto", R.drawable.benac);
        item.put("cargo","Secretaría de Ciencia, Técnica y Postgrado");
        item.put("nombre", "Dra. María José Benac");
        item.put("telefono", "Telefono: 385 450 9560  Int 1855");
        item.put("mail", "mjbenac@unse.edu.ar");
        autoridades.add(item);

        return autoridades;
    }
}
