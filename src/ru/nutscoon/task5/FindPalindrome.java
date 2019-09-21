package ru.nutscoon.task5;

import ru.nutscoon.TaskBase;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class FindPalindrome extends TaskBase {

    @Override
    protected String getTaskDescription() {
        return "Создать программу, которая в последовательности \n" +
                "от 0 до N, находит все числа-палиндромы \n" +
                "(зеркальное значение равно оригинальному). \n" +
                "Длина последовательности N вводится вручную и не должна превышать 100. ";
    }


    @Override
    protected String getGreetMsg() {
        return "Введите длину последовательности (целое число от 0 до 100): ";
    }


    @Override
    protected void solveTask(String input) {
        int n;
        try {
            n = Integer.parseInt(input);
        } catch (NumberFormatException e){
            println("Длина последовательности введена некорректно.");
            return;
        }

        if(n < 1){
            println("В последовательности, длинной меньше 1, палиндромов не будет.");
            return;
        }

        println("Введите натуральные числа, каждое с новой строки.");
        List<String> palindromes = new ArrayList<>();
        for(int i = 0; i < n; i++){
            String number = scanner.nextLine();
            if(number.isEmpty() || isStartWithZero(number) || !isNaturalNumber(number)){
                println("Число введено некорректно. Попробуйте ещё раз.");
                i--;
                continue;
            }

            if(isPalindrome(number)){
                palindromes.add(number);
            }
        }

        println(makeReport(palindromes));
    }


    private String makeReport(List<String> palindromes){
        StringBuilder sb = new StringBuilder();
        if(palindromes.size() > 0){
            sb.append("Числа-палиндромы: ")
                    .append(String.join(", ", palindromes));
        }else {
            sb.append("Чисел-палиндромов нет.");
        }

        return sb.toString();
    }


    private boolean isNaturalNumber(String s){
        try {
            BigInteger n = new BigInteger(s);
            return n.compareTo(BigInteger.ZERO) > 0;
        }catch (NumberFormatException e){
            return false;
        }
    }


    private boolean isStartWithZero(String s){
        return s.startsWith("0");
    }


    private boolean isPalindrome(String s){
        char[] chars = s.toCharArray();
        int length = chars.length;

        for(int i = 0; i < length / 2; i++){
            if(chars[i] != chars[length - i - 1]){
                return false;
            }
        }

        return true;
    }
}
