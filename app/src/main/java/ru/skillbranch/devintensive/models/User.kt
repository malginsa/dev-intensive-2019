package ru.skillbranch.devintensive.models

import ru.skillbranch.devintensive.utils.Utils
import java.util.*

data class User(
    val id: String,
    var firstName: String?,
    var lastName: String?,
    var avatar: String? = null,
    var rating: Int = 0,
    var respect: Int = 0,
    val lastVisit: Date? = null,
    val isOnline: Boolean = false
) {
    constructor() : this("-1", null, null)

    companion object Factory {
        private var lastId: Int = -1
        fun makeUser(fullName: String?): User {
            lastId++
            val (firstName, lastName) = Utils.parseFullName(fullName)
            return User(
                "$lastId",
                firstName,
                lastName
            )
        }

        fun Builder(): UserBuilder {
            return UserBuilder()
        }

    }

    class UserBuilder {

        lateinit var lastName: String
        lateinit var firstName: String
        lateinit var id: String
        lateinit var avatar: String
        var rating : Int = 0
        var respect : Int = 0
        lateinit var lastVisit : Date
        var isOnline : Boolean = false

        fun id(id: String): UserBuilder {
            this.id = id
            return this
        }

        fun fistName(firstName: String): UserBuilder {
            this.firstName = firstName
            return this
        }

        fun lastName(lastName: String): UserBuilder {
            this.lastName = lastName
            return this
        }

        fun avatar(avatar:String):UserBuilder{
            this.avatar = avatar
            return this
        }

        fun rating(rating: Int): UserBuilder {
            this.rating = rating
            return this
        }

        fun respect(respect: Int): UserBuilder {
            this.respect = respect
            return this
        }

        fun lastVisit(lastVisit: Date): UserBuilder {
            this.lastVisit = lastVisit
            return this
        }

        fun isOnline(isOnline : Boolean) : UserBuilder{
            this.isOnline = isOnline
            return this
        }

        fun build(): User {
            return User(id, firstName, lastName, avatar, rating, respect, lastVisit, isOnline)
        }
    }
}

