package com.xy.user.web.domain;

import java.beans.ConstructorProperties;

public class Color {

    private String name;

    private Integer type;

    @ConstructorProperties({"name", "type"})
    public Color(String name, Integer type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Color{" +
                "name='" + name + '\'' +
                ", type=" + type +
                '}';
    }
}
