package org.als.resume.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.Instant;

@Entity
@Builder
public record Address(@Id @GeneratedValue(strategy = GenerationType.SEQUENCE) Long id, String address,
                      @JdbcTypeCode(SqlTypes.BIGINT) Users userSummary, @CreatedDate Instant createdDate,
                      @LastModifiedDate Instant lastModifiedDate, @DefaultValue("0") Boolean isTest,
                      @DefaultValue("0") Boolean deleted) {

    private static final String TEST_ADDRESS = "Lomas de San Judas, de Lab. UNIPHARM 3c al sur, 3c abajo, casa Nº L-13";

    public Address(String address, Users user, Boolean deleted) {
        this(null, address, user, null, null, false, false);
    }

    public static Address getTestAddress() {
        return Address.builder()
                .address(TEST_ADDRESS)
                .isTest(true)
                .deleted(false)
                .build();
    }
}
