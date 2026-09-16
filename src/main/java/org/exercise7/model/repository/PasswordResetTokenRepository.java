package org.exercise7.model.repository;

import org.exercise7.model.entity.PasswordResetToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {

    @Query("SELECT t FROM PasswordResetToken t " +
            "WHERE t.code = :code AND t.used = false AND t.expiresAt > CURRENT_TIMESTAMP " +
            "ORDER BY t.createdAt DESC")
    List<PasswordResetToken> findValidByCode(@Param("code") String code);


    @Modifying
    @Query("UPDATE PasswordResetToken t SET t.used = true WHERE t.id = :id")
    void markAsUsedById(@Param("id") Long id);
}