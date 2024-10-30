package org.als.resume.enums;

public enum WebElementType {
    TEXT_BOX("text_box"),
    NUMERIC_BOX("numeric_box"),
    SELECT("select"),
    RADIO_BUTTON("radio_button"),
    TEXT_AREA("text_area");

    private String type;

    WebElementType( String type ){
        this.type = type;
    }
}
