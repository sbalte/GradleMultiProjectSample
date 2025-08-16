package com.veda.tech.gradle.shared.util

import io.kotest.common.runBlocking
import io.r2dbc.spi.ConnectionFactoryOptions.PASSWORD
import io.r2dbc.spi.ConnectionFactoryOptions.USER
import io.r2dbc.spi.IsolationLevel
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.flow.toList
import org.jetbrains.exposed.v1.core.SqlExpressionBuilder.eq
import org.jetbrains.exposed.v1.core.StdOutSqlLogger
import org.jetbrains.exposed.v1.core.Table
import org.jetbrains.exposed.v1.core.count
import org.jetbrains.exposed.v1.r2dbc.*
import org.jetbrains.exposed.v1.r2dbc.transactions.suspendTransaction


object PostgresDBUtil {
//    val connectioPool: ConnectionPool by lazy {
//        ConnectionFactories.get(
//            ConnectionFactoryOptions.builder()
//                .option(DRIVER, "pool")
//                .option(PROTOCOL, "postgresql")
//                .option(HOST, "localhost")
//                .option(PORT, 5432)
//                .option(USER, "sbalte")
//                .option(PASSWORD, "sbalte")
//                .option(DATABASE, "sboot3")
//                .build()
//        ).run {
//            ConnectionPool(ConnectionPoolConfiguration.builder(this)
//                .maxIdleTime(1000.toDuration(DurationUnit.MILLISECONDS).toJavaDuration())
//                .maxSize(10)
//                .build())
//        }
//    }

    val postgresqldb by lazy {
        R2dbcDatabase.connect(
            url = "r2dbc:pool:postgresql://localhost:5434/sboot3",
            databaseConfig = {
                defaultMaxAttempts = 1
                defaultR2dbcIsolationLevel = IsolationLevel.READ_COMMITTED
                connectionFactoryOptions {
                    option(USER, "sbalte")
                    option(PASSWORD, "sbalte")
                }
            })
    }

    object StarWarsFilmsTable : Table() {
        val id = integer("id").autoIncrement()
        val sequelId = integer("sequel_id").uniqueIndex()
        val name = varchar("name", 64)
        val director = varchar("director", 64)
    }

    object Tasks : Table("tasks") {
        val id = integer("id").autoIncrement()
        val title = varchar("name", 128)
        val description = varchar("description", 128)
        val isCompleted = bool("completed").default(false)
    }

    fun test() = runBlocking {
        suspendTransaction(db = postgresqldb) {
            addLogger(StdOutSqlLogger)
            SchemaUtils.drop(Tasks, StarWarsFilmsTable)
            SchemaUtils.create(Tasks, StarWarsFilmsTable)

            val taskId = Tasks.insert {
                it[title] = "Learn Exposed"
                it[description] = "Go through the Get started with Exposed tutorial"
            } get Tasks.id

            val secondTaskId = Tasks.insert {
                it[title] = "Read The Hobbit"
                it[description] = "Read the first two chapters of The Hobbit"
                it[isCompleted] = true
            } get Tasks.id
            println("Created new tasks with ids $taskId and $secondTaskId.")
            Tasks.select(Tasks.id.count(), Tasks.isCompleted).groupBy(Tasks.isCompleted).collect {
                println("${it[Tasks.isCompleted]}: ${it[Tasks.id.count()]} ")
            }

            Tasks.update({ Tasks.id eq taskId }) {
                it[isCompleted] = true
            }

            val updatedTask = Tasks.select(Tasks.isCompleted).where(Tasks.id eq taskId).single()

            println("Updated task details: $updatedTask")

            Tasks.deleteWhere { id eq secondTaskId }

            println("Remaining tasks: ${Tasks.selectAll().toList()}")
        }
    }
}

fun main() {
    PostgresDBUtil.test()
}