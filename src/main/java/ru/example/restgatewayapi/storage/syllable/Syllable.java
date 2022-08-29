package ru.example.restgatewayapi.storage.syllable;

import java.util.Objects;

public class Syllable implements ISyllable {

    private final ISymbol symbol;
    /* номер позиции (индекс) в колонке «Твердые» (или «Мягкие») */
    private final Integer columnPosNumber;
    /* координаты очерчивающего квадрата в колонке */
    private final Location columnLocation;
    /* координаты очерчивающего квадрата в таблице */
    private final Location tableLocation;
    /* глобальный индекс (для поиска при разборе слов) */
    private final Integer globalSyllableIndex;

    public Syllable(String symbol, Integer columnPosNumber, Integer globalSyllableIndex, Location columnLocation, Location tableLocation) {
        final ISymbol iSmb = new ISymbol() {
            @Override
            public String getString() {
                return new String(symbol);
            }
        };
        this.symbol = iSmb;
        this.columnPosNumber = columnPosNumber;
        this.columnLocation = columnLocation;
        this.tableLocation = tableLocation;
        this.globalSyllableIndex = globalSyllableIndex;
    }

    @Override
    public String getSymbol() {
        return symbol.getString();
    }

    @Override
    public Integer getColumnPosNumber() {
        return columnPosNumber;
    }

    @Override
    public Location getColumnLocation() {
        return columnLocation;
    }

    @Override
    public Location getTableLocation() {
        return tableLocation;
    }

    @Override
    public Integer getGlobalSyllableIndex() {
        return globalSyllableIndex;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Syllable syllable = (Syllable) o;
        return symbol.getString().equals(syllable.symbol.getString());
    }

    @Override
    public int hashCode() {
        return Objects.hash(symbol.getString());
    }

    /**
     * Сортировка слогов из таблицы зайцева.
     * Сортирует по Global Syllable Index
     * Не менять порядок вычитаемых ! тк. это скажется на алгоритме парсинга слов на слоги.
     *
     * @param o ISyllable
     * @return int
     */
    @Override
    public int compareTo(ISyllable o) {
        return this.globalSyllableIndex - o.getGlobalSyllableIndex();
    }
}
