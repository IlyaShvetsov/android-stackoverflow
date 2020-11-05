package com.github.ilyashvetsov.android_stackoverflow.ui

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory
import androidx.lifecycle.viewModelScope
import com.github.ilyashvetsov.android_stackoverflow.App
import com.github.ilyashvetsov.android_stackoverflow.data.AppRepository
import com.github.ilyashvetsov.android_stackoverflow.data.Question
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class ModelFactory(private val application: App) : NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return QuestionViewModel(application) as T
    }
}

class QuestionViewModel(application: App) : AndroidViewModel(application) {
    private val repository: AppRepository = AppRepository(application)
    val allQuestions: LiveData<List<Question>> = repository.allQuestions

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun insert(question: Question) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(question)
    }

//    fun getQuestions(): List<Question> {
//        return repository.getQuestions()
//    }

}
