package org.tttamics.scrapper.core.domain.model.match;

import org.albertsanso.commons.model.ValueObject;

import java.util.Objects;

public class MatchId extends ValueObject {
    private String id;

    private MatchId(String id) {
        this.id = id;
    }

    public static MatchId of(String id) {
        if (Objects.isNull(id)) {
            return null;
        }
        return new MatchId(id);
    }

    public String getId() {
        return id;
    }
}
