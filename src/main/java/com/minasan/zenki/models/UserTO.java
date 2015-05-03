package com.minasan.zenki.models;

public class UserTO {
    private long id;
    private String name;
    private ColorSchemaTO colorSchema;
    private int rate;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ColorSchemaTO getColorSchema() {
        return colorSchema;
    }

    public void setColorSchema(ColorSchemaTO colorSchema) {
        this.colorSchema = colorSchema;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }
}
