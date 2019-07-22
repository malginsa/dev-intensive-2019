package ru.skillbranch.devintensive.models

class Bender(var status: Status = Status.NORMAL,
             var question: Question = Question.NAME,
             var incorrectAnswers: Int) {

    fun askQuestion(): String = when (question) {
        Question.NAME -> Question.NAME.question
        Question.PROFESSION -> Question.PROFESSION.question
        Question.MATERIAL -> Question.MATERIAL.question
        Question.BDAY -> Question.BDAY.question
        Question.SERIAL -> Question.SERIAL.question
        Question.IDLE -> Question.IDLE.question
    }

    fun listenAnswer(answer: String): Pair<String, Triple<Int, Int, Int>> {
        val result: String

        if (question == Question.IDLE) {
            return question.question to status.color
        }

// TODO to simplify, return if not validated
        if (question.validate(answer)) {
            if (question.answers.contains(answer)) {
                question = question.nextQuestion()
                result = "Отлично - ты справился\n${question.question}"
            } else {
                incorrectAnswers++
                if (incorrectAnswers > 3) {
                    incorrectAnswers = 0
                    status = Status.NORMAL
                    question = Question.NAME
                    result = "Это неправильный ответ. Давай все по новой\n${question.question}"
                } else {
                    status = status.nextStatus()
                    result = "Это неправильный ответ\n${question.question}"
                }
            }
        } else {
            result = question.invalidMessage + "\n${question.question}"
        }
        return result to status.color
    }

    enum class Status(val color: Triple<Int, Int, Int>) {
        NORMAL(Triple(255, 255, 255)),
        WARNING(Triple(255, 120, 0)),
        DANGER(Triple(255, 60, 60)),
        CRITICAL(Triple(255, 0, 0));

        fun nextStatus(): Status {
            return if (this.ordinal < values().lastIndex) {
                values()[this.ordinal + 1]
            } else {
                values()[0]
            }
        }
    }

    enum class Question(
        val question: String,
        val answers: List<String>,
        val invalidMessage: String
    ) {
        NAME(
            "Как меня зовут?",
            listOf("Бендер", "Bender"),
            "Имя должно начинаться с заглавной буквы"
        ) {
            override fun validate(answer: String?): Boolean =
                answer?.get(0)?.isUpperCase() ?: false

            override fun nextQuestion(): Question = PROFESSION
        },
        PROFESSION(
            "Назови мою профессию?",
            listOf("сгибальщик", "bender"),
            "Профессия должна начинаться со строчной буквы"
        ) {
            override fun validate(answer: String?): Boolean =
                answer?.get(0)?.isLowerCase() ?: false

            override fun nextQuestion(): Question = MATERIAL
        },
        MATERIAL(
            "Из чего я сделан?",
            listOf("металл", "дерево", "metal", "iron", "wood"),
            "Материал не должен содержать цифр"
        ) {
            override fun validate(answer: String?): Boolean =
                answer?.matches("\\D+".toRegex()) ?: false

            override fun nextQuestion(): Question = BDAY
        },
        BDAY(
            "Когда меня создали?",
            listOf("2993"),
            "Год моего рождения должен содержать только цифры"
        ) {
            override fun validate(answer: String?): Boolean =
                answer?.matches("\\d+".toRegex()) ?: false

            override fun nextQuestion(): Question = SERIAL
        },
        SERIAL(
            "Мой серийный номер?",
            listOf("2716057"),
            "Серийный номер содержит только цифры, и их 7"
        ) {
            override fun validate(answer: String?): Boolean =
                answer?.matches("\\d{7}".toRegex()) ?: false

            override fun nextQuestion(): Question = IDLE
        },
        IDLE(
            "На этом все, вопросов больше нет",
            listOf(),
            ""
        ) {
            override fun validate(answer: String?): Boolean = true
            override fun nextQuestion(): Question = IDLE
        };

        abstract fun nextQuestion(): Question
        abstract fun validate(answer: String?): Boolean
    }
}
