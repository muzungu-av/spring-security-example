package ru.example.restgatewayapi.storage.syllable;

import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Итератор с возможностью сбрасывать счетчик.
 */
public class SyllableStorageIterator implements ISyllableStorageIterator {

    private final List<ISyllable> syllableList;

    private int current;

    @Override
    public void restartIteration() {
        this.current = 0;
    }

    SyllableStorageIterator(final List<ISyllable> syllableList) {
        ArrayList<ISyllable> temp = new ArrayList<>(syllableList);
        Collections.sort(temp);
        this.syllableList = Collections.unmodifiableList(temp);
        current = 0;
    }

    @Override
    public boolean hasNext() {
        return syllableList.size() > current;
    }

    @Override
    public ISyllable next() {
        return syllableList.get(current++);
    }

    @SneakyThrows
    @Override
    public void remove() {
        throw new UnsupportedOperationException("Не поддерживаемая операция remove() в этом итераторе.");
    }
}
