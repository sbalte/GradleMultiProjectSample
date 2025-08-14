package com.veda.tech.gradle.shared.serialization

import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Serializable
data class Data(val a: Int, val b: String)

fun main() {
    Json.encodeToString(Data(42, "str")).let { jStr ->
        println(jStr)
        Json.decodeFromString<Data>(jStr).apply { println(this) }
    }
}
