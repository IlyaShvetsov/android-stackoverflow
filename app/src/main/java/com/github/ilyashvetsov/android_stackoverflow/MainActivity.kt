package com.github.ilyashvetsov.android_stackoverflow

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.github.ilyashvetsov.android_stackoverflow.ui.ModelFactory
import com.github.ilyashvetsov.android_stackoverflow.ui.QuestionAdapter
import com.github.ilyashvetsov.android_stackoverflow.ui.QuestionViewModel



class MainActivity : AppCompatActivity(), SwipeRefreshLayout.OnRefreshListener {
    private lateinit var viewModel: QuestionViewModel
    private lateinit var adapter: QuestionAdapter
    private var container: SwipeRefreshLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this, ModelFactory(applicationContext as App))
            .get(QuestionViewModel::class.java)

        val recView = findViewById<RecyclerView>(R.id.recView)
        recView.layoutManager = LinearLayoutManager(this)
        adapter = QuestionAdapter()
        recView.adapter = adapter

        viewModel.allQuestions.observe(this, {
            adapter.setAll(it)
            container?.isRefreshing = false
        })
        viewModel.updateData()

        container = findViewById(R.id.container)
        container?.setOnRefreshListener(this)
    }

    override fun onRefresh() {
        viewModel.updateData()
    }

}
