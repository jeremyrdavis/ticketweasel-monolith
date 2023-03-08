package io.arrogantprogrammer.ticketweasel.events.infrastructure;

import io.arrogantprogrammer.ticketweasel.events.domain.EventService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;

@Path("/events")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class EventsResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(EventsResource.class);

    @Inject
    EventService eventService;

    @POST
    public Response addEvent(EventValueObject eventValueObject) {

        LOGGER.debug("adding: {}", eventValueObject);
        EventValueObject result = eventService.addEvent(eventValueObject);
        return Response.created(URI.create("/" + result.uuid())).entity(result).build();
    }
}
