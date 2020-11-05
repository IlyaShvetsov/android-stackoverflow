package com.github.ilyashvetsov.android_stackoverflow

import android.app.Application
import androidx.room.Room
import com.github.ilyashvetsov.android_stackoverflow.data.local.AppDatabase


class App : Application() {
    var questionsDatabase: AppDatabase? = null
    private set

    override fun onCreate() {
        super.onCreate()
        questionsDatabase = Room.databaseBuilder(
            applicationContext, AppDatabase::class.java, "questions_database")
                .allowMainThreadQueries()
                .build()
    }

}
