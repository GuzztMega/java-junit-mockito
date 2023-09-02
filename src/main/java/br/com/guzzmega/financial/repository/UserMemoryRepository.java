package br.com.guzzmega.financial.repository;

import br.com.guzzmega.financial.domain.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserMemoryRepository implements UserRepository{

    private Long id;
    private List<User> userList;

    public UserMemoryRepository(){
        setId(0L);
        setUserList(new ArrayList<>());
        save(new User(null, "user #1", "user1@gmail.com", "123456"));
    }

    @Override
    public User save(User user) {
        User newUser = new User(nextId(), user.getName(), user.getEmail(), user.getPassword());
        getUserList().add(newUser);

        return newUser;
    }

    @Override
    public Optional<User> findUserByEmail(String email) {
        return userList.stream()
                .filter(user -> user.getEmail().equalsIgnoreCase(email))
                .findFirst();
    }

    private void printUserList(){
        getUserList().forEach(System.out::println);
    }

    public static void main(String[] args) {
        UserMemoryRepository repo = new UserMemoryRepository();
        //repo.printUserList();
        repo.save(new User(null, "John Doe", "jd@gmail.com", "senha321"));
        repo.save(new User(null, "Mary Doe", "md@gmail.com", "senha456"));
        repo.printUserList();
    }

    private Long nextId(){
        return ++id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }
}
