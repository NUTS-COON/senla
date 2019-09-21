package ru.nutscoon.task1;

import ru.nutscoon.TaskBase;

public class NumberAnalyzer extends TaskBase {

    @Override
    protected String getTaskDescription() {
        return "Создать программу, которая будет сообщать, \n" +
                "является ли целое число, введенное пользователем, \n" +
                "чётным или нечётным, простым или составным. \n" +
                "Если пользователь введёт не целое число, то сообщать ему об ошибке.";
    }


    @Override
    protected String getGreetMsg() {
        return "Введите целое число или q для выхода: ";
    }


    @Override
    protected void solveTask(String input) {
        int number;
        try {
            number = Integer.parseInt(input);
        } catch(Exception e) {
            println("Введённое значение не является целым числом.");
            return;
        }

        String analyze = getNumberAnalyze(number);
        println(analyze);
    }


    private String getNumberAnalyze(int number){
        StringBuilder res = new StringBuilder();

        if(isOdd(number)) {
            res.append("Чётное.");
        } else {
            res.append("Нечётное.");
        }
        res.append(" ");

        if(number <= 1) {
            res.append("Введённое число не является ни простым, ни составным.");
        } else {
            if(isSimple(number)){
                res.append("Простое.");
            } else {
                res.append("Составное.");
            }
        }

        return res.toString();
    }


    private boolean isSimple(int n) {
        if(n < 0){
            return false;
        }

        int mid = (int)Math.sqrt(n);
        for(int i = 2; i < mid; i++){
            if(n % i == 0){
                return false;
            }
        }

        return true;
    }


    private boolean isOdd(int n){
        return n % 2 == 0;
    }
}
