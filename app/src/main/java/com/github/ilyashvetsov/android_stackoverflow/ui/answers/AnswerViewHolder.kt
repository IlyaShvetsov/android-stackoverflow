package com.github.ilyashvetsov.android_stackoverflow.ui.answers

import android.text.Html
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.github.ilyashvetsov.android_stackoverflow.R
import com.github.ilyashvetsov.android_stackoverflow.data.model.Answer



class AnswerViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    private val answerTextView: TextView = itemView.findViewById(R.id.answer_text)
    private val ratingTextView: TextView = itemView.findViewById(R.id.answer_rating)

    fun bind(answer: Answer) {
        answerTextView.text = Html.fromHtml(answer.text)
        ratingTextView.text = answer.rating.toString()
    }

}
