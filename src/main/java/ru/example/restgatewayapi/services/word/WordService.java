package ru.example.restgatewayapi.services.word;

import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Service;
import ru.example.restgatewayapi.storage.syllable.ParsedWordResult;
import ru.example.restgatewayapi.storage.syllable.SyllableStorageHelper;

import java.util.LinkedList;

@Service
public class WordService {

    @Lookup
    SyllableStorageHelper getSyllableStorageHelper() {
        return null;
    }

    /**
     * Переданное слово разделяется на слоги, согласно правилам руского языка и методике.
     *
     * @param word String любое слово русского языка
     * @return коллекция LinkedList из элементов ParsedWordResult, которая хранит этапы парсинга -
     *         найденный слог и оставшаяся часть и другие признаки.
     */
    public LinkedList<ParsedWordResult> parseOneWord(final String word) {
        SyllableStorageHelper helper = getSyllableStorageHelper();
        String tailWord = new String(word);
        LinkedList<ParsedWordResult> resultList = new LinkedList<>();
        ParsedWordResult pwr = null;
        boolean a1 = true;
        boolean a2 = true;
        while (a1 && a2) {
            if (!tailWord.isEmpty()) {
                pwr = helper.parseFirstSyllable(tailWord);
            } else {
                break;
            }
            if (pwr != null) {
                resultList.add(pwr);
            }
            tailWord = pwr.getTailOfWord();
            a1 = !pwr.isEmptyTail();
            a2 = !pwr.isError();
        }
        return resultList;
    }
}