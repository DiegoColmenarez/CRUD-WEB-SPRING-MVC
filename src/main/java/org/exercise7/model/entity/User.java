package org.exercise7.model.entity;
import org.exercise7.model.vo.*;
import org.exercise7.model.enums.TypeUser;

public class User {
    private UserName name;
    private UserName lastName;
    private UserId id;
    private UserEmail email;
    private UserPassword password;
    private UserType type;

    private User(
            UserName name,
            UserName lastName,
            UserEmail email,
            UserPassword password,
            UserType type
    ) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.type = type;
    }

    private User(
            UserId id,
            UserName name,
            UserName lastName,
            UserEmail email,
            UserPassword password,
            UserType type
    ) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.type = type;
    }

    private User(
            UserId id,
            UserName name,
            UserName lastName,
            UserEmail email
    ) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
    }

    private User(
            UserName name,
            UserName lastName,
            UserEmail email,
            UserType type
    ) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.type = type;
    }

    public static User createUser(
            UserName name,
            UserName lastName,
            UserEmail email,
            UserPassword password
    ) {
        return new User(name, lastName, email, password, new UserType(TypeUser.CLIENTE));
    }

    public static User createUser(
            UserId id,
            UserName name,
            UserName lastName,
            UserEmail email,
            UserPassword password,
            UserType type
    ) {
        return new User(id, name, lastName, email, password, type);
    }

    public static User createUser(
            UserId id,
            UserName name,
            UserName lastName,
            UserEmail email
    ) {
        return new User(id, name, lastName, email);
    }

    public static User createUser(
            UserName name,
            UserName lastName,
            UserEmail email,
            UserType type
    ) {
        return new User(name, lastName, email, type);

    }

    public UserName getName() {
        return name;
    }

    public void setName(UserName name) {
        this.name = name;
    }

    public UserName getLastName() {
        return lastName;
    }

    public void setLastName(UserName lastName) {
        this.lastName = lastName;
    }

    public UserEmail getEmail() {
        return email;
    }

    public void setEmail(UserEmail email) {
        this.email = email;
    }

    public UserType getType() {
        return type;
    }

    public void setType(UserType type) {
        this.type = type;
    }

    public UserPassword getPassword() {
        return password;
    }

    public void setPassword(UserPassword password) {
        this.password = password;
    }

    public UserId getId() {
        return id;
    }
}