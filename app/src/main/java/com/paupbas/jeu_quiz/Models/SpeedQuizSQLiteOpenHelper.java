package com.paupbas.jeu_quiz.Models;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SpeedQuizSQLiteOpenHelper extends SQLiteOpenHelper {

    static String DB_NAME="Speedquiz.db";
    static int DB_VERSION=1;

    public SpeedQuizSQLiteOpenHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sqlCreateDatableQuiZ = " CREATE TABLE quiz(idQuiz INTEGER PRIMARY KEY, question TEXT, reponse INTEGER);";
        db.execSQL(sqlCreateDatableQuiZ);
        db.execSQL("INSERT INTO quiz VALUES(1,\"La première guerre mondial c'est terminée en 1945\", 0)");
        db.execSQL("INSERT INTO quiz VALUES(2,\"Alan Turing est le créateur de la machine énigma\", 0)");
        db.execSQL("INSERT INTO quiz VALUES(3,\"La Suisse n'est pas dans l'union européen\", 1)");
        db.execSQL("INSERT INTO quiz VALUES(4,\"Marios bros est le premier jeu de mario\", 1)");
        db.execSQL("INSERT INTO quiz VALUES(5,\"La Playstation 2 est la console la plus vendu\", 1)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}
}
