package com.github.ilyashvetsov.android_stackoverflow.data

import androidx.annotation.WorkerThread
import androidx.lifecycle.MutableLiveData
import com.github.ilyashvetsov.android_stackoverflow.App
import com.github.ilyashvetsov.android_stackoverflow.data.local.QuestionDao
import com.github.ilyashvetsov.android_stackoverflow.data.model.Answer
import com.github.ilyashvetsov.android_stackoverflow.data.model.Question
import com.github.ilyashvetsov.android_stackoverflow.data.remote.StackOverflowAPI



class Repository(application: App) {
    private val questionDao: QuestionDao = application.questionsDatabase!!.questionsDao
    val allQuestions = MutableLiveData<List<Question>>().apply {
        value = mutableListOf()
    }
    val allAnswers = MutableLiveData<List<Answer>>().apply {
        value = mutableListOf()
    }

    /** Возвращает false, если нет интернета (или возникла другая ошибка) и БД пуста */
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun updateQuestionsData(): Boolean {
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

    /** Возвращает false, если нет интернета (или возникла другая ошибка) */
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun updateAnswersData(questionId: Int): Boolean {
        return try {
            val answers = StackOverflowAPI.getAnswers(questionId)
            allAnswers.postValue(answers)
            true
        } catch (e: Exception) {
            false
        }
    }

}
