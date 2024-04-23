package com.rest.interpreter.model

data class Test(
    val testId: String,
    val requestId: String,
    val script: String
) {
    fun execute(requestResponse: Response): TestResult {
        TODO() // Implement this function, it's just a placeholder now
        return TestResult(true, "Test passed")
    }
    fun setResult(result: TestResult) {
        // Save the test result
    }
}

data class TestResult(
    val success: Boolean,
    val message: String
)
