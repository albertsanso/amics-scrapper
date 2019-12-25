package org.tttamics.scrapper.core.domain.model.competition;

import org.albertsanso.commons.model.ValueObject;

public class CompetitionGroup extends ValueObject {

    private static final String EMPTY_COMPETITION_GROUP = "";

    private String name;

    private CompetitionGroup(String name) {
        this.name = name;
    }

    public static CompetitionGroup createCompetitionGroup(String name) {
        return new CompetitionGroup(name);
    }

    public static CompetitionGroup createEmptyCompetitionGroup() {
        return new CompetitionGroup(EMPTY_COMPETITION_GROUP);
    }

    public static String getDefaultCompetitionGroupName() { return EMPTY_COMPETITION_GROUP; }

    public String getName() {
        return name;
    }
}
