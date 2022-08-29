package ru.example.restgatewayapi.storage.syllable;

/**
 * Результат одиночного парсинга слов согласно правилам таблиц.
 * Первый возможный элемент - это слог или одиночная буква, помещается в headSyllable.
 * Остальная часть слова в tailOfWord.
 *
 * Если в headIsVowel оказалась гласная, тогда headIsVowel помечается true.
 * Если остаток в tailOfWord, тогда emptyTail помечается true.
 * Если вобще не удалось распарсить ничего, тогда error помечается true.
 */
public class ParsedWordResult {

    private ISyllable headSyllable;
    private String tailOfWord;
    private boolean headIsVowel; //признак найденного гласного
    private boolean emptyTail;   //признак "пустого" конца
    private boolean error;       //признак ошибки

    public ISyllable getHeadSyllable() {
        return headSyllable;
    }

    void setHeadSyllable(ISyllable headSyllable) {
        this.headSyllable = headSyllable;
    }

    public String getTailOfWord() {
        return tailOfWord;
    }

    void setTailOfWord(String tailOfWord) {
        this.tailOfWord = tailOfWord;
    }

    public boolean isHeadIsVowel() {
        return headIsVowel;
    }

    void setHeadIsVowel(boolean headIsVowel) {
        this.headIsVowel = headIsVowel;
    }

    public boolean isEmptyTail() {
        return emptyTail;
    }

    void setEmptyTail(boolean emptyTail) {
        this.emptyTail = emptyTail;
    }

    public boolean isError() {
        return error;
    }

    void setError(boolean error) {
        this.error = error;
    }
}
