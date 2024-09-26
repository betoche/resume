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
public record CoverLetter(@Id @GeneratedValue(strategy = GenerationType.SEQUENCE) Long id, String letterNoteContent,
                          String htmlVersion, @JdbcTypeCode(SqlTypes.BIGINT) Users userSummary,
                          @JdbcTypeCode(SqlTypes.BIGINT) CssStyle style, @CreatedDate Instant createdDate,
                          @LastModifiedDate Instant lastModifiedDate, Boolean isTest, Boolean deleted) {

    public CoverLetter(String letterNoteContent, String htmlVersion, Users userSummary, CssStyle style) {
        this(null, letterNoteContent, htmlVersion, userSummary, style, null, null, false, false);
    }

    public static CoverLetter getTestCoverLetter() {
        String content = """
                Dear Hiring Manager,


                With over 16 years of experience as a software developer and over 10 years of Java development, building and bringing support to enterprise systems, I am excited to apply for the recently opened position of Remote java Developer.
                                
                In my last position as a Java Developer at eBay I’ve been having the opportunity to acquire more experience developing and bringing support to high-performance, high-volume data processing projects, and also from my previous position at inmobia I’ve also gained experience with highly scalable system in charged of processing millions of mobile user requests.
                                
                I am eager to take on a new challenge and I see this as the perfect opportunity to contribute and grow, being said that I am reaffirming my intention in applying to the mentioned role.
                                
                I welcome the opportunity to speak with you if you feel I’d be a strong candidate for this or any position in your organization.
                               
                                
                Yours faithfully
                Alberto Larios S.
                """;

        return CoverLetter.builder()
                .letterNoteContent(content)
                .htmlVersion(content)
                .style( CssStyle.getTestCssStyle() )
                .build();
    }
}
