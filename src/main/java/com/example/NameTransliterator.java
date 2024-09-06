package com.example;

import java.util.HashMap;
import java.util.Map;

/**
 * @author nimatullah
 */
public class NameTransliterator {

    private static final Map<Character, String> transliterationMap = new HashMap<>();

    static {
        // Транслитерация для всех букв кириллицы
        transliterationMap.put('А', "A");
        transliterationMap.put('Б', "B");
        transliterationMap.put('В', "V");
        transliterationMap.put('Г', "G");
        transliterationMap.put('Д', "D");
        transliterationMap.put('Е', "E");
        transliterationMap.put('Ё', "E");
        transliterationMap.put('Ж', "Zh");
        transliterationMap.put('З', "Z");
        transliterationMap.put('И', "I");
        transliterationMap.put('Й', "Y");
        transliterationMap.put('К', "K");
        transliterationMap.put('Л', "L");
        transliterationMap.put('М', "M");
        transliterationMap.put('Н', "N");
        transliterationMap.put('О', "O");
        transliterationMap.put('П', "P");
        transliterationMap.put('Р', "R");
        transliterationMap.put('С', "S");
        transliterationMap.put('Т', "T");
        transliterationMap.put('У', "U");
        transliterationMap.put('Ф', "F");
        transliterationMap.put('Х', "Kh");
        transliterationMap.put('Ц', "Ts");
        transliterationMap.put('Ч', "Ch");
        transliterationMap.put('Ш', "Sh");
        transliterationMap.put('Щ', "Sch");
        transliterationMap.put('Ъ', "");
        transliterationMap.put('Ы', "Y");
        transliterationMap.put('Ь', "");
        transliterationMap.put('Э', "E");
        transliterationMap.put('Ю', "Yu");
        transliterationMap.put('Я', "Ya");

        // Добавим транслитерацию для строчных букв
        transliterationMap.put('а', "a");
        transliterationMap.put('б', "b");
        transliterationMap.put('в', "v");
        transliterationMap.put('г', "g");
        transliterationMap.put('д', "d");
        transliterationMap.put('е', "e");
        transliterationMap.put('ё', "e");
        transliterationMap.put('ж', "zh");
        transliterationMap.put('з', "z");
        transliterationMap.put('и', "i");
        transliterationMap.put('й', "y");
        transliterationMap.put('к', "k");
        transliterationMap.put('л', "l");
        transliterationMap.put('м', "m");
        transliterationMap.put('н', "n");
        transliterationMap.put('о', "o");
        transliterationMap.put('п', "p");
        transliterationMap.put('р', "r");
        transliterationMap.put('с', "s");
        transliterationMap.put('т', "t");
        transliterationMap.put('у', "u");
        transliterationMap.put('ф', "f");
        transliterationMap.put('х', "kh");
        transliterationMap.put('ц', "ts");
        transliterationMap.put('ч', "ch");
        transliterationMap.put('ш', "sh");
        transliterationMap.put('щ', "sch");
        transliterationMap.put('ъ', "");
        transliterationMap.put('ы', "y");
        transliterationMap.put('ь', "");
        transliterationMap.put('э', "e");
        transliterationMap.put('ю', "yu");
        transliterationMap.put('я', "ya");
    }

    public static String transliterate(String cyrillicName) {
        StringBuilder latinName = new StringBuilder();
        for (char ch : cyrillicName.toCharArray()) {
            latinName.append(transliterationMap.getOrDefault(ch, String.valueOf(ch)));
        }
        return latinName.toString();
    }
}
