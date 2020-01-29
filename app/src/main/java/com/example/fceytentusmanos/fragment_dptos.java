package com.example.fceytentusmanos;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.fceytentusmanos.SQLITE.DataBaseHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class fragment_dptos extends Fragment {
    private List<Map<String, Object>> dptos;
    private DataBaseHelper helper;
    public fragment_dptos() {
        // Required empty public constructor
    }
@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
    View view= inflater.inflate(R.layout.fragment_dptos, container, false);
    String[] datos = {"nombre"};
    int[] vistas = {R.id.nombredpto};
    helper = new DataBaseHelper(getContext());
    SimpleAdapter adaptador =
            new SimpleAdapter(this.getContext(), listadoDptos(),
                    R.layout.item_dpto, datos, vistas);
    ListView list=(ListView)view.findViewById(R.id.listdpto);
    list.setAdapter(adaptador);
    list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Map<String, Object> item = dptos.get(position);
            Toast.makeText(getContext(), "Ha seleccionado " + item.get("nombre").toString(), Toast.LENGTH_LONG).show();
        }
    });
    return view;
    }
    private List<Map<String, Object>> listadoDptos() {
        dptos = new ArrayList<Map<String, Object>>();
        Map<String, Object> item = new HashMap<String, Object>();
        SQLiteDatabase db = helper.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT _id,nombre FROM autoridad",null);

        cursor.moveToFirst();

        for (int i=0; i < cursor.getCount(); i++){
            item = new HashMap<String, Object>();
            item.put("nombre", cursor.getString(1));
            dptos.add(item);
            cursor.moveToNext();
        }

        cursor.close();

        return dptos;
    }

}
