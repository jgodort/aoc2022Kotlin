fun main() {

    val inputData = readInput("Day5_input")
    val (crates, moves) = inputData.split("\n\n")
    val cratesStacks = crates.lines().dropLast(1).reversed().map { it.convertToCrate() }
    val workingCrates = List(9) { ArrayDeque<Char>() }
    cratesStacks.forEach { row ->
        for ((index, letter) in row.withIndex()) {
            if (letter == null) continue
            workingCrates[index].addLast(letter)
        }
    }

    val workingCratesStep1 = workingCrates.map { ArrayDeque(it) }
    val workingCratesStep2 = workingCrates.map { ArrayDeque(it) }
    val movements = processInputMovements(moves)
    movements.forEach { movement -> movement.perform1(workingCratesStep1) }
    movements.forEach { movement -> movement.perform2(workingCratesStep2) }
    val result1 = workingCratesStep1.joinToString("") { "${it.last()}" }
    val result2 = workingCratesStep2.joinToString("") { "${it.last()}" }
    println(result1)
    println(result2)

}

private fun processInputMovements(movements: String): List<Movement> = movements
    .lines()
    .map { instructions ->
        val actions = instructions.split(" ")
        Movement(
            quantity = actions[1].toInt(),
            from = actions[3].toInt(),
            to = actions[5].toInt()
        )
    }

fun String.convertToCrate(): List<Char?> =
    this.chunked(4) {
        if (it[1].isLetter()) it[1] else null
    }

data class Movement(
    val quantity: Int,
    val from: Int,
    val to: Int
) {
    fun perform1(stack: List<ArrayDeque<Char>>) {
        val fromStack = stack[from - 1]
        val toStack = stack[to - 1]

        repeat(quantity) {
            val inCraneClaw = fromStack.removeLast()
            toStack.addLast(inCraneClaw)
        }
    }

    fun perform2(stack: List<ArrayDeque<Char>>) {

        val fromStack = stack[from - 1]
        val toStack = stack[to - 1]

        val inCraneClaw = fromStack.takeLast(quantity)
        repeat(quantity) { fromStack.removeLast() }
        for (crate in inCraneClaw) {
            toStack.addLast(crate)
        }
    }
}

