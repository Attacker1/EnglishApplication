package com.valera.diplom;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.valera.diplom.pojo.GrammarSentence;

@Database(entities = {GrammarSentence.class}, version = 1, exportSchema = false)
public abstract class SentencesDatabase extends RoomDatabase {
    private static SentencesDatabase database;
    private static final String DB_NAME = "sentences2.db";
    private static final Object LOCK = new Object();

    public static SentencesDatabase getInstance(Context context){
        synchronized (LOCK) {
            if (database == null) {
                database = Room.databaseBuilder(context, SentencesDatabase.class, DB_NAME).build();
            }
            return database;
        }
    }

    public abstract GrammarSentencesDao grammarSentencesDao();
}
