package br.com.guzzmega.financial.domain;

import br.com.guzzmega.financial.exception.ValidationException;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Account account)) return false;
        return Objects.equals(getId(), account.getId()) &&
                Objects.equals(getName(), account.getName()) &&
                 Objects.equals(getUser(), account.getUser());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getUser());
    }

    @Override
    public String toString() {
        return "Account { id=" + id + ", name='" + name + "', user=\n\t" + user + "\n }";
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