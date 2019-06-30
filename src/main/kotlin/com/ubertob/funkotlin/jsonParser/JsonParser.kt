package com.ubertob.funkotlin.jsonParser

import com.ubertob.funkotlin.jsonCompactor.compactJson


fun parseJson(json: String): JsonValue {

    val compactJson = compactJson(json.asSequence())

//    compactJson.fold(ValueParser(JsonRoot), ::parse)

    return JsonArray(JsonRoot, emptyList())
}


sealed class JsonNode
object JsonRoot : JsonNode()

sealed class JsonValue : JsonNode()

data class JsonArray(val parent: JsonNode, val children: List<JsonValue>) : JsonValue()
data class JsonObj(val parent: JsonNode, val prop: Map<String, JsonValue>) : JsonValue()
data class JsonString(val parent: JsonNode, val value: String) : JsonValue()
data class JsonLong(val parent: JsonNode, val value: Long) : JsonValue()


data class Location(val slice: String, val index: Int)

data class Located<T>(val loc: Location, val value: T)


sealed class JsonParser<T> {

    abstract fun parse(toBeParsed: Location): Located<T>?
}


data class StringParser(val expected: String) : JsonParser<String>() {

    override fun parse(toBeParsed: Location): Located<String>? {
        if (toBeParsed.slice.startsWith(expected))
            return Located(
                Location(
                    slice = toBeParsed.slice.substring(expected.length),
                    index = toBeParsed.index + expected.length
                ),
                value = expected
            )
        else
            return null
    }

}

data class ManyParser<T>(val innerParser: JsonParser<T>): JsonParser<List<T>>() {
    override fun parse(toBeParsed: Location): Located<List<T>>? {
        var nextParse = toBeParsed
        val value = mutableListOf<T>()
        while (true){
            val r = innerParser.parse(nextParse)
            if (r == null)
                break
            value.add(r.value)
            nextParse = r.loc
        }
        return Located(nextParse, value)
    }

}
//
//data class ValueParser(val currNode: JsonNode): JsonParser() {
//    override fun parse(c: Char): JsonParser =
//        when(c){
//            '[' -> ArrayParser(currNode, this)
//            '{' -> ObjParser(currNode, this)
//            else -> throw Exception("Illegal char $c at currNode $currNode")
//        }
//
//}
//
//data class ArrayParser(val currNode: JsonNode, val prevParser: JsonParser): JsonParser() {
//    override fun parse(c: Char): JsonParser =
//        when(c){
//            ']' -> TODO()
//            ',' -> TODO()
//            else -> ValueParser(currNode).parse(c)
//        }
//}
//
//
//data class ObjParser(val currNode: JsonNode, val prevParser: JsonParser): JsonParser() {
//    override fun parse(c: Char): JsonParser =
//        when(c){
//            '}' -> prevParser.next(JsonObj(currNode, emptyMap()))
//            ',' -> TODO()
//            else -> throw Exception("Illegal char $c at currNode $currNode")
//        }
//}