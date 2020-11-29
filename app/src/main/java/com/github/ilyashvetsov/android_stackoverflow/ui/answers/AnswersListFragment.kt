package com.github.ilyashvetsov.android_stackoverflow.ui.answers

import android.os.Bundle
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
import com.github.ilyashvetsov.android_stackoverflow.data.model.Question



class AnswersListFragment(private val question: Question) : Fragment() {
    // , SwipeRefreshLayout.OnRefreshListener
//    private lateinit var viewModel: QuestionViewModel
//    private lateinit var adapter: QuestionAdapter
//    private var refreshLayout: SwipeRefreshLayout? = null
//    private var recView: RecyclerView? = null
//    private var progressBar: ProgressBar? = null
//    private var errorText: TextView? = null

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_answers_list, container, false)
//        refreshLayout   = root.findViewById(R.id.refresh_layout)
//        recView         = root.findViewById(R.id.rec_view)
//        progressBar     = root.findViewById(R.id.progress_bar)
//        errorText       = root.findViewById(R.id.error_text)

        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

//        viewModel = ViewModelProvider(this, ModelFactory(requireContext().applicationContext as App))
//                .get(QuestionViewModel::class.java)
//
//        adapter = QuestionAdapter()
//        recView?.layoutManager = LinearLayoutManager(requireContext())
//        recView?.adapter = adapter
//
//        viewModel.allQuestions.observe(this, {
//            if (it.isEmpty()) {
//                showProgress()
//            } else {
//                showData(it)
//            }
//            refreshLayout?.isRefreshing = false
//        })
//        viewModel.showError.observe(this, {
//            if (it) {
//                showError()
//            } else {
//                hideError()
//            }
//            refreshLayout?.isRefreshing = false
//        })
//        viewModel.updateData()
//
//        refreshLayout?.setOnRefreshListener(this)
    }

//    override fun onRefresh() {
//        viewModel.updateData()
//    }
//
//    private fun showData(data: List<Question>) {
//        adapter.setAll(data)
//        progressBar?.visibility = View.INVISIBLE
//        recView?.visibility = View.VISIBLE
//    }
//
//    private fun showProgress() {
//        recView?.visibility = View.INVISIBLE
//        progressBar?.visibility = View.VISIBLE
//    }
//
//    private fun showError() {
//        recView?.visibility = View.INVISIBLE
//        progressBar?.visibility = View.INVISIBLE
//        errorText?.visibility = View.VISIBLE
//    }
//
//    private fun hideError() {
//        errorText?.visibility = View.INVISIBLE
//    }

}
