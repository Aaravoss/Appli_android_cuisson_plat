package com.example.tds;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.example.tds.fragments.PageAdapter;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // variable non interactives UI fond sonore
        mp = MediaPlayer.create(this, R.raw.cake_cover_by_madstalker);
        mp.setLooping(true);
        mp.start();

        //on récupère un accès sur le ViewPager et associe au main_activity un adaptateur
        ViewPager2 pager = findViewById(R.id.activity_main_viewpager);
        pager.setAdapter(new PageAdapter(this));
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