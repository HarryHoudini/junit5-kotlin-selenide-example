package com.example.ui.dsl_tests

import com.example.ui.BaseTest
import com.example.ui.extensions.kotlin_extensions.open
import com.example.ui.extensions.kotlin_extensions.should
import org.junit.jupiter.api.Test

// https://www.youtube.com/watch?v=Y5_s8hNROBQ&t=1837s
// https://github.com/ivan-osipov/kotlin-dsl-example

class LoginTests:BaseTest() {

    @Test
    fun `positive login`() = schedule {
        website {
            account {
                firstName = "Savva"
                lastName = "Genchevskiy"
                username = "savva.gench"
                password = "s.g19021992"
            }
            mainPage {
                open()
                loginWith(accounts.get(0))
            }

        } assertions {
            mainPage {
                successMessage.should.be.visible
                successMessage.should.have.text("Login successful.")
                usernameButton.should.be.visible
                usernameButton.should.have.text("Logged in as ${accounts.get(0).firstName} ${accounts.get(0).lastName}")
            }
        }

    }


}