package br.com.guzzmega.financial.service;

import br.com.guzzmega.financial.domain.Account;
import br.com.guzzmega.financial.exception.ValidationException;
import br.com.guzzmega.financial.repository.AccountRepository;
import br.com.guzzmega.financial.service.external.AccountEvent;

import java.util.List;

public class AccountService {

    private AccountRepository repository;
    private AccountEvent event;

    public AccountService(AccountRepository repository, AccountEvent event){
        this.repository = repository;
        this.event = event;
    }

    public Account save(Account account){
        List<Account> accountList = repository.findAccountsByUser(account.getUser().getId());

        accountList.forEach(acc -> {
            if(acc.getName().equalsIgnoreCase(account.getName()))
                throw new ValidationException("User already has an Account with this name");
        });

        Account savedAccount = repository.save(account);
        try {
            event.dispatch(savedAccount, AccountEvent.EvenType.CREATED);
        } catch (Exception ex) {
            repository.delete(savedAccount);
            throw new RuntimeException("Fail to create an Account");
        }

        return savedAccount;
    }
}