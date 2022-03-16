package com.example.tds;

import androidx.fragment.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class FragmentAjouter extends Fragment {

    private TimePicker timePicker; // Permet le choix d'une durée
    private EditText choixNomPlat;
    private EditText choixTemperature;

    private Button boutonValider;
    private Button boutonEffacer;

    private static final String NOM_FICHIER = "donnees.txt";

    public FragmentAjouter(){
        //Required empty public constructor
    }

    /**
     * Factory de la classe FragmentAjouter
     * @return une nouvelle instance du fragment FragmentAjouter
     */
    public static FragmentAjouter newInstance(){
        FragmentAjouter fragment = new FragmentAjouter();
        return fragment;
    }

    @Override
    public void  onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.page_ajouter, container, false);

        timePicker = (TimePicker) view.findViewById(R.id.timePicker);
        timePicker.setIs24HourView(true);

        choixNomPlat = (EditText) view.findViewById(R.id.choixNomDuPlat);
        choixTemperature = (EditText) view.findViewById(R.id.choixTemperature);

        boutonValider = (Button) view.findViewById(R.id.boutonValider);
        boutonEffacer = (Button) view.findViewById(R.id.boutonEffacer);

        boutonEffacer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actionEffacer();
            }
        });

        boutonValider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actionValider();
            }
        });

        actionEffacer();

        return view;
    }


    /**
     * Action réalisée lors du clic sur le bouton Valider
     */
    public void actionValider() {

        File path = getContext().getFilesDir();

            try {
                FileOutputStream writer = new FileOutputStream(new File(path, NOM_FICHIER));
                writer.write(
                        "probleme".getBytes()
                );
                writer.close();
                actionEffacer();

            } catch (IOException e) {
                e.printStackTrace();
            }
    }

    /**
     * Action réalisée lors du clic sur le bouton Effacer
     */
    public void actionEffacer() {

        choixNomPlat.setText("");
        choixTemperature.setText("");
        timePicker.setCurrentHour(0);
        timePicker.setCurrentMinute(0);
    }
}
