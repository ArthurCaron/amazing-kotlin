package io.github.superbob.amazing

import java.util.*

object Amazing {
    var target = 0 // where GOTO goes
    var random = Random(0)
    var result = StringBuffer()
    @JvmStatic
    fun main(args: Array<String>) {
        val colsArg = System.getenv("cols")
        val cols = colsArg?.toInt() ?: 10
        val rowsArg = System.getenv("rows")
        val rows = rowsArg?.toInt() ?: 10
        doit(cols, rows)
        println(result)
    }

    private fun clear() {
        result.setLength(0)
    }

    private fun println() {
        result.append("\n")
    }

    fun print(text: String?) {
        result.append(text)
    }

    fun rnd(count: Int): Int {
        return (count * random.nextFloat()).toInt() + 1
    }

    fun GOTO(lineno: Int) {
        target = lineno
    }

    fun doit(horizontal: Int, vertical: Int) {
        clear()
        print("Amazing - Copyright by Creative Computing, Morristown, NJ")
        println()
        if (horizontal == 1 || vertical == 1) return
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
            if (i == x) print("+  ") else print("+--")
        }
        // 180
        print("+")
        println()

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
                210 -> {
                    if (r != horizontal) GOTO(250) else GOTO(220)
                    continue
                }

                220 -> {
                    if (s != vertical) GOTO(240) else GOTO(230)
                    continue
                }

                230 -> {
                    r = 1
                    s = 1
                    GOTO(260)
                    continue
                }

                240 -> {
                    r = 1
                    s++
                    GOTO(260)
                    continue
                }

                250 -> {
                    r++
                    GOTO(260)
                    continue
                }

                260 -> {
                    if (wArray[r][s] == 0) GOTO(210) else GOTO(270)
                    continue
                }

                270 -> {
                    if (r - 1 == 0) GOTO(600) else GOTO(280)
                    continue
                }

                280 -> {
                    if (wArray[r - 1][s] != 0) GOTO(600) else GOTO(290)
                    continue
                }

                290 -> {
                    if (s - 1 == 0) GOTO(430) else GOTO(300)
                    continue
                }

                300 -> {
                    if (wArray[r][s - 1] != 0) GOTO(430) else GOTO(310)
                    continue
                }

                310 -> {
                    if (r == horizontal) GOTO(350) else GOTO(320)
                    continue
                }

                320 -> {
                    if (wArray[r + 1][s] != 0) GOTO(350) else GOTO(330)
                    continue
                }

                330 -> {
                    x = rnd(3)
                    GOTO(340)
                    continue
                }

                340 -> {
                    if (x == 1) GOTO(940) else if (x == 2) GOTO(980) else if (x == 3) GOTO(1020) else GOTO(350)
                    continue
                }

                350 -> {
                    if (s != vertical) GOTO(380) else GOTO(360)
                    continue
                }

                360 -> {
                    if (z == 1) GOTO(410) else GOTO(370)
                    continue
                }

                370 -> {
                    q = 1
                    GOTO(390)
                    continue
                }

                380 -> {
                    if (wArray[r][s + 1] != 0) GOTO(410) else GOTO(390)
                    continue
                }

                390 -> {
                    x = rnd(3)
                    GOTO(400)
                    continue
                }

                400 -> {
                    if (x == 1) GOTO(940) else if (x == 2) GOTO(980) else if (x == 3) GOTO(1090) else GOTO(410)
                    continue
                }

                410 -> {
                    x = rnd(2)
                    GOTO(420)
                    continue
                }

                420 -> {
                    if (x == 1) GOTO(940) else if (x == 2) GOTO(980) else GOTO(430)
                    continue
                }

                430 -> {
                    if (r == horizontal) GOTO(530) else GOTO(440)
                    continue
                }

                440 -> {
                    if (wArray[r + 1][s] != 0) GOTO(530) else GOTO(450)
                    continue
                }

                450 -> {
                    if (s != vertical) GOTO(480) else GOTO(460)
                    continue
                }

                460 -> {
                    if (z == 1) GOTO(510) else GOTO(470)
                    continue
                }

                470 -> {
                    q = 1
                    GOTO(490)
                    continue
                }

