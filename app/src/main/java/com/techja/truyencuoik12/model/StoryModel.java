package com.techja.truyencuoik12.model;

import java.util.Objects;

public class StoryModel {
    private final String name;
    private final String content;

    public StoryModel(String name, String content) {
        this.name = name;
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StoryModel that = (StoryModel) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "StoryModel{" +
                "name='" + name + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
