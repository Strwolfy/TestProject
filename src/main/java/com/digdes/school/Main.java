package com.digdes.school;

public class Main {

    public static void main(String[] args) {

        JavaSchoolStarter starter = new JavaSchoolStarter();

        try {

            // Пример №1: проверяем вставку
            System.out.println("Запрос 1: " + "INSERT VALUES 'lastName' = 'Федоров', 'id'=1, 'age'=40, 'active'=true");
            starter.execute("INSERT VALUES 'lastName' = 'Федоров', 'id'=1, 'age'=40, 'active'=true");

            // Пример №2: проверяем вставку название inseRt корректно
            System.out.println("Запрос 2: " + "inseRt VALUES 'lastName' = 'Сергей', 'id'=2, 'age'=40, 'cost' = 36.3, 'active'=true");
            starter.execute("inseRt VALUES 'lastName' = 'Сергей', 'id'=2, 'age'=40, 'cost' = 36.3, 'active'=true");

            // Пример №2.1: запрос выдаст исключительную ситуацию
            //starter.execute("УВУс VALUES 'lastName' = 'Сергей', 'active'=true");

            // Пример №3: запрос выбирает по значениям после WHERE, корректно работает с LASTNAME
            System.out.println("Запрос 3: " + "SELECT WHERE 'lastName' = 'Сергей'");
            starter.execute("SELECT WHERE 'LASTNAME' = 'Сергей', 'id' = 2, 'age'=40");

            // Пример №4: запрос выбирает по значениям после WHERE
            System.out.println("Запрос 4: " + "inseRt VALUES 'lastName' = 'Сергей', 'id'=3, 'age'=40, 'cost' = 66.3, 'active'=true");
            starter.execute("inseRt VALUES 'lastName' = 'Сергей', 'id'=3, 'age'=40, 'cost' = 66.3, 'active'=true");

            // Пример №4.1: удаляем id = 2
            System.out.println("Запрос 4.1: " + "DELETE WHERE 'id' = 2");
            starter.execute("DELETE WHERE 'id' = 2");

            // Пример №4.2: Меняем значение false на true
            System.out.println("Запрос 4.2: " + "UPDATE VALUES 'active'=false  where 'active'=true");
            starter.execute("UPDATE VALUES 'active'=false  where 'active'=true");

            // Пример №5: обновляем запрос где id = 3, меняется имя, active и cost
            System.out.println("Запрос 5: " + "UPDATE VALUES 'lastName' = 'КОЛЯ', 'age'=46, 'cost' = 76.5, 'active'=false' WHERE 'id'=3");
            starter.execute("UPDATE VALUES 'lastName' = 'КОЛЯ', 'age'=46, 'cost' = 76.5, 'active'=false' WHERE 'id'=3");

            // Пример №5.1: Обновляем поле 'lastName', где id = 3
            System.out.println("Запрос 5.1: " + "UPDATE VALUES 'lastName' = 'Инокентий', 'cost' = 76.5, 'active'=true' WHERE 'age'=40");
            starter.execute("UPDATE VALUES 'lastName' = 'Инокентий', 'cost' = 76.5, 'active'=true' WHERE 'age'=40");

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

}