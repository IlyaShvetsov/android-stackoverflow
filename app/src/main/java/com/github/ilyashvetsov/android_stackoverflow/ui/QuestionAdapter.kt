package com.github.ilyashvetsov.android_stackoverflow.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.ilyashvetsov.android_stackoverflow.R
import com.github.ilyashvetsov.android_stackoverflow.data.Question


class QuestionAdapter : RecyclerView.Adapter<QuestionViewHolder>() {
    private val dataList: ArrayList<Question> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.question_item, parent, false)
        val holder = QuestionViewHolder(view)

        view.setOnClickListener {
            val position: Int = holder.adapterPosition
            dataList.removeAt(position)
            notifyDataSetChanged()
        }

        return holder
    }

    override fun onBindViewHolder(holder: QuestionViewHolder, position: Int) {
        holder.bind(dataList[position])
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    fun insert(questions: List<Question>) {
        dataList.addAll(questions)
        notifyDataSetChanged()
    }

    fun insert(question: Question) {
        dataList.add(question)
        notifyDataSetChanged()
    }

}
