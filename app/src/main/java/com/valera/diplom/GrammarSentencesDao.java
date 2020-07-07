package com.valera.diplom;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.valera.diplom.pojo.GrammarSentence;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface GrammarSentencesDao {
    @Query("SELECT * FROM sentences")
    LiveData<List<GrammarSentence>> getAllSentences();

    @Insert
    void insertSentences(GrammarSentence sentence);

    @Delete
    void deleteSentence(GrammarSentence sentence);

    @Query("DELETE FROM sentences")
    void deleteAllNotes();
}
