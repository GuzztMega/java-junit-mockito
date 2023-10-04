package br.com.guzzmega.financial.service;

import br.com.guzzmega.financial.domain.Account;
import br.com.guzzmega.financial.domain.builders.AccountBuilder;
import br.com.guzzmega.financial.exception.ValidationException;
import br.com.guzzmega.financial.repository.AccountRepository;
import br.com.guzzmega.financial.service.external.AccountEvent;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;

@Tag("service")
@Tag("account")
@ExtendWith(MockitoExtension.class)
//@MockitoSettings(strictness = Strictness.LENIENT) // Ignores unnecessary stubbings
public class AccountServiceTest {

    @InjectMocks
    AccountService accountService;
    @Mock
    AccountRepository accountRepository;
    @Mock
    AccountEvent accountEvent;

    @Test
    public void mustSaveFirstAccountSuccessfully(){
        Account savingAccount = AccountBuilder.getOneAccount().withId(null).build();

        Mockito.when(accountRepository.save(savingAccount)).thenReturn(AccountBuilder.getOneAccount().build());
        Mockito.doNothing().when(accountEvent).dispatch(AccountBuilder.getOneAccount().build(), AccountEvent.EvenType.CREATED);

        Account savedAccount = accountService.save(savingAccount);
        Assertions.assertNotNull(savedAccount.getId());

        Mockito.verify(accountRepository).save(Mockito.any(Account.class));
    }

    @Test
    public void mustSaveAnotherAccountSuccessfully(){
        Account savingAccount = AccountBuilder.getOneAccount().withId(null).build();
        Mockito.when(accountRepository.findAccountsByUser(savingAccount.getUser().getId()))
                .thenReturn(Arrays.asList(AccountBuilder.getOneAccount().withName("account-xyz").build()));

        Mockito.when(accountRepository.save(savingAccount)).thenReturn(AccountBuilder.getOneAccount().build());

        Account savedAccount = accountService.save(savingAccount);
        Assertions.assertNotNull(savedAccount);
    }

    @Test
    public void mustRejectRepeatedAccount(){
        Account savingAccount = AccountBuilder.getOneAccount().withId(null).build();
        Mockito.when(accountRepository.findAccountsByUser(savingAccount.getUser().getId()))
                .thenReturn(Arrays.asList(AccountBuilder.getOneAccount().build()));
        /**
         // Unnecessary stubbing [will not save because of thown exception]
         Mockito.when(accountRepository.save(savingAccount)).thenReturn(AccountBuilder.getOneAccount().build());
        **/

        String message = Assertions.assertThrows(ValidationException.class, () ->
                accountService.save(savingAccount)).getMessage();
        Assertions.assertEquals("User already has an Account with this name", message);
    }

    @Test
    public void mustDiscardAccountWithoutEvent(){
        Account savedAccount = AccountBuilder.getOneAccount().build();
        Account savingAccount = AccountBuilder.getOneAccount().withId(null).build();

        Mockito.when(accountRepository.save(savingAccount)).thenReturn(savedAccount);
        Mockito.doThrow(new RuntimeException("Brutal Error"))
                .when(accountEvent).dispatch(savedAccount, AccountEvent.EvenType.CREATED);

        String message = Assertions.assertThrows(RuntimeException.class, () ->
                accountService.save(savingAccount)).getMessage();
        Assertions.assertEquals("Fail to create an Account", message);

        Mockito.verify(accountRepository).delete(savedAccount);;
    }
}