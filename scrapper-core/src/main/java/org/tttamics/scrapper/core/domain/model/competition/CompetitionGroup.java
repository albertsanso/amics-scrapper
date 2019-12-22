package org.tttamics.scrapper.core.domain.model.competition;

import org.albertsanso.commons.model.ValueObject;

public class CompetitionGroup extends ValueObject {
    private String name;

    public CompetitionGroup(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
