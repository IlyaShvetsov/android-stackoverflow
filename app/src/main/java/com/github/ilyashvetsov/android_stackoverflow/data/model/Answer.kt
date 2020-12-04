package com.github.ilyashvetsov.android_stackoverflow.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey



@Entity
class Answer(

    @PrimaryKey(autoGenerate = false)
    var id: Int = 0,

    @ColumnInfo(name = "text")
    var text: String? = null,

    @ColumnInfo(name = "rating")
    var rating: Int = 0

)
