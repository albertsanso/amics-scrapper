package org.tttamics;

import org.albertsanso.commons.event.DomainEvent;
import org.albertsanso.commons.event.SynchronousEventBus;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class EventPublisher {

    private SynchronousEventBus synchronousEventBus;

    @Inject
    public EventPublisher(SynchronousEventBus synchronousEventBus) {
        this.synchronousEventBus = synchronousEventBus;
    }

    public void publish(DomainEvent domainEvent) {
        synchronousEventBus.publish(domainEvent);
    }
}
