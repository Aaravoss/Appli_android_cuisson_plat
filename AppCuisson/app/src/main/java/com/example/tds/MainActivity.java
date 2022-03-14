package com.example.tds;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.example.tds.outils.PageAdapter;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText budgetTransport, budgetNuit, budgetRepas, budgetVisiteSortie, cout;
    private RadioGroup nbJours;
    private CheckBox repas2DernierJour;
    MediaPlayer mp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_afficher);

        // variable non interactives UI fond sonore
        mp = MediaPlayer.create(this, R.raw.cake_cover_by_madstalker);
        mp.setLooping(true);
        mp.start();

        // variables d'interraction UI

        //on associe un écouteur à chaque bouton
    }

    public void onClick(View view){

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

    /**
     * Affiche l'état de l'application par défaut
     */
    public void initAppli(){

    }
}