package ru.example.restgatewayapi.storage.syllable.column;

/**
 * Колонка-пара «Звонкие-Глухие»
 * Состоит из двух пар колонок «Твердые — Мягкие», выраженные
 * интерфейсом IComplexColumn.
 *
 */
public class RingingDeafPairColumn implements IComplexColumn {

    /* Звонкие (ringing) Звуки */
    private final IComplexColumn topColumn;

    /* Глухие (deaf) Звуки*/
    private final IComplexColumn bottomColumn;

    public RingingDeafPairColumn(IComplexColumn ringingTopColumn, IComplexColumn deafBottomColumn) {
        this.topColumn = ringingTopColumn;
        this.bottomColumn = deafBottomColumn;
    }

    public IComplexColumn getRingingTopColumn() {
        return topColumn;
    }

    public IComplexColumn getDeafBottomColumn() {
        return bottomColumn;
    }
}
