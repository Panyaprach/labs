package concurrent.future;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by Panyaprach Tularak on Mar 18, 2019
 */
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
