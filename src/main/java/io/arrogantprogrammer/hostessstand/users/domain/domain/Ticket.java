package io.arrogantprogrammer.hostessstand.users.domain.domain;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Objects;
import java.util.UUID;
@Entity
public class Ticket extends PanacheEntity {

    private UUID uuid;

    private boolean isListedForSale;

    @ManyToOne
    @JoinColumn(name="ticketWeaselUser_id", nullable=false)
    TicketWeaselUser ticketWeaselUser;

    public Ticket(UUID uuid) {
        this.uuid = uuid;
    }

    public Ticket() {

    }

    @Override
    public String toString() {
        return "Ticket{" +
                "uuid=" + uuid +
                ", ticketWeaselUser=" + ticketWeaselUser.getId() +
                ", id=" + id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ticket ticket = (Ticket) o;

        if (Objects.equals(uuid, ticket.uuid)) return true;
        return false;
    }

    @Override
    public int hashCode() {
        int result = uuid != null ? uuid.hashCode() : 0;
        result = 31 * result + (isListedForSale ? 1 : 0);
        result = 31 * result + (ticketWeaselUser != null ? ticketWeaselUser.hashCode() : 0);
        return result;
    }

    public UUID getUuid() {
        return uuid;
    }

    void listForSale() {
        this.isListedForSale = true;
    }

    public boolean isListedForSale() {
        return this.isListedForSale;
    }
}
