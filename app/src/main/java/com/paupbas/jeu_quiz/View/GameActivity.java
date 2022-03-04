package com.paupbas.jeu_quiz.View;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.paupbas.jeu_quiz.Controllers.QuestionManager;
import com.paupbas.jeu_quiz.Models.Question;
import com.paupbas.jeu_quiz.R;

import java.util.ArrayList;
import java.util.List;

public class GameActivity extends AppCompatActivity {

    //Player1
    private TextView TV_player1;
    private Button BT_true1;
    private TextView TV_question1;
    private TextView TV_score1;
    private int score1;

    //Player2
    private TextView TV_player2;
    private Button BT_true2;
    private TextView TV_question2;
    private TextView TV_score2;
    private int score2;

    //Interface
    private Button BT_replay;
    private Button BT_menu;

    //Question
    QuestionManager manager;
    private List<Question> questionList;
    private Question questionActuel;

    Runnable questionRunnable = null;

    //Global
    private final int TIMER_MILLIS_QUESTION_DELAY = 2000;
    private final int TIMER_MILLIS_ITERATION_START = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Reprend le constructeur de son parent
        super.onCreate(savedInstanceState);

        // Relit le java au xml
        setContentView(R.layout.activity_game);

        //Player1
        TV_player1     = findViewById(R.id.game_player1);
        BT_true1       = findViewById(R.id.game_buttonTrue1);
        TV_question1   = findViewById(R.id.game_question1);
        TV_score1      = findViewById(R.id.game_score1);

        //Player2
        TV_player2     = findViewById(R.id.game_player2);
        BT_true2       = findViewById(R.id.game_buttonTrue2);
        TV_question2   = findViewById(R.id.game_question2);
        TV_score2      = findViewById(R.id.game_score2);

        //Interface
        BT_replay      = findViewById(R.id.game_replay);
        BT_menu        = findViewById(R.id.game_menu);

        Intent intent = getIntent();

        //Récupère les noms des joueurs
        String player1 = intent.getExtras().getString("player1");
        String player2 = intent.getExtras().getString("player2");

        //Affiche les noms des joueurs
        TV_player1.setText(player1);
        TV_player2.setText(player2);

        manager = new QuestionManager(GameActivity.this);
        questionList = manager.getQuestionList();

        /**
         * Réinitialise les champs pour rejouer une partie
         */
        BT_replay.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 score1 = 0;
                 score2 = 0;

                 TV_score1.setText(Integer.toString(score1));
                 TV_score2.setText(Integer.toString(score2));

                 TV_question1.setTextSize(R.dimen.main_text_size);
                 TV_question1.setTextColor(getColor(R.color.black));
                 TV_question2.setTextSize(R.dimen.main_text_size);
                 TV_question2.setTextColor(getColor(R.color.black));

                 BT_menu.setVisibility(View.GONE);
                 BT_replay.setVisibility(View.GONE);

                 manager = new QuestionManager(GameActivity.this);
                 questionList = manager.getQuestionList();

                 timerQuestion();
             }
         });

        /**
         * Retourne a la page d'accueil
         */
         BT_menu.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                 startActivity(intent);
             }
         });

        /**
         * Bouton vrai du joueur 1
         */
        BT_true1.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 BT_true2.setClickable(false);
                 if (questionActuel.getReponse() == 1)
                     score1++;
                 else
                    if(score1 != 0 )
                        score1--;

                 TV_score1.setText(Integer.toString(score1));
                 BT_true1.setClickable(false);
             }
         });

        /**
         * Bouton vrai du joueur 2
         */
         BT_true2.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 BT_true1.setClickable(false);
                 if (questionActuel.getReponse() == 1)
                     score2++;
                 else
                    if(score2 != 0 )
                        score2--;

                 TV_score2.setText(Integer.toString(score2));
                 BT_true2.setClickable(false);
             }
         });
    }

    @Override
    protected void onStart() {
        super.onStart();

        timerQuestion();
    }

    /**
     * Affiche les question tirées aléatoirement dans la base de données
     */
    private void timerQuestion() {

     Handler handler = new Handler();
     questionRunnable = new Runnable() {
         //Timer des questions
         @Override
         public void run() {
             if(questionList.size() == 0){
                 handler.removeCallbacks(this);
                 //A la fin du timer
                 //Bloque les boutons
                 BT_true1.setClickable(false);
                 BT_true2.setClickable(false);

                 //Affiche les résultats
                 if(score1 > score2) {
                     TV_question1.setText(getString(R.string.game_result_win));
                     TV_question1.setTextColor(getColor(R.color.Green));
                     TV_question2.setText(R.string.game_result_loose);
                     TV_question2.setTextColor(getColor(R.color.Red));
                 } else if(score2 > score1) {
                     TV_question2.setText(getString(R.string.game_result_win));
                     TV_question2.setTextColor(getColor(R.color.Green));
                     TV_question1.setText(R.string.game_result_loose);
                     TV_question1.setTextColor(getColor(R.color.Red));
                 } else {
                     TV_question1.setText(getString(R.string.game_result_egalite));
                     TV_question2.setText(R.string.game_result_egalite);
                 }
                 TV_question1.setTextSize(40);
                 TV_question2.setTextSize(40);

                 //Affiche les boutons d'interface
                 BT_replay.setVisibility(View.VISIBLE);
                 BT_menu.setVisibility(View.VISIBLE);

             }else{
                 //Code pour chaque itération du timer

                 //Affiche une question aléatoire
                 questionActuel = manager.getRandomQuestion(questionList);
                 TV_question1.setText(questionActuel.getQuestion());
                 TV_question2.setText(questionActuel.getQuestion());

                 //Active les boutons vrai
                 BT_true1.setClickable(true);
                 BT_true2.setClickable(true);

                 handler.postDelayed(this,TIMER_MILLIS_QUESTION_DELAY);
             }
         }
     };
        handler.postDelayed(questionRunnable,TIMER_MILLIS_ITERATION_START);
    }
}
