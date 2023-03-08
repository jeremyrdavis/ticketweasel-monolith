package io.arrogantprogrammer.ticketweasel.events.domain;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

@Entity
public class Event extends PanacheEntity {

    UUID uuid;

    String name;

    String venue;

    LocalDate date;

    public Event() {
        this.uuid = UUID.randomUUID();
    }

    public Event(String name, String venue, LocalDate date) {
        this.uuid = UUID.randomUUID();
        this.name = name;
        this.venue = venue;
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Event event = (Event) o;

        return Objects.equals(uuid, event.uuid);
    }

    @Override
    public int hashCode() {
        return uuid.hashCode();
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getName() {
        return name;
    }

    public String getVenue() {
        return venue;
    }

    public LocalDate getDate() {
        return date;
    }
}
