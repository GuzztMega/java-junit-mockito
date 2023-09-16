package br.com.guzzmega.financial.service;

import br.com.guzzmega.financial.domain.User;
import br.com.guzzmega.financial.domain.builders.UserBuilder;
import br.com.guzzmega.financial.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

public class UserServiceTest {

    private UserService userService;

    @Test
    public void musReturnEmptyWhenInexistentUser(){
        UserRepository repository = Mockito.mock(UserRepository.class);
        userService = new UserService(repository);

//        Mockito default behavior [no need for implementation]
//        Mockito.when(repository.findUserByEmail("joao@gmail.com"))
//                .thenReturn(Optional.empty());

        Optional<User> user = userService.findUserByEmail("joao@gmail.com");
        Assertions.assertTrue(user.isEmpty());
    }

    @Test
    public void mustReturnUserByEmail(){
        UserRepository repository = Mockito.mock(UserRepository.class);
        userService = new UserService(repository);

        Mockito.when(repository.findUserByEmail("joao@gmail.com"))
                .thenReturn(Optional.of(UserBuilder.getOneUser().build()));

        Optional<User> user = userService.findUserByEmail("joao@gmail.com");
        Assertions.assertTrue(user.isPresent());
        System.out.println(user);

        /**
        Extra validations [high coupling!]
        Mockito.verify(repository, Mockito.atLeastOnce()).findUserByEmail("joao@gmail.com");
        Mockito.verify(repository, Mockito.never()).findUserByEmail("zeNinguem@gmail.com");
        Mockito.verifyNoMoreInteractions(repository);
        **/
    }
}
