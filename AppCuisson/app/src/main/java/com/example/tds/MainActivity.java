package com.example.tds;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.example.appcuisson.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText budgetTransport, budgetNuit, budgetRepas, budgetVisiteSortie, cout;
    private RadioGroup nbJours;
    private CheckBox repas2DernierJour;
    MediaPlayer mp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // variable non interactives UI fond sonore
        mp = MediaPlayer.create(this, R.raw.cake_cover_by_madstalker);
        mp.setLooping(true);
        mp.start();

        // variables d'interraction UI
        budgetTransport = findViewById(R.id.saisieTransport);
        budgetNuit = findViewById(R.id.saisieNuit);
        budgetRepas = findViewById(R.id.saisieRepas);
        budgetVisiteSortie = findViewById(R.id.saisieVisiteSortie);
        cout = findViewById(R.id.affichCout);
        nbJours = findViewById(R.id.saisieNbJours);
        repas2DernierJour = findViewById(R.id.saisieRepas2);
        Button boutonCalculer = findViewById(R.id.calculer);
        Button boutonAnnuler = findViewById(R.id.annuler);

        //on associe un écouteur à chaque bouton
        boutonCalculer.setOnClickListener(this);
        boutonAnnuler.setOnClickListener(this);

        //on enlève le cache sur les EditText de type numberPassword
        budgetTransport.setTransformationMethod(null);
        budgetNuit.setTransformationMethod(null);
        budgetRepas.setTransformationMethod(null);
        budgetVisiteSortie.setTransformationMethod(null);
    }

    public void onClick(View view){

        if(view.getId() == R.id.calculer ){
            if(     budgetTransport.getText().toString().trim().equals("")
                    ||budgetNuit.getText().toString().trim().equals("")
                    ||budgetRepas.getText().toString().trim().equals("")
                    ||budgetVisiteSortie.getText().toString().trim().equals("")
                    || (Integer.parseInt(String.valueOf(budgetTransport.getText())) >= 0
                    && Integer.parseInt(String.valueOf(budgetNuit.getText())) >= 0
                    && Integer.parseInt(String.valueOf(budgetRepas.getText())) >= 0
                    && Integer.parseInt(String.valueOf(budgetVisiteSortie.getText())) >= 0)
            ) {
                calculerReduc();
            }
        }

        if(view.getId() == R.id.annuler ){
            initAppli();
        }
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
     * Affiche les résultats du calcul
     */
    @SuppressLint("SetTextI18n")
    public void calculerReduc(){

        int pxTransport, pxNuit, pxRepas, pxVisiteSortie, nbNuits, dernierRepas, tarifFinal;

        //calcul des différents éléments
        if( budgetTransport.getText().toString().trim().equals("") ) {
            pxTransport = 0;
        } else {
            pxTransport = Integer.parseInt(String.valueOf(budgetTransport.getText()));
        }
        if( budgetNuit.getText().toString().trim().equals("") ) {
            pxNuit = 0;
        } else {
            pxNuit = Integer.parseInt(String.valueOf(budgetNuit.getText()));
        }
        if( budgetRepas.getText().toString().trim().equals("") ) {
            pxRepas = 0;
        } else {
            pxRepas = Integer.parseInt(String.valueOf(budgetRepas.getText()));
        }
        if( budgetVisiteSortie.getText().toString().trim().equals("") ) {
            pxVisiteSortie = 0;
        } else {
            pxVisiteSortie = Integer.parseInt(String.valueOf(budgetVisiteSortie.getText()));
        }
        if( (nbNuits = nbJours.getCheckedRadioButtonId()) == R.id.jours_2 ) {
            nbNuits = 1;
        } else if(nbNuits == R.id.jours_3) {
            nbNuits = 2;
        } else if(nbNuits == R.id.jours_4) {
            nbNuits = 3;
        } else {
            nbNuits = 4;
        }

        dernierRepas = repas2DernierJour.isChecked() ? 1 : 0;

        //calcul du tarif final
        tarifFinal = (nbNuits*2+1+dernierRepas)*pxRepas + (nbNuits+1)*pxVisiteSortie + nbNuits * pxNuit + pxTransport;

        cout.setText(tarifFinal + " " + getString(R.string.euros));

    }

    /**
     * Affiche l'état de l'application par défaut
     */
    public void initAppli(){

        budgetTransport.setText("");
        budgetNuit.setText("");
        budgetRepas.setText("");
        budgetVisiteSortie.setText("");
        cout.setText("");
        nbJours.clearCheck();
        nbJours.check(R.id.jours_2);
        repas2DernierJour.setChecked(false);
    }
}