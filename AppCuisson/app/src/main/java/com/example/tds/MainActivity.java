package com.example.tds;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.MenuInflater;
import android.widget.NumberPicker;
import android.widget.TimePicker;

import com.example.tds.outils.PageAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TimePicker timePicker;
    MediaPlayer mp;

    private static ArrayList<String> plats;

    private final String NOM_FICHIER = "donnees.txt";


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // variable non interactives UI fond sonore
        mp = MediaPlayer.create(this, R.raw.cake_cover_by_madstalker);
        mp.setLooping(true);
        mp.start();
        ViewPager2 gestionnairePagination = findViewById(R.id.activity_main_viewpager);
        TabLayout gestionnaireOnglet = findViewById(R.id.activity_main_tab_layout);
        plats = new ArrayList<String>();
        getListPlats(NOM_FICHIER);

        // associe au main_activity un adaptateur
        gestionnairePagination.setAdapter(new PageAdapter(this));

        String[] titreOnglet = {
                getString(R.string.onglet_en_cours_0),
                getString(R.string.onglet_en_cours_1)
        };

        new TabLayoutMediator(
                gestionnaireOnglet,
                gestionnairePagination,
                new TabLayoutMediator.TabConfigurationStrategy() {
                    @Override
                    public void onConfigureTab( TabLayout.Tab tab, int position) {
                        tab.setText(titreOnglet[position]);
                    }
                }
                ).attach();
    }

    /**
     * Comportement quand l'application est quittée (mais pas fermée):
     *   - mettre la musique en pause avec auto-sauvegarde en mémoire
     *     de la valeur de parcours du morceau courant (en millisecondes)
     */
    public void onPause() {
        super.onPause();

        //mise en pause de la musique
        mp.pause();
    }

    /**
     * Comportements quand l'utilisateur revient sur l'application déjà ouverte:
     *   - remettre la musique en marche grâce à l'auto-sauvegarde
     *     de la valeur de parcours du morceau courant
     */
    public void onResume() {
        super.onResume();

        //remise en route de la musique là où elle s'est arrêtée
        mp.start();
    }

    public void onDestroy() {
        super.onDestroy();
        File path = getFilesDir();

        try {
            FileOutputStream fileOutputStream = new FileOutputStream(new File(path, NOM_FICHIER));
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
            try (BufferedWriter writer = new BufferedWriter(outputStreamWriter)) {
                writer.write("bonjour");
                fileOutputStream.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getListPlats(String nomFich){

        String platLu;
        File path = getFilesDir();
        File readFrom = new File(path, nomFich);

        try {
            FileInputStream stream = new FileInputStream(readFrom);
            BufferedReader fichier = new BufferedReader(new InputStreamReader(stream));
            while ( (platLu = fichier.readLine()) != null ){
                plats.add(platLu);
            }
            fichier.close();
            stream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void addPlat(String plat){
        plats.add(plat);
    }

    public static ArrayList<String> getPlats(){
        return plats;
    }
}