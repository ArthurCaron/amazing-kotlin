package io.github.superbob.amazing

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

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
        val result = Amazing(0).doit(15, 20)
        assertEquals(expected, result, "Should have the maze that was expected")
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
        val result = Amazing(100).doit(4, 5)
        assertEquals(expected, result, "Should have the maze that was expected")
    }
}
