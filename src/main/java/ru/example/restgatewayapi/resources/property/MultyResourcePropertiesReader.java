package ru.example.restgatewayapi.resources.property;

import ru.example.restgatewayapi.security.JwtCsrfFilter;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Чтение "properties" файлов и значений. Для не-спринг классов.
 * Может читать по-очереди из нескольких файлов. Установка файла
 * делается методом setSourcePropFile(), а чтение свойста методом
 * readValueByKey(). Окончательно получить результат методом get()
 */
public class MultyResourcePropertiesReader {

    private InputStream is;
    private Properties properties;
    private Map<String, Object> fnAccum = new LinkedHashMap<>();
    private ThreadLocal<InputStream> threadInputStream;
    private boolean inpStreamOpen = false;
    private final String controlKey = "_specialControlKey:changeSourceFile:";
    private int controlIndex = 0;

    /**
     * Функция программирует какие файлы будут открыты и прочитанны в будущем.
     * Имя файла сохраняется в замыкании.
     * Результат Supplier-функции игнорируется.
     *
     * @param fileName файл свойств  (*.properties)
     * @return экземпляр объекта MultyResourcePropertiesReader
     */
    public MultyResourcePropertiesReader setSourcePropFile(String fileName) {
        Supplier<String> func = () -> {
            String fileNameResource = new String(fileName);
            openResource(fileNameResource);
            return "";
        };
        this.fnAccum.put(controlKey + controlIndex++, func);
        return this;
    }

    /**
     * Программирует систему на следующее прочитывание свойства.
     * Само свойство не читается сейчас, а будет прочитанно в методе get()
     *
     * @param key ключ свойства для чтения
     * @return экземпляр объекта MultyResourcePropertiesReader
     */
    public MultyResourcePropertiesReader readValueByKey(String key) {
        Function<String, String> func = _key -> {
            if (threadInputStream != null) {
                if (is == null || !inpStreamOpen) {
                    //todo logger.error("Ошибка не открыт файл. ");
                    System.out.println("Ошибка не открыт файл. ");
                } else {
                    //todo logger info
                    return properties.getProperty(_key);
                }
            }
            return "";
        };
        this.fnAccum.put(key, func);
        return this;
    }

    /**
     * Выполняет запрограммированые действия по чтению свойств из разных properties-файлов.
     * Действия "запрограммированы" в Map<String, Object>, где первый String-ключ используется
     * во время вызова функционального интерфейса (созданного ранее) для чтения свойств указанных в key.
     * В значение map-ы второй параметр Object - это функция, которая будет вызванна.
     *
     * Для смены же файла (properties), key - специально содержит маркер (controlKey), если он обнаруживается
     * - вызывается функция Supplier.
     *
     * @return набор прочитанных свойств ключ-значение.
     */
    public Map<String, String> get() {
        Map<String, String> keyValuePair = new HashMap<>();
        fnAccum.forEach((key, fn) -> {
            if (key.startsWith(controlKey)) {
                ((Supplier<String>) fn).get();
            } else {
                keyValuePair.put(key, ((Function<String, String>) fn).apply(key));
            }
        });
        fnAccum.clear();
        closeResource();
        return keyValuePair;
    }

    /**
     * Cоздает и загружает объект Properties из файла свойств
     *
     * @param propFile имя файла свойств
     */
    private void openResource(String propFile) {
        if (threadInputStream == null) {
            threadInputStream = new ThreadLocal<>();
        }
        if (inpStreamOpen) {
            closeResource();
        }
        is = threadInputStream.get();
        if (is == null || !inpStreamOpen) {
            is = JwtCsrfFilter.class.getClassLoader().getResourceAsStream(propFile);
            threadInputStream.set(is);
            inpStreamOpen = true;
        }
        if (properties == null) {
            properties = new Properties();
        }
        try {
            properties.load(Objects.requireNonNull(is));
            //todo logger info
        } catch (IOException e) {
            //todo logger.error("Ошибка при чтении файла application.properties. " + e.getMessage());
        }
    }

    private void closeResource() {
        InputStream is = null;
        if (threadInputStream != null) {
            is = threadInputStream.get();
            threadInputStream.remove();
        }
        if (is != null) {
            try {
                is.close();
                //todo logger trace
            } catch (IOException e) {
                //todo logger warn
            }
        }
        if (properties != null) {
            properties.clear();
        }
        inpStreamOpen = false;
    }
}
