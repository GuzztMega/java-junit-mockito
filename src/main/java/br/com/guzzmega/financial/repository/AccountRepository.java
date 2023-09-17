package br.com.guzzmega.financial.repository;

import br.com.guzzmega.financial.domain.Account;
import java.util.List;

public interface AccountRepository {
    Account save(Account account);

    List<Account> findAccountsByUser(Long userId);
}