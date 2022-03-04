package com.paupbas.jeu_quiz.Controllers;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.paupbas.jeu_quiz.Models.Question;
import com.paupbas.jeu_quiz.Models.SpeedQuizSQLiteOpenHelper;

import java.util.List;
import java.util.Random;

import java.util.ArrayList;
import java.util.Random;

public class QuestionManager {
    private List<Question> questionList;

    public QuestionManager(Context context) {
        questionList = initQuestionList(context);
    }

    public List<Question> getQuestionList() {
        return questionList;
    }

    /**
     * @param questionList liste de question
     * @return un index d'une question
     */
    public static int getIndexQuestion(List<Question> questionList) {
        Random rand = new Random();
        return rand.nextInt(questionList.size());
    }

    /**
     * Donne une question aléatoire de la liste et la supprime
     * @param questionList liste de question récupérer depuis la base de données
     * @return Une question
     */
    public Question getRandomQuestion(List<Question> questionList){
        int randomIndex = getIndexQuestion(questionList);
        Question question = questionList.get(randomIndex);

        questionList.remove(randomIndex);
        return question;
    }

    public void addQuestion() {
        questionList.add(new Question("La première guerre mondial c'est terminée en 1945", 0));
        questionList.add(new Question("Alan Turing est le créateur de la machine énigma", 0));
        questionList.add(new Question("La Suisse n'est pas dans l'union européen", 1));
        questionList.add(new Question("Marios bros est le premier jeu de mario", 1));
        questionList.add(new Question("La Playstation 2 est la console la plus vendu", 1));
    }

    /**
     * Charge une liste de question depuis la DB.
     * @param context Le contexte de l'application pour passer la query
     * @return Une arraylist charger de Question
     */
    private ArrayList<Question> initQuestionList(Context context){
        ArrayList<Question> listQuestion = new ArrayList<>();
        SpeedQuizSQLiteOpenHelper helper = new SpeedQuizSQLiteOpenHelper(context);
        SQLiteDatabase db = helper.getReadableDatabase();

        Cursor cursor = db.query(true,"quiz",new String[]{"idQuiz","question","reponse"},null,null,null,null,"idquiz",null);

        while(cursor.moveToNext()){
            listQuestion.add(new Question(cursor));
        }
        cursor.close();
        db.close();

        return listQuestion;
    }
}