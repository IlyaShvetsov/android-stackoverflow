package com.github.ilyashvetsov.android_stackoverflow.data.remote

import com.github.ilyashvetsov.android_stackoverflow.data.model.Answer
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

    private fun ANSWER_REQ(questionId: Int): String {
        return "/questions/$questionId/answers?" +
                "order=desc&" +
                "sort=votes&" +
                "site=stackoverflow&" +
                "filter=withbody"
    }

    fun getQuestions(): List<Question> {
        val request = Request.Builder()
                .url(API_URL + QUESTION_REQ)
                .build()
        val response = OkHttpClient().newCall(request).execute()
        val responseBody = response.body()?.string() ?: throw Exception("Response body is null")

        val list = Gson().fromJson(responseBody, QuestionsList::class.java)
        val result = ArrayList<Question>()
        for (item in list.items) {
            result.add(Question(
                    item.question_id,
                    item.title,
                    item.body,
                    item.owner.display_name,
                    item.score
            ))
        }

        return result
    }

    fun getAnswers(questionId: Int): List<Answer> {
        val request = Request.Builder()
                .url(API_URL + ANSWER_REQ(questionId))
                .build()
        val response = OkHttpClient().newCall(request).execute()
        val responseBody = response.body()?.string() ?: throw Exception("Response body is null")

        val list = Gson().fromJson(responseBody, AnswersList::class.java)
        val result = ArrayList<Answer>()
        for (item in list.items) {
            result.add(Answer(
                    item.answer_id,
                    item.body,
                    item.score
            ))
        }

        return result
    }
}
