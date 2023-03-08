package io.arrogantprogrammer.ticketweasel.events.infrastructure;

import java.time.LocalDate;
import java.util.UUID;

public record EventValueObject(UUID uuid, String name, String venue, LocalDate date) {

    public EventValueObject(String name, String venue, LocalDate date){
        this(null, name, venue, date);
    }
}
