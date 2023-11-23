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
        start(context)
        result.append(context.buildMazeAsString())
        return result.toString()
    }

    fun start(context: MazeBuildContext) {
        context.doThis {
            if (cantMoveToLeft) cantGoLeft(context)
            else if (cantMoveToTop) m430(context)
            else if (cantMoveToRight) m350(context)
            else {
                when (rnd(3)) {
                    1 -> moveToTheLeftSquareByOpeningTheWallAndStart(context)
                    2 -> moveToTheTopSquareByOpeningTheWallAndStart(context)
                    3 -> moveToTheRightSquareByOpeningTheRightWallOrTheBotAndStart(context)
                    else -> m350(context)
                }
            }
        }
    }

    fun m350(context: MazeBuildContext) {
        context.doThis {
            val cantOpenBot = isLastRow_AND_mazeHasAnEnd || isNotLastRow_AND_botSquareInWArrayIsSet
            if (cantOpenBot) {
                when (rnd(2)) {
                    1 -> moveToTheLeftSquareByOpeningTheWallAndStart(context)
                    2 -> moveToTheTopSquareByOpeningTheWallAndStart(context)
                    else -> m430(context)
                }
            } else {
                when (rnd(3)) {
                    1 -> moveToTheLeftSquareByOpeningTheWallAndStart(context)
                    2 -> moveToTheTopSquareByOpeningTheWallAndStart(context)
                    3 -> m1090AndStart(context)
                    else -> {
                        when (rnd(2)) {
                            1 -> moveToTheLeftSquareByOpeningTheWallAndStart(context)
                            2 -> moveToTheTopSquareByOpeningTheWallAndStart(context)
                            else -> m430(context)
                        }
                    }
                }
            }
        }
    }

    fun m430(context: MazeBuildContext) {
        context.doThis {
            if (mazeHasAnEnd && isLastRow && cantMoveToRight) {
                moveToTheLeftSquareByOpeningTheWallAndStart(context)
            } else if (mazeHasAnEnd && isLastRow && isNotLastCol && rightSquareInWArrayIsUnset) {
                when (rnd(2)) {
                    1 -> moveToTheLeftSquareByOpeningTheWallAndStart(context)
                    2 -> moveToTheRightSquareByOpeningTheRightWallOrTheBotAndStart(context)
                    else -> m530(context)
                }
            } else if (cantMoveToRight && isNotLastRow_AND_botSquareInWArrayIsSet) {
                moveToTheLeftSquareByOpeningTheWallAndStart(context)
            } else if ((cantMoveToRight) && isLastRow_OR_botSquareInWArrayIsUnset) {
                when (rnd(2)) {
                    1 -> moveToTheLeftSquareByOpeningTheWallAndStart(context)
                    2 -> m1090AndStart(context)
                    else -> moveToTheLeftSquareByOpeningTheWallAndStart(context)
                }
            } else if (isNotLastRow_AND_botSquareInWArrayIsSet) {
                when (rnd(2)) {
                    1 -> moveToTheLeftSquareByOpeningTheWallAndStart(context)
                    2 -> moveToTheRightSquareByOpeningTheRightWallOrTheBotAndStart(context)
                    else -> m530(context)
                }
            } else {
                when (rnd(3)) {
                    1 -> moveToTheLeftSquareByOpeningTheWallAndStart(context)
                    2 -> moveToTheRightSquareByOpeningTheRightWallOrTheBotAndStart(context)
                    3 -> m1090AndStart(context)
                    else -> {
                        when (rnd(2)) {
                            1 -> moveToTheLeftSquareByOpeningTheWallAndStart(context)
                            2 -> moveToTheRightSquareByOpeningTheRightWallOrTheBotAndStart(context)
                            else -> m530(context)
                        }
                    }
                }
            }
        }
    }

    fun m530(context: MazeBuildContext) {
        context.doThis {
            if (isNotLastRow_AND_botSquareInWArrayIsSet || (!(isNotLastRow && botSquareInWArrayIsUnset) && mazeHasAnEnd)) {
                moveToTheLeftSquareByOpeningTheWallAndStart(context)
            } else {
                when (rnd(2)) {
                    1 -> moveToTheLeftSquareByOpeningTheWallAndStart(context)
                    2 -> m1090AndStart(context)
                    else -> moveToTheLeftSquareByOpeningTheWallAndStart(context)
                }
            }
        }
    }

    fun cantGoLeft(context: MazeBuildContext) {
        context.doThis {
            if (mazeHasAnEnd && isLastRow && isNotLastCol && rightSquareInWArrayIsUnset && topSquareInWArrayIsUnset) {
                when (rnd(2)) {
                    1 -> moveToTheTopSquareByOpeningTheWallAndStart(context)
                    2 -> moveToTheRightSquareByOpeningTheRightWallOrTheBotAndStart(context)
                    else -> m720(context)
                }
            } else if (mazeHasAnEnd && isLastRow && isNotLastCol && rightSquareInWArrayIsUnset && topSquareInWArrayIsSet) {
                moveToTheRightSquareByOpeningTheRightWallOrTheBotAndStart(context)
            } else if ((mazeHasAnEnd && isLastRow && cantMoveToRight && topSquareInWArrayIsSet)) {
                do {
                    moveToNextSquareOrRestart()
                } while (currentSquareInWArrayIsUnset)
                start(context)
            } else if (cantMoveToTop && cantMoveToRight && isNotLastRow_AND_botSquareInWArrayIsSet) {
                do {
                    moveToNextSquareOrRestart()
                } while (currentSquareInWArrayIsUnset)
                start(context)
            } else if (cantMoveToTop && cantMoveToRight && isLastRow_OR_botSquareInWArrayIsUnset) {
                m1090AndStart(context)
            } else if (cantMoveToTop && isNotLastCol && rightSquareInWArrayIsUnset && isNotLastRow_AND_botSquareInWArrayIsSet) {
                moveToTheRightSquareByOpeningTheRightWallOrTheBotAndStart(context)
            } else if (cantMoveToTop && isNotLastCol && rightSquareInWArrayIsUnset && isNotLastRow && botSquareInWArrayIsUnset) {
                when (rnd(2)) {
                    1 -> moveToTheRightSquareByOpeningTheRightWallOrTheBotAndStart(context)
                    2 -> m1090AndStart(context)
                    else -> moveToTheRightSquareByOpeningTheRightWallOrTheBotAndStart(context)
                }
            } else if (topSquareInWArrayIsSet && isNotLastCol && rightSquareInWArrayIsUnset && isLastRow) {
                moveToTheTopSquareByOpeningTheWall()
                start(context)
            } else if (cantMoveToRight) {
                m720(context)
            } else if (isNotLastRow_AND_botSquareInWArrayIsSet) {
                when (rnd(2)) {
                    1 -> moveToTheTopSquareByOpeningTheWallAndStart(context)
                    2 -> moveToTheRightSquareByOpeningTheRightWallOrTheBotAndStart(context)
                    else -> m720(context)
                }
            } else {
                when (rnd(3)) {
                    1 -> moveToTheTopSquareByOpeningTheWallAndStart(context)
                    2 -> moveToTheRightSquareByOpeningTheRightWallOrTheBotAndStart(context)
                    3 -> m1090AndStart(context)
                    else -> {
                        when (rnd(2)) {
                            1 -> moveToTheTopSquareByOpeningTheWallAndStart(context)
                            2 -> moveToTheRightSquareByOpeningTheRightWallOrTheBotAndStart(context)
                            else -> m720(context)
                        }
                    }
                }
            }
        }
    }

    fun m720(context: MazeBuildContext) {
        context.doThis {
            if (isLastRow_AND_mazeHasAnEnd || isNotLastRow_AND_botSquareInWArrayIsSet) {
                moveToTheTopSquareByOpeningTheWallAndStart(context)
            } else {
                when (rnd(2)) {
                    1 -> moveToTheTopSquareByOpeningTheWallAndStart(context)
                    2 -> m1090AndStart(context)
                    else -> moveToTheTopSquareByOpeningTheWallAndStart(context)
                }
            }
        }
    }

    fun moveToTheLeftSquareByOpeningTheWallAndStart(context: MazeBuildContext) {
        context.doThis {
            moveToTheLeftSquareByOpeningTheWall()
            start(context)
        }
    }

    fun moveToTheTopSquareByOpeningTheWallAndStart(context: MazeBuildContext) {
        context.doThis {
            moveToTheTopSquareByOpeningTheWall()
            start(context)
        }
    }

    fun moveToTheRightSquareByOpeningTheRightWallOrTheBotAndStart(context: MazeBuildContext) {
        context.doThis {
            moveToTheRightSquareByOpeningTheRightWallOrTheBotWall()
            cantGoLeft(context)
        }
    }

    fun m1090AndStart(context: MazeBuildContext) {
        context.doThis {
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
            start(context)
        }
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
        var mazeHasAnEnd: Boolean = false
        private var col: Int = 0
        private var row: Int = 1
        private var numberOfOpenedPaths: Int = 2
        var finished: Boolean = false

        private fun rnd(count: Int): Int {
            return (count * random.nextFloat()).toInt() + 1
        }

        init {
            startingPosition = rnd(width)
            col = startingPosition
            wArray[startingPosition][1] = 1
        }

        fun doThis(block: MazeBuildContext.() -> Unit) {
            if (!finished) {
                apply { block(this) }
            }
        }

        private val rightSquare get() = col + 1
        private val leftSquare get() = col - 1
        private val botSquare get() = row + 1
        private val topSquare get() = row - 1

        val isFirstCol get() = col == 1
        val isFirstRow get() = row == 1
        val isLastCol get() = col == width
        val isNotLastCol get() = !isLastCol
        val isLastRow get() = row == height
        val isNotLastRow get() = !isLastRow

        val currentSquareInWArrayIsUnset get() = wArray[col][row] == 0
        val rightSquareInWArrayIsUnset get() = wArray[rightSquare][row] == 0
        val leftSquareInWArrayIsUnset get() = wArray[leftSquare][row] == 0
        val botSquareInWArrayIsUnset get() = wArray[col][botSquare] == 0
        val topSquareInWArrayIsUnset get() = wArray[col][topSquare] == 0

        val rightSquareInWArrayIsSet get() = !rightSquareInWArrayIsUnset
        val leftSquareInWArrayIsSet get() = !leftSquareInWArrayIsUnset
        val botSquareInWArrayIsSet get() = !botSquareInWArrayIsUnset
        val topSquareInWArrayIsSet get() = !topSquareInWArrayIsUnset

        val cantMoveToLeft get() = isFirstCol || leftSquareInWArrayIsSet
        val cantMoveToTop get() = isFirstRow || topSquareInWArrayIsSet
        val cantMoveToRight get() = isLastCol || rightSquareInWArrayIsSet
        val isNotLastRow_AND_botSquareInWArrayIsSet get() = isNotLastRow && botSquareInWArrayIsSet
        val isLastRow_OR_botSquareInWArrayIsUnset get() = isLastRow || botSquareInWArrayIsUnset

        val currentMazeSquareIsBlocked get() = maze.array[col][row] == Maze.SquareType.BLOCKED

        val isLastRow_AND_mazeHasAnEnd get() = isLastRow && mazeHasAnEnd
        val isLastRow_AND_mazeHasNoEnd get() = isLastRow && !mazeHasAnEnd

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

        fun moveToNextSquareOrRestart() {
            if (isNotLastCol) moveToTheRightSquare()
            else if (isNotLastRow) moveToTheStartOfTheNextLineBotSquare()
            else moveToTheFirstSquareOfTheMaze()
        }

        private fun moveToTheStartOfTheNextLineBotSquare() {
            moveToFirstCol()
            moveToTheBotSquare()
        }

        fun moveToTheFirstSquareOfTheMaze() {
            moveToFirstCol()
            moveToFirstRow()
        }

        fun moveToTheRightSquareByOpeningTheRightWallOrTheBotWall() {
            if (currentMazeSquareIsBlocked) openRight()
            else openRightAndBot()
            moveToTheRightSquare()
            setNumberOfOpenedPathsToCurrentSquareInWArray()
        }

        fun moveToTheLeftSquareByOpeningTheWall() {
            col--
            openRight()
            setNumberOfOpenedPathsToCurrentSquareInWArray()
        }

        fun moveToTheBotSquareByOpeningTheBotWallOrTheRightWall() {
            if (currentMazeSquareIsBlocked) openBot()
            else openRightAndBot()
            moveToTheBotSquare()
            setNumberOfOpenedPathsToCurrentSquareInWArray()
        }

        fun moveToTheTopSquareByOpeningTheWall() {
            row--
            openBot()
            setNumberOfOpenedPathsToCurrentSquareInWArray()
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

        fun openBot() {
            maze.array[col][row] = Maze.SquareType.OPEN_BOT
        }

        fun openRightAndBot() {
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
