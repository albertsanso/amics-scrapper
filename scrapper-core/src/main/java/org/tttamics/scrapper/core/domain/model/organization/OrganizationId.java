package org.tttamics.scrapper.core.domain.model.organization;

import org.albertsanso.commons.model.ValueObject;

import java.util.Objects;

public class OrganizationId extends ValueObject {
    private String id;

    private OrganizationId(String id) {
        this.id = id;
    }

    public static OrganizationId of(String id) {
        if (Objects.isNull(id)) {
            return null;
        }
        return new OrganizationId(id);
    }
    public String getId() {
        return id;
    }
}
