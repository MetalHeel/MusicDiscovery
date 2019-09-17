package org.bram.musicdiscovery.utils;

public class WebUtils {
    public enum ContentType {
        APPLICATION_JSON("application/json"),
        AUDIO_MPEG("audio/mpeg");

        private final String type;

        ContentType(String type) {
            this.type = type;
        }

        public String getType() {
            return type;
        }
    }

    public static String getParameterValueFromQuery(String query, String parameterName) {
        if (StringUtils.isBlank(query)) {
            return null;
        }
        if (StringUtils.isBlank(parameterName)) {
            return null;
        }
        for (String parameter : query.split("&")) {
            String[] parts = parameter.split("=");
            if (parts.length == 0) {
                continue;
            }
            if (StringUtils.equals(parts[0], parameterName)) {
                if (parts.length < 2) {
                    continue;
                }
                return parts[1];
            }
        }
        return null;
    }
}
