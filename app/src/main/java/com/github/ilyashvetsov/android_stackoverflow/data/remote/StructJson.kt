package com.github.ilyashvetsov.android_stackoverflow.data.remote



class Author(val display_name: String)

class QuestionItem(val question_id: Int, val title: String, val body: String, val owner: Author, val score: Int)

class QuestionsList {
    var items = mutableListOf<QuestionItem>()
}



class AnswerItem(val answer_id: Int, val body: String, val score: Int)

class AnswersList {
    var items = mutableListOf<AnswerItem>()
}
