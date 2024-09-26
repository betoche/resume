package org.als.resume.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.Instant;

@Entity
@Builder
public record Email(@Id @GeneratedValue(strategy = GenerationType.SEQUENCE) Long id,
                    @JdbcTypeCode(SqlTypes.BIGINT) Users userSummary, String emailAddress,
                    @CreatedDate Instant createdDate, @LastModifiedDate Instant modifiedDate, Boolean isTest,
                    Boolean deleted) {

    public Email( Users user, String emailAddress ) {
        this(null, user, emailAddress, null, null, false, false);
    }

    public static Email getTestEmail() {
        return Email.builder()
                .isTest(true)
                .deleted(false)
                .emailAddress("larios.alberto@gmail.com")
                .build();
    }
}
