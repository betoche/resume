package org.als.resume.entities;

import lombok.Getter;

@Getter
public enum Country{
    NICARAGUA( 1, "Nicaragua", "NI", "+505" ),
    UNITED_STATES( 2, "United States", "US", "+1");

    private final Integer id;
    private final String name;
    private final String countryCode;
    private final String phonePrefix;

    Country( Integer id, String name, String countryCode, String phonePrefix ) {
        this.id = id;
        this.name = name;
        this.countryCode = countryCode;
        this.phonePrefix = phonePrefix;

    }
}
