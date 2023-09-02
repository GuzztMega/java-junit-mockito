package br.com.guzzmega.financial.domain;

import br.com.guzzmega.financial.exception.ValidationException;

public class Account {

    private Long id;
    private String name;
    private User user;

    public Account(Long id, String name, User user) {
        validate(name, user);

        this.id = id;
        this.name = name;
        this.user = user;
    }

    private void validate(String name, User user){
        if(name == null){
            throw new ValidationException("Name is mandatory");
        }
        if(user == null){
            throw new ValidationException("User is mandatory");
        }
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