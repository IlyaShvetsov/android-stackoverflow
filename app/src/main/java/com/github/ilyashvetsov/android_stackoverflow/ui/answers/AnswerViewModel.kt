package com.github.ilyashvetsov.android_stackoverflow.ui.answers

import androidx.lifecycle.*
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory
import com.github.ilyashvetsov.android_stackoverflow.App
import com.github.ilyashvetsov.android_stackoverflow.data.Repository
import com.github.ilyashvetsov.android_stackoverflow.data.model.Answer
import com.github.ilyashvetsov.android_stackoverflow.data.model.Question
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch



class AnswerModelFactory(private val application: App) : NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return AnswerViewModel(application) as T
    }
}

class AnswerViewModel(application: App) : AndroidViewModel(application) {
    private val repository: Repository = Repository(application)
    val allAnswers: LiveData<List<Answer>> = repository.allAnswers
    val showError = MutableLiveData<Boolean>().apply {
        value = false
    }

    /** Launching a new coroutine to insert the data in a non-blocking way */
    fun updateData(questionId: Int) = viewModelScope.launch(Dispatchers.IO) {
        showError.postValue(!repository.updateAnswersData(questionId))
    }

}
