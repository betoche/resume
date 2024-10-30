package org.als.resume.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import org.als.resume.enums.LanguageEnum;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.Instant;

@Entity
@Builder
public record Summary(@Id @GeneratedValue(strategy = GenerationType.SEQUENCE) Long id, String summaryContent,
                      String htmlVersion, @JdbcTypeCode(SqlTypes.BIGINT) Users userSummary, LanguageEnum language,
                      @JdbcTypeCode(SqlTypes.BIGINT) CssStyle style, @CreatedDate Instant creationDate,
                      @LastModifiedDate Instant lastModifiedDate, Boolean isTest, Boolean deleted) {

    public Summary(String summaryContent, String htmlVersion, Users user, LanguageEnum language, CssStyle style) {
        this(null, summaryContent, htmlVersion, user, language, style, null, null,
                false, false);
    }

    public static Summary getTestSummary() {
        String summary = "With over 16 year of software development experience, the main objective remains the same, " +
                "help to provide good and reliable IT systems functionality by implementing good development practices " +
                "and automatizing manual processes, which gets automatically translated into fast, " +
                "agile and smart business decisions making.";
        return Summary.builder()
                .summaryContent(summary)
                .htmlVersion(summary)
                .language(LanguageEnum.ENGLISH)
                .build();
    }
}
