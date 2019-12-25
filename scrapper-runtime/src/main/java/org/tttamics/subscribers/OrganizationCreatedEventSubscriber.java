package org.tttamics.subscribers;

import org.albertsanso.commons.event.DomainEventSubscriber;
import org.tttamics.scrapper.core.domain.event.OrganizationCreatedEvent;

import javax.inject.Named;

@Named
public class OrganizationCreatedEventSubscriber extends DomainEventSubscriber<OrganizationCreatedEvent> {
    @Override
    public void handle(OrganizationCreatedEvent organizationCreatedEvent) {

        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> OrganizationCreatedEventHandler");
    }
}
