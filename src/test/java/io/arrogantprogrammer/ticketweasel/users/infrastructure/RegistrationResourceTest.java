package io.arrogantprogrammer.ticketweasel.users.infrastructure;

import io.arrogantprogrammer.ticketweasel.users.domain.TicketWeaselUserDTO;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.MediaType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.equalTo;

@QuarkusTest
public class RegistrationResourceTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(RegistrationResourceTest.class);

    @Test
    public void testUserRegistration() {

        TicketWeaselUserDTO ticketWeaselUserDTO = new TicketWeaselUserDTO("jeremy", "davis", "jeremy@someemail.com");

        given()
                .header("Content-Type", MediaType.APPLICATION_JSON)
                .body(ticketWeaselUserDTO)
                .post("/users/register")
                .then()
                .statusCode(201)
                .assertThat()
                .body("email", equalTo("jeremy@someemail.com"));
    }
}
