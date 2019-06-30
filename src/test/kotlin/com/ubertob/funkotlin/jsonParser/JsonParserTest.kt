package com.ubertob.funkotlin.jsonParser

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isNotNull
import org.junit.jupiter.api.Test

internal class JsonParserTest {

//    @Test
//    fun `empty json array`(){
//
//        val json = "[]"
//
//        val jsonTree = parseJson(json)
//
//        assertThat(jsonTree.javaClass.simpleName).isEqualTo("JsonArray")
//    }
//
//    @Test
//    fun `empty json obj`(){
//
//        val json = "{}"
//
//        val jsonTree = parseJson(json)
//
//        assertThat(jsonTree.javaClass.simpleName).isEqualTo("JsonObj")
//    }


    @Test
    fun `parse a string`(){

        val parser = StringParser("pippo")

        val r = parser.parse(Location("pippone", 0))
        assertThat(r).isNotNull()
        assertThat(r!!.value).isEqualTo("pippo")
        assertThat(r!!.loc.index).isEqualTo(5)
        assertThat(r!!.loc.slice).isEqualTo("ne")
    }


    @Test
    fun `parse many strings`(){

        val parser = ManyParser( StringParser("abc-") )

        val r = parser.parse(Location("abc-abc-abc-nono", 0))
        assertThat(r).isNotNull()
        assertThat(r!!.value).isEqualTo(listOf("abc-", "abc-", "abc-"))
        assertThat(r!!.loc.index).isEqualTo(12)
        assertThat(r!!.loc.slice).isEqualTo("nono")
    }
}