package com.example.fceytentusmanos.CarreraDetalle;

import androidx.appcompat.widget.ActionBarContainer;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fceytentusmanos.MapsActivity;
import com.example.fceytentusmanos.R;
import com.example.fceytentusmanos.Carreras.Obj_Carreras;

import static com.example.fceytentusmanos.R.id.tv_nombreCarreras;

public class carreraDetalle extends Fragment {

    private CarreraDetalleViewModel mViewModel;
    private Integer posicionCarrera;
    String nombre;
    private View view;
    private Button btnmapa;

    public static carreraDetalle newInstance() {
        return new carreraDetalle();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        posicionCarrera = getArguments().getInt("Posicion")-1;
        nombre=getArguments().getString("Nombrec");
        view= inflater.inflate(R.layout.carrera_detalle_fragment, container, false);
        btnmapa=(Button) view.findViewById(R.id.btnmap);
        btnmapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), MapsActivity.class));
            }
        });
        rellenarVista(view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(CarreraDetalleViewModel.class);
    }

    private void rellenarVista(View view){
        Obj_Carreras objetoCarrera = new Obj_Carreras("","","","","","","");
        Obj_Carreras[] listaCarreras = objetoCarrera.cargarArregloCarreras();
        /*Obj_Carreras miCarrera = listaCarreras[posicionCarrera];*/
        Obj_Carreras miCarrera=new Obj_Carreras("","","","","","","");
        for(int i=0;i<listaCarreras.length;i++){
            if(nombre.equalsIgnoreCase(listaCarreras[i].getNombreCarrera())){
                miCarrera=listaCarreras[i];
            }
        }
        TextView tvUbicacion = view.findViewById(R.id.tv_Ubicacion), tvContacto = view.findViewById(R.id.tv_Contacto), tvWeb = view.findViewById(R.id.tv_Web), tvDuracion = view.findViewById(R.id.tv_Duracion), tvNombre = view.findViewById(tv_nombreCarreras);
        EditText txtPerfilProfesional = view.findViewById(R.id.txtarea_PerfilProfesional);
        tvUbicacion.setText(miCarrera.getDireccion());
        tvContacto.setText(miCarrera.getTelefono());
        tvDuracion.setText(miCarrera.getDuracion());
        tvWeb.setText(miCarrera.getWebsite());
        txtPerfilProfesional.setText(miCarrera.getPerfilProfesional());
        tvNombre.setText(miCarrera.getNombreCarrera());
    }
}
