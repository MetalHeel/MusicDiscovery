package org.bram.musicdiscovery.web.enums;

public enum RequestParameters {
    ARTIST("artist"),
    ALBUM("album"),
    TRACK("track");

    private final String parameterName;

    RequestParameters(String parameterName) {
        this.parameterName = parameterName;
    }

    public String getParameterName() {
        return parameterName;
    }
}
