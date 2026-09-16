package org.exercise7.model.service;

import org.exercise7.model.entity.User;
import org.exercise7.model.enums.TypeUser;
import org.exercise7.model.exceptions.EmailAlreadyExistsException;
import org.exercise7.model.exceptions.UserNotFoundException;
import org.exercise7.model.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public User registerUser(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw EmailAlreadyExistsException.becauseEmailAlredyExist();
        }
        user.setType(TypeUser.CLIENTE);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Transactional
    public User saveUser(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw EmailAlreadyExistsException.becauseEmailAlredyExist();
        }
        if (user.getType() == null) {
            user.setType(TypeUser.CLIENTE);
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public User findUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> UserNotFoundException.becauseIdDoesNotExist(id));
    }

    @Transactional(readOnly = true)
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<User> findUsersByName(String name) {
        if (name == null || name.trim().isEmpty()) {
            return userRepository.findAll();
        }
        return userRepository.findByName(name.trim());
    }

    @Transactional(readOnly = true)
    public List<User> findUsersByLastName(String lastName) {
        return userRepository.findByLastName(lastName);
    }

    @Transactional(readOnly = true)
    public List<User> findUsersByType(TypeUser type) {
        return userRepository.findByType(type);
    }

    @Transactional
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw UserNotFoundException.becauseIdDoesNotExist(id);
        }
        userRepository.deleteById(id);
    }

    @Transactional
    public void updateUserPassword(Long id, String rawNewPassword) {
        if (!userRepository.existsById(id)) {
            throw UserNotFoundException.becauseIdDoesNotExist(id);
        }
        String encodedPassword = passwordEncoder.encode(rawNewPassword);
        userRepository.updatePassword(id, encodedPassword);
    }

    @Transactional
    public void updateUserBasicInfo(Long id, String newName, String newLastName, String newEmail) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> UserNotFoundException.becauseIdDoesNotExist(id));

        if (!existingUser.getEmail().equals(newEmail) && userRepository.existsByEmail(newEmail)) {
            throw EmailAlreadyExistsException.becauseEmailAlredyExist();
        }
        existingUser.setName(newName);
        existingUser.setLastName(newLastName);
        existingUser.setEmail(newEmail);
        userRepository.save(existingUser);
    }
}