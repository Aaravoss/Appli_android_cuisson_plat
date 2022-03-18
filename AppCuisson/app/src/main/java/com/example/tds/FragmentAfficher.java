package com.example.tds;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tds.outils.OutilCuisson;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class FragmentAfficher extends Fragment {

    private static final String NOM_FICHIER = "donnees.txt";

    /** Liste des plats présentés par l'application */
    private ArrayList<String> plats;

    public FragmentAfficher(){
        //Required empty public constructor
    }

    /**
     * Factory de la classe FragmentAfficher
     * @return une nouvelle instance du fragment FragmentAfficher
     */
    public static FragmentAfficher newInstance(){
        FragmentAfficher fragment = new FragmentAfficher();
        return fragment;
    }

    @Override
    public void  onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        View view = inflater.inflate(R.layout.page_afficher, container, false);

        setHasOptionsMenu(true);

        plats = getListPlats();

        ListView lv = (ListView) view.findViewById(R.id.list_afficher);
        lv.setAdapter(
                new ArrayAdapter<String>(
                        getContext(),
                        R.layout.ligne_liste,
                        plats
                )
        );

        registerForContextMenu(lv);

        return view;
    }

    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {

        getActivity().getMenuInflater().inflate(R.menu.menu_contextuel, menu);
    }

    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch(item.getItemId()) {
            case R.id.option_thermostat:
                afficherThermostat(info.position);
                break;
            case R.id.option_supprimer:
                MainActivity.removePlat(info.position);
                break;
            case R.id.option_annuler:
                break;
        }
        return super.onContextItemSelected(item);
    }

    public void afficherThermostat(int position){

        View boiteThermostat = getLayoutInflater().inflate(R.layout.thermostat, null);

        TextView text = boiteThermostat.findViewById(R.id.dialog_thermostat);
        String nomPlat = OutilCuisson.extrairePlat(plats.get(position));
        int temperature = OutilCuisson.extraireTemperature(plats.get(position));


        text.setText(
                getString(R.string.text_thermostat1) + " " +  nomPlat + "\n\n"
                + getString(R.string.text_thermostat2) + " " + temperature
                + getString(R.string.text_thermostat3) + " " + OutilCuisson.thermostat(temperature)
        );

        new AlertDialog.Builder(getContext())
                .setTitle(getResources().getString(R.string.titre_thermostat))
                .setView(boiteThermostat)
                .setNegativeButton(getResources().getString(R.string.retour), null)
                .show();
    }

    public ArrayList<String> getListPlats(){

        return MainActivity.getPlats();
    }
}
