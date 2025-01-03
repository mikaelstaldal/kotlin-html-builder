package nu.staldal.kotlin.html

import kotlin.test.assertEquals

open class TestBase {
    protected fun validate(expected: String, actual: String) {
        // Doing a replacement to cater for different line endings.
        assertEquals(
            expected.replace("\t", "    "),
            actual.replace("\t", "    ").replace(System.lineSeparator(), "\n"),
        )
    }
}
