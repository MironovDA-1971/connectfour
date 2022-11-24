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

class BoxConstructor(private val playerName: Map<Int, String>,
                     private val boardSize: List<Int>
) {
    private var turnList: List<MutableList<Char>> = List(boardSize[0]){ MutableList(boardSize[1]){' '} }


    private fun getTurn(playerNum: Int): Boolean {
        val rangeColumn = 1..boardSize[1]
        var turn: String
        var stopGame = false
        val playerIndex = (playerNum % 2) + 1
        while (true) {
            println("${playerName[playerIndex]}'s turn:")
            try {
                turn = readln().lowercase()
                if(turn == "end") {
                    stopGame = true
                    break
                }
                if (turn.toInt() !in rangeColumn) println("The column number is out of range (1 - ${boardSize[1]})")
                else break
            } catch (_: Exception) {
                println("Incorrect column number")
            }
        }
        if (!stopGame) fillBox(turn.toInt() - 1, playerIndex)
        return stopGame
    }

    private fun fillBox(turnNumber: Int, playerIndex: Int) {
        for (i in turnList.lastIndex downTo 0) {
            if (turnList[i][turnNumber] == ' ') {
                if (playerIndex == 1) turnList[i][turnNumber] = 'o'
                else turnList[i][turnNumber] = '*'
                break
            }
        }
    }

    fun drawBox() {
        var playerNumber = 1
        var numberItm = ""
        val oneItm = "║"
        var bottomRow = "╚═"
        var flag = 0
        while (true) {
            if(getTurn(++playerNumber)) {
                println("Game over!")
                break
            }

            for(i in turnList.indices) {
                for (num in turnList[i].indices) {
                    if (i == 0) {
                        numberItm += " ${num + 1}"
                        if (flag == 0) bottomRow += if (num != turnList[i].lastIndex) "╩═" else "╝"
                    }
                    else {
                        if (flag == 0) {
                            println(numberItm)
                            flag = 1
                        }
                        if (num != turnList[i].lastIndex) print("$oneItm${ turnList[i][num] }")
                        else println(oneItm)
                    }
                }

                if (i == turnList.lastIndex) println(bottomRow)
            }
        }
        /*
        var numberItm = ""
        var oneItm = "║"
        var bottomRow = "╚═"
        for (num in 1..boardSize[1]) {
            numberItm += " $num"
            oneItm += " ║"
            bottomRow += if (num != boardSize[1]) "╩═" else "╝"
        }
        for (num in 0..boardSize[0]) {
            if (num == 0) println(numberItm) else  println(oneItm)
            if (num == boardSize[0]) println(bottomRow)
        }
        */
    }

}