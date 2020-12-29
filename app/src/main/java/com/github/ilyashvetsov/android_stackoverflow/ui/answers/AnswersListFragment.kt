package com.github.ilyashvetsov.android_stackoverflow.ui.answers

import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.github.ilyashvetsov.android_stackoverflow.App
import com.github.ilyashvetsov.android_stackoverflow.data.model.Answer
import com.github.ilyashvetsov.android_stackoverflow.data.model.Question
import com.github.ilyashvetsov.android_stackoverflow.databinding.FragmentAnswersListBinding



class AnswersListFragment(private val question: Question) : Fragment(), SwipeRefreshLayout.OnRefreshListener {
    private lateinit var viewModel: AnswerViewModel
    private lateinit var binding: FragmentAnswersListBinding
    private lateinit var adapter: AnswerAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        binding = FragmentAnswersListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.questionTitle.text     = question.title
        binding.questionText.text  = Html.fromHtml(question.text)
        binding.questionAuthor.text    = question.author
        binding.questionRating.text    = question.rating.toString()

        viewModel = ViewModelProvider(this, AnswerModelFactory(requireContext().applicationContext as App))
                .get(AnswerViewModel::class.java)

        adapter = AnswerAdapter()
        binding.recView.layoutManager = LinearLayoutManager(requireContext())
        binding.recView.adapter = adapter

        viewModel.allAnswers.observe(this, {
            if (it.isEmpty()) {
                showProgress()
            } else {
                showData(it)
            }
            binding.refreshLayout.isRefreshing = false
        })
        viewModel.showError.observe(this, {
            if (it) {
                showError()
            } else {
                hideError()
            }
            binding.refreshLayout.isRefreshing = false
        })
        viewModel.updateData(question.id)

        binding.refreshLayout.setOnRefreshListener(this)
    }

    override fun onRefresh() {
        viewModel.updateData(question.id)
    }

    private fun showData(data: List<Answer>) {
        adapter.setAll(data)
        binding.progressBar.visibility = View.INVISIBLE
        binding.recView.visibility = View.VISIBLE
    }

    private fun showProgress() {
        binding.recView.visibility = View.INVISIBLE
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun showError() {
        binding.recView.visibility = View.INVISIBLE
        binding.progressBar.visibility = View.INVISIBLE
        binding.errorText.visibility = View.VISIBLE
    }

    private fun hideError() {
        binding.errorText.visibility = View.INVISIBLE
    }

}
