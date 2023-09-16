package br.com.guzzmega.financial.service;

import br.com.guzzmega.financial.domain.User;
import br.com.guzzmega.financial.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

public class UserServiceTest {

    private UserService service;

    @Test
    public void mustReturnUserByEmail(){

        UserRepository repository = Mockito.mock(UserRepository.class);
        service = new UserService(repository);

        Optional<User> user = service.findUserByEmail("joao@gmail.com");
        Assertions.assertTrue(user.isEmpty());
    }
}
