package com.github.ilyashvetsov.android_stackoverflow.data.remote

import com.github.ilyashvetsov.android_stackoverflow.data.model.Question
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Request



object StackOverflowAPI {

    fun getQuestions(): List<Question> {
        val request = Request.Builder()
            .url("https://api.stackexchange.com/2.2/questions?order=asc&sort=creation&tagged=android&site=stackoverflow")
            .build()
        val response = OkHttpClient().newCall(request).execute()
        val responseBody = response.body()!!.string()

        val list = Gson().fromJson(responseBody, ParsedJsonFile::class.java)
        val answer = ArrayList<Question>()
        for (question in list.items) {
            answer.add(Question(question.question_id, question.title))
        }

        return answer
    }

}
