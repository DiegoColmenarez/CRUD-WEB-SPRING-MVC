package org.exercise7.controller;

import org.exercise7.model.entity.User;
import org.exercise7.model.service.PasswordResetService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class PasswordResetControllerTest {

    private MockMvc mockMvc;

    @Mock
    private PasswordResetService passwordResetService;

    @InjectMocks
    private PasswordResetController passwordResetController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(passwordResetController).build();
    }

    @Test
    void testShowEmailForm() throws Exception {
        mockMvc.perform(get("/password-reset"))
                .andExpect(status().isOk())
                .andExpect(view().name("password-reset/enter-email"));
    }

    @Test
    void testSendCodeValidEmail() throws Exception {
        mockMvc.perform(post("/password-reset/send-code")
                        .param("email", "test@example.com"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/password-reset/verify"))
                .andExpect(flash().attributeExists("successMessage"));
    }

    @Test
    void testShowVerifyCodeForm() throws Exception {
        mockMvc.perform(get("/password-reset/verify"))
                .andExpect(status().isOk())
                .andExpect(view().name("password-reset/enter-code"));
    }

    @Test
    void testVerifyCodeSuccessRedirectsToNewPassword() throws Exception {
        User user = new User();
        user.setEmail("test@example.com");
        when(passwordResetService.validateCode("123456")).thenReturn(Optional.of(user));

        mockMvc.perform(post("/password-reset/verify-code")
                        .param("code", "123456"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/password-reset/new-password"))
                .andExpect(request().sessionAttribute("resetCode", "123456"));
    }

    @Test
    void testGetNewPasswordViewWithValidSession() throws Exception {
        User user = new User();
        user.setEmail("test@example.com");
        when(passwordResetService.validateCode("123456")).thenReturn(Optional.of(user));

        MockHttpSession session = new MockHttpSession();
        session.setAttribute("resetCode", "123456");

        mockMvc.perform(get("/password-reset/new-password").session(session))
                .andExpect(status().isOk())
                .andExpect(view().name("password-reset/new-password"))
                .andExpect(model().attributeExists("passwordResetRequest"));
    }

    @Test
    void testGetNewPasswordViewWithoutSessionRedirectsToPasswordReset() throws Exception {
        mockMvc.perform(get("/password-reset/new-password"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/password-reset"));
    }

    @Test
    void testGetNewPasswordViewWithInvalidOrExpiredCodeRedirectsToPasswordReset() throws Exception {
        when(passwordResetService.validateCode("654321")).thenReturn(Optional.empty());

        MockHttpSession session = new MockHttpSession();
        session.setAttribute("resetCode", "654321");

        mockMvc.perform(get("/password-reset/new-password").session(session))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/password-reset"));
    }

    @Test
    void testUpdatePasswordMismatch() throws Exception {
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("resetCode", "123456");

        mockMvc.perform(post("/password-reset/update-password")
                        .session(session)
                        .param("password", "StrongP@ss123")
                        .param("confirmPassword", "DifferentP@ss123"))
                .andExpect(status().isOk())
                .andExpect(view().name("password-reset/new-password"))
                .andExpect(model().attributeHasFieldErrors("passwordResetRequest", "confirmPassword"));
    }

    @Test
    void testUpdatePasswordSuccess() throws Exception {
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("resetCode", "123456");
        session.setAttribute("resetEmail", "test@example.com");

        mockMvc.perform(post("/password-reset/update-password")
                        .session(session)
                        .param("password", "ValidP@ssword1")
                        .param("confirmPassword", "ValidP@ssword1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login?resetSuccess"));
    }
}
