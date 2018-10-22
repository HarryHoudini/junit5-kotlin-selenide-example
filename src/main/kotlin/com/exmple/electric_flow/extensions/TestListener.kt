package com.exmple.electric_flow.extensions

import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.extension.*

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TestListener: BeforeAllCallback,
        AfterAllCallback,
        BeforeEachCallback,
        AfterEachCallback,
        BeforeTestExecutionCallback,
        AfterTestExecutionCallback {
    override fun beforeAll(context: ExtensionContext?) {

    }

    override fun afterAll(context: ExtensionContext?) {

    }

    override fun beforeEach(context: ExtensionContext?) {

    }

    override fun afterEach(context: ExtensionContext?) {

    }

    override fun beforeTestExecution(context: ExtensionContext?) {

    }

    override fun afterTestExecution(context: ExtensionContext?) {

    }


}