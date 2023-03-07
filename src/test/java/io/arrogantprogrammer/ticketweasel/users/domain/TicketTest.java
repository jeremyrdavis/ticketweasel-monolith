package io.arrogantprogrammer.ticketweasel.users.domain;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
public class TicketTest {

    @Test
    public void testToString() {

        Ticket ticket = new Ticket();
        try {
            ticket.toString();
        } catch (Exception e) {
            assertNull(e);
        }
        ticket.uuid = UUID.randomUUID();
        try {
            ticket.toString();
        } catch (Exception e) {
            assertNull(e);
        }
        ticket.isListedForSale = true;
        try {
            ticket.toString();
        } catch (Exception e) {
            assertNull(e);
        }
        ticket.ticketWeaselUser = new TicketWeaselUser();
        try {
            ticket.toString();
        } catch (Exception e) {
            assertNull(e);
        }
    }

    @Test
    public void testEquality() {
        Ticket ticket1 = new Ticket(UUID.randomUUID());
        assertTrue(ticket1.equals(ticket1));
        assertFalse(ticket1.equals(null));
        assertFalse(ticket1.equals(new TicketWeaselUser()));
        assertFalse(ticket1.hashCode() == 0);

        Ticket ticket2 = new Ticket(UUID.randomUUID());
        assertTrue(ticket1.equals(ticket1));
        assertFalse(ticket1.equals(ticket2));
        assertTrue(ticket1.hashCode() == ticket1.hashCode());
        assertFalse(ticket1.hashCode() == ticket2.hashCode());

        Ticket ticket3 = new Ticket();
        Ticket ticket4 = new Ticket();
        assertFalse(ticket3.equals(ticket4));
        assertFalse(ticket3.hashCode() == ticket4.hashCode());

        ticket3.listForSale();
        assertFalse(ticket3.equals(ticket4));
        assertTrue(ticket3.hashCode() != ticket4.hashCode());

    }
}
