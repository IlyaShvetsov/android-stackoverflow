package com.github.ilyashvetsov.android_stackoverflow.ui.questions

import androidx.lifecycle.*
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory
import com.github.ilyashvetsov.android_stackoverflow.App
import com.github.ilyashvetsov.android_stackoverflow.data.Repository
import com.github.ilyashvetsov.android_stackoverflow.data.model.Question
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch



class ModelFactory(private val application: App) : NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return QuestionViewModel(application) as T
    }
}

class QuestionViewModel(application: App) : AndroidViewModel(application) {
    private val repository: Repository = Repository(application)
    val allQuestions: LiveData<List<Question>> = repository.allQuestions
    val showError = MutableLiveData<Boolean>().apply {
        value = false
    }

    /** Launching a new coroutine to insert the data in a non-blocking way */
    fun updateData() = viewModelScope.launch(Dispatchers.IO) {
        showError.postValue(!repository.updateData())
    }

}
