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

    QuestionManager manager;
    private List<Question> questionList;

    Runnable questionRunnable = null;
    private int TIMER_MILLIS_QUESTION_DELAY = 3000;
    private int TIMER_MILLIS_ITERATION_START = 2000;

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

        Intent intent = getIntent();

        String player1 = intent.getExtras().getString("player1");
        String player2 = intent.getExtras().getString("player2");

        TV_player1.setText(player1);
        TV_player2.setText(player2);

         manager = new QuestionManager(GameActivity.this);
         questionList = manager.getQuestionList();


    }

    @Override
    protected void onStart() {
        super.onStart();



        Handler handler = new Handler();

        questionRunnable = new Runnable() {
            @Override
            public void run() {
                if(questionList.size() == 0){
                    //DO_CODE_LAST_QUESTION

                    handler.removeCallbacks(this);
                    //DO_OTHER_EXIT_CODE
                    if(score1 > score2)
                        TV_question1.setText("@string);
                }else{
                    //DO_CODE_QUESTION_ITERATION
                    Question randomQuestion = manager.getRandomQuestion(questionList);
                    TV_question1.setText(randomQuestion.getQuestion());
                    TV_question2.setText(randomQuestion.getQuestion());
                    handler.postDelayed(this,TIMER_MILLIS_QUESTION_DELAY);
                }
            }
        };
        handler.postDelayed(questionRunnable,TIMER_MILLIS_ITERATION_START);
    }
}
