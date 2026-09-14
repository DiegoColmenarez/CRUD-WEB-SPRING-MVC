package org.exercise7.model.entity;

import jakarta.persistence.*;
import org.exercise7.model.enums.TypeUser;
import org.exercise7.model.vo.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "nombre", length = 100))
    private UserName name;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "apellido", length = 100))
    private UserName lastName;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "email", unique = true, columnDefinition = "TEXT"))
    private UserEmail email;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "password", columnDefinition = "TEXT"))
    private UserPassword password;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", length = 100)
    private TypeUser type;

    protected User() {}

    private User(UserName name, UserName lastName, UserEmail email, UserPassword password, TypeUser type) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.type = type;
    }

    private User(Long id, UserName name, UserName lastName, UserEmail email, UserPassword password, TypeUser type) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.type = type;
    }

    private User(Long id, UserName name, UserName lastName, UserEmail email) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
    }

    public static User createUser(UserName name, UserName lastName, UserEmail email, UserPassword password) {
        return new User(name, lastName, email, password, TypeUser.CLIENTE);
    }

    public static User createUser(Long id, UserName name, UserName lastName, UserEmail email, UserPassword password, TypeUser type) {
        return new User(id, name, lastName, email, password, type);
    }

    public static User createUser(Long id, UserName name, UserName lastName, UserEmail email) {
        return new User(id, name, lastName, email);
    }

    public static User createUser(UserName name, UserName lastName, UserEmail email, TypeUser type) {
        return new User(null, name, lastName, email, null, type);
    }

    public UserName getName() { return name; }
    public void setName(UserName name) { this.name = name; }

    public UserName getLastName() { return lastName; }
    public void setLastName(UserName lastName) { this.lastName = lastName; }

    public UserEmail getEmail() { return email; }
    public void setEmail(UserEmail email) { this.email = email; }

    public UserPassword getPassword() { return password; }
    public void setPassword(UserPassword password) { this.password = password; }

    public TypeUser getType() { return type; }
    public void setType(TypeUser type) { this.type = type; }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
}