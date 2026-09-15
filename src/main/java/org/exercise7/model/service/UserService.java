package org.exercise7.model.service;

import org.exercise7.model.entity.User;
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
         user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public User findUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> UserNotFoundException.becauseIdDoesNotExist(id));
    }

    @Transactional(readOnly = true)
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> UserNotFoundException.becauseEmailDoesNotExist(email));
    }

    @Transactional(readOnly = true)
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<User> findUsersByName(String name) {
        return userRepository.findByName(name);
    }

    @Transactional(readOnly = true)
    public List<User> findUsersByLastName(String lastName) {
        return userRepository.findByLastName(lastName);
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
}