package lab_3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Dictionary {
    private List<WordPair> wordPairs = new ArrayList<>();
    
    private static class WordPair {
        String source;
        String target;
        
        WordPair(String source, String target) {
            this.source = source.toLowerCase();
            this.target = target.toLowerCase();
        }
    }
    
    public void loadDictionary(String filename) throws FileReadException, InvalidFileFormatException {
        wordPairs.clear();
        
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(filename));
            String line;
            int lineNum = 1;
            
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;
                
                // Проверяем формат "слово | перевод"
                if (!line.contains("|")) {
                    throw new InvalidFileFormatException(
                        "Ошибка в строке " + lineNum + ": нет разделителя |. " +
                        "Ожидается формат: слово | перевод"
                    );
                }
                
                String[] parts = line.split("\\|", 2);
                if (parts.length != 2) {
                    throw new InvalidFileFormatException(
                        "Ошибка в строке " + lineNum + ": неверный формат. " +
                        "Ожидается: слово | перевод"
                    );
                }
                
                String source = parts[0].trim();
                String target = parts[1].trim();
                
                if (source.isEmpty() || target.isEmpty()) {
                    throw new InvalidFileFormatException(
                        "Ошибка в строке " + lineNum + ": пустое слово или перевод"
                    );
                }
                
                wordPairs.add(new WordPair(source, target));
                lineNum++;
            }
        } catch (java.io.FileNotFoundException e) {
            throw new FileReadException("Файл не найден: " + filename, e);
        } catch (java.io.IOException e) {
            throw new FileReadException("Ошибка чтения файла: " + filename, e);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (java.io.IOException e) { 
                    // Игнорируем ошибку закрытия
                }
            }
        }
    }
    
    public String getTranslation(String word) {
        String searchWord = word.toLowerCase();
        for (WordPair pair : wordPairs) {
            if (pair.source.equals(searchWord)) {
                return pair.target;
            }
        }
        return null;
    }
}