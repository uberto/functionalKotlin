package com.ubertob.funkotlin.jsonCompactor


fun compactJson(json: Sequence<Char>): String =
   json.fold("", ::compactor)



fun compactor(done: String, c: Char): String =
    if (c == ' ') done else done+c
