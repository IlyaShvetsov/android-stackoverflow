package com.github.ilyashvetsov.android_stackoverflow.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.ilyashvetsov.android_stackoverflow.App
import com.github.ilyashvetsov.android_stackoverflow.R


class QuestionListFragment : Fragment() {
    private lateinit var viewModel: QuestionViewModel
    private lateinit var recView: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.question_list_fragment, container, false)
        recView = view.findViewById(R.id.recView)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        recView.layoutManager = LinearLayoutManager(context)
        val adapter = QuestionAdapter()
        recView.adapter = adapter

//        viewModel = ViewModelProvider(this, ModelFactory(context!!.applicationContext as App))
//                .get(QuestionViewModel::class.java)


        // Add an observer on the LiveData returned by getAlphabetizedWords.
        // The onChanged() method fires when the observed data changes and the activity is
        // in the foreground.
        // Update the cached copy of the words in the adapter.
//        viewModel.allQuestions.observe(this, adapter::submitList)

    }

}
