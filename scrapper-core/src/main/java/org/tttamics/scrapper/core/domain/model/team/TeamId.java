package org.tttamics.scrapper.core.domain.model.team;

import org.albertsanso.commons.model.ValueObject;

import java.util.Objects;

public class TeamId extends ValueObject {
    private String id;

    private TeamId(String id) {
        this.id = id;
    }

    public static TeamId of(String id) {
        if (Objects.isNull(id)) {
            return null;
        }
        return new TeamId(id);
    }
    public String getId() {
        return id;
    }
}
