package net.devdiaries.reports.repository;

import net.devdiaries.reports.SearchCriteria;
import net.devdiaries.user.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface SearchCriteriaRepository extends CrudRepository<SearchCriteria, Long> {

    Optional<SearchCriteria> findAllByUser(User user);

}
