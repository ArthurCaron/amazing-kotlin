package io.github.superbob.amazing

import java.util.*

class Amazing(randomSeed: Long? = null) {
    private val random = randomSeed?.let { Random(it) } ?: Random()

    private fun rnd(count: Int): Int {
        return (count * random.nextFloat()).toInt() + 1
    }

    fun doit(horizontal: Int, vertical: Int): String {
        val result = StringBuilder()
        result.appendLine("Amazing - Copyright by Creative Computing, Morristown, NJ")
        if (horizontal == 1 || vertical == 1) return result.toString()

        var target = 270
        fun GOTO(lineno: Int) {
            target = lineno
        }

        val wArray = Array(horizontal + 1) { IntArray(vertical + 1) }
        val vArray = Array(horizontal + 1) { IntArray(vertical + 1) }

        var q = false
        var z = false
        var x = rnd(horizontal)

        for (i in 1..horizontal) {
            if (i == x) {
                result.append("+  ")
            } else {
                result.append("+--")
            }
        }
        result.append("+")
        result.append("\n")

        var c = 1
        wArray[x][1] = c
        c++

        var r = x
        var s = 1

        var finished = false
        while (!finished) {
            when (target) {
                210 -> {
                    if (r != horizontal) {
                        r++
                    } else if (s != vertical) {
                        r = 1
                        s++
                    } else {
                        r = 1
                        s = 1
                    }
                    GOTO(260)
                }

                260 -> {
                    if (wArray[r][s] == 0) {
                        GOTO(210)
                    } else {
                        GOTO(270)
                    }
                }

                270 -> {
                    if (r - 1 == 0 || wArray[r - 1][s] != 0) {
                        GOTO(600)
                    } else if (s - 1 == 0 || wArray[r][s - 1] != 0) {
                        GOTO(430)
                    } else if (r == horizontal || wArray[r + 1][s] != 0) {
                        GOTO(350)
                    } else {
                        GOTO(randomIn270())
                    }
                }

                350 -> {
                    if (s != vertical) {
                        if (wArray[r][s + 1] != 0) {
                            GOTO(random410())
                        } else {
                            GOTO(random390())
                        }
                    } else if (z) {
                        GOTO(random410())
                    } else {
                        q = true
                        GOTO(random390())
                    }
                }

                430 -> {
                    if (r == horizontal || wArray[r + 1][s] != 0) {
                        if (s != vertical) {
                            if (wArray[r][s + 1] != 0) {
                                GOTO(940)
                            } else {
                                GOTO(random570())
                            }
                        } else if (z) {
                            GOTO(940)
                        } else {
                            q = true
                            GOTO(random570())
                        }
                    } else if (s != vertical) {
                        if (wArray[r][s + 1] != 0) {
                            GOTO(random510())
                        } else {
                            GOTO(random490())
                        }
                    } else if (z) {
                        GOTO(random510())
                    } else {
                        q = true
                        GOTO(random490())
                    }
                }

                600 -> {
                    if (s - 1 == 0 || wArray[r][s - 1] != 0) {
                        if (r == horizontal || wArray[r + 1][s] != 0) {
                            if (s != vertical) {
                                if (wArray[r][s + 1] != 0) {
                                    GOTO(210)
                                } else {
                                    GOTO(1090)
                                }
                            } else if (z) {
                                GOTO(210)
                            } else {
                                q = true
                                GOTO(1090)

                            }
                        } else if (s != vertical) {
                            if (wArray[r][s + 1] != 0) {
                                GOTO(1020)
                            } else {
                                GOTO(randomIn600())
                            }
                        } else if (z) {
                            GOTO(1020)
                        } else {
                            q = true
                            GOTO(990)
                        }
                    } else if (r == horizontal || wArray[r + 1][s] != 0) {
                        GOTO(720)
                    } else if (s != vertical) {
                        if (wArray[r][s + 1] != 0) {
                            GOTO(random700())
                        } else {
                            GOTO(random680())
                        }
                    } else if (z) {
                        GOTO(random700())
                    } else {
                        q = true
                        GOTO(random680())
                    }
                }

                720 -> {
                    if (s != vertical) {
                        if (wArray[r][s + 1] != 0) {
                            GOTO(980)
                        } else {
                            GOTO(random760())
                        }
                    } else if (z) {
                        GOTO(980)
                    } else {
                        q = true
                        GOTO(random760())
                    }
                }

                940 -> {
                    wArray[r - 1][s] = c
                    c++
                    vArray[r - 1][s] = 2
                    r--
                    if (c == horizontal * vertical + 1) {
                        finished = true
                    } else {
                        q = false
                        GOTO(270)
                    }
                }

                980 -> {
                    wArray[r][s - 1] = c
                    GOTO(990)
                }

                990 -> {
                    c++
                    vArray[r][s - 1] = 1
                    s--
                    if (c == horizontal * vertical + 1) {
                        finished = true
                    } else {
                        q = false
                        GOTO(270)
                    }
                }

                1020 -> {
                    wArray[r + 1][s] = c
                    c++
                    if (vArray[r][s] == 0) {
                        vArray[r][s] = 2
                    } else {
                        vArray[r][s] = 3
                    }
                    r++
                    if (c == horizontal * vertical + 1) {
                        finished = true
                    } else {
                        GOTO(600)
                    }
                }

                1060 -> {
                }

                1090 -> if (q) {
                    z = true
                    if (vArray[r][s] == 0) {
                        vArray[r][s] = 1
                        q = false
                        r = 1
                        s = 1
                        GOTO(260)
                    } else {
                        vArray[r][s] = 3
                        q = false
                        GOTO(210)
                    }
                } else {
                    wArray[r][s + 1] = c
                    c++
                    if (vArray[r][s] == 0) {
                        vArray[r][s] = 1
                    } else {
                        vArray[r][s] = 3
                    }
                    s++
                    if (c == vertical * horizontal + 1) {
                        finished = true
                    } else {
                        GOTO(270)
                    }
                }
            }
        }

        buildMaze(vertical, result, horizontal, vArray)

        return result.toString()
    }

