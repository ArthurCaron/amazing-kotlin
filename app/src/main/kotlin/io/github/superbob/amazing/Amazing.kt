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

        var target = 0
        fun GOTO(lineno: Int) {
            target = lineno
        }

        val wArray = Array(horizontal + 1) { IntArray(vertical + 1) }
        val vArray = Array(horizontal + 1) { IntArray(vertical + 1) }

        var q = 0
        var z = 0
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
        GOTO(270)

        while (target != -1) {
            when (target) {
                210 -> if (r != horizontal) {
                    r++
                    GOTO(260)
                } else {
                    if (s != vertical) {
                        r = 1
                        s++
                        GOTO(260)
                    } else {
                        r = 1
                        s = 1
                        GOTO(260)
                    }
                }

                260 -> if (wArray[r][s] == 0) {
                    GOTO(210)
                } else {
                    GOTO(270)
                }

                270 -> if (r - 1 == 0) {
                    GOTO(600)
                } else {
                    if (wArray[r - 1][s] != 0) {
                        GOTO(600)
                    } else {
                        if (s - 1 == 0) {
                            GOTO(430)
                        } else {
                            if (wArray[r][s - 1] != 0) {
                                GOTO(430)
                            } else {
                                if (r == horizontal) {
                                    GOTO(350)
                                } else {
                                    if (wArray[r + 1][s] != 0) {
                                        GOTO(350)
                                    } else {
                                        x = rnd(3)
                                        if (x == 1) {
                                            GOTO(940)
                                        } else if (x == 2) {
                                            GOTO(980)
                                        } else if (x == 3) {
                                            GOTO(1020)
                                        } else {
                                            GOTO(350)
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

                350 -> if (s != vertical) {
                    if (wArray[r][s + 1] != 0) {
                        GOTO(410)
                    } else {
                        GOTO(390)
                    }
                } else {
                    if (z == 1) {
                        GOTO(410)
                    } else {
                        q = 1
                        GOTO(390)
                    }
                }

                390 -> {
                    x = rnd(3)
                    if (x == 1) {
                        GOTO(940)
                    } else if (x == 2) {
                        GOTO(980)
                    } else if (x == 3) {
                        GOTO(1090)
                    } else {
                        GOTO(410)
                    }
                }

                410 -> {
                    x = rnd(2)
                    if (x == 1) {
                        GOTO(940)
                    } else if (x == 2) {
                        GOTO(980)
                    } else {
                        GOTO(430)
                    }
                }

                430 -> if (r == horizontal) {
                    GOTO(530)
                } else {
                    if (wArray[r + 1][s] != 0) {
                        GOTO(530)
                    } else {
                        if (s != vertical) {
                            if (wArray[r][s + 1] != 0) {
                                GOTO(510)
                            } else {
                                GOTO(490)
                            }
                        } else {
                            if (z == 1) {
                                GOTO(510)
                            } else {
                                q = 1
                                GOTO(490)
                            }
                        }
                    }
                }

                490 -> {
                    x = rnd(3)
                    if (x == 1) {
                        GOTO(940)
                    } else if (x == 2) {
                        GOTO(1020)
                    } else if (x == 3) {
                        GOTO(1090)
                    } else {
                        GOTO(510)
                    }
                }

                510 -> {
                    x = rnd(2)
                    if (x == 1) {
                        GOTO(940)
                    } else if (x == 2) {
                        GOTO(1020)
                    } else {
                        GOTO(530)
                    }
                }

                530 -> if (s != vertical) {
                    if (wArray[r][s + 1] != 0) {
                        GOTO(940)
                    } else {
                        GOTO(570)
                    }
                } else {
                    if (z == 1) {
                        GOTO(940)
                    } else {
                        q = 1
                        GOTO(570)
                    }
                }

                570 -> {
                    x = rnd(2)
                    if (x == 1) {
                        GOTO(940)
                    } else if (x == 2) {
                        GOTO(1090)
                    } else {
                        GOTO(940)
                    }
                }

                600 -> if (s - 1 == 0) {
                    GOTO(790)
                } else {
                    if (wArray[r][s - 1] != 0) {
                        GOTO(790)
                    } else {
                        if (r == horizontal) {
                            GOTO(720)
                        } else {
                            if (wArray[r + 1][s] != 0) {
                                GOTO(720)
                            } else {
                                if (s != vertical) {
                                    GOTO(670)
                                } else {
                                    if (z == 1) {
                                        GOTO(700)
                                    } else {
                                        q = 1
                                        GOTO(680)
                                    }
                                }
                            }
                        }
                    }
                }

                670 -> if (wArray[r][s + 1] != 0) {
                    GOTO(700)
                } else {
                    GOTO(680)
                }

                680 -> {
                    x = rnd(3)
                    if (x == 1) {
                        GOTO(980)
                    } else if (x == 2) {
                        GOTO(1020)
                    } else if (x == 3) {
                        GOTO(1090)
                    } else {
                        GOTO(700)
                    }
                }

                700 -> {
                    x = rnd(2)
                    if (x == 1) {
                        GOTO(980)
                    } else if (x == 2) {
                        GOTO(1020)
                    } else {
                        GOTO(720)
                    }
                }

                720 -> if (s != vertical) {
                    if (wArray[r][s + 1] != 0) {
                        GOTO(780)
                    } else {
                        GOTO(760)
                    }
                } else {
                    if (z == 1) {
                        GOTO(780)
                    } else {
                        q = 1
                        GOTO(760)
                    }
                }

                760 -> {
                    x = rnd(2)
                    if (x == 1) {
                        GOTO(980)
                    } else if (x == 2) {
                        GOTO(1090)
                    } else {
                        GOTO(780)
                    }
                }

                780 -> {
                    GOTO(980)
                }

                790 -> if (r == horizontal) {
                    GOTO(880)
                } else {
                    if (wArray[r + 1][s] != 0) {
                        GOTO(880)
                    } else {
                        if (s != vertical) {
                            if (wArray[r][s + 1] != 0) {
                                GOTO(870)
                            } else {
                                x = rnd(2)
                                if (x == 1) {
                                    GOTO(1020)
                                } else if (x == 2) {
                                    GOTO(1090)
                                } else {
                                    GOTO(870)
                                }
                            }
                        } else {
                            if (z == 1) {
                                GOTO(870)
                            } else {
                                q = 1
                                GOTO(990)
                            }
                        }
                    }
                }

                870 -> {
                    GOTO(1020)
                }

                880 -> if (s != vertical) {
                    if (wArray[r][s + 1] != 0) {
                        GOTO(210)
                    } else {
                        GOTO(1090)
                    }
                } else {
                    if (z == 1) {
                        GOTO(210)
                    } else {
                        q = 1
                        GOTO(1090)
                    }
                }

                940 -> {
                    wArray[r - 1][s] = c
                    c++
                    vArray[r - 1][s] = 2
                    r--
                    if (c == horizontal * vertical + 1) {
                        GOTO(-1)
                    } else {
                        q = 0
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
                        GOTO(-1)
                    } else {
                        q = 0
                        GOTO(270)
                    }
                }

                1020 -> {
                    wArray[r + 1][s] = c
                    c++
                    if (vArray[r][s] == 0) {
                        vArray[r][s] = 2
                        GOTO(1060)
                    } else {
                        vArray[r][s] = 3
                        GOTO(1060)
                    }
                }

                1060 -> {
                    r++
                    if (c == horizontal * vertical + 1) {
                        GOTO(-1)
                    } else {
                        GOTO(600)
                    }
                }

                1090 -> if (q == 1) {
                    z = 1
                    if (vArray[r][s] == 0) {
                        vArray[r][s] = 1
                        q = 0
                        r = 1
                        s = 1
                        GOTO(260)
                    } else {
                        vArray[r][s] = 3
                        q = 0
                        GOTO(210)
                    }
                } else {
                    wArray[r][s + 1] = c
                    c++
                    if (vArray[r][s] == 0) {
                        vArray[r][s] = 1
                        GOTO(1130)
                    } else {
                        vArray[r][s] = 3
                        GOTO(1130)
                    }
                }

                1130 -> {
                    s++
                    if (c == vertical * horizontal + 1) {
                        GOTO(-1)
                    } else {
                        GOTO(270)
                    }
                }
            }
        }

        buildMaze(vertical, result, horizontal, vArray)

        return result.toString()
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
