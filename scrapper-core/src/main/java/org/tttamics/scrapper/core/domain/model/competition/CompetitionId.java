package org.tttamics.scrapper.core.domain.model.competition;

import org.albertsanso.commons.model.ValueObject;

import java.util.Objects;

public class CompetitionId extends ValueObject {
    private final String id;

    private CompetitionId(String id) {
        this.id = id;
    }

    public static CompetitionId of(String id) {
        if (Objects.isNull(id)) {
            return null;
        }
        return new CompetitionId(id);
    }

    public String getId() {
        return id;
    }
}
