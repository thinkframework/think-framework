package io.github.thinkframework.expression.impl;

public class Token {

    public static final String STRING = "[0-9a-zA-z]";

    public static final String ADD = "\\+";

    public static final String SUBTRACT = "\\-";

    public static final String MULTIPLY = "\\*";

    public static final String DIVIDE = "\\/";

    private String type;
    private String value;

    public Token(String type) {
        this.type = type;
    }

    public Token(String type, String value) {
        this.type = type;
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
