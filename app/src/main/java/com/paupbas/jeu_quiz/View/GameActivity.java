package com.paupbas.jeu_quiz.View;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import com.paupbas.jeu_quiz.R;

public class GameActivity extends AppCompatActivity {

    private TextView TV_player1;
    private Button BT_true1;
    private TextView TV_question1;

    private TextView TV_player2;
    private Button BT_true2;
    private TextView TV_question2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Reprend le constructeur de son parent
        super.onCreate(savedInstanceState);

        // Relit le java au xml
        setContentView(R.layout.activity_game);

        TV_player1 = findViewById(R.id.game_player1);
        BT_true1 = findViewById(R.id.game_buttonTrue1);
        TV_question1 = findViewById(R.id.game_question1);

        TV_player2 = findViewById(R.id.game_player2);
        BT_true2 = findViewById(R.id.game_buttonTrue2);
        TV_question2 = findViewById(R.id.game_question2);

        Intent intent = getIntent();

        String player1 = intent.getExtras().getString("player1");
        String player2 = intent.getExtras().getString("player2");

        TV_player1.setText(player1);
        TV_player2.setText(player2);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}
