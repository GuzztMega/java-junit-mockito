package br.com.guzzmega.financial.domain.builders;

import br.com.guzzmega.financial.domain.Account;
import br.com.guzzmega.financial.domain.User;

public class AccountBuilder {

    private Long id;
    private String name;
    private User user;

    public Account build() {
        return new Account(getId(), getName(), getUser());
    }

    public static AccountBuilder getOneAccount() {
        AccountBuilder builder = new AccountBuilder();
        initializeDefaultValues(builder);
        return builder;
    }

    private static void initializeDefaultValues(AccountBuilder builder) {
        builder.setId(1L);
        builder.setName("account-xyz");
        builder.setUser(UserBuilder.getOneUser().build());
    }

    public AccountBuilder withId(Long param) {
        this.setId(param);
        return this;
    }

    public AccountBuilder withName(String param) {
        this.setName(param);
        return this;
    }

    public AccountBuilder withUser(User param) {
        this.setUser(param);
        return this;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}