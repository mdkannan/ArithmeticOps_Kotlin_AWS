package com.test

import kotlin.test.Test
import kotlin.test.assertEquals

class AppTest {
    @Test fun testAppHasAGreeting() {
        val classUnderTest = App()
        val input = HandlerInput()
        input.argument1 = "0"
        val expected = HandlerOutput("","","","","","Success","200")
        var output = classUnderTest.handleRequest(input, null)
        assertEquals(expected, output)
    }
}