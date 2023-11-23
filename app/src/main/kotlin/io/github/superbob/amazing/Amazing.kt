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

        val wArray = Array(width + 1) { IntArray(height + 1) }
        val maze = Maze(width + 1, height + 1)

        var mazeOpening = rnd(width)

        wArray[mazeOpening][1] = 1
        buildMazeStart(mazeOpening, result, width)

        val context = MazeBuildContext(width, height, wArray, maze, col = mazeOpening)

        m270(context)

        result.append(buildMaze(context))

        return result.toString()
    }

    fun moveToNextSquare(context: MazeBuildContext) {
        context.doThis {
            if (col != width) {
                col++
            } else if (row != height) {
                col = 1
                row++
            } else {
                col = 1
                row = 1
            }
            if (wArray[col][row] != 0) {
                m270(context)
            }
            moveToNextSquare(context)
        }
    }

    fun m270(context: MazeBuildContext) {
        context.doThis {
            if (col == 1 || wArray[col - 1][row] != 0) {
                m600(context)
            } else if (row == 1 || wArray[col][row - 1] != 0) {
                m430(context)
            } else if (col == width || wArray[col + 1][row] != 0) {
                m350(context)
            } else {
                random3(::m940, ::m980, ::m1020, ::m350)(context)
            }
        }
    }

    fun m350(context: MazeBuildContext) {
        context.doThis {
            activateQIfNeeded()
            if (zAndSIsVertical() || (row != height && wArray[col][row + 1] != 0)) {
                random2(::m940, ::m980, ::m430)(context)
            } else {
                random3(::m940, ::m980, ::m1090, ::m410)(context)
            }
        }
    }

    fun m410(context: MazeBuildContext) {
        context.doThis {
            random2(::m940, ::m980, ::m430)(context)
        }
    }

    fun m430(context: MazeBuildContext) {
        context.doThis {
            activateQIfNeeded()
            if (zAndSIsVertical() && (col == width || wArray[col + 1][row] != 0)) {
                m940(context)
            } else if (zAndSIsVertical() && !(col == width || wArray[col + 1][row] != 0)) {
                random2(::m940, ::m1020, ::m530)(context)
            } else if ((col == width || wArray[col + 1][row] != 0) && (row != height && wArray[col][row + 1] != 0)) {
                m940(context)
            } else if ((col == width || wArray[col + 1][row] != 0) && !(row != height && wArray[col][row + 1] != 0)) {
                random2(::m940, ::m1090, ::m940)(context)
            } else if (row != height && wArray[col][row + 1] != 0) {
                random2(::m940, ::m1020, ::m530)(context)
            } else {
                random3(::m940, ::m1020, ::m1090, ::m510)(context)
            }
        }
    }

    fun m510(context: MazeBuildContext) {
        context.doThis {
            random2(::m940, ::m1020, ::m530)(context)
        }
    }

    fun m530(context: MazeBuildContext) {
        context.doThis {
            if (row != height) {
                if (wArray[col][row + 1] != 0) {
                    m940(context)
                } else {
                    random2(::m940, ::m1090, ::m940)(context)
                }
            } else if (z) {
                m940(context)
            } else {
                q = true
                random2(::m940, ::m1090, ::m940)(context)
            }
        }
    }

    fun m600(context: MazeBuildContext) {
        context.doThis {
            activateQIfNeeded()
            if ((zAndSIsVertical() && !(col == width || wArray[col + 1][row] != 0)) && (wArray[col][row - 1] == 0)) {
                random2(::m980, ::m1020, ::m720)(context)
            } else if ((zAndSIsVertical() && !(col == width || wArray[col + 1][row] != 0)) && !(wArray[col][row - 1] == 0)) {
                m1020(context)
            } else if (zAndSIsVertical() && (col == width || wArray[col + 1][row] != 0) && wArray[col][row - 1] != 0) {
                moveToNextSquare(context)
            } else if (row == 1 || wArray[col][row - 1] != 0) {
                if (col == width || wArray[col + 1][row] != 0) {
                    if (row != height && wArray[col][row + 1] != 0) {
                        moveToNextSquare(context)
                    } else {
                        m1090(context)
                    }
                } else if (row != height) {
                    if (wArray[col][row + 1] != 0) {
                        m1020(context)
                    } else {
                        random2(::m1020, ::m1090, ::m1020)(context)
                    }
                } else {
                    m990(context)
                }
            } else if (col == width || wArray[col + 1][row] != 0) {
                m720(context)
            } else if (row != height && wArray[col][row + 1] != 0) {
                random2(::m980, ::m1020, ::m720)(context)
            } else {
                random3(::m980, ::m1020, ::m1090, ::m700)(context)
            }
        }
    }

    fun m700(context: MazeBuildContext) {
        context.doThis {
            random2(::m980, ::m1020, ::m720)(context)
        }
    }

    fun m720(context: MazeBuildContext) {
        context.doThis {
            activateQIfNeeded()
            if (zAndSIsVertical()) {
                m980(context)
            } else if (row != height && wArray[col][row + 1] != 0) {
                m980(context)
            } else {
                random2(::m980, ::m1090, ::m980)(context)
            }
        }
    }

    fun m940(context: MazeBuildContext) {
        context.doThis {
            wArray[col - 1][row] = c
            c++

            maze.array[col - 1][row] = Maze.Square.OPEN_RIGHT
            col--
            if (context.isFinished()) {
                context.finished = true
            } else {
                q = false
                m270(context)
            }
        }
    }

    fun m980(context: MazeBuildContext) {
        context.doThis {
            wArray[col][row - 1] = c
            m990(context)
        }
    }

    fun m990(context: MazeBuildContext) {
        context.doThis {
            c++

            maze.array[col][row - 1] = Maze.Square.OPEN_BOT
            row--
            if (context.isFinished()) {
                context.finished = true
            } else {
                q = false
                m270(context)
            }
        }
    }

    fun m1020(context: MazeBuildContext) {
        context.doThis {
            wArray[col + 1][row] = c
            c++

            if (maze.array[col][row] == Maze.Square.BLOCKED) {
                maze.array[col][row] = Maze.Square.OPEN_RIGHT
            } else {
                maze.array[col][row] = Maze.Square.FULLY_OPEN
            }
            col++
            if (context.isFinished()) {
                context.finished = true
            } else {
                m600(context)
            }
        }
    }

    fun m1090(context: MazeBuildContext) {
        context.doThis {
            if (!q) {
                wArray[col][row + 1] = c
                c++
            }

            if (q) {
                z = true
                q = false

                if (maze.array[col][row] == Maze.Square.BLOCKED) {
                    maze.array[col][row] = Maze.Square.OPEN_BOT
                    col = 1
                    row = 1

                    if (wArray[col][row] == 0) {
                        moveToNextSquare(context)
                    } else {
                        m270(context)
                    }
                } else {
                    maze.array[col][row] = Maze.Square.FULLY_OPEN
                    moveToNextSquare(context)
                }
            } else {
                if (maze.array[col][row] == Maze.Square.BLOCKED) {
                    maze.array[col][row] = Maze.Square.OPEN_BOT
                } else {
                    maze.array[col][row] = Maze.Square.FULLY_OPEN
                }
                row++
                if (context.isFinished()) {
                    context.finished = true
                } else {
                    m270(context)
                }
            }
        }
    }

    private fun random2(
        ifOne: (MazeBuildContext) -> Unit,
        ifTwo: (MazeBuildContext) -> Unit,
        ifThree: (MazeBuildContext) -> Unit
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
        ifFour: (MazeBuildContext) -> Unit
    ): (MazeBuildContext) -> Unit {
        val x = rnd(3)
        return when (x) {
            1 -> ifOne
            2 -> ifTwo
            3 -> ifThree
            else -> ifFour
        }
    }

    private fun buildMazeStart(mazeOpening: Int, result: StringBuilder, horizontal: Int) {
        for (i in 1..horizontal) {
            if (i == mazeOpening) {
                result.append("+  ")
            } else {
                result.append("+--")
            }
        }
        result.append("+")
        result.append("\n")
    }

    private fun buildMaze(context: MazeBuildContext): String =
        with (context) {
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

    class Maze(
        width: Int,
        height: Int,
    ) {
        val array: Array<Array<Square>> = Array(width + 1) { Array(height + 1) { Square.BLOCKED } }

        enum class Square(val line1: String, val line2: String) {
            BLOCKED("  |", "+--"),
            OPEN_BOT("  |", "+  "),
            OPEN_RIGHT("   ", "+--"),
            FULLY_OPEN("   ", "+  ")
        }
    }

    class MazeBuildContext(
        var width: Int,
        var height: Int,
        var wArray: Array<IntArray>,
        var maze: Maze,
        var q: Boolean = false,
        var z: Boolean = false,
        var col: Int,
        var row: Int = 1,
        var c: Int = 2,
        var finished: Boolean = false,
    ) {

        fun doThis(block: MazeBuildContext.() -> Unit) {
            if (isNotFinished()) {
                apply { block(this) }
            }
        }

        fun isFinished() = c == width * height + 1
        fun isNotFinished() = !finished


        fun zAndSIsVertical() = z && row == height
        fun activateQIfNeeded() {
            if (!z && row == height) {
                q = true
            }
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
