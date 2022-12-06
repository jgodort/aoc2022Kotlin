fun main() {
    val inputData = readInput("Day4_input")
    println(day4Part1(inputData))
    println(day4Part2(inputData))
}

private fun convertToRange(value: String): IntRange {
    val elements = value.split("-")
        .map { it.toInt() }
        .sorted()
    return IntRange(elements.component1(), elements.component2())
}

private fun IntRange.isFullyContained(other: IntRange): Boolean =
    this.contains(other.first) && this.contains(other.last)

private fun getRanges(it: String): List<IntRange> = it
    .split(",")
    .map { elfAssigment -> convertToRange(elfAssigment) }

fun day4Part1(input: String): Int = input.lines()
    .sumOf { line ->
        val ranges = getRanges(line)
        val firstRange = ranges.component1()
        val secondRange = ranges.component2()
        val result = if (firstRange.isFullyContained(secondRange) || secondRange.isFullyContained(firstRange)) 1 else 0
        result
    }

fun day4Part2(input: String): Int = input.lines()
    .sumOf { line ->
        val ranges = getRanges(line)
        val firstRange = ranges.component1()
        val secondRange = ranges.component2()
        val result = if (firstRange.intersect(secondRange).isNotEmpty()) 1 else 0
        result
    }


