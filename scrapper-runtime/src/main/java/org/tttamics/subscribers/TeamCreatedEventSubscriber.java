package org.tttamics.subscribers;

import org.albertsanso.commons.event.DomainEventSubscriber;
import org.tttamics.scrapper.core.domain.event.TeamCreatedEvent;

import javax.inject.Named;

@Named
public class TeamCreatedEventSubscriber extends DomainEventSubscriber<TeamCreatedEvent> {
    @Override
    public void handle(TeamCreatedEvent teamCreatedEvent) {

        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> TeamCreatedEventHandler");
    }
}
