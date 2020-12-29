package com.github.ilyashvetsov.android_stackoverflow.ui.questions

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.ilyashvetsov.android_stackoverflow.data.model.Question
import com.github.ilyashvetsov.android_stackoverflow.databinding.QuestionItemBinding



class QuestionAdapter(private val onClick : (Question) -> Unit) : RecyclerView.Adapter<QuestionViewHolder>() {
    private val dataList: ArrayList<Question> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val questionItemBinding = QuestionItemBinding.inflate(inflater, parent, false)
        return QuestionViewHolder(questionItemBinding, onClick)
    }

    override fun onBindViewHolder(holder: QuestionViewHolder, position: Int) {
        holder.bind(dataList[position])
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    fun setAll(questions: List<Question>) {
        dataList.clear()
        dataList.addAll(questions)
        notifyDataSetChanged()
    }

}
