package connectfour

object Players {
    private fun readName(defaultName: String): String {
        println("$defaultName player's name:")
        val a = readln().trim()
        return if (a != "") a else defaultName
    }
    private val firstPlayerName: String
        get() = readName("First")

    private val secondPlayerName: String
        get() = readName("Second")

    val list: Map<Int, String> = mapOf(
        1 to firstPlayerName,
        2 to secondPlayerName
    )
}

object InitBoard {
    val sizeBoard: List<Int>
        get() = checkInputField()

    private fun checkInputField(): List<Int> {
        val range = 5..9
        var sizeBoardInt: List<Int>
        val regex = """^(\d)+\s*x\s*(\d)+$""".toRegex()

        while (true) {
            println("Set the board dimensions (Rows x Columns)\n" +
                    "Press Enter for default (6 x 7)")
            val string = readln().trim().lowercase()
            if (string.isNotEmpty()) {
                try {
                    val (a, b) = regex.find(string)!!.destructured
                    sizeBoardInt = listOf(a.toInt(), b.toInt())
                    if (sizeBoardInt[0] !in range) println("Board rows should be from 5 to 9")
                    else if (sizeBoardInt[1] !in range) println("Board columns should be from 5 to 9")
                    else break
                } catch (_: Exception) {
                    println("Invalid input")
                }

            } else {
                sizeBoardInt = listOf(6, 7)
                break
            }
        }
        return sizeBoardInt
    }
}