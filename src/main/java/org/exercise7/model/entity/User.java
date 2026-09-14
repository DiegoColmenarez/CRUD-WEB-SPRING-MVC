package org.exercise7.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.exercise7.model.enums.TypeUser;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "The name is empty or null")
    @Pattern(regexp = "^[\\p{L} \\-']+$", message = "The name contains invalid characters")
    @Column(name = "nombre", length = 100)
    private String name;

    @NotBlank(message = "The last name is empty or null")
    @Pattern(regexp = "^[\\p{L} \\-']+$", message = "The last name contains invalid characters")
    @Column(name = "apellido", length = 100)
    private String lastName;

    @NotBlank(message = "Email is Invalid, It's empty or NULL")
    @Pattern(regexp = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,}$",
            message = "Email is Invalid, it's not an Email format")
    @Column(name = "email", unique = true, columnDefinition = "TEXT")
    private String email;

    @NotBlank(message = "The password is invalid, it's empty or NULL")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$",
            message = "The password must contain at least: a special character, a number, a lowercase, and an uppercase letter.")
    @Column(name = "password", columnDefinition = "TEXT")
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", length = 100)
    private TypeUser type;

    // Hibernate requiere un constructor vacío
    public User() {}

    public User(String name, String lastName, String email, String password, TypeUser type) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.type = type;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public TypeUser getType() { return type; }
    public void setType(TypeUser type) { this.type = type; }
}