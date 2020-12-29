package com.github.ilyashvetsov.android_stackoverflow.ui.questions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.github.ilyashvetsov.android_stackoverflow.App
import com.github.ilyashvetsov.android_stackoverflow.MainActivity
import com.github.ilyashvetsov.android_stackoverflow.data.model.Question
import com.github.ilyashvetsov.android_stackoverflow.databinding.FragmentQuestionsListBinding



class QuestionsListFragment : Fragment(), SwipeRefreshLayout.OnRefreshListener {
    private lateinit var viewModel: QuestionViewModel
    private lateinit var binding: FragmentQuestionsListBinding
    private lateinit var adapter: QuestionAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        binding = FragmentQuestionsListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProvider(this, QuestionModelFactory(requireContext().applicationContext as App))
                .get(QuestionViewModel::class.java)

        adapter = QuestionAdapter { question ->
            showAnswers(question)
        }
        binding.recView.layoutManager = LinearLayoutManager(requireContext())
        binding.recView.adapter = adapter

        viewModel.allQuestions.observe(this, {
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
        viewModel.updateData()

        binding.refreshLayout.setOnRefreshListener(this)
    }

    override fun onRefresh() {
        viewModel.updateData()
    }

    private fun showData(data: List<Question>) {
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

    private fun showAnswers(question: Question) {
        (activity as MainActivity).showAnswers(question)
    }

}
