package io.arrogantprogrammer.ticketweasel.users.domain;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
class TicketWeaselUserRepository implements PanacheRepository<TicketWeaselUser> {
}
