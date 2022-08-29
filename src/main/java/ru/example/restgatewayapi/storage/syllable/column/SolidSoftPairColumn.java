package ru.example.restgatewayapi.storage.syllable.column;

/**
 * Колонка-пара  «Твердые — Мягкие»
 * Состоит из двух колонок «Твердые» и «Мягкие», выраженные
 * простым интерфейсом IColumn.
 */
public class SolidSoftPairColumn implements IComplexColumn {

    private final IColumn solidColumn;
    private final IColumn softColumn;

    public SolidSoftPairColumn(IColumn solidColumn, IColumn softColumn) {
        this.solidColumn = solidColumn;
        this.softColumn = softColumn;
    }

    public IColumn getSolidColumn() {
        return solidColumn;
    }

    public IColumn getSoftColumn() {
        return softColumn;
    }
}
