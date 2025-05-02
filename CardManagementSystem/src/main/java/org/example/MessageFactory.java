package org.example;

public class MessageFactory {
    public static MessageProvider getProvider(String language) {
        return switch (language.toLowerCase()) {
            case "french" -> new FrenchMessageProvider();
            case "english" -> new EnglishMessageProvider();
            default -> new EnglishMessageProvider();
        };
    }
}

