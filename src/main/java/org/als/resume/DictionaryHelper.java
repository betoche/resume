package org.als.resume;

import lombok.Getter;
import org.als.resume.entities.IdText;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Objects;

public class DictionaryHelper {
    private Object dataObject;
    private List<DictionaryField> fieldMap;
    private String dictionaryName;

    public DictionaryHelper( Object dataObject ) {
        this.dataObject = dataObject;
    }

    public List<DictionaryField> getFieldMap() {
        if ( Objects.isNull(fieldMap) ) {
            for(Field field : dataObject.getClass().getFields()){
                String fieldName = field.getName();
                if ( field.getType().isAssignableFrom(IdText.class)) {

                } else {

                }
            }
        }

        return fieldMap;
    }

    public String getDictionaryName(){
        if( dictionaryName.isEmpty() ){
            dictionaryName = dataObject.getClass().getSimpleName();
        }

        return dictionaryName;
    }

    @Getter
    public class DictionaryField {
        private String name;
        private Boolean isList;
        private String value;
        //private List<ValueList> valueList;

        private DictionaryField(){}

        /*
        public static DictionaryField createGenericField( Object fieldValue ) {
            DictionaryField dicField = new DictionaryField();
            return new DictionaryField();
        }
        */
    }
}
