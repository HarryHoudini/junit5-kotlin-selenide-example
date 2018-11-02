import com.example.api.extensions.body
import com.example.api.extensions.contentType
import com.example.api.extensions.shouldHave
import com.example.api.models.User
import com.example.api.services.UserApiService
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import groovy.json.JsonBuilder
import io.kotlintest.properties.assertAll
import io.restassured.http.ContentType.*
import io.restassured.response.ResponseBodyExtractionOptions
import io.restassured.response.ValidatableResponse
import org.hamcrest.Matchers.notNullValue
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.extension.RegisterExtension
import java.util.stream.Stream
import kotlin.streams.toList

class RegistrationTests: BaseTest(){


    @RegisterExtension
    val userApiService = UserApiService()

    @RegisterExtension
    val randomUser = User(firstName = "", lastName = "",
            username = faker.name().firstName(),
            email = faker.internet().emailAddress(),
            password =  faker.internet().password(),
            id = faker.internet().uuid())

    @AfterEach
    fun `Delete User`(){
        val customers = userApiService.getCustomers().extract().body()
                .jsonPath().param("userName", "savva.genchevskiy")
                .get<List<Map<String, String>>>("_embedded.customer.findAll { customer -> customer.username != userName }")
        customers.forEach { customer -> userApiService.deleteCustomer(customer.get("id").toString()) }
    }



    @Test
    @DisplayName("")
    fun `register User`(){
        userApiService.registerCustomer(randomUser)
                .shouldHave.statusCode(200)
                .shouldHave(contentType(JSON))
                .shouldHave(body("id", notNullValue()))
    }

    @Test
    fun `get customers`(){
        val users = userApiService.getCustomers().extractUsers()
        assertTrue(users.isNotEmpty())
        assertEquals(users.filter{ it.username == "savva.gench"}.size, 1)
        assertAll("Users with emails ends on .com ",
                users.filter{ it.username == "savva.gench"}
                .sortedBy { it.username }
                .map { { assertTrue(it.email.endsWith(".com")) } }
        )
    }
}





fun ValidatableResponse.extractAs(java: Class<User>): List<User> {
    return this.extract().body().`as`<List<User>>(java)
}

fun ValidatableResponse.extractAsUsers(path: String): List<User> {
    return Gson().fromJson<List<User>>(this.extract().body().jsonPath().getString(path) , object: TypeToken<List<User>>(){}.type)
}

fun ValidatableResponse.extractUsers(): List<User> {
    return this.extract().body().`as`<List<User>>(User::class.java)
}

