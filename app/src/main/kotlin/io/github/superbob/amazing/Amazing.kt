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
        result.append(context.buildMazeStart())

        m270(context)

        result.append(context.buildMazeAsString())

        return result.toString()
    }

    fun moveToNextSquare(context: MazeBuildContext) {
        context.doThis {
            if (notReachedTheRight()) {
                moveToTheRightSquare()
            } else if (notReachedTheBot()) {
                moveToTheStartOfTheNextLineBotSquare()
            } else {
                moveToTheFirstSquareOfTheMaze()
            }
            if (currentSquareInWArrayIsSet()) {
                m270(context)
            }
            moveToNextSquare(context)
        }
    }

    fun m270(context: MazeBuildContext) {
        context.doThis {
            if (colIsReset() || leftSquareInWArrayIsSet()) {
                m600(context)
            } else if (rowIsReset() || topSquareInWArrayIsSet()) {
                m430(context)
            } else if (reachedTheRight() || rightSquareInWArrayIsSet()) {
                m350(context)
            } else {
                random3(::openLeftSquareToTheCurrentOneByTheRightSize, ::m980, ::openMazeToTheRightIfBlockedOrFullyOpen, ::m350)(context)
            }
        }
    }

    fun m350(context: MazeBuildContext) {
        context.doThis {
            activateQIfNotZAndReachedTheBot()
            if (zAndSIsVertical() || (notReachedTheBot() && botSquareInWArrayIsSet())) {
                random2(::openLeftSquareToTheCurrentOneByTheRightSize, ::m980, ::m430)(context)
            } else {
                random3(::openLeftSquareToTheCurrentOneByTheRightSize, ::m980, ::m1090, ::m410)(context)
            }
        }
    }

    fun m410(context: MazeBuildContext) {
        context.doThis {
            random2(::openLeftSquareToTheCurrentOneByTheRightSize, ::m980, ::m430)(context)
        }
    }

    fun m430(context: MazeBuildContext) {
        context.doThis {
            activateQIfNotZAndReachedTheBot()
            if (zAndSIsVertical() && (reachedTheRight() || rightSquareInWArrayIsSet())) {
                openLeftSquareToTheCurrentOneByTheRightSize(context)
            } else if (zAndSIsVertical() && !(reachedTheRight() || rightSquareInWArrayIsSet())) {
                random2(::openLeftSquareToTheCurrentOneByTheRightSize, ::openMazeToTheRightIfBlockedOrFullyOpen, ::m530)(context)
            } else if ((reachedTheRight() || rightSquareInWArrayIsSet()) && (notReachedTheBot() && botSquareInWArrayIsSet())) {
                openLeftSquareToTheCurrentOneByTheRightSize(context)
            } else if ((reachedTheRight() || rightSquareInWArrayIsSet()) && !(notReachedTheBot() && botSquareInWArrayIsSet())) {
                random2(::openLeftSquareToTheCurrentOneByTheRightSize, ::m1090, ::openLeftSquareToTheCurrentOneByTheRightSize)(context)
            } else if (notReachedTheBot() && botSquareInWArrayIsSet()) {
                random2(::openLeftSquareToTheCurrentOneByTheRightSize, ::openMazeToTheRightIfBlockedOrFullyOpen, ::m530)(context)
            } else {
                random3(::openLeftSquareToTheCurrentOneByTheRightSize, ::openMazeToTheRightIfBlockedOrFullyOpen, ::m1090, ::m510)(context)
            }
        }
    }

    fun m510(context: MazeBuildContext) {
        context.doThis {
            random2(::openLeftSquareToTheCurrentOneByTheRightSize, ::openMazeToTheRightIfBlockedOrFullyOpen, ::m530)(context)
        }
    }

    fun m530(context: MazeBuildContext) {
        context.doThis {
            activateQIfNotZAndReachedTheBot()
            if (notReachedTheBot()) {
                if (botSquareInWArrayIsSet()) {
                    openLeftSquareToTheCurrentOneByTheRightSize(context)
                } else {
                    random2(::openLeftSquareToTheCurrentOneByTheRightSize, ::m1090, ::openLeftSquareToTheCurrentOneByTheRightSize)(context)
                }
            } else if (zIsTrue()) {
                openLeftSquareToTheCurrentOneByTheRightSize(context)
            } else {
                random2(::openLeftSquareToTheCurrentOneByTheRightSize, ::m1090, ::openLeftSquareToTheCurrentOneByTheRightSize)(context)
            }
        }
    }

    fun m600(context: MazeBuildContext) {
        context.doThis {
            activateQIfNotZAndReachedTheBot()
            if ((zAndSIsVertical() && !(reachedTheRight() || rightSquareInWArrayIsSet())) && (topSquareInWArrayIsUnset())) {
                random2(::m980, ::openMazeToTheRightIfBlockedOrFullyOpen, ::m720)(context)
            } else if ((zAndSIsVertical() && !(reachedTheRight() || rightSquareInWArrayIsSet())) && !(topSquareInWArrayIsUnset())) {
                openMazeToTheRightIfBlockedOrFullyOpen(context)
            } else if (zAndSIsVertical() && (reachedTheRight() || rightSquareInWArrayIsSet()) && topSquareInWArrayIsSet()) {
                moveToNextSquare(context)
            } else if (rowIsReset() || topSquareInWArrayIsSet()) {
                if (reachedTheRight() || rightSquareInWArrayIsSet()) {
                    if (notReachedTheBot() && botSquareInWArrayIsSet()) {
                        moveToNextSquare(context)
                    } else {
                        m1090(context)
                    }
                } else if (notReachedTheBot()) {
                    if (botSquareInWArrayIsSet()) {
                        openMazeToTheRightIfBlockedOrFullyOpen(context)
                    } else {
                        random2(::openMazeToTheRightIfBlockedOrFullyOpen, ::m1090, ::openMazeToTheRightIfBlockedOrFullyOpen)(context)
                    }
                } else if (notReachedTheRight() && rightSquareInWArrayIsUnset() && reachedTheBot()) {
                    increaseC()
                    setMazeTopSquare(Maze.SquareType.OPEN_BOT)
                    moveToTheTopSquare()
                    if (isFinished()) {
                        setIsFinished()
                    } else {
                        setQToFalse()
                        m270(context)
                    }
                }
            } else if (reachedTheRight() || rightSquareInWArrayIsSet()) {
                m720(context)
            } else if (notReachedTheBot() && botSquareInWArrayIsSet()) {
                random2(::m980, ::openMazeToTheRightIfBlockedOrFullyOpen, ::m720)(context)
            } else {
                random3(::m980, ::openMazeToTheRightIfBlockedOrFullyOpen, ::m1090, ::m700)(context)
            }
        }
    }

    fun m700(context: MazeBuildContext) {
        context.doThis {
            random2(::m980, ::openMazeToTheRightIfBlockedOrFullyOpen, ::m720)(context)
        }
    }

    fun m720(context: MazeBuildContext) {
        context.doThis {
            activateQIfNotZAndReachedTheBot()
            if (zAndSIsVertical()) {
                m980(context)
            } else if (notReachedTheBot() && botSquareInWArrayIsSet()) {
                m980(context)
            } else {
                random2(::m980, ::m1090, ::m980)(context)
            }
        }
    }

    fun openLeftSquareToTheCurrentOneByTheRightSize(context: MazeBuildContext) {
        context.doThis {
            setCToLeftSquareInWArray()

            setMazeLeftSquare(Maze.SquareType.OPEN_RIGHT)
            moveToTheLeftSquare()
            if (isFinished()) {
                setIsFinished()
            } else {
                setQToFalse()
                m270(context)
            }
        }
    }

    fun m980(context: MazeBuildContext) {
        context.doThis {
            setCToTopSquareInWArray()
            setMazeTopSquare(Maze.SquareType.OPEN_BOT)
            moveToTheTopSquare()
            if (isFinished()) {
                setIsFinished()
            } else {
                setQToFalse()
                m270(context)
            }
        }
    }

    fun openMazeToTheRightIfBlockedOrFullyOpen(context: MazeBuildContext) {
        context.doThis {
            setCToRightSquareInWArray()

            if (currentMazeSquareIsBlocked()) {
                setMazeCurrentSquare(Maze.SquareType.OPEN_RIGHT)
            } else {
                setMazeCurrentSquare(Maze.SquareType.FULLY_OPEN)
            }
            moveToTheRightSquare()
            if (isFinished()) {
                setIsFinished()
            } else {
                m600(context)
            }
        }
    }

    fun m1090(context: MazeBuildContext) {
        context.doThis {
            if (qIsFalse()) {
                setCToBotSquareInWArray()
            }

            if (qIsTrueWhichMeansNotZAndReachedTheBot()) {
                setZToTrue()
                setQToFalse()

                if (currentMazeSquareIsBlocked()) {
                    setMazeCurrentSquare(Maze.SquareType.OPEN_BOT)
                    moveToTheFirstSquareOfTheMaze()

                    if (currentSquareInWArrayIsUnset()) {
                        moveToNextSquare(context)
                    } else {
                        m270(context)
                    }
                } else {
                    setMazeCurrentSquare(Maze.SquareType.FULLY_OPEN)
                    moveToNextSquare(context)
                }
            } else {
                if (currentMazeSquareIsBlocked()) {
                    setMazeCurrentSquare(Maze.SquareType.OPEN_BOT)
                } else {
                    setMazeCurrentSquare(Maze.SquareType.FULLY_OPEN)
                }
                moveToTheBotSquare()
                if (isFinished()) {
                    setIsFinished()
                } else {
                    m270(context)
                }
            }
        }
    }

    private fun random2(
            ifOne: (MazeBuildContext) -> Unit,
            ifTwo: (MazeBuildContext) -> Unit,
            ifThree: (MazeBuildContext) -> Unit,
    ): (MazeBuildContext) -> Unit {
        val x = rnd(2)
        return when (x) {
            1 -> ifOne
            2 -> ifTwo
            else -> ifThree
        }
    }

    private fun random3(
            ifOne: (MazeBuildContext) -> Unit,
            ifTwo: (MazeBuildContext) -> Unit,
            ifThree: (MazeBuildContext) -> Unit,
            ifFour: (MazeBuildContext) -> Unit,
    ): (MazeBuildContext) -> Unit {
        val x = rnd(3)
        return when (x) {
            1 -> ifOne
            2 -> ifTwo
            3 -> ifThree
            else -> ifFour
        }
    }

    class Maze(
            width: Int,
            height: Int,
    ) {
        val array: Array<Array<SquareType>> = Array(width + 1) { Array(height + 1) { SquareType.BLOCKED } }

        enum class SquareType(val line1: String, val line2: String) {
            BLOCKED("  |", "+--"),
            OPEN_BOT("  |", "+  "),
            OPEN_RIGHT("   ", "+--"),
            FULLY_OPEN("   ", "+  ")
        }
    }

    class MazeBuildContext(
            private val random: Random,
            private var width: Int,
            private var height: Int,
    ) {
        private val wArray: Array<IntArray> = Array(width + 1) { IntArray(height + 1) }
        private var maze: Maze = Maze(width + 1, height + 1)
        private var q: Boolean = false
        private var z: Boolean = false
        private var col: Int = 0
        private var row: Int = 1
        private var c: Int = 2
        var finished: Boolean = false

        private fun rnd(count: Int): Int {
            return (count * random.nextFloat()).toInt() + 1
        }

        init {
            val startingPosition = rnd(width)
            col = startingPosition
            wArray[startingPosition][1] = 1
        }
        private val rightSquare get() = col + 1
        private val leftSquare get() = col - 1
        private val botSquare get() = row + 1
        private val topSquare get() = row - 1

        fun setQToFalse() { q = false }
        fun setZToTrue() { z = true }
        fun setIsFinished() { finished = true }

        fun colIsReset() = col == 1
        fun rowIsReset() = row == 1
        fun reachedTheRight() = col == width
        fun notReachedTheRight() = !reachedTheRight()
        fun reachedTheBot() = row == height
        fun notReachedTheBot() = !reachedTheBot()

        private fun resetCol() {
            col = 1
        }

        private fun resetRow() {
            row = 1
        }

        fun moveToTheRightSquare() {
            col++
        }

        fun moveToTheLeftSquare() {
            col--
        }

        fun moveToTheBotSquare() {
            row++
        }

        fun moveToTheTopSquare() {
            row--
        }

        fun moveToTheStartOfTheNextLineBotSquare() {
            resetCol()
            moveToTheBotSquare()
        }

        fun moveToTheFirstSquareOfTheMaze() {
            resetCol()
            resetRow()
        }


        fun increaseC() {
            c++
        }

        fun setCToRightSquareInWArray() {
            wArray[rightSquare][row] = c
            increaseC()
        }

        fun setCToLeftSquareInWArray() {
            wArray[leftSquare][row] = c
            increaseC()
        }

        fun setCToBotSquareInWArray() {
            wArray[col][botSquare] = c
            increaseC()
        }

        fun setCToTopSquareInWArray() {
            wArray[col][topSquare] = c
            increaseC()
        }

        fun setMazeCurrentSquare(squareType: Maze.SquareType) {
            maze.array[col][row] = squareType
        }

        fun setMazeLeftSquare(squareType: Maze.SquareType) {
            maze.array[leftSquare][row] = squareType
        }

        fun setMazeTopSquare(squareType: Maze.SquareType) {
            maze.array[col][topSquare] = squareType
        }

        fun currentSquareInWArrayIsUnset() = wArray[col][row] == 0
        fun rightSquareInWArrayIsUnset() = wArray[rightSquare][row] == 0
        fun topSquareInWArrayIsUnset() = wArray[col][topSquare] == 0

        fun currentSquareInWArrayIsSet() = wArray[col][row] != 0
        fun rightSquareInWArrayIsSet() = wArray[rightSquare][row] != 0
        fun leftSquareInWArrayIsSet() = wArray[leftSquare][row] != 0
        fun botSquareInWArrayIsSet() = wArray[col][botSquare] != 0
        fun topSquareInWArrayIsSet() = wArray[col][topSquare] != 0

        fun currentMazeSquareIsBlocked() = maze.array[col][row] == Maze.SquareType.BLOCKED

        fun doThis(block: MazeBuildContext.() -> Unit) {
            if (!finished) {
                apply { block(this) }
            }
        }

        fun isFinished() = c == width * height + 1


        fun zAndSIsVertical() = z && reachedTheBot()
        fun activateQIfNotZAndReachedTheBot() {
            if (!z && reachedTheBot()) {
                q = true
            }
        }

        fun qIsTrueWhichMeansNotZAndReachedTheBot() = q
        fun qIsFalse() = !q
        fun zIsTrue() = z

        fun buildMazeStart(): String {
            val result = StringBuilder()
            for (i in 1..width) {
                if (i == col) {
                    result.append("+  ")
                } else {
                    result.append("+--")
                }
            }
            result.append("+")
            result.append("\n")
            return result.toString()
        }

        fun buildMazeAsString(): String {
            val result = StringBuilder()
            for (j in 1..height) {
                result.append("|")

                val line1 = StringBuilder()
                val line2 = StringBuilder()

                for (i in 1..width) {
                    line1.append(maze.array[i][j].line1)
                    line2.append(maze.array[i][j].line2)
                }

                result.append(line1)
                result.append(" ")
                result.append("\n")

                result.append(line2)
                result.append("+")
                result.append("\n")
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
