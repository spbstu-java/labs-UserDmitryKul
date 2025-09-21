package lab1;
import java.util.*;

public class Main {
    private static HashMap<Integer, IMovementStrategy> strategies = new HashMap<>();
    private static List<String> listMainMenu = new ArrayList<String>();
    private static Hero hero;
    private static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {

        // Инициализация стратегий
        strategies.put(0, new WalkStrategy());
        strategies.put(1, new HorseStrategy());
        strategies.put(2, new DragonStrategy());
        
        listMainMenu.add(strategies.get(0).getName());
        listMainMenu.add(strategies.get(1).getName());
        listMainMenu.add(strategies.get(2).getName());

        hero = new Hero(0, 0, strategies.get(0));

        int pos = 0;

        while (true) {
            printMenu(pos, listMainMenu);

            String input = scanner.nextLine().toLowerCase(); // ← Читаем всю строку
            if (input.isEmpty()) continue;
            int key = input.charAt(0);

            switch (key) {
                case 'w': // Вверх
                    pos = (pos - 1 + listMainMenu.size()) % listMainMenu.size();
                    break;
                
                case 's': // Вниз
                    pos = (pos + 1) % listMainMenu.size();
                    break;
                
                case 'e': // Выбрать
                    hero.setMoveStrategy(strategies.get(pos));
                    break;
                
                case 'm': // Переместиться
                    clearScreen();
                    System.out.println("Введите координаты через пробел");
                    int xPos = scanner.nextInt();
                    int yPos = scanner.nextInt();

                    hero.move(xPos, yPos);
                    break;
                    
                case 'q': // Выход
                    System.out.println("Выход...");
                    return;
            }
        }
    }

    static void printMenu(int pos, List<String> listMainMenu) {
        clearScreen();
        System.out.println("Текущие координаты героя: "   + 
                            "x-" + hero.getXPos()  + " " + 
                            "y-" + hero.getYPos()  + "\n");
        System.out.println("Текущий способ передвижения: " + hero.getMoveStrategy() + "\n");

        System.out.println("=== ВЫБЕРИТЕ СПОСОБ ПЕРЕДВИЖЕНИЯ ===");
        for (int i = 0; i < listMainMenu.size(); i++) {
            System.out.print((pos == i) ? "==> " : "    ");
            System.out.println((i + 1) + ". " + listMainMenu.get(i));
        }
        System.out.println("==================================");
        System.out.println("W/S - навигация, E - выбрать, M - переместиться Q - выход");
    }

    public static void clearScreen() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } 
        catch (Exception e) {
            System.out.println("Ошибка очистки консоли: " + e.getMessage());
        }
    }
}