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
public record CssStyle(@Id @GeneratedValue(strategy = GenerationType.SEQUENCE) Long id, String styleContent,
                       @CreatedDate Instant creationDate,@LastModifiedDate Instant lastModifiedDate, Boolean isTest,
                       Boolean deleted) {

    public CssStyle( String styleContent) {
        this(null, styleContent, null, null, false, false);
    }

    public static CssStyle getTestCssStyle() {
        String style = """
                * {
                  width: 100%;
                }
                """;

        return CssStyle.builder()
                .styleContent(style)
                .build();
    }
}
