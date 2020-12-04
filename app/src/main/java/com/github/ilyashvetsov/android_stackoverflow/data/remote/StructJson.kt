package com.github.ilyashvetsov.android_stackoverflow.data.remote



internal class Author {
    lateinit var display_name: String
}

internal class QuestionItem {
    var question_id: Int = 0
    lateinit var title: String
    lateinit var body: String
    lateinit var owner: Author
    var score: Int = 0
}

internal class QuestionsList {
    internal var items = mutableListOf<QuestionItem>()
}



internal class AnswerItem {
    var answer_id: Int = 0
    lateinit var body: String
    var score: Int = 0
}

internal class AnswersList {
    internal var items = mutableListOf<AnswerItem>()
}

