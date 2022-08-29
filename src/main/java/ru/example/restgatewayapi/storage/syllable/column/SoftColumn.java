package ru.example.restgatewayapi.storage.syllable.column;

import ru.example.restgatewayapi.storage.syllable.ISyllable;

import java.util.ArrayList;

/**
 * Колонка звуков "Мягкие", представленна в таблице
 * маленькими кубиками.
 */
public class SoftColumn implements IColumn {

    private final ArrayList<ISyllable> array;

    public SoftColumn(ArrayList<ISyllable> array) {
        this.array = array;
    }

    @Override
    public ArrayList<ISyllable> getSyllableList() {
        return null;
    }

    @Override
    public ArrayList<ISyllable> getSyllableIndexIterator() {
        return null;
    }
}
