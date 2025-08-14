package com.veda.tech.gradle.shared.serialization

import io.ktor.util.*
import kotlinx.serialization.*
import kotlinx.serialization.cbor.Cbor
import kotlinx.serialization.json.Json
import kotlinx.serialization.properties.Properties
import kotlinx.serialization.properties.encodeToMap

fun main() {
//    testCbor()
    testProperty()
}

fun testJson() {
    @Serializable
    data class Data(val a: Int, val b: String)
    Json.encodeToString(Data(42, "str")).let { jStr ->
        println(jStr)
        Json.decodeFromString<Data>(jStr).apply { println(this) }
    }
}

@OptIn(ExperimentalSerializationApi::class)
fun testCbor() {
    @Serializable
    data class Project(val name: String, val language: String)

    val data = Project("kotlinx.serialization", "Kotlin")
    val bytes = Cbor { ignoreUnknownKeys = true }.encodeToByteArray(data)
    println(bytes.encodeBase64())
    val obj = Cbor.decodeFromByteArray<Project>(bytes)
    println(obj)

    val format = Cbor { ignoreUnknownKeys = true }

    val data1 = format.decodeFromHexString<Project>(
        "bf646e616d65756b6f746c696e782e73657269616c697a6174696f6e686c616e6775616765664b6f746c696eff"
    )
    println(data1)
}

@OptIn(ExperimentalSerializationApi::class)
fun testProperty() {
    @Serializable
    class User(val name: String)
    @Serializable
    class Project(val name: String, val owner: User)
    val data = Project("kotlinx.serialization",  User("kotlin"))
    val map = Properties.encodeToMap(data)
    map.forEach { (k, v) -> println("$k = $v") }
}
