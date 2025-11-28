package lab_4;

import java.util.*;
import java.util.stream.Collectors;

public class StreamClass {

    // 1. Метод для среднего значения списка целых чисел
    public static double getAverage(List<Integer> numbers){
        return numbers.stream()                 // Создаем поток из списка чисел
            .mapToInt(Integer::intValue)        // Преобразуем Integer в примитивный int
            .average()                          // Вычисляем среднее значение
            .orElse(0.0);                // Если список пуст - возвращаем 0.0
    }

    // 2. Метод для преобразования строк с префиксом и верхним регистром
    public static List<String> strToUpperPrefix(List<String> strings){
        return strings.stream()
            // Для каждой строки: добавляем префикс и переводим в верхний регистр
            .map(str -> "_new_" + str.toUpperCase())
            // Собираем результат в новый список
            .toList();
    }

    // 3. Метод для получения квадратов уникальных элементов
    public static List<Integer> squareSingleNumber(List<Integer> numbers){
        return numbers.stream()
            // Оставляем только элементы, которые встречаются ровно один раз
            .filter(number -> Collections.frequency(numbers, number) == 1)
            // Возводим каждый оставшийся элемент в квадрат
            .map(number -> number * number).toList();
    }

    // 4. Метод для получения последнего элемента коллекции
    public static <T> T getLastElement(Collection<T> collection) throws NoSuchElementException {
        return collection.stream()
            // Проходим по всем элементам, сохраняя последний
            .reduce((first, second) -> second)
            // Если коллекция пуста - бросаем исключение
            .orElseThrow(() -> new NoSuchElementException("Коллекция пуста"));
    }

    // 5. Метод для суммы чётных чисел массива
    public static int getEvenSum(int[] numbers){
        return Arrays.stream(numbers)
                // Оставляем только четные числа
                .filter(number -> number % 2 == 0)
                // Суммируем оставшиеся числа
                .sum();
    }

    // 6. Метод для преобразования строк в Map
    public static Map<Character, String> getStringsMap(List<String> strings){
        return strings.stream()
            // Игнорируем пустые строки
            .filter(string -> !string.isEmpty())
            // Преобразуем в Map, где:
            .collect(Collectors.toMap(
                // Ключ: первый символ строки
                string -> string.charAt(0),  
                // Значение: остаток строки (или пустая строка)         
                string -> string.length() > 1 ? string.substring(1) : "", 
                // При конфликте ключей оставляем существующее значение
                (existing, replacement) -> existing   
            ));
    }

    public static void main(String[] args) {
        // Тестовые данные
        List<Integer> numbers = List.of(7, 81, 347);
        List<String> words = List.of("how", "are", "you");
        List<Integer> numbers2 = List.of(1, 1, 2, 3, 5, 6, 7, 7);
        int[] numbersArray = {1, 2, 3, 4, 5, 6, 7, 8};

        System.out.println("=== Демонстрация Stream API ===");
        
        // 1. Среднее значение
        System.out.println("1. Среднее значение " + numbers + ": " + getAverage(numbers));
        
        // 2. Строки с префиксом
        System.out.println("2. Строки с префиксом: " + strToUpperPrefix(words));
        
        // 3. Квадраты уникальных чисел
        System.out.println("3. Квадраты уникальных чисел: " + squareSingleNumber(numbers2));
        
        // 4. Последний элемент
        try {
            System.out.println("4. Последний элемент: " + getLastElement(words));
        } catch (NoSuchElementException e) {
            System.out.println("4. Последний элемент: " + e.getMessage());
        }
        
        // 5. Сумма четных чисел
        System.out.println("5. Сумма четных чисел: " + getEvenSum(numbersArray));
        
        // 6. Map из строк
        System.out.println("6. Преобразование строк в Map: " + getStringsMap(words));
        
        System.out.println('\n');
        // Тест исключения для пустой коллекции
        try {
            System.out.println("Последний элемент пустой коллекции: " + getLastElement(List.of()));
        } catch (NoSuchElementException e) {
            System.out.println("Исключение при пустой коллекции: " + e.getMessage());
        }
    }
}
