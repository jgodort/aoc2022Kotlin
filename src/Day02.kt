import java.lang.Exception
import java.lang.IllegalArgumentException


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
            listOf("A", "X").any { it == this } -> HandShape.Rock
            listOf("B", "Y").any { it == this } -> HandShape.Paper
            listOf("C", "Z").any { it == this } -> HandShape.Scissors
            else -> error("Wrong Shape")
        }
    }

    fun calculateRound(pair: Pair<HandShape, HandShape>) = when (pair) {
        (HandShape.Paper to HandShape.Rock),
        (HandShape.Rock to HandShape.Scissors),
        (HandShape.Scissors to HandShape.Paper) -> 0

        (HandShape.Paper to HandShape.Paper),
        (HandShape.Rock to HandShape.Rock),
        (HandShape.Scissors to HandShape.Scissors) -> 3

        (HandShape.Scissors to HandShape.Rock),
        (HandShape.Rock to HandShape.Paper),
        (HandShape.Paper to HandShape.Scissors) -> 6

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
        "A" -> HandShape.Rock
        "B" -> HandShape.Paper
        "C" -> HandShape.Scissors
        else -> error("Wrong Shape")
    }

    fun computeRoundResult(roundResult: String) = when (roundResult) {
        "X" -> RoundResult.LOSE
        "Y" -> RoundResult.DRAW
        "Z" -> RoundResult.WIN
        else -> error("Wrong Shape")
    }

    fun computeUserHand(roundResult: RoundResult, opponentHand: HandShape): HandShape = when (roundResult) {
        RoundResult.WIN -> {
            when (opponentHand) {
                HandShape.Rock -> HandShape.Paper
                HandShape.Paper -> HandShape.Scissors
                HandShape.Scissors -> HandShape.Rock
            }
        }

        RoundResult.LOSE -> {
            when (opponentHand) {
                HandShape.Rock -> HandShape.Scissors
                HandShape.Paper -> HandShape.Rock
                HandShape.Scissors -> HandShape.Paper
            }
        }

        RoundResult.DRAW -> {
            when (opponentHand) {
                HandShape.Rock -> HandShape.Rock
                HandShape.Paper -> HandShape.Paper
                HandShape.Scissors -> HandShape.Scissors
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






