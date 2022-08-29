package ru.example.restgatewayapi.services.word;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.example.restgatewayapi.storage.syllable.ParsedWordResult;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Тестируется Service слой - Проверяется способность WordService парсить слова правильно.
 */
@SpringBootTest
class WordServiceTest {

    @Autowired
    private WordService service;

    @Test
    void parse1() {
        LinkedList<ParsedWordResult> result = service.parseOneWord("УНМЮ");
        assertEquals(3, result.size());

        ParsedWordResult first = result.get(0);
        assertEquals(first.getHeadSyllable().getSymbol(), "У");
        assertEquals(first.getTailOfWord(), "НМЮ");
        assertFalse(first.isEmptyTail());
        assertFalse(first.isError());
        assertTrue(first.isHeadIsVowel());

        ParsedWordResult second = result.get(1);
        assertEquals(second.getHeadSyllable().getSymbol(), "Н");
        assertEquals(second.getTailOfWord(), "МЮ");
        assertFalse(second.isEmptyTail());
        assertFalse(second.isError());
        assertFalse(second.isHeadIsVowel());

        ParsedWordResult a3 = result.get(2);
        assertEquals(a3.getHeadSyllable().getSymbol(), "МЮ");
        assertTrue(a3.getTailOfWord().isEmpty());
        assertTrue(a3.isEmptyTail());
        assertFalse(a3.isError());
        assertFalse(a3.isHeadIsVowel());
    }

    @Test
    void parse2() {
        LinkedList<ParsedWordResult> result = service.parseOneWord("АЮН");
        assertEquals(3, result.size());

        ParsedWordResult first = result.get(0);
        assertEquals(first.getHeadSyllable().getSymbol(), "А");
        assertEquals(first.getTailOfWord(), "ЮН");
        assertFalse(first.isEmptyTail());
        assertFalse(first.isError());
        assertTrue(first.isHeadIsVowel());

        ParsedWordResult second = result.get(1);
        assertEquals(second.getHeadSyllable().getSymbol(), "Ю");
        assertEquals(second.getTailOfWord(), "Н");
        assertFalse(second.isEmptyTail());
        assertFalse(second.isError());
        assertTrue(second.isHeadIsVowel());

        ParsedWordResult a3 = result.get(2);
        assertEquals(a3.getHeadSyllable().getSymbol(), "Н");
        assertTrue(a3.getTailOfWord().isEmpty());
        assertTrue(a3.isEmptyTail());
        assertFalse(a3.isError());
        assertFalse(a3.isHeadIsVowel());
    }

    @Test
    void parse3() {
        LinkedList<ParsedWordResult> result = service.parseOneWord("АННА");
        assertEquals(3, result.size());

        ParsedWordResult a1 = result.get(0);
        assertEquals(a1.getHeadSyllable().getSymbol(), "А");
        assertEquals(a1.getTailOfWord(), "ННА");
        assertFalse(a1.isEmptyTail());
        assertFalse(a1.isError());
        assertTrue(a1.isHeadIsVowel());

        ParsedWordResult a2 = result.get(1);
        assertEquals(a2.getHeadSyllable().getSymbol(), "Н");
        assertEquals(a2.getTailOfWord(), "НА");
        assertFalse(a2.isEmptyTail());
        assertFalse(a2.isError());
        assertFalse(a2.isHeadIsVowel());

        ParsedWordResult a3 = result.get(2);
        assertEquals(a3.getHeadSyllable().getSymbol(), "НА");
        assertTrue(a3.getTailOfWord().isEmpty());
        assertTrue(a3.isEmptyTail());
        assertFalse(a3.isError());
        assertFalse(a3.isHeadIsVowel());
    }


    /* многое удалено */
}