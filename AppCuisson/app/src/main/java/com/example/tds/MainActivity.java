package com.example.tds;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.NumberPicker;
import android.widget.TimePicker;

import com.example.tds.outils.PageAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivity extends AppCompatActivity {

    TimePicker timePicker;
    MediaPlayer mp;

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
}