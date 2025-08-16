@file:JvmName("StringExt")
package com.veda.tech.gradle.shared.util

import io.kotest.common.runBlocking

fun String.someFun(): String = println(this).let { "Hello World $this" }

fun <R> functionWithArgLogic(logic: () -> R) = logic()
fun <T, R> functionWithArgLogic(obj: T, logic: T.() -> R) = logic(obj)
fun <T, R> functionWithArgLogic1(obj: T, logic: (T) -> R) = logic(obj)
fun <T, R> functionWithBlock(obj: T, logic: (T) -> R) = runBlocking { logic(obj) }
