import com.example.api.extensions.body
import com.example.api.extensions.contentType
import com.example.api.extensions.extractAsUsers
import com.example.api.extensions.shouldHave
import com.example.api.models.User
import com.example.api.services.UserApiService
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import groovy.json.JsonBuilder
import io.restassured.http.ContentType.*
import org.hamcrest.Matchers.notNullValue
import org.junit.jupiter.api.*
import org.junit.jupiter.api.extension.RegisterExtension

class RegistrationTests: BaseTest(){


    @RegisterExtension
    val userApiService = UserApiService()


    @Test
    fun `register User`(){



        val user = User(firstName = "", lastName = "",
                username = faker.name().firstName(),
                email = faker.internet().emailAddress(),
                password =  faker.internet().password(),
                id = faker.internet().uuid())
        userApiService.registerCustomer(user)
                .shouldHave.statusCode(200)
                .shouldHave(contentType(JSON))
                .shouldHave(body("id", notNullValue()))

        val users = userApiService.getCustomers.extract().body().jsonPath().get<List<User>>("_embedded.customer")

    }





    @AfterEach
    fun `Delete User`(){
        val customers = userApiService.getCustomers.extract().body()
                .jsonPath().param("userName", "savva.genchevskiy")
                .get<List<Map<String, String>>>("_embedded.customer.findAll { customer -> customer.username != userName }")
        customers.forEach { customer -> userApiService.deleteCustomer(customer.get("id").toString()) }
    }


}