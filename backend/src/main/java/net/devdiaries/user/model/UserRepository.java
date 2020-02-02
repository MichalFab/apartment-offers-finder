package net.devdiaries.user.model;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findByUserCredentialsUsername(String username);
}
