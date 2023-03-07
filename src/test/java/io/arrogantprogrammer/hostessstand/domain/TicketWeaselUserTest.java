package io.arrogantprogrammer.hostessstand.domain;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.Collections;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@QuarkusTest
public class TicketWeaselUserTest {

    @Inject
    TicketWeaselUserRepository repository;

    @Test @Transactional
    public void testAddingTicketsToUser() {

        TicketWeaselUser ticketWeaselUser = new TicketWeaselUser("Jeremy", "Davis", "jeremy@someemail.com");
        repository.persist(ticketWeaselUser);
        ticketWeaselUser.addTickets(Collections.singletonList(new Ticket(UUID.randomUUID())));
        assertEquals(1, ticketWeaselUser.getTickets().size());
    }

    @Test @Transactional
    public void testListingTicketsForSale() {

        TicketWeaselUser ticketWeaselUser = new TicketWeaselUser("Jeremy", "Davis", "jeremy@someemail.com");
        repository.persist(ticketWeaselUser);
        repository.flush();
        ticketWeaselUser.addTickets(Collections.singletonList(new Ticket(UUID.randomUUID())));
        assertEquals(1, ticketWeaselUser.getTickets().size());
        Ticket ticket = ticketWeaselUser.getTickets().get(0);
        ticketWeaselUser.listTicketsForSale(Collections.singletonList(ticket));
        assertTrue(ticketWeaselUser.getTickets().get(0).isListedForSale());
    }

}
