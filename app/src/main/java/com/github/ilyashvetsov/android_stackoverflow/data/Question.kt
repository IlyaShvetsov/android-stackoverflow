package com.github.ilyashvetsov.android_stackoverflow.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
class Question() {

    @PrimaryKey
    @ColumnInfo(name = "id")
    var id = 0

    @ColumnInfo(name = "question_text")
    var questionText: String? = null

    @ColumnInfo(name = "author")
    var author: String? = null

    @ColumnInfo(name = "rating")
    var rating = 0

    constructor(id: Int, text: String?, author: String?, rating: Int): this() {
        this.id = id
        this.questionText = text
        this.author = author
        this.rating = rating
    }

    constructor(text: String?): this() {
        this.questionText = text
    }

}
