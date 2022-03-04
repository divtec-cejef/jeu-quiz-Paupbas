package com.paupbas.jeu_quiz.Models;

import android.database.Cursor;

/**
 * Représente une question
 */
public class Question {

    private  String question;
    // 0 = false
    // 1 = true
    private int reponse;

    public Question() {}

    public Question(String question, int reponse) {
        this.question = question;
        this.reponse = reponse;
    }

    /**
     * Constructeur avec un curseur comme paramètre
     * @param cursor curseur
     */
    public Question(Cursor cursor){
        question = cursor.getString(cursor.getColumnIndexOrThrow("question"));
        reponse = cursor.getInt(cursor.getColumnIndexOrThrow("reponse"));
    }

    /**
     * @return la question
     */
    public String getQuestion() {
        return question;
    }

    /**
     * @return la réponse
     */
    public int getReponse() {
        return reponse;
    }
}
