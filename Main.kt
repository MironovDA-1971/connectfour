package connectfour

fun main() {
    println("Connect Four")
    val players = Players.list
    val board = InitBoard.sizeBoard
    println("${players[1]} VS ${players[2]}")
    println("${board[0]} X ${board[1]} board")
    BoxConstructor.drawBox(board)
}