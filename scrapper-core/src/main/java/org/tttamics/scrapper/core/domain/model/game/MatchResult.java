package org.tttamics.scrapper.core.domain.model.game;

import org.albertsanso.commons.model.ValueObject;

public class MatchResult extends ValueObject {
    private Integer localResultValue;
    private Integer visitorResultValue;

    public MatchResult(Integer localResultValue, Integer visitorResultValue) {
        this.localResultValue = localResultValue;
        this.visitorResultValue = visitorResultValue;
    }

    public Integer getLocalResultValue() {
        return localResultValue;
    }

    public Integer getVisitorResultValue() {
        return visitorResultValue;
    }
}
