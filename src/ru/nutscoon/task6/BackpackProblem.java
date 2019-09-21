package ru.nutscoon.task6;

import ru.nutscoon.TaskBase;

import java.util.*;

public class BackpackProblem extends TaskBase {

    @Override
    protected String getTaskDescription() {
        return "Имеется набор вещей, которые необходимо поместить в рюкзак. \n" +
                "Рюкзак обладает заданной грузоподъемностью. \n" +
                "Вещи в свою очередь обладают двумя параметрами — весом и стоимостью. \n" +
                "Цель задачи заполнить рюкзак не превысив его грузоподъемность \n" +
                "и максимизировать суммарную ценность груза.";
    }


    @Override
    protected String getGreetMsg() {
        return "Введите вместимость рюкзака (целое число, больше 0) или q для выхода: ";
    }


    @Override
    protected void solveTask(String input) {
        int capacity = parseNaturalNumber(input);
        if(capacity < 1){
            println("Вместимость рюкзака введёна некорректно.");
            return;
        }

        print("Введите количество вещей (целое число, больше 0): ");
        input = scanner.nextLine();
        int thingsCount = parseNaturalNumber(input);
        if(thingsCount < 1){
            println("Количество вещей введёно некорректно.");
            return;
        }

        Thing[] things = new Thing[thingsCount];
        println("Введите данные о предметах в следующем формате: название вес стоимость. " +
                "Каждый предмет вводить с новой строки. Вес и стоимость целые неотрицательные числа.");
        for(int i = 0; i < thingsCount; i++){
            input = scanner.nextLine();
            Thing thing = parseThing(input);
            if(thing == null){
                println("Данные о предмете введены некорректно. Попробуйте ещё раз.");
                i--;
                continue;
            }

            things[i] = thing;
        }

        int[] bestThingsIndex = getBestThingsIndex(capacity, things);
        Thing[] bestThings = getBestThings(things, bestThingsIndex);
        println(makeReport(bestThings));
    }


    private int[] getBestThingsIndex(int capacity, Thing[] things){
        int[][] costs = new int[things.length + 1][capacity + 1];
        List<Integer> bestThingsIndex = new ArrayList<>();

        for (int k = 1; k < things.length + 1; k++){
            int w_k = things[k - 1].getWeight();
            int c_k = things[k - 1].getCost();

            for (int w = 1; w < capacity + 1; w++){
                if(w - w_k < 0){
                    costs[k][w] = costs[k - 1][w];
                }else {
                    costs[k][w] = Math.max(c_k + costs[k - 1][w - w_k], costs[k - 1][w]);
                }
            }
        }

        int w = capacity;
        for(int i = things.length; i > 0 && w > 0; i--){
            while (costs[i - 1][w] == costs[i][w]){
                i--;
                if(i == 0) {
                    return toSortedArray(bestThingsIndex);
                }
            }

            bestThingsIndex.add(i - 1);
            w = w - things[i - 1].getWeight();
        }

        return toSortedArray(bestThingsIndex);
    }


    private String makeReport(Thing[] bestThings){
        int totalWeight = 0;
        int totalCost = 0;

        StringBuilder totalReport = new StringBuilder();
        StringBuilder thingsReport = new StringBuilder();
        totalReport.append("Количество вещей, вместившихся в рюкзак: ").append(bestThings.length).append(" \n");
        for (Thing bestThing : bestThings) {
            totalWeight += bestThing.getWeight();
            totalCost += bestThing.getCost();
            thingsReport.append(bestThing.toString()).append(" \n");
        }

        totalReport.append("Суммарный вес: ").append(totalWeight).append(" \n");
        totalReport.append("Суммарная стоимость: ").append(totalCost).append(" \n");
        totalReport.append(thingsReport.toString());

        return totalReport.toString();
    }


    private Thing parseThing(String str){
        String[] data = str.split(" ");
        if(data.length != 3){
            return null;
        }

        String name = data[0];
        int weight = parseNaturalNumber(data[1]);
        if(weight < 0){
            return null;
        }

        int cost = parseNaturalNumber(data[2]);
        if(cost < 0){
            return null;
        }

        return new Thing(name, weight, cost);
    }


    private int[] toSortedArray(List<Integer> list){
        int[] res = new int[list.size()];
        for(int i = 0; i < list.size(); i++){
            res[i] = list.get(i);
        }
        Arrays.sort(res);

        return res;
    }


    private Thing[] getBestThings(Thing[] things, int[] bestThingsIndex) {
        Thing[] bestThings = new Thing[bestThingsIndex.length];
        for(int i = 0; i < bestThings.length; i++){
            bestThings[i] = things[bestThingsIndex[i]];
        }

        return bestThings;
    }
}