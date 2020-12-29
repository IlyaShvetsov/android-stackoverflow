package com.github.ilyashvetsov.android_stackoverflow.ui.answers

import android.text.Html
import androidx.recyclerview.widget.RecyclerView
import com.github.ilyashvetsov.android_stackoverflow.data.model.Answer
import com.github.ilyashvetsov.android_stackoverflow.databinding.AnswerItemBinding



class AnswerViewHolder(private val binding: AnswerItemBinding): RecyclerView.ViewHolder(binding.root) {

    fun bind(answer: Answer) {
        binding.answerText.text = Html.fromHtml(answer.text)
        binding.answerRating.text = answer.rating.toString()
    }

}
