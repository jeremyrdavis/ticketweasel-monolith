package io.arrogantprogrammer.hostessstand.users.domain.domain;

import io.arrogantprogrammer.hostessstand.users.domain.domain.Ticket;
import io.arrogantprogrammer.hostessstand.users.domain.domain.TicketWeaselUser;
import io.arrogantprogrammer.hostessstand.users.domain.domain.TicketWeaselUserRepository;
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
        ticketWeaselUser.listTicketsForSale(Collections.singletonList(ticket.getUuid()));
        assertTrue(ticketWeaselUser.getTickets().get(0).isListedForSale());
    }

    @Test
    @Transactional
    public void testSellingTickets() {

        TicketWeaselUser ticketWeaselUser = new TicketWeaselUser("Lemmy", "Kilminster", "lemmy@motorhead.com");
        repository.persist(ticketWeaselUser);
        repository.flush();

        Ticket ticket = new Ticket(UUID.randomUUID());
        ticketWeaselUser.addTickets(Collections.singletonList(ticket));
        assertEquals(1, ticketWeaselUser.getTickets().size());
        ticketWeaselUser.listTicketsForSale(Collections.singletonList(ticket.getUuid()));
        assertTrue(ticketWeaselUser.getTickets().get(0).isListedForSale());

        ticketWeaselUser.sellTicket(Collections.singletonList(ticket.getUuid()));
        assertEquals(0, ticketWeaselUser.getTickets().size());
    }

}
