package com.ubertob.funkotlin.jsonCompactor


fun compactJson(json: Sequence<Char>): String =
   json.fold(OutQuotes(""), ::compactor).done


fun compactor(prev: JsonCompactor, c: Char): JsonCompactor =
    prev.compact(c)




sealed class JsonCompactor{
    abstract val done: String
    abstract fun compact(c: Char): JsonCompactor
}

data class InQuotes(override val done: String): JsonCompactor() {
    override fun compact(c: Char): JsonCompactor =
        if (c == '"')
            OutQuotes(done+c)
        else
            InQuotes(done+c)
}

data class OutQuotes(override val done: String): JsonCompactor() {
    override fun compact( c: Char): JsonCompactor =
        if ( c in arrayOf(' ', '\n', '\t'))
            this
        else if (c == '"')
            InQuotes(done+c)
        else
            OutQuotes(done+c)
}