package br.com.guzzmega.financial.domain;

import br.com.guzzmega.financial.domain.builders.AccountBuilder;
import br.com.guzzmega.financial.domain.builders.UserBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AccountTest {

    @Test
    @DisplayName("Must Create a Valid Account")
    public void mustCreateValidAccount(){

        Account account = AccountBuilder.getOneAccount().build();
        Assertions.assertAll(
                "Create a Valid Account",
                () -> Assertions.assertEquals(1L, account.getId()),
                () -> Assertions.assertEquals("account-xyz", account.getName()),
                () -> Assertions.assertEquals(UserBuilder.getOneUser().build(), account.getUser())
        );
    }
}