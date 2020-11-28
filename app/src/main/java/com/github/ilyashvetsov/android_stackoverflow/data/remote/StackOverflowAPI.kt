package com.github.ilyashvetsov.android_stackoverflow.data.remote

import com.github.ilyashvetsov.android_stackoverflow.data.model.Question
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Request



object StackOverflowAPI {
    private const val API_URL = "https://api.stackexchange.com/2.2"
    private const val QUESTION_REQ = "/questions?" +
            "page=1" + "&" +
            "pagesize=100" + "&" +
            "order=asc" + "&" +
            "sort=creation" + "&" +
            "tagged=android" + "&" +
            "site=stackoverflow" + "&" +
            "filter=withbody"

    fun getQuestions(): List<Question> {
        val request = Request.Builder()
            .url(API_URL + QUESTION_REQ)
            .build()
        val response = OkHttpClient().newCall(request).execute()
        val responseBody = response.body()!!.string()

        val list = Gson().fromJson(responseBody, ParsedJsonFile::class.java)
        val answer = ArrayList<Question>()
        for (item in list.items) {
            answer.add(Question(
                    item.question_id,
                    item.title,
                    item.body,
                    item.owner.display_name,
                    item.score
            ))
        }

        return answer
    }

}
