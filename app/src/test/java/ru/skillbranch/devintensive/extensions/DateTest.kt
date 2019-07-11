package ru.skillbranch.devintensive.extensions

import org.junit.Assert.assertEquals
import org.junit.Test
import java.util.*

class DateTest {

    private val date = Date(1_000_000_000_000)
    private val current = Date()

    @Test
    fun format_full(){
        assertEquals("05:46:40 09.09.01", date.format())
    }

    @Test
    fun format_short(){
        assertEquals("05:46", date.format("HH:mm"))
    }

    @Test
    fun add_second(){
        val add = date.add(2, TimeUnits.SECOND)
        assertEquals(1_000_000_002_000, add.time)
    }

    @Test
    fun add_day(){
        val add = date.add(-4, TimeUnits.DAY)
        assertEquals(999_654_400_000, add.time)
    }

    @Test
    fun timeUnits_second(){
        val plural = TimeUnits.SECOND.plural(1)
        assertEquals("1 секунду", plural)
    }

    @Test
    fun timeUnits_minute(){
        val plural = TimeUnits.MINUTE.plural(4)
        assertEquals("4 минуты", plural)
    }

    @Test
    fun timeUnits_hour(){
        val plural = TimeUnits.HOUR.plural(19)
        assertEquals("19 часов", plural)
    }

    @Test
    fun timeUnits_day(){
        val plural = TimeUnits.DAY.plural(222)
        assertEquals("222 дня", plural)
    }

    @Test
    fun humanizeDiff_justNow(){
        val actual = current.humanizeDiff()
        assertEquals("только что", actual)
    }

    @Test
    fun humanizeDiff_severalSecondsAgo(){
        val actual = current.add(-21, TimeUnits.SECOND).humanizeDiff()
        assertEquals("несколько секунд назад", actual)
    }

    @Test
    fun humanizeDiff_OneMinuteAgo(){
        val actual = current.add(-65, TimeUnits.SECOND).humanizeDiff()
        assertEquals("минуту назад", actual)
    }

    @Test
    fun humanizeDiff_SomeMinutesAgo(){
        val actual = current.add(-18, TimeUnits.MINUTE).humanizeDiff()
        assertEquals("18 минут назад", actual)
    }

    @Test
    fun humanizeDiff_OneHourAgo(){
        val actual = current.add(-46, TimeUnits.MINUTE).humanizeDiff()
        assertEquals("час назад", actual)
    }

    @Test
    fun humanizeDiff_SomeHoursAgo(){
        val actual = current.add(-11, TimeUnits.HOUR).humanizeDiff()
        assertEquals("11 часов назад", actual)
    }

    @Test
    fun humanizeDiff_OneDayAgo(){
        val actual = current.add(-25, TimeUnits.HOUR).humanizeDiff()
        assertEquals("день назад", actual)
    }

    @Test
    fun humanizeDiff_SomeDaysAgo(){
        val actual = current.add(-117, TimeUnits.DAY).humanizeDiff()
        assertEquals("117 дней назад", actual)
    }

    @Test
    fun humanizeDiff_MoreThanOneYearAgo(){
        val actual = current.add(-365, TimeUnits.DAY).humanizeDiff()
        assertEquals("более года назад", actual)
    }

    @Test
    fun humanizeDiff_1(){
        val actual = current.add(-2, TimeUnits.HOUR).humanizeDiff()
        assertEquals("2 часа назад", actual)
    }

    @Test
    fun humanizeDiff_2(){
        val actual = current.add(-5, TimeUnits.DAY).humanizeDiff()
        assertEquals("5 дней назад", actual)
    }

    @Test
    fun humanizeDiff_3(){
        val actual = current.add(2, TimeUnits.MINUTE).humanizeDiff()
        assertEquals("через 2 минуты", actual)
    }

    @Test
    fun humanizeDiff_4(){
        val actual = current.add(7, TimeUnits.DAY).humanizeDiff()
        assertEquals("через 7 дней", actual)
    }

    @Test
    fun humanizeDiff_5(){
        val actual = current.add(-400, TimeUnits.DAY).humanizeDiff()
        assertEquals("более года назад", actual)
    }

    @Test
    fun humanizeDiff_6(){
        val actual = current.add(400, TimeUnits.DAY).humanizeDiff()
        assertEquals("более чем через год", actual)
    }
}
