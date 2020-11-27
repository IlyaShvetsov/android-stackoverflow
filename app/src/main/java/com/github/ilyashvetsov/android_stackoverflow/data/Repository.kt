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

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun updateData() {
        try {
            val questions = StackOverflowAPI.getQuestions()
            allQuestions.postValue(questions)
            questionDao.insertQuestions(questions)
        } catch (e: Exception) {
            allQuestions.postValue(questionDao.getQuestions())
        }
    }

}
