package br.com.guzzmega.financial.domain;

import br.com.guzzmega.financial.domain.builders.AccountBuilder;
import br.com.guzzmega.financial.domain.builders.UserBuilder;
import br.com.guzzmega.financial.exception.ValidationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class AccountTest {

    @Test
    @DisplayName("Must Create a Valid Account")
    public void mustCreateValidAccount(){
        Account account = AccountBuilder.getOneAccount().build();
        Assertions.assertAll(
                "Create a Valid Account",
                () -> Assertions.assertEquals(1L, account.getId()),
                () -> Assertions.assertEquals("account-abc", account.getName()),
                () -> Assertions.assertEquals(UserBuilder.getOneUser().build(), account.getUser())
        );
    }

    /* Está gerando o erro: java.lang.NoSuchMethodError: 'boolean org.junit.platform.commons.util.CollectionUtils.isConvertibleToStream(java.lang.Class)'
    @DisplayName("Must Reject an Invalid Account")
    @ParameterizedTest
    @MethodSource(value = "provideData")
    public void mustRejectInvalidAccount(Long id, String name, User user, String message){
        String errorMessage = Assertions.assertThrows(ValidationException.class,
            () -> AccountBuilder.getOneAccount().withId(id).withName(name).withUser(user).build()
        ).getMessage();

        Assertions.assertEquals(message, errorMessage);
    }

    private static Stream<Arguments> provideData(){
        return Stream.of(
            Arguments.of(1L, null, UserBuilder.getOneUser().build(), "Name is mandatory"),
            Arguments.of(1L, "João", null, "User is mandatory")
        );
    }
    */
}