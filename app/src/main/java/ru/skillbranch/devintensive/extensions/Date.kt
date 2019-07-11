package ru.skillbranch.devintensive.extensions

import java.text.SimpleDateFormat
import java.util.*

const val SECOND = 1000L
const val MINUTE = 60 * SECOND
const val HOUR = 60 * MINUTE
const val DAY = 24 * HOUR

fun Date.humanizeDiff(date:Date = Date()): String {
    return "когда-то"
}

fun Date.format(pattern: String = "HH:mm:ss dd:MM:yy"): String {
    return SimpleDateFormat(pattern, Locale("ru")).format(time)
}

fun Date.add(value: Int, units: TimeUnits):Date {
    var time = this.time
    time += when(units){
        TimeUnits.SECOND -> value * SECOND
        TimeUnits.MINUTE -> value * MINUTE
        TimeUnits.HOUR -> value * HOUR
        TimeUnits.DAY -> value * DAY
    }
    this.time = time
    return this
}

enum class TimeUnits {
    SECOND,
    MINUTE,
    HOUR,
    DAY;

    fun plural(value: Int): String {
        val normalized = if (value>20) value%10 else value
        var result = "$value "
        result += when (this) {
            SECOND -> when(normalized) {
                1 -> "секунду"
                in 2..4 -> "секунды"
                else -> "секунд"
            }
            MINUTE -> when(normalized) {
                1 -> "минуту"
                in 2..4 -> "минуты"
                else -> "минут"
            }
            HOUR -> when(normalized) {
                1 -> "час"
                in 2..4 -> "часа"
                else -> "часов"
            }
            DAY -> when(normalized) {
                1 -> "день"
                in 2..4 -> "дня"
                else -> "дней"
            }
        }
        return result
    }
}
