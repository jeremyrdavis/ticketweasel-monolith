package io.arrogantprogrammer.ticketweasel.users.domain;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
public class TicketWeaselUserTest {

    @Inject
    TicketWeaselUserRepository repository;

    @Test @Transactional
    public void testAddingTicketsToUser() {

        TicketWeaselUser ticketWeaselUser = new TicketWeaselUser("Jeremy", "Davis", "jeremy@someemail.com");
        ticketWeaselUser.addTickets(Collections.singletonList(new Ticket(UUID.randomUUID())));
        repository.persist(ticketWeaselUser);
        assertEquals(1, ticketWeaselUser.getTickets().get().size());
        ticketWeaselUser.addTickets(Collections.singletonList(new Ticket(UUID.randomUUID())));
        assertEquals(2, ticketWeaselUser.getTickets().get().size());
    }

    @Test @Transactional
    public void testListingTicketsForSale() {

        TicketWeaselUser ticketWeaselUser = new TicketWeaselUser("Jeremy", "Davis", "jeremy@someemail.com");
        repository.persist(ticketWeaselUser);
        repository.flush();
        ticketWeaselUser.addTickets(Collections.singletonList(new Ticket(UUID.randomUUID())));
        assertEquals(1, ticketWeaselUser.getTickets().get().size());
        Ticket ticket = ticketWeaselUser.getTickets().get().get(0);
        ticketWeaselUser.listTicketsForSale(Collections.singletonList(ticket.getUuid()));
        assertTrue(ticketWeaselUser.getTickets().get().get(0).isListedForSale());
    }

    @Test
    @Transactional
    public void testSellingTickets() {

        TicketWeaselUser ticketWeaselUser = new TicketWeaselUser("Lemmy", "Kilminster", "lemmy@motorhead.com");
        repository.persist(ticketWeaselUser);
        repository.flush();

        Ticket ticket = new Ticket(UUID.randomUUID());
        ticketWeaselUser.addTickets(Collections.singletonList(ticket));
        assertEquals(1, ticketWeaselUser.getTickets().get().size());
        ticketWeaselUser.listTicketsForSale(Collections.singletonList(ticket.getUuid()));
        assertTrue(ticketWeaselUser.getTickets().get().get(0).isListedForSale());

        ticketWeaselUser.sellTicket(Collections.singletonList(ticket.getUuid()));
        assertEquals(0, ticketWeaselUser.getTickets().get().size());
    }

    @Test
    public void testSellingTicketsTicketDoesNotExist() {

        TicketWeaselUser ticketWeaselUser = new TicketWeaselUser("Lemmy", "Kilminster", "lemmy@motorhead.com");
        Ticket ticket = new Ticket(UUID.randomUUID());
        ticketWeaselUser.addTickets(Collections.singletonList(ticket));
        assertEquals(1, ticketWeaselUser.getTickets().get().size());
        ticketWeaselUser.listTicketsForSale(Collections.singletonList(UUID.randomUUID()));
        assertFalse(ticketWeaselUser.getTickets().get().get(0).isListedForSale());

        ticketWeaselUser.sellTicket(Collections.singletonList(UUID.randomUUID()));
        assertEquals(1, ticketWeaselUser.getTickets().get().size());
    }

    @Test
    public void testEmptyTickets() {
        TicketWeaselUser ticketWeaselUser = new TicketWeaselUser("Paul", "McCartney", "paul@wings.com");
        assertTrue(ticketWeaselUser.getTickets().isEmpty());
        ticketWeaselUser.addTickets(Collections.singletonList(new Ticket(UUID.randomUUID())));
        assertFalse(ticketWeaselUser.getTickets().isEmpty());
        assertEquals(1, ticketWeaselUser.getTickets().get().size());
    }

    @Test
    public void testEquality() {

        TicketWeaselUser ticketWeaselUser1 = new TicketWeaselUser("John", "Lennon", "john@beatles.com");
        assertTrue(ticketWeaselUser1.equals(ticketWeaselUser1));
        assertTrue(ticketWeaselUser1.hashCode() == ticketWeaselUser1.hashCode());
        assertFalse(ticketWeaselUser1.equals(null));
        assertFalse(ticketWeaselUser1.equals(new Ticket()));


        TicketWeaselUser ticketWeaselUser2 = new TicketWeaselUser("John", "Lennon", "john@beatles.com");
        assertTrue(ticketWeaselUser1.equals(ticketWeaselUser2));
        assertTrue(ticketWeaselUser1.hashCode() == ticketWeaselUser2.hashCode());

        TicketWeaselUser ticketWeaselUser3 = new TicketWeaselUser("George", "Harrison", "george@beatles.com");
        assertFalse(ticketWeaselUser1.equals(ticketWeaselUser3));
        assertFalse(ticketWeaselUser1.hashCode() == ticketWeaselUser3.hashCode());

        TicketWeaselUser ticketWeaselUser4 = new TicketWeaselUser();
        TicketWeaselUser ticketWeaselUser5 = new TicketWeaselUser();
        assertTrue(ticketWeaselUser4.equals(ticketWeaselUser5));
        assertTrue(ticketWeaselUser4.hashCode() == ticketWeaselUser5.hashCode());

        TicketWeaselUser ticketWeaselUser6 = new TicketWeaselUser("Paul", "McCartney", null);
        TicketWeaselUser ticketWeaselUser7 = new TicketWeaselUser("Paul", "Simenon", null);
        assertFalse(ticketWeaselUser6.equals(ticketWeaselUser7));
        assertFalse(ticketWeaselUser6.hashCode() == ticketWeaselUser7.hashCode());
    }

    @Test
    public void testToString() {
        TicketWeaselUser ticketWeaselUser = new TicketWeaselUser();
        try {
            String toString = ticketWeaselUser.toString();
        } catch (Exception e) {
            assertNull(e);
        }
        ticketWeaselUser.firstName = "Ringo";
        try {
            String toString = ticketWeaselUser.toString();
        } catch (Exception e) {
            assertNull(e);
        }
    }

}
