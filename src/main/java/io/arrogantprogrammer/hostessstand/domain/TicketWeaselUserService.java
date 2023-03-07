package io.arrogantprogrammer.hostessstand.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

@ApplicationScoped
public class TicketWeaselUserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TicketWeaselUserService.class);

    @Inject
    TicketWeaselUserRepository ticketWeaselUserRepository;

    @Transactional
    public TicketWeaselUserDTO register(TicketWeaselUserDTO ticketWeaselUserDTO) {

        TicketWeaselUser ticketWeaselUser = new TicketWeaselUser(ticketWeaselUserDTO.firstName(), ticketWeaselUserDTO.lastName(), ticketWeaselUserDTO.email());
        LOGGER.debug("registering {} from {}", ticketWeaselUser, ticketWeaselUserDTO);
        ticketWeaselUserRepository.persist(ticketWeaselUser);
        LOGGER.debug("registered: {}", ticketWeaselUser);
        return new TicketWeaselUserDTO(ticketWeaselUser.getId(), ticketWeaselUser.getFirstName(), ticketWeaselUser.getLastName(), ticketWeaselUser.getEmail());
    }
}
