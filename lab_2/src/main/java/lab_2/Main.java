package lab_2;

import java.lang.reflect.Method;

public class Main {
    
    private static Object createDefaultValue(Class<?> type) {
        if (type == String.class) return "test";
        if (type == int.class) return 42;
        if (type == double.class) return 3.14;
        if (type == boolean.class) return false;
        return null;
    }
    
    public static void main(String[] args) {
        TestClass testObj = new TestClass();
        
        // Получаем все методы класса
        Method[] methods = testObj.getClass().getDeclaredMethods();
        
        for (Method method : methods) {
            // Проверяем наличие аннотации и не-public доступ
            if (method.isAnnotationPresent(Repeat.class) && 
                !java.lang.reflect.Modifier.isPublic(method.getModifiers())) {
                
                Repeat repeat = method.getAnnotation(Repeat.class);
                int runCount = repeat.runs();
                
                // Разрешаем доступ к приватным/защищённым методам
                method.setAccessible(true);
                
                // Подготавливаем параметры
                Class<?>[] paramTypes = method.getParameterTypes();
                Object[] params = new Object[paramTypes.length];
                for (int i = 0; i < paramTypes.length; i++) {
                    params[i] = createDefaultValue(paramTypes[i]);
                }
                
                System.out.println(method.getName() + " -> " + runCount + " runs");
                
                // Выполняем метод указанное количество раз
                for (int i = 0; i < runCount; i++) {
                    try {
                        method.invoke(testObj, params);
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                }
                System.out.println();
            }
        }
    }
}