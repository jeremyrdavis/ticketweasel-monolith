package io.arrogantprogrammer.hostessstand.infrastructure;

import io.arrogantprogrammer.hostessstand.domain.TicketWeaselUserDTO;
import io.arrogantprogrammer.hostessstand.domain.TicketWeaselUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.net.URI;

@Path("/users/register")
public class RegistrationResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(RegistrationResource.class);

    @Inject
    TicketWeaselUserService ticketWeaselUserService;

    @POST
    public Response register(TicketWeaselUserDTO ticketWeaselUserDTO) {

        TicketWeaselUserDTO registeredUser = ticketWeaselUserService.register(ticketWeaselUserDTO);
        LOGGER.debug("created: {}", registeredUser);
        return Response.created(URI.create("/users/" + registeredUser.id())).entity(registeredUser).build();
    }
}
