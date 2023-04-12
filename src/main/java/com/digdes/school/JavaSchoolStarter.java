package com.digdes.school;

import java.util.*;

public class JavaSchoolStarter {

    // переменные для полей
    private Long id;
    private String lastName;
    private Integer age;
    private Double cost;
    private Boolean active;

    // для регулярок
    private String[] words;
    private String request;
    private Map<String, Object> map;
    private HashMap<String, Object> afterWhere;
    private String[] withoutWhere;
    private String[] truncatedFields;

    // коллекция
    private final List<Map<String, Object>> collection;

    // инициализация
    public JavaSchoolStarter() {
        collection = new ArrayList<>();
    }

    // удаление строки из коллекции по индексу
    public void delete() {
        collection.removeIf(map -> map.containsKey("id") && map.get("id").equals(id));
    }

    // удаление строки из коллекции по индексу
    public void update() {

        // Итерация по списку collection
        for (Map<String, Object> row : collection) {

            try {
                // Проверка на соответствие id
                if (row.containsKey("id") && row.get("id").equals(id)) {
                    pushMap(map);
                    break; // Выход из цикла после обновления первого элемента с заданным id
                }

                // Проверка на соответствие значения фамилии
                if (row.containsKey("lastName") && row.get("lastName").equals(lastName)) {
                    // Обновление значений

                    break; // Выход из цикла после обновления первого элемента с заданной фамилией
                }

//                // Проверка на соответствие значению стоимости
//                if (row.containsKey("cost") && row.get("cost").equals(cost)) {
//                    // Обновление значений
//
//                    break; // Выход из цикла после обновления первого элемента с заданной фамилией
//                }

                // Проверка на соответствие значений полей
                if (row.containsKey("id") && row.get("id").equals(id) &&
                        row.containsKey("lastName") && row.get("lastName").equals(lastName) &&
                        row.containsKey("age") && row.get("age").equals(age) &&
                        row.containsKey("cost") && row.get("cost").equals(cost)) {
                    // Обновление значений
                    row.put("active", false);
                    break; // Выход из цикла после обновления первого элемента, удовлетворяющего всем условиям
                }

            } catch (NullPointerException exception) {
                exception.getStackTrace();
            }
        }
    }

    // добавление строки в коллекцию
    public void insert(Map<String, Object> row) {
        collection.add(row);
    }

    // находит число соответствующее строке
    public int findWhere(String columnName, Object value) {
        for (int i = 0; i < collection.size(); i++) {
            Map<String, Object> row = collection.get(i);
            if (row.get(columnName).equals(value)) {
                return i + 1;
            }
        }
        return -1;
    }

    // Получаем список колонок из первой строки таблицы
    List<String> listColumns() {
        List<String> columns = new ArrayList<>(collection.get(0).keySet());
        System.out.println("Поля " + columns);
        return columns;
    }

    // создаём мапу для вставки
    void insertMap() {

        map = new HashMap<>();

        map.put("id", id);
        map.put("lastName", lastName);
        map.put("age", age);
        map.put("cost", cost);
        map.put("active", active);

        insert(map);
    }

    // создаём мапу и инициализируем
    void selectMap() {
        for (Map<String, Object> row : collection) {
        // выводит если найдены параметры
            if ((row.get("lastName").equals(lastName))
                && row.get("id").equals(id)
                    && row.get("age").equals(age)
            ) {
                System.out.println("Результат запроса: ----");
                printMap(map);
                System.out.println("Конец запроса: ----");
            }
        }
    }

    void pushMap(Map<String, Object> map) {

        map.put("id", id);
        map.put("lastName", lastName);
        map.put("age", age);
        map.put("cost", cost);
        map.put("active", active);
    }


    // String word : words
    void readInit() {

        for (int i = 0; i < withoutWhere.length; i++) {
            switch (withoutWhere[i]) {
                case "id" -> id = Long.parseLong(withoutWhere[++i]);
                case "lastName" -> lastName = withoutWhere[++i];
                case "age" -> age = Integer.parseInt(withoutWhere[++i]);
                case "cost" -> cost = Double.parseDouble(withoutWhere[++i]);
                case "active" -> active = Boolean.parseBoolean(withoutWhere[++i]);
            }
        }
    }

    public void printCollection(List<Map<String, Object>> collection) {
        System.out.println("Начало таблицы: ");
        // счётчик
        int count = 0;
        for (Map<String, Object> row : collection) {
            count++;
            System.out.println("* * * Строка №" + count);
            for (Map.Entry<String, Object> entry : row.entrySet()) {
                String columnName = entry.getKey();
                Object value = entry.getValue();
                System.out.println(columnName + ": " + value);
            }
        }
        System.out.println("=== всего строк в таблице => " + count + "\n");
    }

    // разделяем запрос на слова
    void regular() {
        // разделяем запрос на слова
        words = request.split("[\\s,();'=]+");

        System.out.println("Поля " + Arrays.toString(words));
    }

    public void printMap(Map<String, Object> map) {
        if (map.isEmpty()) {
            System.out.println("Map пустая ");
        } else {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }
        }
    }

    void firstComand() {

        regular();

        findWhere();

        readInit();

        String input = words[0];
        switch (input.toLowerCase()) {

            case "insert" -> insertMap();
            case "select" -> selectMap();
            case "update" -> update();
            case "delete" -> delete();
            default -> throw new Exception("Неправильная команда " + words[0]);
        }
    }

    public List<Map<String, Object>> execute(String request) {

        this.request = request;

        // разбиваем регулярное выражение
        firstComand();

        // печатаем коллекцию
         printCollection(collection);

        return collection;
    }

    // поиск по where....
    // Если слово "WHERE" найдено, обрезаем массив
    void findWhere() {

        truncatedFields = withoutWhere = words;

        // Ищем индекс слова "WHERE" в массиве
        for (int i = 0; i < words.length; i++) {
            if (words[i].equalsIgnoreCase("WHERE")) {
                truncatedFields = Arrays.copyOfRange(words, i + 1, words.length);
                withoutWhere = Arrays.copyOfRange(words, 0, i );
            }
        }

        if (truncatedFields.length != 0) {
            afterWhere = new HashMap<>();
            afterWhere.put(truncatedFields[0], truncatedFields[1]);
        }
    }

}
