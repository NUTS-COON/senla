package ru.nutscoon;

import java.util.Scanner;

public abstract class TaskBase implements Task {

    protected Scanner scanner;


    protected TaskBase(){
        scanner = new Scanner(System.in);
    }
    
    @Override
    public void run() {
        String taskDescription = getTaskDescription();
        if(taskDescription != null){
            println("");
            println(taskDescription);
            println("");
        }

        String greetMsg = getGreetMsg();
        while (true) {
            print(greetMsg);
            String input = scanner.nextLine();
            if(input.equals("q")) return;
            
            solveTask(input);
        }
    }

    abstract protected String getTaskDescription();

    abstract protected String getGreetMsg();

    abstract protected void solveTask(String input);

    protected void println(String str){
        System.out.println(str);
    }

    protected void print(String str){
        System.out.print(str);
    }

    protected int parseNaturalNumber(String str){
        try {
            return Integer.parseInt(str);
        } catch (Exception e){
            return -1;
        }
    }
}
