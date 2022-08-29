package ru.example.restgatewayapi.storage.syllable.column;

import ru.example.restgatewayapi.storage.syllable.ISyllable;

import java.util.ArrayList;

/**
 * Колонка звуков "Твердые", представленна в таблице
 * большими кубиками.
 */
public class SolidColumn implements IColumn {

    private final ArrayList<ISyllable> array;

    public SolidColumn(ArrayList<ISyllable> array) {
        this.array = array;
    }

    @Override
    public ArrayList<ISyllable> getSyllableList() {
        return array;
    }

    @Override
    public ArrayList<ISyllable> getSyllableIndexIterator() {
        return null;
    }
}
