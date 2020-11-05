package com.github.ilyashvetsov.android_stackoverflow.data

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.github.ilyashvetsov.android_stackoverflow.App
import com.github.ilyashvetsov.android_stackoverflow.data.local.QuestionDao


class AppRepository(application: App) {
    private val questionDao: QuestionDao = application.questionsDatabase!!.questionsDao
    val allQuestions: LiveData<List<Question>> = questionDao.getQuestions()

    // You must call this on a non-UI thread or your app will crash. So we're making this a
    // suspend function so the caller methods know this.
    // Like this, Room ensures that you're not doing any long running operations on the main
    // thread, blocking the UI.

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(questions: List<Question>) {
        questionDao.insertQuestions(questions)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(question: Question) {
        questionDao.insertQuestion(question)
    }

}
