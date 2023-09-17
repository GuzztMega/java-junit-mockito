package br.com.guzzmega.financial.service;

import br.com.guzzmega.financial.domain.Account;
import br.com.guzzmega.financial.exception.ValidationException;
import br.com.guzzmega.financial.repository.AccountRepository;

import java.util.List;

public class AccountService {

    private final AccountRepository repository;

    public AccountService(AccountRepository repository){
        this.repository = repository;
    }

    public Account save(Account account){
        List<Account> accountList = repository.findAccountsByUser(account.getUser().getId());

        accountList.forEach(acc -> {
            if(acc.getName().equalsIgnoreCase(account.getName()))
                throw new ValidationException("User already has an Account with this name");
        });

        return repository.save(account);
    }
}