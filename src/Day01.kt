fun main() {
    fun part1(input: String): Int = input.split("\n\n")
        .maxOfOrNull { elf ->
            elf.lines().sumOf { it.toInt() }
        } ?: 0


    fun part2(input: String): Int {
        return input.split("\n\n")
            .map { elf ->
                elf.lines().sumOf { it.toInt() }
            }.sortedDescending().take(3).sum()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day1_input")
    println(part1(testInput))
    println(part2(testInput))
}