    private fun randomIn270() = random3(940, 980, 1020, 350)
    private fun random390() = random3(940, 980, 1090, 410)
    private fun random410() = random2(940, 980, 430)
    private fun random490() = random3(940, 1020, 1090, 510)
    private fun random510() = random2(940, 1020, 530)
    private fun random570() = random2(940, 1090, 940)
    private fun randomIn600() = random2(1020, 1090, 1020)
    private fun random680() = random3(980, 1020, 1090, 700)
    private fun random700() = random2(980, 1020, 720)
    private fun random760() = random2(980, 1090, 980)

    private fun random2(ifOne: Int, ifTwo: Int, ifThree: Int): Int {
        val x = rnd(2)
        return when (x) {
            1 -> ifOne
            2 -> ifTwo
            else -> ifThree
        }
    }

    private fun random3(ifOne: Int, ifTwo: Int, ifThree: Int, ifFour: Int): Int {
        val x = rnd(3)
        return when (x) {
            1 -> ifOne
            2 -> ifTwo
            3 -> ifThree
            else -> ifFour
        }
    }

    private fun buildMaze(vertical: Int, result: StringBuilder, horizontal: Int, vArray: Array<IntArray>) {
        for (j in 1..vertical) {
            result.append("|")

            for (i in 1..horizontal) {
                when (vArray[i][j]) {
                    0 -> result.append("  |")
                    1 -> result.append("  |")
                    2 -> result.append("   ")
                    3 -> result.append("   ")
                }
            }

            result.append(" ")
            result.append("\n")

            for (i in 1..horizontal) {
                when (vArray[i][j]) {
                    0 -> result.append("+--")
                    1 -> result.append("+  ")
                    2 -> result.append("+--")
                    3 -> result.append("+  ")
                }
            }

            result.append("+")
            result.append("\n")
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
