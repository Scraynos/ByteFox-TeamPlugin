package de.juliansoftware.group;

public class Group {

    private final String name;
    private final String color;

    public Group(String name, String color) {
        this.name = name;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }
}
