package br.com.guzzmega.financial.service;

import br.com.guzzmega.financial.domain.Transaction;
import br.com.guzzmega.financial.exception.ValidationException;
import br.com.guzzmega.financial.repository.TransactionDAO;

import java.time.LocalDateTime;

public class TransactionService {

    TransactionDAO transactionDAO;

    public Transaction save(Transaction transaction){
        if(LocalDateTime.now().getHour() > 16) return null;

        if(transaction.getDescription() == null){
            throw new ValidationException("Description is mandatory.");
        }
        if(transaction.getValue() == null){
            throw new ValidationException("Value is mandatory.");
        }
        if(transaction.getDate() == null){
            throw new ValidationException("Date is mandatory.");
        }
        if(transaction.getAccount() == null){
            throw new ValidationException("Account is mandatory.");
        }
        if(transaction.getStatus() == null){
            throw new ValidationException("Status is mandatory.");
        }

        return transactionDAO.save(transaction);
    }
}