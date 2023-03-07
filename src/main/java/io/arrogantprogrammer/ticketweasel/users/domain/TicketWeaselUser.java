package io.arrogantprogrammer.ticketweasel.users.domain;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.*;
import java.util.*;

@Entity
class TicketWeaselUser extends PanacheEntity {

    String firstName;

    String lastName;

    String email;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_ticket_mapping",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "ticket_uuid", referencedColumnName = "uuid")})
    @MapKeyColumn(name = "uuid")
    Map<UUID, Ticket> tickets;

    public TicketWeaselUser(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public TicketWeaselUser() {
    }

    @Override
    public String toString() {
        return "TicketWeaselUser{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", id=" + id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TicketWeaselUser that = (TicketWeaselUser) o;

        if (!Objects.equals(firstName, that.firstName)) return false;
        if (!Objects.equals(lastName, that.lastName)) return false;
        return Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        int result = firstName != null ? firstName.hashCode() : 0;
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public void addTickets(List<Ticket> ticketsList) {
        if (this.tickets == null) {
            tickets = new HashMap<>(ticketsList.size());
        }
        ticketsList.forEach(ticket -> {
            ticket.ticketWeaselUser = this;
            tickets.put(ticket.getUuid(), ticket);
        });
    }

    public List<Ticket> getTickets() {
        if (this.tickets == null) {
            return new ArrayList<>();
        }else {
            return this.tickets.values().stream().toList();
        }
    }

    public void listTicketsForSale(List<UUID> ticketsToList) {
        ticketsToList.forEach(uuid -> {
            if(tickets.containsKey(uuid)){
                tickets.get(uuid).listForSale();
            }
        });
    }

    public void sellTicket(List<UUID> purchasedTickets) {

        purchasedTickets.forEach(uuid -> {
            if(tickets.containsKey(uuid)){
                tickets.remove(uuid);
            }
        });
    }
}
