package by.tanyab.task1.observer;

import by.tanyab.task1.entity.CustomArray;

public class CustomArrayEvent {
    private final CustomArray source;

    public CustomArrayEvent(CustomArray source) {
        this.source = source;
    }

    public CustomArray getArray() {
        return source;
    }
}