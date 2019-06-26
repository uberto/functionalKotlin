package com.ubertob.funkotlin.jsonParser


fun parseJson(json: String): JsonNode = JsonArray(JsonRoot, emptyList())


sealed class JsonNode
object JsonRoot: JsonNode()
data class JsonArray(val parent: JsonNode, val children: List<JsonNode>): JsonNode()


class JsonParser {
}