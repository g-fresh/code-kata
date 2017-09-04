package me.gfresh.kata.bob;

public final class Bob {

    public String hey(String message) {
        if (wasShouted(message)) return "Whoa, chill out!";
        if (isQuestion(message)) return "Sure.";
        if (dontUnderstand(message)) return "Fine. Be that way!";
        return "Whatever.";
    }

    private boolean wasShouted(String message) {
        return containsLetter(message) && allUpperCase(message);
    }

    private boolean containsLetter(String message) {
        return message.chars().anyMatch(Character::isLetter);
    }

    private boolean allUpperCase(String message) {
        return ! message.chars().anyMatch(Character::isLowerCase);
    }

    private boolean isQuestion(String message) {
        return message.endsWith("?");
    }

    private boolean dontUnderstand(String message) {
        return message.trim().isEmpty();
    }
}
