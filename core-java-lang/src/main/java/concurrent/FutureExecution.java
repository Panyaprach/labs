package concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by Panyaprach Tularak on Mar 17, 2019
 */
class FutureExecution {

    public static void main(String[] args) throws Exception {
        CircleAreaCalculator calculator = new CircleAreaCalculator();
        Future futureArea = calculator.calculate(2.5);

        while (!futureArea.isDone()) {
            System.out.println("Calculating...");
            Thread.sleep(200);
        }

        System.out.println("Calculate complete Area... " + futureArea.get());
    }
}

class CircleAreaCalculator {

    private transient ExecutorService executor;

    CircleAreaCalculator() {
        executor = Executors.newSingleThreadExecutor();
    }

    Future<Double> calculate(double radius) {

        return executor.submit(() -> {
            Thread.sleep(1000);
            double area = Math.PI * Math.pow(radius, 2);

            return area;
        });
    }
}
