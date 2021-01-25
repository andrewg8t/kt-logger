package com.example.logger

import org.slf4j.LoggerFactory

/**
 * Logger Usage (Slf4j) Format String
 */
class LoggerTest() {

    val inputTopic = "input-topic-1"
    val outputTopic = "output-topic-1"

    fun run() {

        // Building a message without substitution. i.e without anchors {}
        // Its not a good idea as the underlying JVM code produces temporary String objects by concatenation.
        // If you have logger.info in some loop it could result in big memory consumption
        logger.error("1 formatted message: $inputTopic $outputTopic")

        // Test message format without anchors"
        // BAD. inputTopic, outputTopic values wont be printed
        logger.error("2 formatted message: ", inputTopic, outputTopic)

        // Test message format with less number of anchors
        // BAD. outputTopic value wont be printed
        logger.error("3 formatted message: {} ", inputTopic, outputTopic)

        // Test message format with anchors
        // GOOD. This is the message we want
        logger.error("4 formatted message: {}  {} ", inputTopic, outputTopic)

        // Test message format with Throwable
        try {
            createThrowable("Test exception")
        } catch (t: Throwable) {
            // GOOD
            logger.error("5 formatted message", t)
            // input topic is lost
            logger.error("6 formatted message", inputTopic, t)
            // input topic and output topic are lost
            logger.error("7 formatted message", inputTopic, outputTopic, t)
            // GOOD: put t in the end and use {} for topics
            logger.error("8 formatted message input={} output={}", inputTopic, outputTopic, t)
        }
    }

    fun createThrowable(name: String) {
        throw Throwable(name)
    }

    companion object {
        private val logger = LoggerFactory.getLogger(LoggerTest::class.simpleName)
    }
}

fun main() {
    LoggerTest().run()

}


