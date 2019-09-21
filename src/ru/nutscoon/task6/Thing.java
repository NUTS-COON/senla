package ru.nutscoon.task6;

class Thing {

    private String name;
    private int weight;
    private int cost;


    Thing(String name, int weight, int cost) {
        this.name = name;
        this.weight = weight;
        this.cost = cost;
    }

    int getWeight() {
        return weight;
    }

    int getCost() {
        return cost;
    }

    @Override
    public String toString() {
        return "название - " + name + ", вес - " + weight + ", стоимость - " + cost;
    }
}
