package org.als.resume.helper;

import lombok.Builder;
import lombok.Getter;
import org.als.resume.entities.IdText;
import org.als.resume.enums.WebElementType;

@Getter
@Builder
public class DictionaryField {
    private String fieldName;
    private WebElementType webElementType;
    private Object fieldValue;
    private Iterable<IdText> fieldValueOptions;
}
