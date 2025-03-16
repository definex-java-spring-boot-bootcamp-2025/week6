package dev.patika.definexjavaspringbootbootcamp2025.fundamentalsofdeclarativeprogramming;

import dev.patika.definexjavaspringbootbootcamp2025.fundamentalsofdeclarativeprogramming.model.AdditionCalculator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class MethodChainApp {

    public static void main(String[] args) {
        SpringApplication.run(MethodChainApp.class, args);

        AdditionCalculator addCalculator = new AdditionCalculator();
        int result = addCalculator
                .setAddition1(1)
                .setAddition2(2)
                .calculate()
                .result();

        List<Integer> nums = new ArrayList<>();
        nums.stream().map(con -> con ^ 2).toList();
        System.out.println(result);
    }
}
