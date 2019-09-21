package ru.nutscoon.task2;

import ru.nutscoon.TaskBase;

public class LcmAndGcd extends TaskBase {

    @Override
    protected String getTaskDescription() {
        return "Создать программу, которая будет вычислять и выводить на экран \n" +
                "НОК (наименьшее общее кратное) и НОД (наибольший общий делитель) \n" +
                "двух целых чисел, введенных пользователем. \n" +
                "Если пользователь некорректно введёт хотя бы одно из чисел, то сообщать об ошибке.";
    }

    @Override
    protected String getGreetMsg() {
        return "Введите 2 целых числа через пробел или q для выхода: ";
    }


    @Override
    protected void solveTask(String input) {
        int[] numbers;
        try {
            numbers = parseNumbers(input);
        } catch(Exception e) {
            println("Одно из чисел введено некорректно.");
            return;
        }

        if(numbers.length != 2){
            println("Нужно ввести 2 целых числа.");
            return;
        }

        int a = numbers[0];
        int b = numbers[1];

        println(findLcmAndGcd(a, b));
    }


    private int[] parseNumbers(String str){
        String[] values = str.split(" ");
        int[] res = new int[values.length];

        for(int i = 0; i < values.length; i++){
            res[i] = Integer.parseInt(values[i]);
        }

        return res;
    }


    private String findLcmAndGcd(int a, int b){
        if(a == 0 && b == 0){
            return "НОК и НОД для этих чисел не определён.";
        } else {
            int gcd = getGcd(Math.abs(a), Math.abs(b));
            int lcm = getLcm(a, b, gcd);
            return "НОК: " + lcm + " НОД: " + gcd;
        }
    }


    private int getGcd(int a, int b){
        if(a == 0) return b;
        if(b == 0) return a;

        if(a < b){
            int temp = a;
            a = b;
            b = temp;
        }

        int temp;
        while (b != 0){
            temp = b;
            b = a % b;
            a = temp;
        }

        return a;
    }


    private int getLcm(int a, int b, int gcd){
        return Math.abs(a * b) / gcd;
    }
}
