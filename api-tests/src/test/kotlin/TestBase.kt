import com.example.api.services.UserApiService
import com.github.javafaker.Faker
import io.restassured.RestAssured
import io.restassured.RestAssured.*
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.extension.RegisterExtension
import java.util.*


abstract class BaseTest {

    val faker: Faker = Faker(Locale("en-US"))

    init {
        baseURI = "http://35.232.243.253"
    }


    companion object {

        @BeforeAll
        @JvmStatic
        fun setUp() {

            println("Runs once before all test cases.")
        }

        @AfterAll
        @JvmStatic
        fun tearDown() {
            println("Runs once after all test cases.")
        }


    }


}