                480 -> {
                    if (wArray[r][s + 1] != 0) GOTO(510) else GOTO(490)
                    continue
                }

                490 -> {
                    x = rnd(3)
                    GOTO(500)
                    continue
                }

                500 -> {
                    if (x == 1) GOTO(940) else if (x == 2) GOTO(1020) else if (x == 3) GOTO(1090) else GOTO(510)
                    continue
                }

                510 -> {
                    x = rnd(2)
                    GOTO(520)
                    continue
                }

                520 -> {
                    if (x == 1) GOTO(940) else if (x == 2) GOTO(1020) else GOTO(530)
                    continue
                }

                530 -> {
                    if (s != vertical) GOTO(560) else GOTO(540)
                    continue
                }

                540 -> {
                    if (z == 1) GOTO(590) else GOTO(550)
                    continue
                }

                550 -> {
                    q = 1
                    GOTO(570)
                    continue
                }

                560 -> {
                    if (wArray[r][s + 1] != 0) GOTO(590) else GOTO(570)
                    continue
                }

                570 -> {
                    x = rnd(2)
                    GOTO(580)
                    continue
                }

                580 -> {
                    if (x == 1) GOTO(940) else if (x == 2) GOTO(1090) else GOTO(590)
                    continue
                }

                590 -> {
                    GOTO(940)
                    continue
                }

                600 -> {
                    if (s - 1 == 0) GOTO(790) else GOTO(610)
                    continue
                }

                610 -> {
                    if (wArray[r][s - 1] != 0) GOTO(790) else GOTO(620)
                    continue
                }

                620 -> {
                    if (r == horizontal) GOTO(720) else GOTO(630)
                    continue
                }

                630 -> {
                    if (wArray[r + 1][s] != 0) GOTO(720) else GOTO(640)
                    continue
                }

                640 -> {
                    if (s != vertical) GOTO(670) else GOTO(650)
                    continue
                }

                650 -> {
                    if (z == 1) GOTO(700) else GOTO(660)
                    continue
                }

                660 -> {
                    q = 1
                    GOTO(680)
                    continue
                }

                670 -> {
                    if (wArray[r][s + 1] != 0) GOTO(700) else GOTO(680)
                    continue
                }

                680 -> {
                    x = rnd(3)
                    GOTO(690)
                    continue
                }

                690 -> {
                    if (x == 1) GOTO(980) else if (x == 2) GOTO(1020) else if (x == 3) GOTO(1090) else GOTO(700)
                    continue
                }

                700 -> {
                    x = rnd(2)
                    GOTO(710)
                    continue
                }

                710 -> {
                    if (x == 1) GOTO(980) else if (x == 2) GOTO(1020) else GOTO(720)
                    continue
                }

                720 -> {
                    if (s != vertical) GOTO(750) else GOTO(730)
                    continue
                }

                730 -> {
                    if (z == 1) GOTO(780) else GOTO(740)
                    continue
                }

                740 -> {
                    q = 1
                    GOTO(760)
                    continue
                }

                750 -> {
                    if (wArray[r][s + 1] != 0) GOTO(780) else GOTO(760)
                    continue
                }

                760 -> {
                    x = rnd(2)
                    GOTO(770)
                    continue
                }

                770 -> {
                    if (x == 1) GOTO(980) else if (x == 2) GOTO(1090) else GOTO(780)
                    continue
                }

                780 -> {
                    GOTO(980)
                    continue
                }

                790 -> {
                    if (r == horizontal) GOTO(880) else GOTO(800)
                    continue
                }

                800 -> {
                    if (wArray[r + 1][s] != 0) GOTO(880) else GOTO(810)
                    continue
                }

                810 -> {
                    if (s != vertical) GOTO(840) else GOTO(820)
                    continue
                }

                820 -> {
                    if (z == 1) GOTO(870) else GOTO(830)
                    continue
                }

                830 -> {
                    q = 1
                    GOTO(990)
                    continue
                }

                840 -> {
                    if (wArray[r][s + 1] != 0) GOTO(870) else GOTO(850)
                    continue
                }

                850 -> {
                    x = rnd(2)
                    GOTO(860)
                    continue
                }

