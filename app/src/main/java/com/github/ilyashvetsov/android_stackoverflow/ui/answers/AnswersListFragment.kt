package com.github.ilyashvetsov.android_stackoverflow.ui.answers

import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.github.ilyashvetsov.android_stackoverflow.App
import com.github.ilyashvetsov.android_stackoverflow.R
import com.github.ilyashvetsov.android_stackoverflow.data.model.Answer
import com.github.ilyashvetsov.android_stackoverflow.data.model.Question



class AnswersListFragment(private val question: Question) : Fragment(), SwipeRefreshLayout.OnRefreshListener {
    private lateinit var viewModel: AnswerViewModel
    private lateinit var adapter: AnswerAdapter
    private var refreshLayout: SwipeRefreshLayout? = null
    private var titleTextView: TextView? = null
    private var questionTextView: TextView? = null
    private var authorTextView: TextView? = null
    private var ratingTextView: TextView? = null
    private var recView: RecyclerView? = null
    private var progressBar: ProgressBar? = null
    private var errorText: TextView? = null

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_answers_list, container, false)
        refreshLayout       = root.findViewById(R.id.refresh_layout)
        titleTextView       = root.findViewById(R.id.question_title)
        questionTextView    = root.findViewById(R.id.question_text)
        authorTextView      = root.findViewById(R.id.question_author)
        ratingTextView      = root.findViewById(R.id.question_rating)
        recView             = root.findViewById(R.id.rec_view)
        progressBar         = root.findViewById(R.id.progress_bar)
        errorText           = root.findViewById(R.id.error_text)

        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        titleTextView?.text     = question.title
        questionTextView?.text  = Html.fromHtml(question.text)
        authorTextView?.text    = question.author
        ratingTextView?.text    = question.rating.toString()

        viewModel = ViewModelProvider(this, AnswerModelFactory(requireContext().applicationContext as App))
                .get(AnswerViewModel::class.java)

        adapter = AnswerAdapter()
        recView?.layoutManager = LinearLayoutManager(requireContext())
        recView?.adapter = adapter

        viewModel.allAnswers.observe(this, {
            if (it.isEmpty()) {
                showProgress()
            } else {
                showData(it)
            }
            refreshLayout?.isRefreshing = false
        })
        viewModel.showError.observe(this, {
            if (it) {
                showError()
            } else {
                hideError()
            }
            refreshLayout?.isRefreshing = false
        })
        viewModel.updateData(question.id)

        refreshLayout?.setOnRefreshListener(this)
    }

    override fun onRefresh() {
        viewModel.updateData(question.id)
    }

    private fun showData(data: List<Answer>) {
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
