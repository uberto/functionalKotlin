package com.ubertob.funkotlin.jsonParser

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

internal class JsonParserTest {

    @Test
    fun `empty json array`(){

        val json = "[]"

        val jsonTree = parseJson(json)

        assertThat(jsonTree.javaClass.simpleName).isEqualTo("JsonArray")
    }
}