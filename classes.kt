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

object InputField {
    val rowsColumns: List<String>
        get() = checkInputField()

    private fun checkInputField(): List<String> {
        val range = 5..9
        //val a: List<String>
        while (true) {
            println("Set the board dimensions (Rows x Columns)\n" +
                    "Press Enter for default (6 x 7)")
            val a = readln().trim().split("\d\s?[xX]\s?\d")
            if (a.size != 3 || a[1] != "x") println("Invalid input")
            else if (a[0].toInt() !in range) println("Board rows should be from 5 to 9")
            else if (a[0].toInt() !in range) println("Board columns should be from 5 to 9")
            else break
        }
    }
}