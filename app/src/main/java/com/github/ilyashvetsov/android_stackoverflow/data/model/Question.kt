package com.github.ilyashvetsov.android_stackoverflow.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
class Question(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo(name = "question_text")
    var questionText: String? = null,

    @ColumnInfo(name = "author")
    var author: String? = null,

    @ColumnInfo(name = "rating")
    var rating: Int = 0

) {

    // TODO потом удалить
    constructor(text: String?): this() {
        this.questionText = text
    }

}
