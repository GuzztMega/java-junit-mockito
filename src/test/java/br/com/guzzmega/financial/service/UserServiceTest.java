package br.com.guzzmega.financial.service;

import br.com.guzzmega.financial.domain.User;
import br.com.guzzmega.financial.domain.builders.UserBuilder;
import br.com.guzzmega.financial.repository.UserDummyRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UserServiceTest {

    private final UserService service = new UserService(new UserDummyRepository());

    @Test
    public void mustSaveUser(){

        User user = UserBuilder.getOneUser().withId(null).withEmail("some-email@gmail.com").build();
        User savedUser = service.save(user);

        Assertions.assertNotNull(savedUser.getId());
    }
}
