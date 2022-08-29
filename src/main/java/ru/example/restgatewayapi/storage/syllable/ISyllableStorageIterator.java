package ru.example.restgatewayapi.storage.syllable;

import java.util.Iterator;

/**
 * Итератор с возможностью сбрасывать счетчик.
 *
 * @param <ISyllable>
 */
public interface ISyllableStorageIterator<ISyllable> extends Iterator<ISyllable> {
    void restartIteration();
}
