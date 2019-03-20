package shapesrandom;

import org.apache.flink.streaming.api.functions.source.RichParallelSourceFunction;

/**
 *
 * Created by Panyaprach Tularak on Mar 19, 2019
 */
public class MathRandomSource extends RichParallelSourceFunction<Double>{
    private volatile boolean run = true;
    
    @Override
    public void run(SourceContext<Double> ctx) throws Exception {
        while(run){
            long delay = (long) (Math.random() * 10000);
            Thread.sleep(delay);
            ctx.collect(Math.random() * 100);
        }
    }

    @Override
    public void cancel() {
        run = false;
    }

}
