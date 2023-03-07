package io.arrogantprogrammer.ticketweasel.users.domain;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Objects;
import java.util.UUID;
@Entity
public class Ticket extends PanacheEntity {

    UUID uuid;

    boolean isListedForSale;

    @ManyToOne
    @JoinColumn(name="ticketWeaselUser_id", nullable=false)
    TicketWeaselUser ticketWeaselUser;

    public Ticket() {
        this.uuid = UUID.randomUUID();
    }

    public Ticket(UUID uuid) {
        this.uuid = uuid;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("Ticket{uuid=")
                .append(uuid)
                .append(", ticketWeaselUser=");
                if(ticketWeaselUser != null){
                    stringBuilder.append(ticketWeaselUser);
                }else {
                    stringBuilder.append("null");
                }
                stringBuilder.append(", id=").append(id).append("'}'");
                return stringBuilder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ticket ticket = (Ticket) o;

        if (isListedForSale != ticket.isListedForSale) return false;
        return uuid.equals(ticket.uuid);
    }

    @Override
    public int hashCode() {
        int result = uuid.hashCode();
        result = 31 * result + (isListedForSale ? 1 : 0);
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
