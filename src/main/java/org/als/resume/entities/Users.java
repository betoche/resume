package org.als.resume.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Entity
@Builder
public record Users( @Id @GeneratedValue(strategy = GenerationType.SEQUENCE) Long id, String firstName,
                     String secondName, String lastName, String secondLastName,
                     @JdbcTypeCode(SqlTypes.BIGINT) Phone phone, @JdbcTypeCode(SqlTypes.BIGINT) Email email,
                     @JdbcTypeCode(SqlTypes.BIGINT) Address address, @JdbcTypeCode(SqlTypes.BIGINT) Summary summary,
                     @JdbcTypeCode(SqlTypes.BIGINT) CoverLetter coverletter, Boolean isTest, Boolean deleted ) {

    public static Users getTestUser() {
        return Users.builder()
                .firstName("Alberto")
                .secondName("José")
                .lastName("Larios")
                .secondLastName("Sánchez")
                .phone(Phone.getTestPhone())
                .email(Email.getTestEmail())
                .address(Address.getTestAddress())
                .summary(Summary.getTestSummary())
                .coverletter(CoverLetter.getTestCoverLetter())
                .isTest(true)
                .deleted(false)
                .isTest(true).build();
    }

    public String getFullName(){
        return String.format("%s %s %s %s", firstName, secondName, lastName, secondLastName);
    }
}
