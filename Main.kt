package connectfour

fun main() {
    var count = 0
    println("Connect Four")
    val players = Players.players
    val boardSize = InitBoard.size
    val numRounds = InitBoard.rounds

    println("${players[1]} VS ${players[2]}")
    println("${boardSize[0]} X ${boardSize[1]} board")
    println(InitBoard.textRound)

        for (i in 1..numRounds) {
            BoxConstructor(players, boardSize, numRounds, i).outBox(count)
        if(numRounds != 1) {
            count++
            println("Score\n${players[1]}: ${Players.totalPoints[0]} " +
                    "${players[2]}: ${Players.totalPoints[1]}")
        }
    }
    println("Game over!")
}