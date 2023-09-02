package br.com.guzzmega.financial.infra;

import br.com.guzzmega.financial.domain.User;
import br.com.guzzmega.financial.domain.builders.UserBuilder;
import br.com.guzzmega.financial.exception.ValidationException;
import br.com.guzzmega.financial.repository.UserMemoryRepository;
import br.com.guzzmega.financial.service.UserService;
import org.junit.jupiter.api.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserMemoryRepositoryServiceTest {

    private static final UserService service = new UserService(new UserMemoryRepository());

    @Test
    @Order(1)
    public void mustSaveValidUser(){
        User user = service.save(UserBuilder.getOneUser().withId(null).build());
        Assertions.assertNotNull(user.getId());
    }

    @Test
    @Order(2)
    public void mustRejectExistentUser(){
        ValidationException exception = Assertions.assertThrows(ValidationException.class, () ->
                service.save(UserBuilder.getOneUser().withId(null).build()));
        Assertions.assertEquals("joao@gmail.com is already in use", exception.getMessage());
    }
}
