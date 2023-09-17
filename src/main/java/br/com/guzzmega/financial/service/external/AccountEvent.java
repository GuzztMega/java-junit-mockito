package br.com.guzzmega.financial.service.external;

import br.com.guzzmega.financial.domain.Account;

public interface AccountEvent {

    enum EvenType {CREATED, UPDATED, DELETED}

    void dispatch(Account account, EvenType evenType);
}
