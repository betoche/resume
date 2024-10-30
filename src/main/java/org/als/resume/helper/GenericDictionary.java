package org.als.resume.helper;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.als.resume.entities.IdText;
import org.als.resume.enums.WebElementType;
import org.als.resume.repositories.RepositoryFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.repository.CrudRepository;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@Builder
public class GenericDictionary {
    private String dictionaryName;
    private List<DictionaryField> valueList;

    private final static Logger LOGGER = LoggerFactory.getLogger(GenericDictionary.class);

    public static GenericDictionary createGenericDictionary(Object dictionaryObject) {
        List<DictionaryField> valueList = new ArrayList<>();

        for(Field field : dictionaryObject.getClass().getFields()) {
            try {
                String fieldName = field.getName();
                var dictionaryFieldBuilder = DictionaryField.builder()
                        .fieldName(fieldName)
                        .fieldValue(field.get(dictionaryObject))
                        .webElementType(
                            switch(field.get(dictionaryObject)) {
                                case Long l -> WebElementType.NUMERIC_BOX;
                                case IdText i -> WebElementType.SELECT;
                                default -> WebElementType.TEXT_BOX;
                            }
                        );

                if ( field.getType().isAssignableFrom(IdText.class) ) {
                    RepositoryFactory repo = new RepositoryFactory(dictionaryObject);
                    dictionaryFieldBuilder.fieldValueOptions(repo.findAll());
                }

                valueList.add(dictionaryFieldBuilder.build());

            } catch(IllegalAccessException e){
                LOGGER.error( e.getMessage(), e );
            }
        }

        return GenericDictionary.builder()
                .dictionaryName( dictionaryObject.getClass().getSimpleName() )
                .valueList(valueList)
                .build();
    }
}
