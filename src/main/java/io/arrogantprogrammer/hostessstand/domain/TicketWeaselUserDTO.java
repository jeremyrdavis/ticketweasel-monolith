package io.arrogantprogrammer.hostessstand.domain;

public record TicketWeaselUserDTO(Long id, String firstName, String lastName, String email) {

    public TicketWeaselUserDTO(String firstName, String lastName, String email) {
        this(null, firstName, lastName, email);
    }

}
