package br.com.guzzmega.financial.domain.builders;

import java.lang.Long;

import br.com.guzzmega.financial.domain.Account;
import br.com.guzzmega.financial.domain.Transaction;

import java.time.LocalDate;
import java.lang.Boolean;
import java.lang.Double;
import java.lang.String;


public class TransactionBuilder {
    private Transaction element;
    private TransactionBuilder(){}

    public static TransactionBuilder getOneTransaction() {
        TransactionBuilder builder = new TransactionBuilder();
        inicializarDadosPadroes(builder);
        return builder;
    }

    public static void inicializarDadosPadroes(TransactionBuilder builder) {
        builder.element = new Transaction();
        Transaction transaction = builder.element;

        transaction.setId(1L);
        transaction.setDescription("Bill Payment NF-e 123456");
        transaction.setValue(10.0);
        transaction.setAccount(AccountBuilder.getOneAccount().build());
        transaction.setDate(LocalDate.now());
        transaction.setStatus(true);
    }

    public TransactionBuilder withId(Long param) {
        element.setId(param);
        return this;
    }

    public TransactionBuilder withDescription(String param) {
        element.setDescription(param);
        return this;
    }

    public TransactionBuilder withValue(Double param) {
        element.setValue(param);
        return this;
    }

    public TransactionBuilder withAccount(Account param) {
        element.setAccount(param);
        return this;
    }

    public TransactionBuilder withDate(LocalDate param) {
        element.setDate(param);
        return this;
    }

    public TransactionBuilder withStatus(Boolean param) {
        element.setStatus(param);
        return this;
    }

    public Transaction build() {
        return element;
    }
}
