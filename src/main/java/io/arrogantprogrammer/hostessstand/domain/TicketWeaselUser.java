package io.arrogantprogrammer.hostessstand.domain;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.*;

@Entity
class TicketWeaselUser extends PanacheEntity {

    String firstName;

    String lastName;

    String email;

    @OneToMany(mappedBy = "ticketWeaselUser")
    List<Ticket> tickets;

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
            tickets = new ArrayList<>(ticketsList.size());
        }
        ticketsList.forEach(ticket -> {
            ticket.ticketWeaselUser = this;
        });
        this.tickets.addAll(ticketsList);
    }

    public List<Ticket> getTickets() {
        if (this.tickets == null) {
            return new ArrayList<>();
        }else {
            return this.tickets;
        }
    }

    public void listTicketsForSale(List<Ticket> ticketsToList) {
        ticketsToList.forEach(ticketToList -> {
            tickets.forEach(ticket -> {
                if(ticketToList.getUuid().equals(ticket.getUuid())){
                    ticket.listForSale();
                }
            });
        });
    }
}
