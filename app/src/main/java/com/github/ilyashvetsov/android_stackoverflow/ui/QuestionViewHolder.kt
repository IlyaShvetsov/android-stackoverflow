package com.github.ilyashvetsov.android_stackoverflow.ui

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.github.ilyashvetsov.android_stackoverflow.R
import com.github.ilyashvetsov.android_stackoverflow.data.model.Question


class QuestionViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    private val textView: TextView = itemView.findViewById(R.id.questionTextView)

    fun bind(question: Question) {
        textView.text = question.questionText
    }

}
