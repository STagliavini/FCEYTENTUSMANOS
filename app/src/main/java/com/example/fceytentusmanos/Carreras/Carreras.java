package com.example.fceytentusmanos.Carreras;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fceytentusmanos.R;
import com.example.fceytentusmanos.CarreraDetalle.CarreraDetalleViewModel;
import com.example.fceytentusmanos.CarreraDetalle.carreraDetalle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Carreras extends Fragment {

    private CarrerasViewModel mViewModel;

    public static Carreras newInstance() {
        return new Carreras();
    }
    private EditText et_buscador;
    private ListView lvCarr;
    private List<Map<String, Object>> Carreras;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        //Creamos una vista y la asociamos a la lista de la vista del fragmento de carreras
        View view= inflater.inflate(R.layout.carreras_fragment, container, false);
        lvCarr = (ListView)view.findViewById(R.id.lv_Carreras);
        String[] datos = {"Nombre", "Departamento","Duracion"};
        int[] vistas = {R.id.txt_nombreCarrera, R.id.txt_dptoCarrera, R.id.txt_duracion};
        final SimpleAdapter adapter = new SimpleAdapter(this.getContext(), listadoCarreras(), R.layout.itemcarrera, datos, vistas);
        lvCarr.setAdapter(adapter);
        //Ponemos a punto el buscador
        et_buscador = view.findViewById(R.id.et_Buscador);
        et_buscador.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        //Ponemos a punto la seleccion de la lista

        lvCarr.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> AdapterView, View view, int position, long id) {
                String listChoice = lvCarr.getItemAtPosition (position).toString();
                String s = String.valueOf(position);//aca tengo la posicion del toque
                int pos = Integer.parseInt(s);
                Map<String, Object> item=(Map<String, Object>)adapter.getItem(pos);
                String car=item.get("Nombre").toString();
                if(pos != 0 && pos !=9&& pos !=14&& pos !=18&& pos !=25){
                    Bundle bundle = new Bundle();
                    bundle.putInt("Posicion",pos);
                    bundle.putString("Nombrec",car);
                    carreraDetalle cd = new carreraDetalle();
                    cd.setArguments(bundle);
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.replace(R.id.nav_host_fragment, cd);
                    transaction.addToBackStack(null);
                    transaction.commit();
                }
            }
        });


        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(CarrerasViewModel.class);
    }

    //Rellenando la lista
    private List<Map<String, Object>> listadoCarreras() {
        Carreras = new ArrayList<Map<String, Object>>();
        Map<String, Object> item = new HashMap<String, Object>();

        //Separador de Ingenierias


        item = new HashMap<String, Object>();
        item.put("Nombre", "");
        item.put("Departamento", "\t\t\t\t\t\t\t\t\t\t\t\tCARRERAS DE INGENIERIA");
        item.put("Duracion", "");
        Carreras.add(item);


        //-------------------------

        item = new HashMap<String, Object>();
        item.put("Nombre", "Ingenieria civil");
        item.put("Departamento", "Departamento: "+"Dpto. de Estructura y Construcciones");
        item.put("Duracion", "Duracion: "+"5 años");
        Carreras.add(item);


        item = new HashMap<String, Object>();
        item.put("Nombre", "Ingenieria electromecanica");
        item.put("Departamento", "Departamento: "+ "Dpto. de Mecanica");
        item.put("Duracion", "Duracion: "+"5 años");
        Carreras.add(item);


        item = new HashMap<String, Object>();
        item.put("Nombre", "Ingenieria electronica");
        item.put("Departamento", "Departamento: "+"Dpto. de Electronica");
        item.put("Duracion", "Duracion: "+"5 años");
        Carreras.add(item);

        item = new HashMap<String, Object>();
        item.put("Nombre", "Ingenieria electrica");
        item.put("Departamento", "Departamento: "+ "Dpto. de Electricidad");
        item.put("Duracion", "Duracion: "+"5 años");
        Carreras.add(item);


        item = new HashMap<String, Object>();
        item.put("Nombre", "Ingenieria en Agrimensura");
        item.put("Departamento", "Departamento: "+"Dpto. de Geologia y Geotecnia");
        item.put("Duracion", "Duracion: "+"5 años");
        Carreras.add(item);


        item = new HashMap<String, Object>();
        item.put("Nombre", "Ingenieria hidraulica");
        item.put("Departamento", "Departamento: "+"Dpto. de Recursos Hidricos");
        item.put("Duracion", "Duracion: "+"5 años");
        Carreras.add(item);


        item = new HashMap<String, Object>();
        item.put("Nombre", "Ingenieria industrial");
        item.put("Departamento", "Departamento: "+"Dpto. de Estructura y Construcciones");
        item.put("Duracion", "Duracion: "+"5 años");
        Carreras.add(item);


        item = new HashMap<String, Object>();
        item.put("Nombre", "Ingenieria vial");
        item.put("Departamento", "Departamento: "+"Dpto. de Obras Viales");
        item.put("Duracion", "Duracion: "+"5 años");
        Carreras.add(item);

        //Separador de licenciaturas


        item = new HashMap<String, Object>();
        item.put("Nombre", "");
        item.put("Departamento", "\t\t\t\t\t\t\t\t\t\t\t\tCARRERAS DE LICENCIATURA");
        item.put("Duracion", "");
        Carreras.add(item);


        //-------------------------

        item = new HashMap<String, Object>();
        item.put("Nombre", "Licenciatura en hidrologia subterranea");
        item.put("Departamento", "Departamento: "+"Dpto. de Recursos Hidricos");
        item.put("Duracion", "Duracion: "+"4 años");
        Carreras.add(item);


        item = new HashMap<String, Object>();
        item.put("Nombre", "Licenciatura en sistemas de informacion");
        item.put("Departamento", "Departamento: "+"Dpto. de informatica");
        item.put("Duracion", "Duracion: "+"5 años");
        Carreras.add(item);

        item = new HashMap<String, Object>();
        item.put("Nombre", "Licenciatura en matematicas");
        item.put("Departamento", "Departamento: "+"Dpto. de matematica");
        item.put("Duracion", "Duracion: "+"4 años");
        Carreras.add(item);

        item = new HashMap<String, Object>();
        item.put("Nombre", "Licenciatura en matematicas (ciclo de complementacion)");
        item.put("Departamento", "Departamento: "+"Dpto. de matematica");
        item.put("Duracion", "Duracion: "+"2 años y medio");
        Carreras.add(item);

        //Separador de Profesorados

        item = new HashMap<String, Object>();
        item.put("Nombre", "");
        item.put("Departamento", "\t\t\t\t\t\t\t\t\t\t\t\tCARRERAS DE PROFESORADO");
        item.put("Duracion", "");
        Carreras.add(item);


        //-------------------------


        item = new HashMap<String, Object>();
        item.put("Nombre", "Profesorado de matematica");
        item.put("Departamento", "Departamento: "+"Dpto. de matematica");
        item.put("Duracion", "Duracion: "+"4 años");
        Carreras.add(item);

        item = new HashMap<String, Object>();
        item.put("Nombre", "Profesorado de informatica");
        item.put("Departamento", "Departamento: "+"Dpto. de Informatica");
        item.put("Duracion", "Duracion: "+"4 años");
        Carreras.add(item);

        item = new HashMap<String, Object>();
        item.put("Nombre", "Profesorado de fisica");
        item.put("Departamento", "Departamento: "+"Dpto. de matematica");
        item.put("Duracion", "Duracion: "+"4 años");
        Carreras.add(item);

        //Separador carreras de post-grado

        item = new HashMap<String, Object>();
        item.put("Nombre", "");
        item.put("Departamento", "\t\t\t\t\t\t\t\t\t\t\t\tCARRERAS DE POSTGRADO");
        item.put("Duracion", "");
        Carreras.add(item);


        //-------------------------

        item = new HashMap<String, Object>();
        item.put("Nombre", "Especializacion en enseñanza en ciencias exactas");
        item.put("Departamento", "Departamento: "+"Dpto. de Educación y Formación Complementaria");
        item.put("Duracion", "Duracion: "+"14 meses");
        Carreras.add(item);

        item = new HashMap<String, Object>();
        item.put("Nombre", "Especializacion en enseñanza en tecnologias");
        item.put("Departamento", "Departamento: "+"Dpto. de Educación y Formación Complementaria");
        item.put("Duracion", "Duracion: "+"14 meses");
        Carreras.add(item);

        item = new HashMap<String, Object>();
        item.put("Nombre", "Especialización en Hidráulica de Ríos");
        item.put("Departamento", "Departamento: "+"Dpto. de Educación y Formación Complementaria");
        item.put("Duracion", "Duracion: "+"500 horas");
        Carreras.add(item);

        item = new HashMap<String, Object>();
        item.put("Nombre", "Especialización en Higiene y Seguridad en el Trabajo");
        item.put("Departamento", "Departamento: "+"Dpto. de Educación y Formación Complementaria");
        item.put("Duracion", "Duracion: "+"544 horas");
        Carreras.add(item);

        item = new HashMap<String, Object>();
        item.put("Nombre", "Especialización en Informática Educativa");
        item.put("Departamento", "Departamento: "+"Dpto. de Educación y Formación Complementaria");
        item.put("Duracion", "Duracion: "+"1 año");
        Carreras.add(item);

        item = new HashMap<String, Object>();
        item.put("Nombre", "Maestría en Informática Educativa");
        item.put("Departamento", "Departamento: "+"Dpto. de Educación y Formación Complementaria");
        item.put("Duracion", "Duracion: "+"620 horas");
        Carreras.add(item);

        //Separador para carreras de pre-grado

        item = new HashMap<String, Object>();
        item.put("Nombre", "");
        item.put("Departamento", "\t\t\t\t\t\t\t\t\t\t\t\tCARRERAS DE PREGRADO");
        item.put("Duracion", "");
        Carreras.add(item);


        //-------------------------

        item = new HashMap<String, Object>();
        item.put("Nombre", "Programador universitario en informatica");
        item.put("Departamento", "Departamento: "+"Dpto. de informatica");
        item.put("Duracion", "Duracion: "+"3 años");
        Carreras.add(item);

        item = new HashMap<String, Object>();
        item.put("Nombre", "Tecnicatura universitaria vial");
        item.put("Departamento", "Departamento: "+"Dpto. de Obras Viales");
        item.put("Duracion", "Duracion: "+"2 años y medio");
        Carreras.add(item);

        item = new HashMap<String, Object>();
        item.put("Nombre", "Tecnicatura universitaria en construcciones");
        item.put("Departamento", "Departamento: "+"Dpto. de Estructuras y Construcciones");
        item.put("Duracion", "Duracion: "+"2 años y medio");
        Carreras.add(item);

        item = new HashMap<String, Object>();
        item.put("Nombre", "Tecnicatura universitaria en hidrologia subterranea");
        item.put("Departamento", "Departamento: "+"Dpto. de Recursos Hídricos");
        item.put("Duracion", "Duracion: "+"3 años");
        Carreras.add(item);

        item = new HashMap<String, Object>();
        item.put("Nombre", "Tecnicatura universitaria en organizacion y control de la produccion");
        item.put("Departamento", "Departamento: "+"Dpto. de Estructuras y Construcciones");
        item.put("Duracion", "Duracion: "+"3 años");
        Carreras.add(item);


        return Carreras;

    }
}
