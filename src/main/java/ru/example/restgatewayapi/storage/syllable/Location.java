package ru.example.restgatewayapi.storage.syllable;

/**
 * Координаты базового элемента относительно верхнего/левого угла колонки
 */
public abstract class Location {
    private Integer posX;
    private Integer posY;

    public Location(Integer posX, Integer posY) {
        this.posX = posX;
        this.posY = posY;
    }

    public Integer getPosX() {
        return posX;
    }

    public Integer getPosY() {
        return posY;
    }
}
