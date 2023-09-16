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
        UserRepository mock = Mockito.mock(UserRepository.class);
        userService = new UserService(mock);

        /**
         Mockito default behavior [no need for implementation]
         Mockito.when(repository.findUserByEmail("joao@gmail.com"))
                .thenReturn(Optional.empty());
        **/

        Optional<User> user = userService.findUserByEmail("joao@gmail.com");
        Assertions.assertTrue(user.isEmpty());
    }

    @Test
    public void mustReturnUserByEmail(){
        UserRepository mock = Mockito.mock(UserRepository.class);
        userService = new UserService(mock);

        Mockito.when(mock.findUserByEmail("joao@gmail.com"))
                .thenReturn(Optional.of(UserBuilder.getOneUser().build()), Optional.of(UserBuilder.getOneUser().build()), null);

        Optional<User> user = userService.findUserByEmail("joao@gmail.com");
        Assertions.assertTrue(user.isPresent());
        System.out.println(user);

        /**
         // Repetitions test
         System.out.println(userService.findUserByEmail("joao@gmail.com"));
         System.out.println(userService.findUserByEmail("joao@gmail.com"));
         System.out.println(userService.findUserByEmail("joao123@gmail.com"));

         // Extra validations [high coupling!]
         Mockito.verify(repository, Mockito.times(3)).findUserByEmail("joao@gmail.com");
         Mockito.verify(repository, Mockito.times(1)).findUserByEmail("joao123@gmail.com");
         Mockito.verify(repository, Mockito.never()).findUserByEmail("zeNinguem@gmail.com");
         Mockito.verifyNoMoreInteractions(repository);
        **/
    }

    @Test
    public void mustSaveUserSuccessfully(){
        UserRepository mock = Mockito.mock(UserRepository.class);
        userService = new UserService(mock);

        User savingUser = UserBuilder.getOneUser().withId(null).build();
        Mockito.when(mock.save(savingUser)).thenReturn(UserBuilder.getOneUser().build());

        User savedUser = userService.save(savingUser);
        Assertions.assertNotNull(savedUser.getId());

        Mockito.verify(mock).findUserByEmail(savingUser.getEmail());

        /**
         // Extra validation [high coupling!]
         Mockito.verify(mock).save(savingUser);
        **/

    }
}
