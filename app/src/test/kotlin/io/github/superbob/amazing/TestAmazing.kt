package io.github.superbob.amazing

import io.github.superbob.amazing.Amazing.doit
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.util.*

class TestAmazing {
    @Test
    fun testSeed0size15x20() {
        val expected = """
             Amazing - Copyright by Creative Computing, Morristown, NJ
             +--+--+--+--+--+--+--+--+--+--+  +--+--+--+--+
             |  |           |        |     |     |        | 
             +  +  +  +  +  +  +--+  +  +  +  +--+  +--+  +
             |     |  |  |  |     |     |     |     |  |  | 
             +--+--+  +  +--+  +  +--+--+--+--+  +  +  +  +
             |     |  |        |  |              |  |     | 
             +  +  +  +--+--+--+  +  +  +--+--+--+--+--+--+
             |  |     |        |     |  |     |        |  | 
             +--+--+  +--+  +--+  +--+  +  +  +  +--+  +  +
             |     |        |        |  |  |     |     |  | 
             +  +  +--+--+--+  +--+  +  +  +--+--+  +--+  +
             |  |              |     |  |  |     |  |     | 
             +  +--+--+--+--+--+--+  +  +  +  +--+  +--+  +
             |        |           |  |  |  |     |  |     | 
             +  +--+  +--+  +  +  +  +  +  +--+  +  +  +--+
             |  |     |     |  |  |  |  |     |  |  |  |  | 
             +  +  +--+  +--+  +  +  +  +--+  +  +  +  +  +
             |  |        |     |  |  |        |  |  |  |  | 
             +  +--+  +--+  +--+  +  +--+--+--+  +  +  +  +
             |     |     |  |  |  |  |     |        |     | 
             +--+  +--+  +  +  +  +--+  +  +  +--+--+  +--+
             |  |     |  |     |     |  |  |     |  |     | 
             +  +--+  +--+--+--+  +  +  +  +--+  +  +  +  +
             |     |     |     |  |  |  |     |  |  |  |  | 
             +  +  +--+  +  +  +--+  +  +--+  +  +  +  +  +
             |  |        |  |     |  |     |     |  |  |  | 
             +  +--+--+  +--+  +  +  +--+  +--+--+  +  +--+
             |  |     |        |  |     |     |     |     | 
             +  +  +--+--+--+--+  +  +--+  +  +  +--+--+  +
             |     |           |  |     |  |  |  |     |  | 
             +  +--+  +--+--+--+  +--+  +  +  +  +  +  +  +
             |  |                 |     |  |  |     |  |  | 
             +  +  +--+--+--+--+--+  +--+  +--+  +--+  +  +
             |  |  |  |           |     |     |  |     |  | 
             +  +  +  +  +--+  +  +--+  +--+  +  +  +--+  +
             |  |  |     |     |  |  |     |     |  |     | 
             +--+  +--+--+  +  +  +  +  +  +--+--+  +--+  +
             |     |        |  |  |  |  |  |     |        | 
             +  +--+  +--+--+  +  +  +  +  +--+  +  +--+--+
             |        |        |     |  |        |        | 
             +--+--+--+--+--+--+--+--+  +--+--+--+--+--+--+
             
             """.trimIndent()
        Amazing.random = Random(0)
        doit(15, 20)
        assertEquals(expected, Amazing.result.toString(), "Should have the maze that was expected")
    }

    @Test
    fun testSeed100size4x5() {
        val expected = """
             Amazing - Copyright by Creative Computing, Morristown, NJ
             +--+--+  +--+
             |     |     | 
             +  +--+  +  +
             |  |     |  | 
             +  +  +--+  +
             |  |  |     | 
             +  +  +  +  +
             |  |  |  |  | 
             +  +--+  +  +
             |  |  |  |  | 
             +--+--+  +--+
             
             """.trimIndent()
        Amazing.random = Random(100)
        doit(4, 5)
        assertEquals(expected, Amazing.result.toString(), "Should have the maze that was expected")
    }
}
