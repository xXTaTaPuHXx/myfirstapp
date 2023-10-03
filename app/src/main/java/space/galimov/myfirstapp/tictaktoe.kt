package space.galimov.myfirstapp

import kotlin.random.Random

fun Boolean?.toMark(): String = when (this) {
    true -> "X"
    false -> "O"
    null -> "-"
}

interface Game {
    val isFinished: Boolean
    val winner: Boolean?
    val field: Field
    fun act(row: Int, col: Int): Boolean
}

interface Field {
    val size: Int
    fun get(row: Int, col: Int): Boolean?
}

interface MutableField : Field {
    fun set(row: Int, col: Int, value: Boolean)
}

class GameImp : Game {

    override var isFinished: Boolean = false
    override var winner: Boolean? = null
    override val field: MutableField = ArrayField(3)

    private val userMark = Random.nextBoolean()

    init {
        if (!userMark) {
            actAi()
        }
    }

    override fun act(row: Int, col: Int): Boolean {
        if (field.get(row, col) != null) {
            return false
        }
        field.set(row, col, userMark)
        checkEnd()
        if (!isFinished) {
            actAi()
            checkEnd()
        }
        return true
    }

    private fun checkEnd() {
        if (((field.get(0, 0).toMark() != "-") and (field.get(0, 1).toMark() != "-") and (field.get(
                0,
                2
            ).toMark() != "-") and (winner == null))
        ) {
            winner = ((field.get(0, 0))!! and (field.get(0, 1)!!) and (field.get(0, 2)!!))
            if (!winner!!) {
                winner = (!(field.get(0, 0))!! and !(field.get(0, 1)!!) and !(field.get(0, 2)!!))
                if (!winner!!) {
                    winner = null
                }
            }
        }
        if (((field.get(1, 0).toMark() != "-") and (field.get(1, 1).toMark() != "-") and (field.get(
                1,
                2
            ).toMark() != "-") and (winner == null))
        ) {
            winner = ((field.get(1, 0))!! and (field.get(1, 1)!!) and (field.get(1, 2)!!))
            if (!winner!!) {
                winner = (!(field.get(1, 0))!! and !(field.get(1, 1)!!) and !(field.get(1, 2)!!))
                if (!winner!!) {
                    winner = null
                }
            }
        }
        if (((field.get(2, 0).toMark() != "-") and (field.get(2, 1).toMark() != "-") and (field.get(
                2,
                2
            ).toMark() != "-") and (winner == null)
                    )
        ) {
            winner = ((field.get(2, 0))!! and (field.get(2, 1)!!) and (field.get(2, 2)!!))
            if (!winner!!) {
                winner = (!(field.get(2, 0))!! and !(field.get(2, 1)!!) and !(field.get(2, 2)!!))
                if (!winner!!) {
                    winner = null
                }
            }
        }
        if (((field.get(0, 0).toMark() != "-") and (field.get(1, 0).toMark() != "-") and (field.get(
                2,
                0
            ).toMark() != "-") and (winner == null)
                    )
        ) {
            winner = ((field.get(0, 0))!! and (field.get(1, 0)!!) and (field.get(2, 0)!!))
            if (!winner!!) {
                winner = (!(field.get(0, 0))!! and !(field.get(1, 0)!!) and !(field.get(2, 0)!!))
                if (!winner!!) {
                    winner = null
                }
            }
        }
        if (((field.get(0, 1).toMark() != "-") and (field.get(1, 1).toMark() != "-") and (field.get(
                2,
                1
            ).toMark() != "-") and (winner == null)
                    )
        ) {
            winner = ((field.get(0, 1))!! and (field.get(1, 1)!!) and (field.get(2, 1)!!))
            if (!winner!!) {
                winner = (!(field.get(0, 1))!! and !(field.get(1, 1)!!) and !(field.get(2, 1)!!))
                if (!winner!!) {
                    winner = null
                }
            }
        }
        if (((field.get(0, 2).toMark() != "-") and (field.get(1, 2).toMark() != "-") and (field.get(
                2,
                2
            ).toMark() != "-") and (winner == null)
                    )
        ) {
            winner = ((field.get(0, 2))!! and (field.get(1, 2)!!) and (field.get(2, 2)!!))
            if (!winner!!) {
                winner = (!(field.get(0, 2))!! and !(field.get(1, 2)!!) and !(field.get(2, 2)!!))
                if (!winner!!) {
                    winner = null
                }
            }
        }
        if (((field.get(0, 2).toMark() != "-") and (field.get(1, 1).toMark() != "-") and (field.get(
                2,
                0
            ).toMark() != "-") and (winner == null)
                    )
        ) {
            winner = ((field.get(0, 2))!! and (field.get(1, 1)!!) and (field.get(2, 0)!!))
            if (!winner!!) {
                winner = (!(field.get(0, 2))!! and !(field.get(1, 1)!!) and !(field.get(2, 0)!!))
                if (!winner!!) {
                    winner = null
                }
            }
        }
        if (((field.get(0, 0).toMark() != "-") and (field.get(1, 1).toMark() != "-") and (field.get(
                2,
                2
            ).toMark() != "-") and (winner == null)
                    )
        ) {
            winner = ((field.get(0, 0))!! and (field.get(1, 1)!!) and (field.get(2, 2)!!))
            if (!winner!!) {
                winner = (!(field.get(0, 0))!! and !(field.get(1, 1)!!) and !(field.get(2, 2)!!))
                if (!winner!!) {
                    winner = null
                }
            }
        }
        if (winner != null) {
            isFinished = true
        }
        else {
            field.forEach { _, _, value ->
                if (value == null) {
                    return
                }
            }
            isFinished = true
        }
    }

    private fun actAi() {
        field.forEach { row, col, value ->
            if (value == null) {
                field.set(row, col, !userMark)
                return
            }
        }
    }
}

class ArrayField(override val size: Int) : MutableField {

    private val points: Array<Array<Boolean?>> = Array(size) { arrayOfNulls(size) }

    override fun set(row: Int, col: Int, value: Boolean) {
        points[row][col] = value
    }

    override fun get(row: Int, col: Int): Boolean? = points[row][col]
}

inline fun Field.forEach(action: (row: Int, col: Int, value: Boolean?) -> Unit) {
    repeat(size) { row ->
        repeat(size) { col ->
            action(row, col, get(row, col))
        }
    }
}