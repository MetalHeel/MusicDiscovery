package org.bram.musicdiscovery.web.enums;

public enum  ResponseParameters {
    ARTISTS("artists"),
    ALBUMS("albums"),
    SONGS("songs");

    private final String parameterName;

    ResponseParameters(String parameterName) {
        this.parameterName = parameterName;
    }

    public String getParameterName() {
        return parameterName;
    }
}
