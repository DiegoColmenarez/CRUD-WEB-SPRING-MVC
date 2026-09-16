package org.exercise7.model.service;

import org.exercise7.model.repository.PasswordResetTokenRepository;
import org.exercise7.model.repository.UserRepository;

public class PasswordResetService {
    private static final int CODE_EXPIRATION_MINUTES = 15;

    private final UserRepository userRepository;
    private final PasswordResetTokenRepository tokenRepository;
    private final PasswordResetCodeGenerator codeGenerator;
    private final EmailService emailService;
    private final UserService userService;

    public PasswordResetService(
            UserRepository userRepository,
            PasswordResetTokenRepository tokenRepository,
            PasswordResetCodeGenerator codeGenerator,
            EmailService emailService,
            UserService userService) {
        this.userRepository = userRepository;
        this.tokenRepository = tokenRepository;
        this.codeGenerator = codeGenerator;
        this.emailService = emailService;
        this.userService = userService;
    }
}
