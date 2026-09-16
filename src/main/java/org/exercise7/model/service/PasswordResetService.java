package org.exercise7.model.service;

import org.exercise7.model.entity.PasswordResetToken;
import org.exercise7.model.entity.User;
import org.exercise7.model.repository.PasswordResetTokenRepository;
import org.exercise7.model.repository.UserRepository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

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

    @Transactional
    public void requestResetCode(String email) {
        Optional<User> userOpt = userRepository.findByEmail(email);
        if (userOpt.isEmpty()) {
            return;
        }
        User user = userOpt.get();
        String code = codeGenerator.generate();
        LocalDateTime expiresAt = LocalDateTime.now().plusMinutes(CODE_EXPIRATION_MINUTES);
        PasswordResetToken token = new PasswordResetToken(user, code, expiresAt);
        tokenRepository.save(token);
        emailService.sendResetCode(email, code);
    }
}
