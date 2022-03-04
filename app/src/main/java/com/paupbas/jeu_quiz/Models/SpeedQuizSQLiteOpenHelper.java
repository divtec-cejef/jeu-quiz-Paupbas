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
        db.execSQL("INSERT INTO quiz VALUES(4,\"Marios bros est le premier jeu de mario\", 0)");
        db.execSQL("INSERT INTO quiz VALUES(5,\"La Playstation 2 est la console la plus vendu\", 1)");
        db.execSQL("INSERT INTO quiz VALUES(6,\"Tetris est un jeu créé par un suisse\", 0)");
        db.execSQL("INSERT INTO quiz VALUES(7,\"La Dreamcast est la dernière console de Sega\", 1)");
        db.execSQL("INSERT INTO quiz VALUES(8,\"La terre est plate\", 0)");
        db.execSQL("INSERT INTO quiz VALUES(9,\"Tezuka est le père du manga moderne\", 1)");
        db.execSQL("INSERT INTO quiz VALUES(10,\"Luffy veut devenir hokage\", 0)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}
}
