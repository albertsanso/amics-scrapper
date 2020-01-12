package org.tttamics.scrapper.core.domain.model.team;

import org.albertsanso.commons.model.Entity;
import org.tttamics.scrapper.core.domain.event.OrganizationCreatedEvent;

public class Organization extends Entity {

    private OrganizationId id;
    private String name;
    private OrganizationType type;

    private Organization(OrganizationId id, String name, OrganizationType type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    public static OrganizationBuilder builder(String organizationName) {
        return new OrganizationBuilder(organizationName);
    }

    private static Organization createNewOrganization(OrganizationBuilder builder) {
        Organization organization = new Organization(
                builder.getId(),
                builder.getName(),
                builder.getType());

        organization.initOrganizationCreatedEvent();

        return organization;
    }

    private void initOrganizationCreatedEvent() {
        publishEvent(new OrganizationCreatedEvent());
    }

    @Override
    public String toString() {
        return "Organization{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type=" + type +
                '}';
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

    public static final class OrganizationBuilder {
        private OrganizationId id;
        private String name;
        private OrganizationType type;

        public OrganizationBuilder(String name) {
            this.name = name;
        }

        public OrganizationBuilder withId(OrganizationId id) {
            this.id = id;
            return this;
        }

        public OrganizationBuilder withType(OrganizationType type) {
            this.type = type;
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

        public Organization create() { return createNewOrganization(this); }

    }
}
