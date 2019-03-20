package concurrent.future;

import java.util.concurrent.Future;

/**
 * Created by Panyaprach Tularak on Mar 17, 2019
 */
class FutureExecution {

    public static void main(String[] args) throws Exception {
        CircleAreaCalculator circleCalculator = new CircleAreaCalculator();
        
        Future<Double> futureOne = circleCalculator.calculate(2.5);
        Future<Double> futureTwo = circleCalculator.calculate(7.95);

        while (!(futureOne.isDone() && futureTwo.isDone())) {
            System.out.println(
                    String.format(
                            "futureOne is %s and futureTwo is %s",
                            futureOne.isDone() ? "done" : "not done",
                            futureTwo.isDone() ? "done" : "not done"
                    )
            );
            Thread.sleep(200);
        }
        Double resultOne = futureOne.get();
        Double resultTwo = futureTwo.get();
        System.out.println(resultOne + " and " + resultTwo);
        circleCalculator.shutdown();
    }
}
