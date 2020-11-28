package com.github.ilyashvetsov.android_stackoverflow

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.github.ilyashvetsov.android_stackoverflow.data.model.Question
import com.github.ilyashvetsov.android_stackoverflow.ui.ModelFactory
import com.github.ilyashvetsov.android_stackoverflow.ui.QuestionAdapter
import com.github.ilyashvetsov.android_stackoverflow.ui.QuestionViewModel



class MainActivity : AppCompatActivity(), SwipeRefreshLayout.OnRefreshListener {
    private lateinit var viewModel: QuestionViewModel
    private lateinit var adapter: QuestionAdapter
    private var container: SwipeRefreshLayout? = null
    private var recView: RecyclerView? = null
    private var progressBar: ProgressBar? = null
    private var errorText: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        container = findViewById(R.id.container)
        recView = findViewById(R.id.rec_view)
        progressBar = findViewById(R.id.progress_bar)
        errorText = findViewById(R.id.error_text)

        viewModel = ViewModelProvider(this, ModelFactory(applicationContext as App))
            .get(QuestionViewModel::class.java)

        adapter = QuestionAdapter()
        recView?.layoutManager = LinearLayoutManager(this)
        recView?.adapter = adapter

        viewModel.allQuestions.observe(this, {
            if (it.isEmpty()) {
                showProgress()
            } else {
                showData(it)
            }
            container?.isRefreshing = false
        })
        viewModel.showError.observe(this, {
            if (it) {
                showError()
            } else {
                hideError()
            }
            container?.isRefreshing = false
        })
        viewModel.updateData()

        container?.setOnRefreshListener(this)
    }

    override fun onRefresh() {
        viewModel.updateData()
    }

    private fun showData(data: List<Question>) {
        adapter.setAll(data)
        progressBar?.visibility = View.INVISIBLE
        recView?.visibility = View.VISIBLE
    }

    private fun showProgress() {
        recView?.visibility = View.INVISIBLE
        progressBar?.visibility = View.VISIBLE
    }

    private fun showError() {
        recView?.visibility = View.INVISIBLE
        progressBar?.visibility = View.INVISIBLE
        errorText?.visibility = View.VISIBLE
    }

    private fun hideError() {
        errorText?.visibility = View.INVISIBLE
    }

}
