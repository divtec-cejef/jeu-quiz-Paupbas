package com.paupbas.jeu_quiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    private RelativeLayout settings;
    private Button BT_player;
    private EditText ET_addPlayer;
    private Button BT_closeSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Reprend le constructeur de son parent
        super.onCreate(savedInstanceState);

        // relit le java au xml
        setContentView(R.layout.activity_main);
        Toolbar mainToolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(mainToolbar);

        settings = findViewById(R.id.main_settings);

        BT_player = findViewById(R.id.main_button_addPlayer);
        ET_addPlayer = findViewById(R.id.main_textInput_player);
        BT_closeSettings = findViewById(R.id.settings_close);
    }

    @Override
    protected void onStart() {
        super.onStart();

        BT_closeSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                settings.setVisibility(View.INVISIBLE);
            }
        });

        BT_player.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ET_addPlayer.setVisibility(View.VISIBLE);
            }
        });
    }
        public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.action_settings:
                settings.setVisibility(View.VISIBLE);
                break;
            case R.id.action_question:
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }
}