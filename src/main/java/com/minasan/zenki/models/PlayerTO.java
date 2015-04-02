package com.minasan.zenki.models;

import java.util.Set;

public class PlayerTO {
    private long id;
    private String name;
    private ColorSchemaTO colorSchema;
    private PlayerStatusTO status;
    private Set<CardTO> cards;

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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public PlayerStatusTO getStatus() {
        return status;
    }

    public void setStatus(PlayerStatusTO status) {
        this.status = status;
    }

    public Set<CardTO> getCards() {
        return cards;
    }

    public void setCards(Set<CardTO> cards) {
        this.cards = cards;
    }
}
