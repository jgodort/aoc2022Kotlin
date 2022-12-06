import HandShape.*
import RoundResult.*


enum class HandShape(val points: Int) {
    Rock(1),
    Paper(2),
    Scissors(3)
}

enum class RoundResult(val points: Int) {
    WIN(6),
    LOSE(0),
    DRAW(3)
}


fun main() {
    val inputData = readInput("Day2_input")
    println("""Result 1 ${part1(inputData)}""")
    println("""Result 2 ${part2(inputData)}""")
}

fun part1(input: String): Int {

    fun String.convertToHandShape(): HandShape {
        return when {
            listOf("A", "X").any { it == this } -> Rock
            listOf("B", "Y").any { it == this } -> Paper
            listOf("C", "Z").any { it == this } -> Scissors
            else -> error("Wrong Shape")
        }
    }

    fun calculateRound(pair: Pair<HandShape, HandShape>) = when (pair) {
        (Paper to Rock),
        (Rock to Scissors),
        (Scissors to Paper) -> 0

        (Paper to Paper),
        (Rock to Rock),
        (Scissors to Scissors) -> 3

        (Scissors to Rock),
        (Rock to Paper),
        (Paper to Scissors) -> 6

        else -> error("Wrong Round")
    }

    fun resultScore(pair: Pair<HandShape, HandShape>): Int =
        pair.second.points + calculateRound(pair)


    return input
        .lines()
        .map { rounds ->
            val game = rounds.split(" ")
            game[0].convertToHandShape() to game[1].convertToHandShape()
        }.sumOf { game -> resultScore(game) }
}

fun part2(input: String): Int {

    fun convertToShape(shape: String): HandShape = when (shape) {
        "A" -> Rock
        "B" -> Paper
        "C" -> Scissors
        else -> error("Wrong Shape")
    }

    fun computeRoundResult(roundResult: String) = when (roundResult) {
        "X" -> LOSE
        "Y" -> DRAW
        "Z" -> WIN
        else -> error("Wrong Shape")
    }

    fun computeUserHand(roundResult: RoundResult, opponentHand: HandShape): HandShape = when (roundResult) {
        WIN -> {
            when (opponentHand) {
                Rock -> Paper
                Paper -> Scissors
                Scissors -> Rock
            }
        }

        LOSE -> {
            when (opponentHand) {
                Rock -> Scissors
                Paper -> Rock
                Scissors -> Paper
            }
        }

        DRAW -> {
            when (opponentHand) {
                Rock -> Rock
                Paper -> Paper
                Scissors -> Scissors
            }
        }
    }


    return input.lines().sumOf { rounds ->
        val game = rounds.split(" ")
        val opponentHand = convertToShape(game[0])
        val roundResult = computeRoundResult(game[1])
        val userHand = computeUserHand(roundResult, opponentHand)

        userHand.points + roundResult.points
    }
}






