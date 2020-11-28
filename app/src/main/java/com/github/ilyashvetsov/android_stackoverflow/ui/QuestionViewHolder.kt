package com.github.ilyashvetsov.android_stackoverflow.ui

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.github.ilyashvetsov.android_stackoverflow.R
import com.github.ilyashvetsov.android_stackoverflow.data.model.Question



class QuestionViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    private val titleTextView: TextView = itemView.findViewById(R.id.question_title)
    private val authorTextView: TextView = itemView.findViewById(R.id.question_author)
    private val ratingTextView: TextView = itemView.findViewById(R.id.question_rating)

    fun bind(question: Question) {
        titleTextView.text = question.title
        authorTextView.text = question.author
        ratingTextView.text = question.rating.toString()
    }

}
