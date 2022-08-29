package ru.example.restgatewayapi.storage.syllable;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Тестируется слой бизнес-логики - Класс TwoSymbol,
 * проверяем его способность отделять первых два символа (или один если нет)
 */
class HeadAndTailTest {

    @Test
    void whenNormalHeadAndTail5Letters() {
        HeadAndTail s1 = new HeadAndTail("ВАРАН");
        String head = s1.getHeadString();
        String tail = s1.getTailString();
        assertEquals("ВА", head);
        assertEquals("РАН", tail);
    }

    @Test
    void whenNormalHeadAndTail4Letters() {
        HeadAndTail s1 = new HeadAndTail("ВАРА");
        String head = s1.getHeadString();
        String tail = s1.getTailString();
        assertEquals("ВА", head);
        assertEquals("РА", tail);
    }

    @Test
    void whenNormalHeadAndTail3Letters() {
        HeadAndTail s1 = new HeadAndTail("ВВП");
        String head = s1.getHeadString();
        String tail = s1.getTailString();
        assertEquals("ВВ", head);
        assertEquals("П", tail);
    }

    @Test
    void whenEmptyTail2Letters() {
        HeadAndTail s1 = new HeadAndTail("ВО");
        String head = s1.getHeadString();
        String tail = s1.getTailString();
        assertEquals("ВО", head);
        assertTrue(tail.isEmpty());
    }

    @Test
    void whenEmptyTail1Letter() {
        HeadAndTail s1 = new HeadAndTail("В");
        String head = s1.getHeadString();
        String tail = s1.getTailString();
        assertEquals("В", head);
        assertTrue(tail.isEmpty());
    }

    @Test
    void whenEmptyHeadAndTail() {
        assertThrows(IllegalArgumentException.class, () -> new HeadAndTail(""));
    }
}