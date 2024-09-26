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
public record Phone(@Id @GeneratedValue(strategy = GenerationType.SEQUENCE) Long id,
                    @JdbcTypeCode(SqlTypes.BIGINT) Users userSummary, Country country, String phoneNumber,
                    @CreatedDate Instant createdDate, @LastModifiedDate Instant lastModifiedDate, Boolean isTest,
                    Boolean deleted) {

    private static final String TEST_NUMBER = "84843705";

    public Phone(Users user, Country country, String phoneNumber) {
        this(null, user, country, phoneNumber, null, null, false, false);
    }

    public static Phone getTestPhone() {
        return Phone.builder()
                .isTest(true)
                .deleted(false)
                .country(Country.UNITED_STATES)
                .phoneNumber(TEST_NUMBER)
                .isTest(true)
                .deleted(false)
                .build();
    }

    public String getPhoneStr(){
        return String.format("%s %s", country.getPhonePrefix(), phoneNumber);
    }
}
