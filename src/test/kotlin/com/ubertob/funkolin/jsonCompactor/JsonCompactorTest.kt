package com.ubertob.funkolin.jsonCompactor

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class JsonCompactorTest {

    @Test
    fun `strip the spaces`(){

        val jsonText = "{ \"a\" : 20 }"


        val expected = "{\"a\":20}"

        assertThat(jsonText).isEqualTo(expected)

    }

}