package com.ubertob.funkotlin.console

import java.io.BufferedReader
import java.io.InputStreamReader


data class IO<T> (val run: () -> T) {

    fun andThen(other: IO<T>): IO<T> = IO {
        this.run()
        other.run()
    }
}


fun printIO(msg: String) = IO { println(msg)}

val reader =  BufferedReader( InputStreamReader(System.`in`))

fun readlineIO(msg: String) = IO<String> { reader.readLine() }

