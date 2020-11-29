package com.github.ilyashvetsov.android_stackoverflow

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.ilyashvetsov.android_stackoverflow.data.model.Question
import com.github.ilyashvetsov.android_stackoverflow.ui.answers.AnswersListFragment
import com.github.ilyashvetsov.android_stackoverflow.ui.questions.QuestionsListFragment



class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.container, QuestionsListFragment())
                .commit()
    }

    fun showAnswers(question: Question) {
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.container, AnswersListFragment(question))
                .addToBackStack(null)
                .commit()
    }

}
