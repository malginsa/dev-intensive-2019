package ru.skillbranch.devintensive.extensions

import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.absoluteValue
import kotlin.math.roundToInt

const val SECOND = 1000L
const val MINUTE = 60 * SECOND
const val HOUR = 60 * MINUTE
const val DAY = 24 * HOUR

fun Date.humanizeDiff(compareTo: Date = Date()): String {
    val diff = compareTo.time - this.time
    val isInThePast = compareTo.time > this.time
    val result = when (diff.absoluteValue) {
        in 0 * SECOND..1 * SECOND -> return "только что"
        in 2 * SECOND..45 * SECOND -> "несколько секунд"
        in 46 * SECOND..75 * SECOND -> "минуту"
        in 76 * SECOND..45 * MINUTE -> TimeUnits.MINUTE.plural((1.0 * diff / MINUTE).roundToInt())
        in 46 * SECOND..75 * MINUTE -> "час"
        in 76 * MINUTE..22 * HOUR -> TimeUnits.HOUR.plural((1.0 * diff / HOUR).roundToInt())
        in 23 * HOUR..26 * HOUR -> "день"
        in 27 * HOUR..360 * DAY -> TimeUnits.DAY.plural((1.0 * diff / DAY).roundToInt())
        else -> {return if(isInThePast) "более года назад" else "более чем через год"}
    }
    return if (isInThePast) result + " назад" else "через " + result
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
        val absoluteValue = value.absoluteValue
        val normalized = if (value>20) (absoluteValue%10) else absoluteValue
        var result = "$absoluteValue "
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
