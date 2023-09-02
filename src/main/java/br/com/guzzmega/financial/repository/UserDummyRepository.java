package br.com.guzzmega.financial.repository;

import br.com.guzzmega.financial.domain.User;
import br.com.guzzmega.financial.domain.builders.UserBuilder;

import java.util.Optional;

public class UserDummyRepository implements UserRepository {

    @Override
    public User save(User user) {
        return UserBuilder.getOneUser()
                .withName(user.getName())
                .withEmail(user.getEmail())
                .withPassword(user.getPassword())
                .build();
    }

    @Override
    public Optional<User> findUserByEmail(String email) {
        if("joao@gmail.com".equalsIgnoreCase(email)){
            return Optional.of(UserBuilder.getOneUser().withEmail(email).build());
        }

        return Optional.empty();
    }
}