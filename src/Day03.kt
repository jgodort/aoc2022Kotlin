const val LOWERCASE_DIFFERENCE = 96
const val UPPERCASE_DIFFERENCE = 38
fun main() {
    val inputData = readInput("Day3_input")
    println(day3Part1(inputData))
    println(day3Part2(inputData))
}

fun day3Part1(input: String): Int = input.lines().map { line ->
    val content = line.toList()
    val rucksacks = content.chunked(content.size / 2)
    rucksacks[0]
        .intersect(other = rucksacks[1].toSet())
        .first()
}.sumOf { convertToScore(it) }

fun day3Part2(input: String): Int = input.lines()
    .chunked(3)
    .map { group ->
        group.first().toList()
            .intersect(group[1].toSet())
            .intersect(group[2].toSet())
            .first()
    }.sumOf { convertToScore(it) }


private fun convertToScore(char: Char): Int =
    if (char.isUpperCase()) char.code.minus(UPPERCASE_DIFFERENCE)
    else char.code.minus(LOWERCASE_DIFFERENCE)