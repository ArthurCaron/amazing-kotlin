package io.github.superbob.amazing

import java.util.*

class Amazing(randomSeed: Long? = null) {
    private val random = randomSeed?.let { Random(it) } ?: Random()

    private fun rnd(count: Int): Int {
        return (count * random.nextFloat()).toInt() + 1
    }

    fun doit(width: Int, height: Int): String {
        val result = StringBuilder()
        result.appendLine("Amazing - Copyright by Creative Computing, Morristown, NJ")
        if (width == 1 || height == 1) return result.toString()
        val context = MazeBuildContext(random, width, height)
        context.start()
        result.append(context.buildMazeAsString())
        return result.toString()
    }

    class Maze(
            width: Int,
            height: Int,
    ) {
        val array: Array<Array<SquareType>> = Array(width + 1) { Array(height + 1) { SquareType.BLOCKED } }

        enum class SquareType(val line1: String, val line2: String) {
            BLOCKED("  |", "--+"),
            OPEN_BOT("  |", "  +"),
            OPEN_RIGHT("   ", "--+"),
            FULLY_OPEN("   ", "  +")
        }
    }

    class MazeBuildContext(
            private val random: Random,
            private var width: Int,
            private var height: Int,
    ) {
        private val startingPosition: Int
        private val wArray: Array<IntArray> = Array(width + 1) { IntArray(height + 1) }
        private var maze: Maze = Maze(width + 1, height + 1)
        private var mazeHasAnEnd: Boolean = false
        private var col: Int = 0
        private var row: Int = 1
        private var numberOfOpenedPaths: Int = 2
        private var finished: Boolean = false

        private fun rnd(count: Int): Int {
            return (count * random.nextFloat()).toInt() + 1
        }

        init {
            startingPosition = rnd(width)
            col = startingPosition
            wArray[startingPosition][1] = 1
        }

        private fun doNothing() {}

        fun start() {
            if (!finished) {
                if (cantMoveToLeft) cantGoLeft()
                else if (cantMoveToTop) m430()
                else if (cantMoveToRight) m350()
                else {
                    when (rnd(3)) {
                        1 -> moveToTheLeftSquareByOpeningTheWall()
                        2 -> moveToTheTopSquareByOpeningTheWall()
                        3 -> moveToTheRightSquareByOpeningTheRightWallOrTheBotWall()
                        else -> doNothing()
                    }
                }
            }
        }

        private fun m350() {
            if (!finished) {
                if (cantOpenBot) {
                    when (rnd(2)) {
                        1 -> moveToTheLeftSquareByOpeningTheWall()
                        2 -> moveToTheTopSquareByOpeningTheWall()
                        else -> doNothing()
                    }
                } else {
                    when (rnd(3)) {
                        1 -> moveToTheLeftSquareByOpeningTheWall()
                        2 -> moveToTheTopSquareByOpeningTheWall()
                        3 -> endMazeIfPossibleAndStart()
                        else -> doNothing()
                    }
                }
            }
        }

        private fun m430() {
            if (!finished) {
                if (mazeHasAnEnd && isLastRow && cantMoveToRight) {
                    moveToTheLeftSquareByOpeningTheWall()
                } else if (mazeHasAnEnd && isLastRow && isNotLastCol && rightSquareInWArrayIsUnset) {
                    when (rnd(2)) {
                        1 -> moveToTheLeftSquareByOpeningTheWall()
                        2 -> moveToTheRightSquareByOpeningTheRightWallOrTheBotWall()
                        else -> doNothing()
                    }
                } else if (cantMoveToRight && isNotLastRow_AND_botSquareInWArrayIsSet) {
                    moveToTheLeftSquareByOpeningTheWall()
                } else if ((cantMoveToRight) && isLastRow_OR_botSquareInWArrayIsUnset) {
                    when (rnd(2)) {
                        1 -> moveToTheLeftSquareByOpeningTheWall()
                        2 -> endMazeIfPossibleAndStart()
                        else -> doNothing()
                    }
                } else if (isNotLastRow_AND_botSquareInWArrayIsSet) {
                    when (rnd(2)) {
                        1 -> moveToTheLeftSquareByOpeningTheWall()
                        2 -> moveToTheRightSquareByOpeningTheRightWallOrTheBotWall()
                        else -> doNothing()
                    }
                } else {
                    when (rnd(3)) {
                        1 -> moveToTheLeftSquareByOpeningTheWall()
                        2 -> moveToTheRightSquareByOpeningTheRightWallOrTheBotWall()
                        3 -> endMazeIfPossibleAndStart()
                        else -> doNothing()
                    }
                }
            }
        }

        private fun cantGoLeft() {
            if (!finished) {
                if (mazeHasAnEnd && isLastRow && isNotLastCol && rightSquareInWArrayIsUnset && topSquareInWArrayIsUnset) {
                    when (rnd(2)) {
                        1 -> moveToTheTopSquareByOpeningTheWall()
                        2 -> moveToTheRightSquareByOpeningTheRightWallOrTheBotWall()
                        else -> doNothing()
                    }
                } else if (mazeHasAnEnd && isLastRow && isNotLastCol && rightSquareInWArrayIsUnset && topSquareInWArrayIsSet) {
                    moveToTheRightSquareByOpeningTheRightWallOrTheBotWall()
                } else if (mazeHasAnEnd && isLastRow && cantMoveToRight && topSquareInWArrayIsSet) {
                    do {
                        moveToNextSquareOrRestart()
                    } while (currentSquareInWArrayIsUnset)
                    start()
                } else if (cantMoveToTop && cantMoveToRight && isNotLastRow_AND_botSquareInWArrayIsSet) {
                    do {
                        moveToNextSquareOrRestart()
                    } while (currentSquareInWArrayIsUnset)
                    start()
                } else if (cantMoveToTop && cantMoveToRight && isLastRow_OR_botSquareInWArrayIsUnset) {
                    endMazeIfPossibleAndStart()
                } else if (cantMoveToTop && isNotLastCol && rightSquareInWArrayIsUnset && isNotLastRow_AND_botSquareInWArrayIsSet) {
                    moveToTheRightSquareByOpeningTheRightWallOrTheBotWall()
                } else if (cantMoveToTop && isNotLastCol && rightSquareInWArrayIsUnset && isNotLastRow && botSquareInWArrayIsUnset) {
                    when (rnd(2)) {
                        1 -> moveToTheRightSquareByOpeningTheRightWallOrTheBotWall()
                        2 -> endMazeIfPossibleAndStart()
                        else -> doNothing()
                    }
                } else if (topSquareInWArrayIsSet && isNotLastCol && rightSquareInWArrayIsUnset && isLastRow) {
                    moveToTheTopSquareByOpeningTheWall()
                } else if (cantMoveToRight) {
                    m720()
                } else if (isNotLastRow_AND_botSquareInWArrayIsSet) {
                    when (rnd(2)) {
                        1 -> moveToTheTopSquareByOpeningTheWall()
                        2 -> moveToTheRightSquareByOpeningTheRightWallOrTheBotWall()
                        else -> doNothing()
                    }
                } else {
                    when (rnd(3)) {
                        1 -> moveToTheTopSquareByOpeningTheWall()
                        2 -> moveToTheRightSquareByOpeningTheRightWallOrTheBotWall()
                        3 -> endMazeIfPossibleAndStart()
                        else -> doNothing()
                    }
                }
            }
        }

        private fun m720() {
            if (!finished) {
                if (cantOpenBot) {
                    moveToTheTopSquareByOpeningTheWall()
                } else {
                    when (rnd(2)) {
                        1 -> moveToTheTopSquareByOpeningTheWall()
                        2 -> endMazeIfPossibleAndStart()
                        else -> doNothing()
                    }
                }
            }
        }

        private fun endMazeIfPossibleAndStart() {
            if (!finished) {
                if (isLastRow_AND_mazeHasNoEnd) {
                    mazeHasAnEnd = true

                    if (currentMazeSquareIsBlocked) {
                        openBot()
                        moveToTheFirstSquareOfTheMaze()
                        while (currentSquareInWArrayIsUnset) {
                            moveToNextSquareOrRestart()
                        }
                    } else {
                        openRightAndBot()
                        do {
                            moveToNextSquareOrRestart()
                        } while (currentSquareInWArrayIsUnset)
                    }
                } else {
                    moveToTheBotSquareByOpeningTheBotWallOrTheRightWall()
                }
                start()
            }
        }

        private val rightSquare get() = col + 1
        private val leftSquare get() = col - 1
        private val botSquare get() = row + 1
        private val topSquare get() = row - 1

        private val isFirstCol get() = col == 1
        private val isFirstRow get() = row == 1
        private val isLastCol get() = col == width
        private val isNotLastCol get() = !isLastCol
        private val isLastRow get() = row == height
        private val isNotLastRow get() = !isLastRow

        private val currentSquareInWArrayIsUnset get() = wArray[col][row] == 0
        private val rightSquareInWArrayIsUnset get() = wArray[rightSquare][row] == 0
        private val leftSquareInWArrayIsUnset get() = wArray[leftSquare][row] == 0
        private val botSquareInWArrayIsUnset get() = wArray[col][botSquare] == 0
        private val topSquareInWArrayIsUnset get() = wArray[col][topSquare] == 0

        private val rightSquareInWArrayIsSet get() = !rightSquareInWArrayIsUnset
        private val leftSquareInWArrayIsSet get() = !leftSquareInWArrayIsUnset
        private val botSquareInWArrayIsSet get() = !botSquareInWArrayIsUnset
        private val topSquareInWArrayIsSet get() = !topSquareInWArrayIsUnset

        private val cantMoveToLeft get() = isFirstCol || leftSquareInWArrayIsSet
        private val cantMoveToTop get() = isFirstRow || topSquareInWArrayIsSet
        private val cantMoveToRight get() = isLastCol || rightSquareInWArrayIsSet
        private val isNotLastRow_AND_botSquareInWArrayIsSet get() = isNotLastRow && botSquareInWArrayIsSet
        private val isLastRow_OR_botSquareInWArrayIsUnset get() = isLastRow || botSquareInWArrayIsUnset

        private val currentMazeSquareIsBlocked get() = maze.array[col][row] == Maze.SquareType.BLOCKED

        private val isLastRow_AND_mazeHasAnEnd get() = isLastRow && mazeHasAnEnd
        private val isLastRow_AND_mazeHasNoEnd get() = isLastRow && !mazeHasAnEnd

        private val cantOpenBot get() = isLastRow_AND_mazeHasAnEnd || isNotLastRow_AND_botSquareInWArrayIsSet

        private fun moveToFirstCol() {
            col = 1
        }

        private fun moveToFirstRow() {
            row = 1
        }

        private fun moveToTheRightSquare() {
            col++
        }

        private fun moveToTheBotSquare() {
            row++
        }

        private fun moveToNextSquareOrRestart() {
            if (isNotLastCol) moveToTheRightSquare()
            else if (isNotLastRow) moveToTheStartOfTheNextLineBotSquare()
            else moveToTheFirstSquareOfTheMaze()
        }

        private fun moveToTheStartOfTheNextLineBotSquare() {
            moveToFirstCol()
            moveToTheBotSquare()
        }

        private fun moveToTheFirstSquareOfTheMaze() {
            moveToFirstCol()
            moveToFirstRow()
        }

        private fun moveToTheRightSquareByOpeningTheRightWallOrTheBotWall() {
            if (currentMazeSquareIsBlocked) openRight()
            else openRightAndBot()
            moveToTheRightSquare()
            setNumberOfOpenedPathsToCurrentSquareInWArray()
            cantGoLeft()
        }

        private fun moveToTheLeftSquareByOpeningTheWall() {
            col--
            openRight()
            setNumberOfOpenedPathsToCurrentSquareInWArray()
            start()
        }

        private fun moveToTheBotSquareByOpeningTheBotWallOrTheRightWall() {
            if (currentMazeSquareIsBlocked) openBot()
            else openRightAndBot()
            moveToTheBotSquare()
            setNumberOfOpenedPathsToCurrentSquareInWArray()
        }

        private fun moveToTheTopSquareByOpeningTheWall() {
            row--
            openBot()
            setNumberOfOpenedPathsToCurrentSquareInWArray()
            start()
        }

        private fun setNumberOfOpenedPathsToCurrentSquareInWArray() {
            wArray[col][row] = numberOfOpenedPaths
            numberOfOpenedPaths++
            if (numberOfOpenedPaths == width * height + 1) {
                finished = true
            }
        }

        private fun openRight() {
            maze.array[col][row] = Maze.SquareType.OPEN_RIGHT
        }

        private fun openBot() {
            maze.array[col][row] = Maze.SquareType.OPEN_BOT
        }

        private fun openRightAndBot() {
            maze.array[col][row] = Maze.SquareType.FULLY_OPEN
        }

        fun buildMazeAsString(): String {
            val result = StringBuilder()
            for (i in 1..width) {
                if (i == startingPosition) {
                    result.append("+  ")
                } else {
                    result.append("+--")
                }
            }
            result.append("+").append("\n")

            for (j in 1..height) {
                val line1 = StringBuilder()
                val line2 = StringBuilder()
                line1.append("|")
                line2.append("+")

                for (i in 1..width) {
                    line1.append(maze.array[i][j].line1)
                    line2.append(maze.array[i][j].line2)
                }

                result.append(line1).append(" ").append("\n")
                result.append(line2).append("\n")
            }

            return result.toString()
        }
    }
}

fun main(args: Array<String>) {
    val colsArg = args.getOrNull(0) ?: System.getenv("cols")
    val cols = colsArg?.toInt() ?: 10
    val rowsArg = args.getOrNull(1) ?: args.getOrNull(0) ?: System.getenv("rows")
    val rows = rowsArg?.toInt() ?: 10
    println(Amazing().doit(cols, rows))
}
