package org.tttamics.scrapper.core.repository.jpa.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.tttamics.scrapper.core.repository.jpa.model.JpaTeam;

@Repository
public interface TeamJpaRepositoryHelper extends CrudRepository<JpaTeam, String> {
    JpaTeam findByName(String name);
    JpaTeam findByNameLike(String name);
}
