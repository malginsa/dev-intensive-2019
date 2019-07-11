package ru.skillbranch.devintensive.extensions

import org.junit.Assert.assertEquals
import org.junit.Test
import java.util.*

class DateTest {

    val date = Date(1_000_000_000_000)

    @Test
    fun format_full(){
        assertEquals("05:46:40 09:09:01", date.format())
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
}
