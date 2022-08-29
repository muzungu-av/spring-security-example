package ru.example.restgatewayapi.storage.syllable;

/**
 * Разделяет строку на голову и хвост. Голова содержит два первых или один первый символ строки,
 * остальное в хвосте.
 */
public class HeadAndTail {

    private String head;

    private String tail;

    HeadAndTail(String input) {
        if (input.length() > 1) {
            this.head = new String(input.substring(0, 2));
            this.tail = new String(input.substring(2));
        } else if (input.length() == 1) {
            this.head = new String(input);
            this.tail = new String("");
        } else {
            throw new IllegalArgumentException("Полученна пустая строка - не смогу выделить один или два первых символа.");
        }
    }

    String getHeadString() {
        return head;
    }

    String getTailString() {
        return tail;
    }
}
