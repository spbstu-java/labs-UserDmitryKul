package lab_3;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Dictionary dictionary = new Dictionary();
        String dictionaryFile = "dic.txt";
        
        try {
            System.out.println("Загружаем словарь из файла: " + dictionaryFile);
            dictionary.loadDictionary(dictionaryFile);
            System.out.println("Словарь успешно загружен!");
            
            Translator translator = new Translator(dictionary);
            
            System.out.println("\nПереводчик готов к работе.");
            System.out.println("Вводите текст для перевода (или 'q' для завершения):");
            
            while (true) {
                System.out.print("> ");
                String userInput = scanner.nextLine().trim();
                
                if (userInput.equalsIgnoreCase("q")) {
                    break;
                }
                
                if (userInput.isEmpty()) {
                    continue;
                }
                
                String translatedText = translator.translateText(userInput);
                System.out.println("Результат: " + translatedText);
            }
            
            System.out.println("Работа переводчика завершена.");
            
        } catch (FileReadException e) {
            System.out.println("Ошибка чтения файла: " + e.getMessage());
            if (e.getCause() != null) {
                System.out.println("Причина: " + e.getCause().getMessage());
            }
        } catch (InvalidFileFormatException e) {
            System.out.println("Ошибка формата файла: " + e.getMessage());
        } catch (Exception error) {
            System.out.println("Неожиданная ошибка: " + error.getMessage());
        } finally {
            scanner.close();
        }
    }
}