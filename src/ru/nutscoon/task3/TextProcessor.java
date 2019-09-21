package ru.nutscoon.task3;

import ru.nutscoon.TaskBase;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class TextProcessor extends TaskBase {

    @Override
    protected String getTaskDescription() {
        return "Создать программу, которая будет:\n" +
                "\t- подсчитывать количество слов в предложении\n" +
                "\t- выводить их в отсортированном виде \n" +
                "\t- делать первую букву каждого слова заглавной. \n" +
                "Предложение вводится вручную. (Разделитель пробел (“ ”)).";
    }

    @Override
    protected String getGreetMsg() {
        return "Введите предложение или q для выхода: ";
    }


    @Override
    protected void solveTask(String input) {
        if(input.isEmpty()) {
            println("Введена пустая строка.");
            return;
        }

        List<String> words = getWords(input);
        capitalizeWords(words);
        sortWords(words);

        println(makeReport(words));
    }


    private static String makeReport(List<String> words){
        return new StringBuilder()
                .append("Количество слов: ")
                .append(words.size())
                .append(". Слова в отсортированном порядке: ")
                .append(String.join(", ", words))
                .append(".")
                .toString();
    }


    private List<String> getWords(String text){
        String[] words = text
                .trim()
                .replaceAll("[()]", " ")
                .replaceAll("\\p{Punct}", "")
                .replaceAll("\\p{Digit}", "")
                .split("\\s+");
        List<String> res = new ArrayList<String>(Arrays.asList(words));
        res.removeIf(String::isEmpty);

        return res;
    }


    private void capitalizeWords(List<String> words){
        for(int i = 0; i < words.size(); i++){
            words.set(i, capitalizeWord(words.get(i)));
        }
    }


    private String capitalizeWord(String word){
        return word.substring(0, 1).toUpperCase() + word.substring(1);
    }


    private void sortWords(List<String> words){
        Collator collator = Collator.getInstance(new Locale("ru"));
        collator.setStrength(Collator.PRIMARY);
        words.sort(collator);
    }
}
