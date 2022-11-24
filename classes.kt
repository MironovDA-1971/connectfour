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
        var sizeBoardInt: List<Int>
        val regex = """^(\d)+\s*x\s*(\d)+$""".toRegex()

        while (true) {
            println("Set the board dimensions (Rows x Columns)\n" +
                    "Press Enter for default (6 x 7)")
            val string = readln().trim().lowercase()
            if (string.isEmpty()) { sizeBoardInt = listOf(6, 7); break
            } else {
                try {
                    val (a, b) = regex.find(string)!!.destructured
                    sizeBoardInt = listOf(a.toInt(), b.toInt())
                    when {
                        sizeBoardInt[0] !in 5..9 -> println("Board rows should be from 5 to 9")
                        sizeBoardInt[1] !in 5..9 -> println("Board columns should be from 5 to 9")
                        else -> break
                    }
                } catch (_: Exception) { println("Invalid input") }
            }
        }
        return sizeBoardInt
    }
}

class BoxConstructor(private val playerName: Map<Int, String>,
                     private val boardSize: List<Int>
                    ) {
    private var turnList: List<MutableList<Char>> = List(boardSize[0]){ MutableList(boardSize[1]){' '} }

    private fun getTurn(playerNum: Int): Boolean {
        var turn: String
        var stopGame = false
        var columnFull: Boolean
        val playerIndex = (playerNum % 2) + 1
        while (true) {
            println("${playerName[playerIndex]}'s turn:")
            try {
                turn = readln().lowercase()
                if(turn == "end") {
                    println("Game over!")
                    stopGame = true
                    break
                }
                if (turn.toInt() !in 1..boardSize[1]) println("The column number is out of range (1 - ${boardSize[1]})")
                else {
                    columnFull = fillBox(turn.toInt() - 1, playerIndex)
                    if (columnFull) println("Column $turn is full")
                    else break
                }
            } catch (_: Exception) {
                println("Incorrect column number")
            }
        }
        return stopGame
    }

    private fun fillBox(turnNumber: Int, playerIndex: Int): Boolean {
        var columnFull = true
        for (i in turnList.lastIndex downTo 0) {
            if (turnList[i][turnNumber] == ' ') {
                if (playerIndex == 1) turnList[i][turnNumber] = 'o'
                else turnList[i][turnNumber] = '*'
                columnFull = false
                break
            }
        }
        return columnFull
    }

    private fun numberString(i: Int) {
        var numberItm = ""
        for (n in 1 .. turnList[i].size) numberItm += " $n"
        println(numberItm)
    }

    private fun drawBox () {
        for(i in turnList.indices) {
            for (num in turnList[i].indices) {
                if (i == 0 && num == 0) numberString(i)
                print("║${ turnList[i][num] }")
            }
            println("║")
            if (i == turnList.lastIndex) println("╚═${"╩═".repeat(turnList[i].lastIndex)}╝")
        }
    }

    fun outBox() {
        var startPlayerNumber = 1
        var endGame: Boolean
        drawBox()
        while (true) {
            endGame = getTurn(++startPlayerNumber)
            if(endGame) break
            drawBox()
        }
    }

}