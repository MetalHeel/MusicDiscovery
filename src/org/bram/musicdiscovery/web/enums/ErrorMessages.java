package org.bram.musicdiscovery.web.enums;

public enum ErrorMessages {
    BLANK_ARTIST("Artist is blank"),
    BLANK_ALBUM("Album is blank"),
    BLANK_TRACK("Track is blank"),
    INVALID_INTEGER("Track needs to be a valid positive integer"),
    MISSING_PATH("Track did not have corresponding file path"),
    MISSING_FILE("Music file does not exist");

    private final String message;

    ErrorMessages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
