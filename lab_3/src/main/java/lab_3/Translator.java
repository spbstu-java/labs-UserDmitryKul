package lab_3;

import java.util.*;

public class Translator {
    private Dictionary dictionary;
    
    public Translator(Dictionary dictionary) {
        this.dictionary = dictionary;
    }
    
    public String translateText(String inputText) {
        if (inputText == null || inputText.trim().isEmpty()) {
            return inputText;
        }
        
        // Разбиваем текст на слова с сохранением пробелов
        String[] words = inputText.split("\\s+");
        List<String> result = new ArrayList<>();
        
        int i = 0;
        while (i < words.length) {
            String longestMatch = findLongestMatch(words, i);
            if (longestMatch != null) {
                // Нашли фразу в словаре
                String translation = dictionary.getTranslation(longestMatch);
                if (translation != null) {
                    // Восстанавливаем регистр
                    translation = fixCase(words[i], translation);
                    result.add(translation);
                    i += longestMatch.split("\\s+").length;
                    continue;
                }
            }
            
            // Не нашли перевод - оставляем слово как есть
            result.add(words[i]);
            i++;
        }
        
        return String.join(" ", result);
    }
    
    // Ищем самую длинную фразу из словаря, начиная с позиции i
    private String findLongestMatch(String[] words, int start) {
        String currentPhrase = "";
        String bestMatch = null;
        
        for (int j = start; j < words.length; j++) {
            if (currentPhrase.isEmpty()) {
                currentPhrase = words[j];
            } else {
                currentPhrase += " " + words[j];
            }
            
            if (dictionary.getTranslation(currentPhrase) != null) {
                bestMatch = currentPhrase;
            }
        }
        
        return bestMatch;
    }
    
    // Делаем первую букву заглавной, если в оригинале она была заглавной
    private String fixCase(String originalWord, String translatedWord) {
        if (originalWord.isEmpty() || translatedWord.isEmpty()) {
            return translatedWord;
        }
        
        if (Character.isUpperCase(originalWord.charAt(0))) {
            if (translatedWord.length() == 1) {
                return translatedWord.toUpperCase();
            } else {
                return translatedWord.substring(0, 1).toUpperCase() + 
                       translatedWord.substring(1);
            }
        }
        
        return translatedWord;
    }
}