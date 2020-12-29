package com.github.ilyashvetsov.android_stackoverflow.ui.answers

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.ilyashvetsov.android_stackoverflow.data.model.Answer
import com.github.ilyashvetsov.android_stackoverflow.databinding.AnswerItemBinding



class AnswerAdapter : RecyclerView.Adapter<AnswerViewHolder>() {
    private val dataList: ArrayList<Answer> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnswerViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val answerItemBinding = AnswerItemBinding.inflate(inflater, parent, false)
        return AnswerViewHolder(answerItemBinding)
    }

    override fun onBindViewHolder(holder: AnswerViewHolder, position: Int) {
        holder.bind(dataList[position])
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    fun setAll(answers: List<Answer>) {
        dataList.clear()
        dataList.addAll(answers)
        notifyDataSetChanged()
    }

}
