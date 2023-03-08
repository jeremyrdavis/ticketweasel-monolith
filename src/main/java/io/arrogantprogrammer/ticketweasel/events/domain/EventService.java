package io.arrogantprogrammer.ticketweasel.events.domain;

import io.arrogantprogrammer.ticketweasel.events.infrastructure.EventValueObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

@ApplicationScoped
public class EventService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EventService.class);

    @Inject
    EventRepository eventRepository;
    @Transactional
    public EventValueObject addEvent(EventValueObject eventValueObject) {

        Event event = new Event(eventValueObject.venue(), eventValueObject.name(), eventValueObject.date());
        eventRepository.persist(event);
        return new EventValueObject(event.getUuid(), event.getVenue(), event.getName(), event.getDate());
    }
}
