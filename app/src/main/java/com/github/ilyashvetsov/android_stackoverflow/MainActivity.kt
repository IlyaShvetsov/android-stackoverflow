package com.github.ilyashvetsov.android_stackoverflow

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.github.ilyashvetsov.android_stackoverflow.data.Question
import com.github.ilyashvetsov.android_stackoverflow.temp.NewWordActivity
import com.github.ilyashvetsov.android_stackoverflow.ui.ModelFactory
import com.github.ilyashvetsov.android_stackoverflow.ui.QuestionListFragment
import com.github.ilyashvetsov.android_stackoverflow.ui.QuestionViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton


class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: QuestionViewModel
    companion object {
        private const val NEW_WORD_ACTIVITY_REQUEST_CODE = 42
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, QuestionListFragment())
                    .commitNow()
        }

        viewModel = ViewModelProvider(this, ModelFactory(applicationContext as App))
                .get(QuestionViewModel::class.java)

        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            val intent = Intent(this@MainActivity, NewWordActivity::class.java)
            startActivityForResult(intent, NEW_WORD_ACTIVITY_REQUEST_CODE)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == NEW_WORD_ACTIVITY_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            val question = Question(data!!.getStringExtra(NewWordActivity.EXTRA_REPLY))
            viewModel.insert(question)
        }
    }

}
