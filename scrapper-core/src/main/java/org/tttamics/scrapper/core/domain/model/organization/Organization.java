package org.tttamics.scrapper.core.domain.model.organization;

import org.albertsanso.commons.model.Entity;
import org.tttamics.scrapper.core.domain.event.OrganizationCreatedEvent;
import org.tttamics.scrapper.core.domain.event.OrganizationModifiedEvent;

public class Organization extends Entity {

    private String id;
    private String name;
    private OrganizationType type;
    private boolean isActive;

    private Organization(String id, String name, OrganizationType type, boolean isActive) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.isActive = isActive;
    }

    public static OrganizationBuilder builder(String name) { return new OrganizationBuilder(name); }

    private static Organization createNewOrganization(OrganizationBuilder builder) {
        Organization organization = new Organization(
                builder.getId(),
                builder.getName(),
                builder.getType(),
                builder.isActive());

        organization.initOrganizationCreatedEvent();

        return organization;
    }

    private void initOrganizationCreatedEvent() {
        publishEvent(new OrganizationCreatedEvent());
    }

    public void disableOrganization() {
        this.isActive = false;
    }

    public String getId() {
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

    public void modifyOrganization(String name, OrganizationType type, boolean isActive) {
        this.name = name;
        this.type = type;
        this.isActive = isActive;

        publishEvent(new OrganizationModifiedEvent());
    }

    public static final class OrganizationBuilder {

        private String id;
        private String name;
        private OrganizationType type;
        private boolean isActive;

        public OrganizationBuilder(String name) {
            this.name = name;
        }

        public OrganizationBuilder withId(String id) {
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

        public String getId() {
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

        public Organization create() { return createNewOrganization(this); }
    }
}
