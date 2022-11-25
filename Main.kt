package connectfour

fun main() {
    println("Connect Four")
    val players = Players.players
    val boardSize = InitBoard.size

    println("${players[1]} VS ${players[2]}")
    println("${boardSize[0]} X ${boardSize[1]} board")

    val numRounds = InitBoard.rounds
    var win: Int
    for (i in 1..numRounds) {
        win = BoxConstructor(players, boardSize, numRounds, i).outBox()
        if (win == 3) Players.totalPoints[0] += win
        else if (win == 4) Players.totalPoints[1] += win
        println("Game over!")
    }
}