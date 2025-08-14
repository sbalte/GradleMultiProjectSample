package com.veda.tech.gradle.shared.util

import com.veda.tech.gradle.shared.util.MonthUtil.getDaysInMonthsForYear
import com.veda.tech.gradle.shared.util.MonthUtil.getUniqueYearsFromDateStrings
import kotlinx.datetime.*

object MonthUtil {
    fun getDaysInMonthsForYear(years: List<Int>): Map<Int, Map<Month, Int>> =
        years.associateWith { year ->
            Month.entries.associateWith { month ->
                LocalDate(year, month, 1).run {
                    plus(1, DateTimeUnit.MONTH)
                        .minus(1, DateTimeUnit.DAY).day
                }
            }
        }
    fun getUniqueYearsFromDateStrings(dateStrings: List<String>) = dateStrings
        .map { LocalDate.parse(it).year }
        .distinct()
}

fun main() {
    getUniqueYearsFromDateStrings(
        listOf("2023-01-15", "2019-05-20", "2024-05-20", "2026-05-20", "2023-11-10",
            "2025-02-01", "2024-08-30", "2027-05-20", "2028-05-20",)
    ).let {
        getDaysInMonthsForYear(it).forEach { (year, monthMap) ->
            println()
            println("Number of days per month for the year $year:")
            monthMap.forEach { (month, days) ->
                println("${month.name.padEnd(9)} : $days days")
            }
        }
    }
}


