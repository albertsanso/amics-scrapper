package org.tttamics.scrapper.core.domain.model.organization;

import org.albertsanso.commons.model.Entity;
import org.tttamics.scrapper.core.domain.event.OrganizationCreatedEvent;
import org.tttamics.scrapper.core.domain.event.OrganizationDeactivatedEvent;
import org.tttamics.scrapper.core.domain.event.OrganizationModifiedEvent;
import org.tttamics.scrapper.core.domain.event.OrganizationRemovedEvent;
import org.tttamics.scrapper.core.domain.model.match.Match;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Organization extends Entity {

    private OrganizationId id;
    private String name;
    private OrganizationType type;
    private boolean isActive;
    private List<Match> matches;

    private Organization(OrganizationId id, String name, OrganizationType type, boolean isActive, List<Match> matches) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.isActive = isActive;
        this.matches = matches;
    }

    public static OrganizationBuilder builder(String name) { return new OrganizationBuilder(name); }

    private static Organization createNewOrganization(OrganizationBuilder builder) {

        List<Match> matches = builder.getMatches();
        if (Objects.isNull(matches)) {
            matches = new ArrayList<>();
        }
        Organization organization = new Organization(
                builder.getId(),
                builder.getName(),
                builder.getType(),
                builder.isActive(),
                matches);

        organization.initOrganizationCreatedEvent();

        return organization;
    }

    private void initOrganizationCreatedEvent() {
        publishEvent(new OrganizationCreatedEvent());
    }

    public Organization deactivate() {
        this.isActive = false;
        triggerOrganizationDeactivatedEvent();
        return this;
    }

    private void triggerOrganizationDeactivatedEvent() { publishEvent(new OrganizationDeactivatedEvent()); }

    public Organization remove() {
        triggerOrganizationRemovedEvent();
        return this;
    }

    private void triggerOrganizationRemovedEvent() { publishEvent(new OrganizationRemovedEvent()); }

    public void disableOrganization() {
        this.isActive = false;
    }

    public OrganizationId getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public OrganizationType getType() {
        return type;
    }

    public boolean isActive() {
        return isActive;
    }

    public List<Match> getMatches() {
        return Collections.unmodifiableList(matches);
    }

    public void modifyOrganization(String name, OrganizationType type, boolean isActive) {
        this.name = name;
        this.type = type;
        this.isActive = isActive;

        publishEvent(new OrganizationModifiedEvent());
    }

    @Override
    public String toString() {
        return "Organization{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", isActive=" + isActive +
                ", matches=" + matches +
                '}';
    }

    public static final class OrganizationBuilder {

        private OrganizationId id;
        private String name;
        private OrganizationType type;
        private boolean isActive;
        private List<Match> matches;

        public OrganizationBuilder(String name) {
            this.name = name;
        }

        public OrganizationBuilder withId(OrganizationId id) {
            this.id = id;
            return this;
        }

        public OrganizationBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public OrganizationBuilder withOrganizationType(OrganizationType organizationType) {
            this.type = organizationType;
            return this;
        }

        public OrganizationBuilder withActive(boolean active) {
            this.isActive = active;
            return this;
        }

        public OrganizationBuilder withMatches(List<Match> matches) {
            this.matches = matches;
            return this;
        }

        public OrganizationId getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public OrganizationType getType() {
            return type;
        }

        public boolean isActive() {
            return isActive;
        }

        public List<Match> getMatches() {
            return matches;
        }

        public Organization create() { return createNewOrganization(this); }
    }
}
