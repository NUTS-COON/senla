package ru.nutscoon.task4;

import ru.nutscoon.TaskBase;

public class TextAnalyzer extends TaskBase {

    @Override
    protected String getTaskDescription() {
        return "Создать программу, которая подсчитывает \n" +
                "сколько раз употребляется слово в тексте (без учета регистра). \n" +
                "Текст и слово вводится вручную.";
    }


    @Override
    protected String getGreetMsg() {
        return "Введите текст и слово, разделённые пустой строкой. Для выхода введите q.\n";
    }

    @Override
    protected void solveTask(String input) {
        if(input.isEmpty()){
            println("Вы не ввели текст.");
            return;
        }

        StringBuilder text = new StringBuilder();
        text.append(input);
        while (!input.isEmpty()){
            input = scanner.nextLine();
            text.append(" ");
            text.append(input);
        }

        print("Теперь введите слово: ");
        String word = scanner.nextLine();
        if(word.isEmpty()){
            println("Вы не ввели слово.");
            return;
        }

        int count = countWordInText(text.toString(), word);
        println("Слово \"" + word + "\" встречается в тексте " + count + " раз(а).");
    }


    private int countWordInText(String text, String word){
        int count = 0;
        String[] words = text
                .trim()
                .replaceAll("[()]", " ")
                .replaceAll("\\p{Punct}", "")
                .replaceAll("\\p{Digit}", "")
                .split("\\s+");

        for (String s : words) {
            if (s.equalsIgnoreCase(word)) {
                count++;
            }
        }

        return count;
    }
}
