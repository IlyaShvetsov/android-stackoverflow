package com.github.ilyashvetsov.android_stackoverflow.ui.questions

import androidx.recyclerview.widget.RecyclerView
import com.github.ilyashvetsov.android_stackoverflow.data.model.Question
import com.github.ilyashvetsov.android_stackoverflow.databinding.QuestionItemBinding



class QuestionViewHolder(private val binding: QuestionItemBinding, private val onClick : (Question) -> Unit) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(question: Question) {
        binding.questionTitle.text = question.title
        binding.questionAuthor.text = question.author
        binding.questionRating.text = question.rating.toString()
        itemView.setOnClickListener {
            onClick(question)
        }
    }

}
