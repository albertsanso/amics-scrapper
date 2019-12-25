package org.tttamics.scrapper.core.repository.jpa.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.tttamics.scrapper.core.repository.jpa.model.JpaMatch;

import java.util.List;

@Repository
public interface MatchJpaRepositoryHelper extends CrudRepository<JpaMatch, String> {
    List<JpaMatch> findByCompetitionId(String id);
}
