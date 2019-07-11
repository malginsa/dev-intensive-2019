package ru.skillbranch.devintensive.utils

object Utils {
    fun parseFullName(fullName:String?): Pair<String?, String?> {

        val parts : List<String>? = fullName?.split(" ")

        var firstName : String? = parts?.getOrNull(0)
        if (firstName.isNullOrBlank()) firstName = null

        var lastName : String? = parts?.getOrNull(1)
        if (lastName.isNullOrBlank()) lastName = null

        return firstName to lastName
    }

    fun toInitials(firstName : String?, lastName : String?) : String?{
//        var result : String? = null
//        val firstInitial: String?
//        if(!firstName.isNullOrBlank()) firstInitial = firstName.get(0).toUpperCase()

        var dirty = "" + firstName?.getOrNull(0)?.toUpperCase() + lastName?.getOrNull(0)?.toUpperCase()

        dirty = dirty.replace("null", "")

        return if (dirty.isBlank()) null else dirty
//
//        val firstInitial = firstName?.get(0)?.toUpperCase()
//        val lastInitial = lastName?.get(0)?.toUpperCase()
//
//        if (firstInitial == null && lastInitial == null) return null
//
//        var result:String? = null
//        if (firstInitial != null) result = "$firstInitial"
////        if (lastInitial != null) result += "$lastInitial"
//        return result
    }

    fun transliteration(payload : String?, divider : String? = " ") : String?{
        val map = mapOf<Char, String>('а' to "a" , 'б' to "b" , 'в' to "v" , 'г' to "g" , 'д' to "d" , 'е' to "e" , 'ё' to "e" ,
            'ж' to "zh" , 'з' to "z" , 'и' to "i" , 'й' to "i" , 'к' to "k" , 'л' to "l" , 'м' to "m" , 'н' to "n" ,
            'о' to "o" , 'п' to "p" , 'р' to "r" , 'с' to "s" , 'т' to "t" , 'у' to "u" , 'ф' to "f" , 'х' to "h" ,
            'ц' to "c" , 'ч' to "ch" , 'ш' to "sh" , 'щ' to "sh'" , 'ъ' to "" , 'ы' to "i" , 'ь' to "" , 'э' to "e" ,
            'ю' to "yu" , 'я' to "ya", ' ' to "$divider")
        if (payload == null) return null
        if (payload.isEmpty()) return ""
        val result = StringBuilder()
        for (char in payload.toCharArray()) {
            val upperCase = char.isUpperCase()
            var to:String = map.getOrElse(char.toLowerCase(), {"$char"})
            if (upperCase) {
                to = to.capitalize()
            }
            result.append(to)
        }
        return result.toString()
    }
}
