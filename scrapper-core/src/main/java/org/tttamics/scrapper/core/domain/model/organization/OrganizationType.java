package org.tttamics.scrapper.core.domain.model.organization;

import org.albertsanso.commons.model.ValueObject;

public enum OrganizationType  {
    CLUB ("club");

    private String typeValue;

    OrganizationType(String typeValue) {
        this.typeValue = typeValue;
    }

    public static OrganizationType getByKey(String key) {
        for (OrganizationType organizationType : OrganizationType.values()) {
            if (organizationType.typeValue.equals(key)) {
                return organizationType;
            }
        }
        return null;
    }
}
