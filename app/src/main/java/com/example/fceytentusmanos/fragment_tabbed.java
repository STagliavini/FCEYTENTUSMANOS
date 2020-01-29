package com.example.fceytentusmanos;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fceytentusmanos.SQLITE.DataBaseHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;


public class fragment_tabbed extends Fragment {
    private DataBaseHelper helper;
    public fragment_tabbed() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_tabbed, container, false);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this.getContext(),getChildFragmentManager());
        ViewPager viewPager = (ViewPager)view.findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = (TabLayout) view.findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
        /*helper = new DataBaseHelper(getContext());
        SQLiteDatabase db=helper.getWritableDatabase();
        insertIni("Departamento Académico de Agrimensura",db);
        insertIni("Departamento Académico de Educación y Formación Complementaria",db);
        insertIni("Departamento Académico de Dibujo",db);
        insertIni("Departamento Académico de Electricidad",db);*/
        return view;
    }
    /*public void insertIni(String nombre, SQLiteDatabase db){
        ContentValues valores = new ContentValues();
        valores.put("nombre", nombre);
        long resultadoInsert = db.insert("autoridad", null, valores);
    }*/

}
