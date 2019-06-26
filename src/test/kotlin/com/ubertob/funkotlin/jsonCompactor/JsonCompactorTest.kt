package com.ubertob.funkotlin.jsonCompactor

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class JsonCompactorTest {

    @Test
    fun `strip the spaces`(){

        val jsonText = "{ \"a\" : 20 }".asSequence()

        val expected = "{\"a\":20}"

        assertThat( compactJson( jsonText )).isEqualTo(expected)

    }


    @Test
    fun `strip the new lines and tabs`(){

        val jsonText = "{ \t\t\"a\" : 20, \n \"b\": true }".asSequence()

        val expected = "{\"a\":20,\"b\":true}"

        assertThat( compactJson( jsonText )).isEqualTo(expected)

    }


    @Test
    fun `keep spaces in quotes`(){

        val jsonText = "{ \"my greetings\" :   \"hello world\" }".asSequence()

        val expected = "{\"my greetings\":\"hello world\"}"

        assertThat( compactJson( jsonText )).isEqualTo(expected)

    }

}