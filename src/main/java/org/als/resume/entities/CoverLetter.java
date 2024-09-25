package org.als.resume.entities;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.Instant;

@Builder
public record CoverLetter(@Id @GeneratedValue(strategy = GenerationType.SEQUENCE) Long id, String letterNoteContent,
                         String htmlVersion, CssStyle style, @CreatedDate Instant createdDate,
                         @LastModifiedDate Instant lastModifiedDate, Boolean isTest, Boolean deleted) {

    public CoverLetter(String letterNoteContent, String htmlVersion, CssStyle style) {
        this(null, letterNoteContent, htmlVersion, style, null, null, false, false);
    }

    public static CoverLetter getTestLetterNote() {
        String content = """
                
                """;

        return CoverLetter.builder()
                .letterNoteContent(content)
                .htmlVersion(content)
                .style( CssStyle.getTestCssStyle() )
                .build();
    }
}
