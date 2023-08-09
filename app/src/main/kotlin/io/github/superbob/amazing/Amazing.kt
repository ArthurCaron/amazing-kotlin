package io.github.superbob.amazing

import java.util.*

class Amazing(randomSeed: Long = 0) {
    private val random = Random(randomSeed)

    private fun rnd(count: Int): Int {
        return (count * random.nextFloat()).toInt() + 1
    }

    fun doit(horizontal: Int, vertical: Int): String {
        var target = 0 // where GOTO goes
        val result = StringBuilder()

        fun GOTO(lineno: Int) {
            target = lineno
        }

        result.appendLine("Amazing - Copyright by Creative Computing, Morristown, NJ")
        if (horizontal == 1 || vertical == 1) return result.toString()
        val wArray = Array(horizontal + 1) { IntArray(vertical + 1) }
        for (i in 0..horizontal) {
            wArray[i] = IntArray(vertical + 1)
        }
        val vArray = Array(horizontal + 1) { IntArray(vertical + 1) }
        for (i in 0..horizontal) {
            vArray[i] = IntArray(vertical + 1)
        }
        var q = 0
        var z = 0
        var x = rnd(horizontal)

        // 130:170
        for (i in 1..horizontal) {
            if (i == x) result.append("+  ") else result.append("+--")
        }
        // 180
        result.append("+")
        result.append("\n")

        // 190
        var c = 1
        wArray[x][1] = c
        c++

        // 200
        var r = x
        var s = 1
        GOTO(270)
        while (target != -1) {
            when (target) {
                210 -> if (r != horizontal) GOTO(250) else GOTO(220)

                220 -> if (s != vertical) GOTO(240) else GOTO(230)

                230 -> {
                    r = 1
                    s = 1
                    GOTO(260)
                }

                240 -> {
                    r = 1
                    s++
                    GOTO(260)
                }

                250 -> {
                    r++
                    GOTO(260)
                }

                260 -> if (wArray[r][s] == 0) GOTO(210) else GOTO(270)

                270 -> if (r - 1 == 0) GOTO(600) else GOTO(280)

                280 -> if (wArray[r - 1][s] != 0) GOTO(600) else GOTO(290)

                290 -> if (s - 1 == 0) GOTO(430) else GOTO(300)

                300 -> if (wArray[r][s - 1] != 0) GOTO(430) else GOTO(310)

                310 -> if (r == horizontal) GOTO(350) else GOTO(320)

                320 -> if (wArray[r + 1][s] != 0) GOTO(350) else GOTO(330)

                330 -> {
                    x = rnd(3)
                    GOTO(340)
                }

                340 -> if (x == 1) GOTO(940) else if (x == 2) GOTO(980) else if (x == 3) GOTO(1020) else GOTO(350)

                350 -> if (s != vertical) GOTO(380) else GOTO(360)

                360 -> if (z == 1) GOTO(410) else GOTO(370)

                370 -> {
                    q = 1
                    GOTO(390)
                }

                380 -> if (wArray[r][s + 1] != 0) GOTO(410) else GOTO(390)

                390 -> {
                    x = rnd(3)
                    GOTO(400)
                }

                400 -> if (x == 1) GOTO(940) else if (x == 2) GOTO(980) else if (x == 3) GOTO(1090) else GOTO(410)

                410 -> {
                    x = rnd(2)
                    GOTO(420)
                }

                420 -> if (x == 1) GOTO(940) else if (x == 2) GOTO(980) else GOTO(430)

                430 -> if (r == horizontal) GOTO(530) else GOTO(440)

                440 -> if (wArray[r + 1][s] != 0) GOTO(530) else GOTO(450)

                450 -> if (s != vertical) GOTO(480) else GOTO(460)

                460 -> if (z == 1) GOTO(510) else GOTO(470)

                470 -> {
                    q = 1
                    GOTO(490)
                }

                480 -> if (wArray[r][s + 1] != 0) GOTO(510) else GOTO(490)

                490 -> {
                    x = rnd(3)
                    GOTO(500)
                }

                500 -> if (x == 1) GOTO(940) else if (x == 2) GOTO(1020) else if (x == 3) GOTO(1090) else GOTO(510)

                510 -> {
                    x = rnd(2)
                    GOTO(520)
                }

                520 -> if (x == 1) GOTO(940) else if (x == 2) GOTO(1020) else GOTO(530)

                530 -> if (s != vertical) GOTO(560) else GOTO(540)

                540 -> if (z == 1) GOTO(590) else GOTO(550)

                550 -> {
                    q = 1
                    GOTO(570)
                }

                560 -> if (wArray[r][s + 1] != 0) GOTO(590) else GOTO(570)

                570 -> {
                    x = rnd(2)
                    GOTO(580)
                }

                580 -> if (x == 1) GOTO(940) else if (x == 2) GOTO(1090) else GOTO(590)

                590 -> GOTO(940)

                600 -> if (s - 1 == 0) GOTO(790) else GOTO(610)

                610 -> if (wArray[r][s - 1] != 0) GOTO(790) else GOTO(620)

                620 -> if (r == horizontal) GOTO(720) else GOTO(630)

                630 -> if (wArray[r + 1][s] != 0) GOTO(720) else GOTO(640)

                640 -> if (s != vertical) GOTO(670) else GOTO(650)

                650 -> if (z == 1) GOTO(700) else GOTO(660)

                660 -> {
                    q = 1
                    GOTO(680)
                }

                670 -> if (wArray[r][s + 1] != 0) GOTO(700) else GOTO(680)

                680 -> {
                    x = rnd(3)
                    GOTO(690)
                }

                690 -> if (x == 1) GOTO(980) else if (x == 2) GOTO(1020) else if (x == 3) GOTO(1090) else GOTO(700)

                700 -> {
                    x = rnd(2)
                    GOTO(710)
                }

                710 -> if (x == 1) GOTO(980) else if (x == 2) GOTO(1020) else GOTO(720)

                720 -> if (s != vertical) GOTO(750) else GOTO(730)

                730 -> if (z == 1) GOTO(780) else GOTO(740)

                740 -> {
                    q = 1
                    GOTO(760)
                }

                750 -> if (wArray[r][s + 1] != 0) GOTO(780) else GOTO(760)

                760 -> {
                    x = rnd(2)
                    GOTO(770)
                }

                770 -> if (x == 1) GOTO(980) else if (x == 2) GOTO(1090) else GOTO(780)

                780 -> GOTO(980)

                790 -> if (r == horizontal) GOTO(880) else GOTO(800)

                800 -> if (wArray[r + 1][s] != 0) GOTO(880) else GOTO(810)

                810 -> if (s != vertical) GOTO(840) else GOTO(820)

                820 -> if (z == 1) GOTO(870) else GOTO(830)

                830 -> {
                    q = 1
                    GOTO(990)
                }

                840 -> if (wArray[r][s + 1] != 0) GOTO(870) else GOTO(850)

                850 -> {
                    x = rnd(2)
                    GOTO(860)
                }

                860 -> if (x == 1) GOTO(1020) else if (x == 2) GOTO(1090) else GOTO(870)

                870 -> GOTO(1020)

                880 -> if (s != vertical) GOTO(910) else GOTO(890)

                890 -> if (z == 1) GOTO(930) else GOTO(900)

                900 -> {
                    q = 1
                    GOTO(920)
                }

                910 -> if (wArray[r][s + 1] != 0) GOTO(930) else GOTO(920)

                920 -> GOTO(1090)

                930 -> GOTO(1190)

                940 -> {
                    wArray[r - 1][s] = c
                    GOTO(950)
                }

                950 -> {
                    c++
                    vArray[r - 1][s] = 2
                    r--
                    GOTO(960)
                }

                960 -> if (c == horizontal * vertical + 1) GOTO(1200) else GOTO(970)

                970 -> {
                    q = 0
                    GOTO(270)
                }

                980 -> {
                    wArray[r][s - 1] = c
                    GOTO(990)
                }

                990 -> {
                    c++
                    GOTO(1000)
                }

                1000 -> {
                    vArray[r][s - 1] = 1
                    s--
                    if (c == horizontal * vertical + 1) GOTO(1200) else GOTO(1010)
                }

                1010 -> {
                    q = 0
                    GOTO(270)
                }

                1020 -> {
                    wArray[r + 1][s] = c
                    GOTO(1030)
                }

                1030 -> {
                    c++
                    if (vArray[r][s] == 0) GOTO(1050) else GOTO(1040)
                }

                1040 -> {
                    vArray[r][s] = 3
                    GOTO(1060)
                }

                1050 -> {
                    vArray[r][s] = 2
                    GOTO(1060)
                }

                1060 -> {
                    r++
                    GOTO(1070)
                }

                1070 -> if (c == horizontal * vertical + 1) GOTO(1200) else GOTO(1080)

                1080 -> GOTO(600)

                1090 -> if (q == 1) GOTO(1150) else GOTO(1100)

                1100 -> {
                    wArray[r][s + 1] = c
                    c++
                    if (vArray[r][s] == 0) GOTO(1120) else GOTO(1110)
                }

                1110 -> {
                    vArray[r][s] = 3
                    GOTO(1130)
                }

                1120 -> {
                    vArray[r][s] = 1
                    GOTO(1130)
                }

                1130 -> {
                    s++
                    if (c == vertical * horizontal + 1) GOTO(1200) else GOTO(1140)
                }

                1140 -> GOTO(270)

                1150 -> {
                    z = 1
                    GOTO(1160)
                }

                1160 -> if (vArray[r][s] == 0) GOTO(1180) else GOTO(1170)

                1170 -> {
                    vArray[r][s] = 3
                    q = 0
                    GOTO(1190)
                }

                1180 -> {
                    vArray[r][s] = 1
                    q = 0
                    r = 1
                    s = 1
                    GOTO(260)
                }

                1190 -> GOTO(210)

                1200 -> target = -1
            }
        }

        // 1200:
        for (j in 1..vertical) {
            result.append("|") // 1210
            for (i in 1..horizontal) {
                if (vArray[i][j] >= 2) result.append("   ") // 1240
                else result.append("  |") // 1260
            }
            result.append(" ") // 1280
            result.append("\n")
            for (i in 1..horizontal) {
                if (vArray[i][j] == 0) result.append("+--") // 1300, 1340
                else if (vArray[i][j] == 2) result.append("+--") // 1310, 1340
                else result.append("+  ") // 1320
            }
            result.append("+") // 1360
            result.append("\n")
        }

        return result.toString()
    }
}

fun main(args: Array<String>) {
    val colsArg = args.getOrNull(0) ?: System.getenv("cols")
    val cols = colsArg?.toInt() ?: 10
    val rowsArg = args.getOrNull(1) ?: args.getOrNull(0) ?: System.getenv("rows")
    val rows = rowsArg?.toInt() ?: 10
    println(Amazing().doit(cols, rows))
}
