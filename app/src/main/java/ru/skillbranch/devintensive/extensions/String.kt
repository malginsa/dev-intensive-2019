package ru.skillbranch.devintensive.extensions

import java.util.regex.Pattern

val HTML_CLENUP = Pattern.compile("(<.+?>|&.+?;|(?<=\\s)\\s+)")

fun String.truncate(size: Int = 16): String {
    var result = this.trimEnd()
    if (result.length > size) {
        result = result.substring(0..size - 1).trimEnd() + "..."
    }
    return result
}

fun String.stripHtml():String{
    return HTML_CLENUP.matcher(this).replaceAll("")
}