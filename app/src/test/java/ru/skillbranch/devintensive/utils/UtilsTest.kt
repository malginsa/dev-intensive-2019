package ru.skillbranch.devintensive.utils

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class UtilsTest {

    @Test
    fun parseFullName_null(){
        val (firstName, lastName) = Utils.parseFullName(null)
        assertNull(firstName)
        assertNull(lastName)
    }

    @Test
    fun parseFullName_empty(){
        val (firstName, lastName) = Utils.parseFullName("")
        assertNull(firstName)
        assertNull(lastName)
    }

    @Test
    fun parseFullName_space(){
        val (firstName, lastName) = Utils.parseFullName(" ")
        assertNull(firstName)
        assertNull(lastName)
    }

    @Test
    fun parseFullName_firstName(){
        val (firstName, lastName) = Utils.parseFullName("John")
        assertEquals(firstName, "John")
        assertNull(lastName)
    }

    @Test
    fun toInitials_fullName(){
        val initials = Utils.toInitials("john", "doe")
        assertEquals("JD", initials)
    }

    @Test
    fun toInitials_firstName(){
        val initials = Utils.toInitials("john", null)
        assertEquals("J", initials)
    }

    @Test
    fun toInitials_null(){
        val initials = Utils.toInitials(null, null)
        assertNull(initials)
    }

    @Test
    fun toInitials_empty(){
        val initials = Utils.toInitials(" ", "")
        assertNull(initials)
    }

    @Test
    fun transliterations_fullNameWithoutDivider(){
        val transliterations = Utils.transliterations("Женя Стереотипов")
        assertEquals("Zhenya Stereotipov", transliterations)
    }

    @Test
    fun transliterations_fullNameWithDivider(){
        val transliterations = Utils.transliterations("Amazing Петр", "_")
        assertEquals("Amazing_Petr", transliterations)
    }
}
