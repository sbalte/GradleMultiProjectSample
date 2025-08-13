package com.veda.tech.gradle.app

import com.hoc081098.flowext.FlowExtPreview
import com.hoc081098.flowext.groupBy
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking

@OptIn(ExperimentalCoroutinesApi::class, FlowExtPreview::class)
fun flowGroupBy(): Unit = runBlocking {
    (1..10).toList().asFlow()
        .groupBy { it % 2 }
        .flatMapMerge { groupedFlow ->
            groupedFlow
                .map { groupedFlow.key to it }
        }.collect { println("groupBy: $it") }
}

fun main() {
    flowGroupBy()
}