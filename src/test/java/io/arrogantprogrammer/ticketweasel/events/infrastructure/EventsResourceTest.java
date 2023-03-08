package io.arrogantprogrammer.ticketweasel.events.infrastructure;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.MediaType;

import java.time.LocalDate;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@QuarkusTest
public class EventsResourceTest {


    @Test
    public void testCreateEvent() {

        EventValueObject evo = new EventValueObject("Betty Who", "Variety Playhouse", LocalDate.of(2023, 03, 07));
        given()
                .header("Content-Type", MediaType.APPLICATION_JSON)
                .body(evo)
                .post("/events")
                .then()
                .statusCode(201)
                .assertThat()
                .body("name", equalTo("Betty Who"));
    }
}
