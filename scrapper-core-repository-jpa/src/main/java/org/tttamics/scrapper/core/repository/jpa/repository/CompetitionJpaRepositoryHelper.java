package org.tttamics.scrapper.core.repository.jpa.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.tttamics.scrapper.core.repository.jpa.model.JpaCompetition;

@Repository
public interface CompetitionJpaRepositoryHelper extends CrudRepository<JpaCompetition, String> {
    JpaCompetition findByName(String name);
}
