package br.com.guzzmega.financial.repository;

import br.com.guzzmega.financial.domain.User;

import java.util.Optional;

public interface UserRepository {

    User save(User user);

    Optional<User> findUserByEmail(String email);
}
