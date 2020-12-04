package com.github.ilyashvetsov.android_stackoverflow

import android.app.Application
import androidx.room.Room
import com.github.ilyashvetsov.android_stackoverflow.data.local.QuestionDatabase



class App : Application() {
    var questionsDatabase: QuestionDatabase? = null
    private set

    override fun onCreate() {
        super.onCreate()
        questionsDatabase = Room.databaseBuilder(
            applicationContext, QuestionDatabase::class.java, "questions_database")
                .build()
    }

}
