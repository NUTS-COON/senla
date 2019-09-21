package ru.nutscoon;

import ru.nutscoon.task1.NumberAnalyzer;
import ru.nutscoon.task2.LcmAndGcd;
import ru.nutscoon.task3.TextProcessor;
import ru.nutscoon.task4.TextAnalyzer;
import ru.nutscoon.task5.FindPalindrome;
import ru.nutscoon.task6.BackpackProblem;

public class MainTask extends TaskBase {

    private Task[] tasks = new Task[]{
            new NumberAnalyzer(),
            new LcmAndGcd(),
            new TextProcessor(),
            new TextAnalyzer(),
            new FindPalindrome(),
            new BackpackProblem()
    };


    @Override
    protected String getTaskDescription() {
        return null;
    }


    @Override
    protected String getGreetMsg() {
        return "Введите номер задачи от 1 до " + tasks.length + " или q для выхода: ";
    }


    @Override
    protected void solveTask(String input) {
        int taskNumber = parseNaturalNumber(input);

        if(taskNumber < 1 || taskNumber - 1 >= tasks.length){
            System.out.println("Номер задачи введён некорректно.");
            return;
        }

        tasks[taskNumber - 1].run();
    }
}
