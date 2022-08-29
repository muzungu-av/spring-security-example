package ru.example.restgatewayapi.storage.syllable.creator;

import ru.example.restgatewayapi.storage.syllable.ISyllable;
import ru.example.restgatewayapi.storage.syllable.Syllable;
import ru.example.restgatewayapi.storage.syllable.column.ColumnLocation;
import ru.example.restgatewayapi.storage.syllable.table.TableLocation;

import java.util.ArrayList;

/**
 * Гласные буквы
 */
/* global syllable index: 235-244 */
public class LetterVowels {
    public static ArrayList<ISyllable> createSolid(ArrayList<ISyllable> array) {
        if (array == null) {
            return null;
        }
        array.add(new Syllable("У", 0, 235,
                new ColumnLocation(0, 0),
                new TableLocation(0, 0)));

        array.add(new Syllable("О", 1, 236,
                new ColumnLocation(0, 0),
                new TableLocation(0, 0)));

        array.add(new Syllable("А", 2, 237,
                new ColumnLocation(0, 0),
                new TableLocation(0, 0)));

        array.add(new Syllable("Э", 3, 238,
                new ColumnLocation(0, 0),
                new TableLocation(0, 0)));

        array.add(new Syllable("Ы", 4, 239,
                new ColumnLocation(0, 0),
                new TableLocation(0, 0)));
        return array;
    }

    public static ArrayList<ISyllable> createSoft(ArrayList<ISyllable> array) {
        if (array == null) {
            return array;
        }
        array.add(new Syllable("Ю", 0, 240,
                new ColumnLocation(0, 0),
                new TableLocation(0, 0)));

        array.add(new Syllable("Ё", 1, 241,
                new ColumnLocation(0, 0),
                new TableLocation(0, 0)));

        array.add(new Syllable("Я", 2, 242,
                new ColumnLocation(0, 0),
                new TableLocation(0, 0)));

        array.add(new Syllable("Е", 3, 243,
                new ColumnLocation(0, 0),
                new TableLocation(0, 0)));

        array.add(new Syllable("И", 4, 244,
                new ColumnLocation(0, 0),
                new TableLocation(0, 0)));
        return array;
    }
}
