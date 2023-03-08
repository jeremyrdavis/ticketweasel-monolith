package io.arrogantprogrammer.ticketweasel.events.domain;

import io.arrogantprogrammer.ticketweasel.events.infrastructure.EventValueObject;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
public class EventTest {

    @Test
    public void testEventEquality() {

        Event event1 = new Event();
        assertNotNull((event1.getUuid()));
        assertTrue(event1.equals(event1));
        assertFalse(event1.equals(null));
        assertFalse(event1.equals(new EventValueObject("Betty Who", "Variety Playhouse", LocalDate.of(2023, 03, 07))));
        assertTrue(event1.hashCode() == event1.hashCode());

        Event event2 = new Event("Betty Who", "Variety Playhouse", LocalDate.of(2023, 03, 07));
        assertFalse(event1.equals(event2));
        assertFalse(event1.hashCode() == event2.hashCode());

        assertEquals("Betty Who", event2.getName());
        assertEquals("Variety Playhouse", event2.getVenue());
        assertEquals(LocalDate.of(2023, 03, 07), event2.getDate());

        Event event3 = new Event("Samia", "Variety Playhouse", LocalDate.of(2023, 03, 10));
        assertFalse(event2.equals(event3));
        assertFalse(event2.hashCode() == event3.hashCode());

        assertFalse(event3.equals(null));
        assertFalse(event3.equals(new Event()));

        Event event4 = new Event("Samia", "Variety Playhouse", LocalDate.of(2023, 03, 10));
        assertFalse(event3.equals(event4));

        Event event5 = new Event("Samia", "The Star Bar", LocalDate.of(2023, 03, 10));
        assertFalse(event4.equals(event5));

        Event event6 = new Event("Samia", "The Star Bar", LocalDate.of(2023, 03, 11));
        assertFalse(event5.equals(event6));
    }
}
