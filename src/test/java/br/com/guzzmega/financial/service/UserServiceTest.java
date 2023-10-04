package br.com.guzzmega.financial.service;

import br.com.guzzmega.financial.domain.User;
import br.com.guzzmega.financial.domain.builders.UserBuilder;
import br.com.guzzmega.financial.exception.ValidationException;
import br.com.guzzmega.financial.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@Tag("service")
@Tag("user")
@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    /*
     // Redundant because @ExtendWith(MockitoExtension.class)
     @BeforeEach
     public void setup(){
     userRepository = Mockito.mock(UserRepository.class);
     userService = new UserService(userRepository);
     }

     // Extra validations [high coupling!]
     @AfterEach
     public void tearDown(){
         Mockito.verifyNoMoreInteractions(userRepository);
     }
    */

    @Test
    public void musReturnEmptyWhenInexistentUser(){
        /*
         Mockito default behavior [no need for implementation]
         Mockito.when(userRepository.findUserByEmail("joao@gmail.com")).thenReturn(Optional.empty());
        */

        Optional<User> user = userService.findUserByEmail("joao@gmail.com");
        Assertions.assertTrue(user.isEmpty());
    }

    @Test
    public void mustReturnUserByEmail(){
        Mockito.when(userRepository.findUserByEmail("joao@gmail.com"))
                .thenReturn(Optional.of(UserBuilder.getOneUser().build()), Optional.of(UserBuilder.getOneUser().build()), null);

        Optional<User> user = userService.findUserByEmail("joao@gmail.com");
        Assertions.assertTrue(user.isPresent());
        System.out.println(user);

        /*
         // Repetitions test
         Mockito.when(userRepository.findUserByEmail("joao@gmail.com"))
         .thenReturn(Optional.of(UserBuilder.getOneUser().build()), Optional.of(UserBuilder.getOneUser().build()), null);

         System.out.println(userService.findUserByEmail("joao@gmail.com"));
         System.out.println(userService.findUserByEmail("joao@gmail.com"));
         System.out.println(userService.findUserByEmail("joao123@gmail.com"));

         // Extra validations [high coupling!]
         Mockito.verify(userRepository, Mockito.times(3)).findUserByEmail("joao@gmail.com");
         Mockito.verify(userRepository, Mockito.atLeastOnce())).findUserByEmail("joao123@gmail.com");
         Mockito.verify(userRepository, Mockito.never()).findUserByEmail("zeNinguem@gmail.com");
         Mockito.verifyNoMoreInteractions(userRepository);
        */
    }

    @Test
    public void mustSaveUserSuccessfully(){
        User savingUser = UserBuilder.getOneUser().withId(null).build();
        Mockito.when(userRepository.save(savingUser)).thenReturn(UserBuilder.getOneUser().build());

        User savedUser = userService.save(savingUser);
        Assertions.assertNotNull(savedUser.getId());

        Mockito.verify(userRepository).findUserByEmail(savingUser.getEmail());

        /*
         // Extra validation [high coupling!]
         Mockito.verify(userRepository).save(savingUser);
        */
    }

    @Test
    public void mustRejectExistentUserOnSave(){
        User savingUser = UserBuilder.getOneUser().withId(null).build();

        Mockito.when(userRepository.findUserByEmail(savingUser.getEmail()))
                .thenReturn(Optional.of(UserBuilder.getOneUser().build()));

        ValidationException exception = Assertions.assertThrows(ValidationException.class, () ->
                userService.save(savingUser));
        Assertions.assertTrue(exception.getMessage().endsWith("is already in use"));

        Mockito.verify(userRepository, Mockito.never()).save(savingUser);
    }
}