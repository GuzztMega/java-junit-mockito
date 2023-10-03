package br.com.guzzmega.financial.service;

import br.com.guzzmega.financial.domain.Transaction;
import br.com.guzzmega.financial.exception.ValidationException;
import br.com.guzzmega.financial.repository.TransactionDAO;
import br.com.guzzmega.financial.service.external.ClockService;

public class TransactionService {

    private TransactionDAO transactionDAO;
    private ClockService clockService;

    public Transaction save(Transaction transaction){
        if(clockService.getCurrentTime().getHour() > 16)
            throw new RuntimeException("Business hours closed. Try again tomorrow.");

        validateAttributes(transaction);
        return transactionDAO.save(transaction);
    }

    private void validateAttributes(Transaction transaction) {
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
    }
}