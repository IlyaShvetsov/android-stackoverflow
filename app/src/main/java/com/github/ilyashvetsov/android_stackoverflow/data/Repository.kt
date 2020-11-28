package com.github.ilyashvetsov.android_stackoverflow.data

import androidx.annotation.WorkerThread
import androidx.lifecycle.MutableLiveData
import com.github.ilyashvetsov.android_stackoverflow.App
import com.github.ilyashvetsov.android_stackoverflow.data.local.QuestionDao
import com.github.ilyashvetsov.android_stackoverflow.data.model.Question
import com.github.ilyashvetsov.android_stackoverflow.data.remote.StackOverflowAPI



class Repository(application: App) {
    private val questionDao: QuestionDao = application.questionsDatabase!!.questionsDao
    val allQuestions = MutableLiveData<List<Question>>().apply {
        value = mutableListOf()
    }

    /** Возвращает false, если БД пуста и нет интернета */
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun updateData(): Boolean {
        try {
            val questions = StackOverflowAPI.getQuestions()
            allQuestions.postValue(questions)
            questionDao.insertQuestions(questions)
            return true
        } catch (e: Exception) {
            val questions = questionDao.getQuestions()
            if (questions.isEmpty()) return false
            allQuestions.postValue(questions)
            return true
        }
    }

}
