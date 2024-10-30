package org.als.resume.enums;

public enum HonorificPersonTitleEnum {
    ING("Ing." ),
    MR( "Mr." ),
    MRS( "Mrs." ),
    MS( "Ms." ),
    SR( "Sr." ),
    SRTA( "Srta." );

    private String text;

    HonorificPersonTitleEnum(String text) {
        this.text = text;
    }
}
