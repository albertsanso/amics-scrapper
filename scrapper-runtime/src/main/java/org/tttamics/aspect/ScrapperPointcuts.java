package org.tttamics.aspect;

import org.albertsanso.commons.event.DomainEvent;
import org.albertsanso.commons.event.Event;
import org.albertsanso.commons.model.Entity;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.tttamics.EventPublisher;

import javax.inject.Inject;

@Aspect
@Component
public class ScrapperPointcuts {

    private EventPublisher eventPublisher;

    @Inject
    public ScrapperPointcuts(EventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    //@Pointcut(value="execution(* org.tttamics.scrapper.core.domain.service.**.*Service.*(..))")
    @Pointcut(value="execution(* org.tttamics.scrapper.core.repository.jpa.adapters.*Repository.save(..))")
    void triggerRepositorySave() {}

    @Around(value = "triggerRepositorySave()")
    public void aroundAdvice(ProceedingJoinPoint pjp) throws Throwable {
        extractAndPublishEvents(pjp);
        try {
            pjp.proceed();
        } finally {
            // Do something useful.
        }
    }

    private void extractAndPublishEvents(ProceedingJoinPoint pjp) {
        Entity entity = (Entity) pjp.getArgs()[0];
        for (Event event : entity.getEvents()) {
            eventPublisher.publish((DomainEvent) event);
        }

    }
}
