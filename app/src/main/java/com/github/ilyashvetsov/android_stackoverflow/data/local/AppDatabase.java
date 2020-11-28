package com.github.ilyashvetsov.android_stackoverflow.data.local;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import com.github.ilyashvetsov.android_stackoverflow.data.model.Question;



@Database(entities = {Question.class}, version = 4)
public abstract class AppDatabase extends RoomDatabase {
    public abstract QuestionDao getQuestionsDao();
}
