package br.com.guzzmega.financial.service;

import br.com.guzzmega.financial.domain.User;
import br.com.guzzmega.financial.exception.ValidationException;
import br.com.guzzmega.financial.repository.UserRepository;

import java.util.Optional;

public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository){
        this.repository = repository;
    }

    public User save(User user){
        repository.findUserByEmail(user.getEmail()).ifPresent(entity -> {
            throw new ValidationException(String.format("%s is already in use", entity.getEmail()));
        });
        return repository.save(user);
    }

    public Optional<User> findUserByEmail(String email){
        return repository.findUserByEmail(email);
    }
}
