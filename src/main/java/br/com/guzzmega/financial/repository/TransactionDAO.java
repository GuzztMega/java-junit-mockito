package br.com.guzzmega.financial.repository;

import br.com.guzzmega.financial.domain.Transaction;

public interface TransactionDAO {

    Transaction save(Transaction transaction);
}