                860 -> {
                    if (x == 1) GOTO(1020) else if (x == 2) GOTO(1090) else GOTO(870)
                    continue
                }

                870 -> {
                    GOTO(1020)
                    continue
                }

                880 -> {
                    if (s != vertical) GOTO(910) else GOTO(890)
                    continue
                }

                890 -> {
                    if (z == 1) GOTO(930) else GOTO(900)
                    continue
                }

                900 -> {
                    q = 1
                    GOTO(920)
                    continue
                }

                910 -> {
                    if (wArray[r][s + 1] != 0) GOTO(930) else GOTO(920)
                    continue
                }

                920 -> {
                    GOTO(1090)
                    continue
                }

                930 -> {
                    GOTO(1190)
                    continue
                }

                940 -> {
                    wArray[r - 1][s] = c
                    GOTO(950)
                    continue
                }

                950 -> {
                    c++
                    vArray[r - 1][s] = 2
                    r--
                    GOTO(960)
                    continue
                }

                960 -> {
                    if (c == horizontal * vertical + 1) GOTO(1200) else GOTO(970)
                    continue
                }

                970 -> {
                    q = 0
                    GOTO(270)
                    continue
                }

                980 -> {
                    wArray[r][s - 1] = c
                    GOTO(990)
                    continue
                }

                990 -> {
                    c++
                    GOTO(1000)
                    continue
                }

                1000 -> {
                    vArray[r][s - 1] = 1
                    s--
                    if (c == horizontal * vertical + 1) GOTO(1200) else GOTO(1010)
                    continue
                }

                1010 -> {
                    q = 0
                    GOTO(270)
                    continue
                }

                1020 -> {
                    wArray[r + 1][s] = c
                    GOTO(1030)
                    continue
                }

                1030 -> {
                    c++
                    if (vArray[r][s] == 0) GOTO(1050) else GOTO(1040)
                    continue
                }

                1040 -> {
                    vArray[r][s] = 3
                    GOTO(1060)
                    continue
                }

                1050 -> {
                    vArray[r][s] = 2
                    GOTO(1060)
                    continue
                }

                1060 -> {
                    r++
                    GOTO(1070)
                    continue
                }

                1070 -> {
                    if (c == horizontal * vertical + 1) GOTO(1200) else GOTO(1080)
                    continue
                }

                1080 -> {
                    GOTO(600)
                    continue
                }

                1090 -> {
                    if (q == 1) GOTO(1150) else GOTO(1100)
                    continue
                }

                1100 -> {
                    wArray[r][s + 1] = c
                    c++
                    if (vArray[r][s] == 0) GOTO(1120) else GOTO(1110)
                    continue
                }

                1110 -> {
                    vArray[r][s] = 3
                    GOTO(1130)
                    continue
                }

                1120 -> {
                    vArray[r][s] = 1
                    GOTO(1130)
                    continue
                }

                1130 -> {
                    s++
                    if (c == vertical * horizontal + 1) GOTO(1200) else GOTO(1140)
                    continue
                }

                1140 -> {
                    GOTO(270)
                    continue
                }

                1150 -> {
                    z = 1
                    GOTO(1160)
                    continue
                }

                1160 -> {
                    if (vArray[r][s] == 0) GOTO(1180) else GOTO(1170)
                    continue
                }

                1170 -> {
                    vArray[r][s] = 3
                    q = 0
                    GOTO(1190)
                    continue
                }

                1180 -> {
                    vArray[r][s] = 1
                    q = 0
                    r = 1
                    s = 1
                    GOTO(260)
                    continue
                }

                1190 -> {
                    GOTO(210)
                    continue
                }

                1200 -> {
                    target = -1
                    continue
                }
            }
        }

        // 1200:
        for (j in 1..vertical) {
            print("|") // 1210
            for (i in 1..horizontal) {
                if (vArray[i][j] >= 2) print("   ") // 1240
                else print("  |") // 1260
            }
            print(" ") // 1280
            println()
            for (i in 1..horizontal) {
                if (vArray[i][j] == 0) print("+--") // 1300, 1340
                else if (vArray[i][j] == 2) print("+--") // 1310, 1340
                else print("+  ") // 1320
            }
            print("+") // 1360
            println()
        }
    }
}
