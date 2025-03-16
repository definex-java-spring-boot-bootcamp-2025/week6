package dev.patika.definexjavaspringbootbootcamp2025.fundamentalsofdeclarativeprogramming.model;

public class AdditionCalculator {

    private int addition1;
    private int addition2;

    private int sum;

    public AdditionCalculator() {

    }

    public AdditionCalculator setAddition1(int add1) {
        this.addition1 = add1;
        return this;
    }

    public AdditionCalculator setAddition2(int add2) {
        this.addition2 = add2;
        return this;
    }

    public AdditionCalculator calculate() {
        this.sum = this.addition1 + this.addition2;
        return this;
    }

    public int result() {
        return this.sum;
    }
}
