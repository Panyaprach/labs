package shapesrandom;

import java.util.HashMap;
import java.util.Random;
import org.apache.flink.streaming.api.functions.source.RichParallelSourceFunction;
import static shapesrandom.Color.*;

/**
 *
 * Created by Panyaprach Tularak on Mar 18, 2019
 */
public class ShapeRandomSource extends RichParallelSourceFunction<Shape> {

    private volatile boolean run = true;

    @Override
    public void run(SourceContext<Shape> ctx) throws Exception {
        while(run){
            long delay = (long)(Math.random() * 10000);
            Thread.sleep(delay);
            Shape element = random();
            ctx.collect(element);
        }
    }

    @Override
    public void cancel() {
        run = false;
    }

    private Shape random() {
        HashMap<Integer, Color> colors = new HashMap<Integer, Color>() {
            {
                put(1, Red);
                put(2, Yellow);
                put(3, Pink);
                put(4, Green);
                put(5, Orange);
                put(6, Cyan);
                put(7, Purple);
            }
        };
        Random random = new Random();
        int color = random.nextInt(7) + 1;
        int shape = random.nextInt(2) +1 ;
        double value = Math.random() * 100;
        
        return newShape(shape, colors.get(color), value);
    }

    private Shape newShape(int shape, Color color, double val) {
        switch (shape) {
            case 1:
                return new Circle(color, val);
            case 2:
                return new Square(color, val);
            default:
                return null;
        }
    }

}
