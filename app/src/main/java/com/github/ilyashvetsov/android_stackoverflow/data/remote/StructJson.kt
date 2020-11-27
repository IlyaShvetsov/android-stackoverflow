package com.github.ilyashvetsov.android_stackoverflow.data.remote



internal class Quu {
    var question_id: Int = 0
    lateinit var title: String
}

internal class ParsedJsonFile {
    internal var items = mutableListOf<Quu>()
}
