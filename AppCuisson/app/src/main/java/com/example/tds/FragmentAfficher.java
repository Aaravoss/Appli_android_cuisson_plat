package com.example.tds;

import androidx.fragment.app.Fragment;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

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

        plats = getListPlats();

        ListView lv = (ListView) view.findViewById(R.id.list_afficher);
        Toast.makeText(getActivity(), "listview réussi", Toast.LENGTH_LONG).show();

        lv.setAdapter(
                new ArrayAdapter<String>(
                        getContext(),
                        android.R.layout.simple_list_item_1,
                        plats
                )

        );

        Toast.makeText(getActivity(), "setadapter réussi", Toast.LENGTH_LONG).show();

        return view;
    }

    public ArrayList<String> getListPlats(){

        return MainActivity.getPlats();
    }
}
