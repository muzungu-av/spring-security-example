package ru.example.restgatewayapi.storage.syllable;


/**
 * Слог - состоит из одной или двух букв,
 * помещеных в визуальный квадрат в таблице Зайцева.
 */
public interface ISyllable extends Comparable<ISyllable> {
    /* русское символьное написание */
    String getSymbol();
    /* номер позиции (индекс) в колонке «Твердые» (или «Мягкие») */
    Integer getColumnPosNumber();
    /* координаты очерчивающего квадрата в колонке */
    Location getColumnLocation();
    /* координаты очерчивающего квадрата в таблице */
    Location getTableLocation();
    /* глобальный индекс (для поиска при разборе слов) */
    Integer getGlobalSyllableIndex();
}
