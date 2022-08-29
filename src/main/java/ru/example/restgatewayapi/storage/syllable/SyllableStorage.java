package ru.example.restgatewayapi.storage.syllable;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Хранилище слогов. Синглтон.
 * <p>
 * Хранит все возможные разновидности сочетаний слогов-букв русского языка.
 * За основу взяты таблицы методики Зайцева.
 * <p>
 * Все гласные храняться в отдельном листе, согласные в мапе, наборы каждой гласной в своей коллекции List, в качестве
 * значений этой мапы.
 * <p>
 * Ранее планировалось всё хранить в одном листе, поэтому был введен GlobalSyllableIndex,
 * и поддерживается такая нумерация, которая обеспечивает корректный разбор слова на слоги
 * (одиночные согласные буквы имеют более высокий индекс, чем слоги с этими согласными);
 * однако разделив одну коллекцию на несколько, индекс хотя остался и является по-прежнему необходимым,
 * все таки утрантил свой первоначальный смысл...
 *
 * Также подготавливает и хранит итераторы - для гласных простой SyllableStorageIterator с возможностью сбрасывать
 * счетчик. Для согласных подготавливает для каждой буквы свой собственный итератор, хранятся они
 * в Map<String, SyllableStorageIterator>.
 * Хранение (а не создание по запросу) итераторов дает некоторую экономию по времени во время каждого парсинга слов,
 * просто предоставляется итератор у которого предварительно сбрасывается счетчик.
 */
@Component
@Scope("singleton")
class SyllableStorage {

    private List<ISyllable> vowels;                      //гласные
    private Map<String, List<ISyllable>> consonant;      //согласные
    private SyllableStorageIterator vowelsIterator;
    private Map<String, SyllableStorageIterator> consonantIterator;

    List<ISyllable> getVowels() {
        return this.vowels;
    }

    Map<String, List<ISyllable>> getConsonant() {
        return this.consonant;
    }

    /**
     * добавляет гласные в коллекцию List.
     *
     * @param array List<ISyllable>
     */
    void addVowels(List<ISyllable> array) {
        if (this.vowels == null) {
            this.vowels = new ArrayList<>();
        }
        vowels.addAll(array);
    }

    /**
     * добавляет согласные в коллекцию HashMap<String, ArrayList<ISyllable>> .
     * ключ мапы - согласная. значение мапы - коллекция этой согласной.
     *
     * @param array List<ISyllable>
     */
    void addConsonant(List<ISyllable> array) {
        if (this.consonant == null) {
            this.consonant = new HashMap<>();
        }
        if (array == null || array.size() == 0) {
            return;
        }
        String key = String.valueOf(array.get(0).getSymbol().charAt(0));
        if (this.consonant.containsKey(key)) {
            this.consonant.get(key).addAll(array);
        } else {
            this.consonant.put(key, array);
        }
    }

    /**
     * подготавливает итератор для гласных.
     * метод следует вызывать в конце, когда колекция гласных уже инициирована.
     */
    void setVowelsIterator() {
        if (this.vowels == null || this.vowels.size() < 1) {
            throw new ArrayStoreException("Попытка инициализировать итератор пустой коллекцией гласных.");
        }
        this.vowelsIterator = new SyllableStorageIterator(this.vowels);
    }

    /**
     * подготавливает итератор для согласных.
     * метод следует вызывать в конце, когда колекция согласных уже инициирована.
     */
    void setConsonantIterator() {
        this.consonantIterator = new HashMap<>();
        this.consonant.forEach((str, array) -> {
            if (array == null || array.size() < 1) {
                throw new ArrayStoreException("Попытка инициализировать итератор пустой коллекцией согласных.");
            }
            this.consonantIterator.put(str, new SyllableStorageIterator(array));
        });
    }

    /**
     * предоставляет итератор для гласных.
     *
     * @return SyllableStorageIterator<ISyllable>
     */
    SyllableStorageIterator getVowelsIterator() {
        if (this.vowelsIterator == null) {
            throw new NullPointerException("Итератор (VowelsIterator) не был проинициализирован.");
        }
        this.vowelsIterator.restartIteration(); //важно!
        return this.vowelsIterator;
    }

    /**
     * предоставляет итератор для указанной согласной.
     *
     * @param keyWord согласная буква (String) для которой нужно найти итератор.
     * @return SyllableStorageIterator Итератор
     */
    SyllableStorageIterator getConsonantIterator(final String keyWord) {
        if (this.consonantIterator == null) {
            throw new NullPointerException("Итератор (ConsonantIterator) не был проинициализирован.");
        }
        SyllableStorageIterator iterator = this.consonantIterator.get(keyWord);
        iterator.restartIteration(); //важно!
        return iterator;
    }
}