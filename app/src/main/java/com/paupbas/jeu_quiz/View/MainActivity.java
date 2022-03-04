package com.paupbas.jeu_quiz.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Switch;

import com.google.android.material.switchmaterial.SwitchMaterial;
import com.google.android.material.textfield.TextInputLayout;
import com.paupbas.jeu_quiz.R;

public class MainActivity extends AppCompatActivity {

    //Settings
    private RelativeLayout settings;
    private Button BT_closeSettings;
    private SwitchMaterial SW_nightMode;

    //Question
    private RelativeLayout questions;
    private Button BT_closeQuestions;

    //Accueil
    private Button BT_play;
    private TextInputLayout TIL_playerLayout1;
    private EditText ET_Player1;
    private TextInputLayout TIL_playerLayout2;
    private EditText ET_Player2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Reprend le constructeur de son parent
        super.onCreate(savedInstanceState);

        // Relit le java au xml
        setContentView(R.layout.activity_main);
        Toolbar mainToolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(mainToolbar);

        //Accueil
        BT_play = findViewById(R.id.main_button_play);
        TIL_playerLayout1 = findViewById(R.id.main_textInputLayout1);
        ET_Player1 = findViewById(R.id.main_textInput_player1);
        TIL_playerLayout2 = findViewById(R.id.main_textInputLayout2);
        ET_Player2 = findViewById(R.id.main_textInput_player2);

        //Settings
        settings = findViewById(R.id.main_settings);
        SW_nightMode = findViewById(R.id.settings_switch_night);
        BT_closeSettings = findViewById(R.id.settings_close);

        //Question
        questions = findViewById(R.id.main_questions);
        BT_closeQuestions = findViewById(R.id.questions_close);
    }

    @Override
    protected void onStart() {
        super.onStart();

        /**
         * Affiche un deuxième joueur lors de la saisi du premier
         */
        ET_Player1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!ET_Player1.getText().toString().equals(""))
                    TIL_playerLayout2.setVisibility(View.VISIBLE);
            }
            @Override
            public void afterTextChanged(Editable editable) {
                if (!ET_Player1.getText().toString().isEmpty() && !ET_Player2.getText().toString().isEmpty())
                    BT_play.setVisibility(View.VISIBLE);
                else
                    BT_play.setVisibility(View.GONE);
            }
        });

        /**
         * Affiche le bouton jouer lors de la saisi du deuxième joueur
         */
        ET_Player2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                if (!ET_Player1.getText().toString().isEmpty() && !ET_Player2.getText().toString().isEmpty())
                    BT_play.setVisibility(View.VISIBLE);
                else
                    BT_play.setVisibility(View.GONE);
            }
        });

        /**
         * Ouvre la page de jeu et fournit les noms des joueurs
         */
        BT_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Intent intent = new Intent(getApplicationContext(), GameActivity.class);
                    intent.putExtra("player1", ET_Player1.getText().toString());
                    intent.putExtra("player2", ET_Player2.getText().toString());
                    startActivity(intent);
            }
        });

        /**
         * Active le style nuit ou pas
         */
        SW_nightMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                }
                recreate();

                if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
                    setTheme(R.style.Theme_jeu_night);
                } else {
                    setTheme(R.style.Theme_jeu_light);
                }
                setContentView(R.layout.activity_main);
            }
        });

        BT_closeSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                settings.setVisibility(View.GONE);
                ET_Player1.setVisibility(View.VISIBLE);
                ET_Player2.setVisibility(View.VISIBLE);
            }
        });

        BT_closeQuestions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                questions.setVisibility(View.GONE);
                ET_Player1.setVisibility(View.VISIBLE);
                ET_Player2.setVisibility(View.VISIBLE);
            }
        });
    }

    //Crée le menu dans la toolbar
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    //Défini les actions dans le menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.action_settings:
                settings.setVisibility(View.VISIBLE);
                ET_Player1.setVisibility(View.INVISIBLE);
                ET_Player2.setVisibility(View.INVISIBLE);
                BT_play.setVisibility(View.INVISIBLE);
                break;
            case R.id.action_questions:
                questions.setVisibility(View.VISIBLE);
                ET_Player1.setVisibility(View.INVISIBLE);
                ET_Player2.setVisibility(View.INVISIBLE);
                BT_play.setVisibility(View.INVISIBLE);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }
}