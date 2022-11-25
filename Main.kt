package connectfour

fun main() {
    println("Connect Four")
    val players = Players.list
    val board = InitBoard

    println("${players[1]} VS ${players[2]}")
    println("${board.size[0]} X ${board.size[1]} board")
    val numRounds = board.rounds
    for (i in 1..numRounds) {
        BoxConstructor(players, board.size, numRounds).outBox()
    }
    println("Game over!")
}