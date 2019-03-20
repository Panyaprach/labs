package shapesrandom;

import java.io.File;
import org.apache.flink.api.common.restartstrategy.RestartStrategies;
import org.apache.flink.api.common.typeinfo.TypeHint;
import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.core.fs.Path;
import org.apache.flink.runtime.state.StateBackend;
import org.apache.flink.runtime.state.filesystem.FsStateBackend;
import org.apache.flink.runtime.state.memory.MemoryStateBackend;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.source.SourceFunction;

/**
 *
 * Created by Panyaprach Tularak on Mar 18, 2019
 */
public class ShapeRandom {

    public static void main(String[] args) throws Exception{
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        /*env.setParallelism(1);
        File folder = new File("tmp/checkpoints");
        if(!folder.exists()){
            boolean result = folder.mkdirs();
            System.out.println(result);
        }
        env.enableCheckpointing(1000);
        env.setRestartStrategy(RestartStrategies.fixedDelayRestart(1, 1000));
        Path path = new Path(folder.toURI());
        StateBackend stateBackend = new FsStateBackend(path, false);
        env.setStateBackend(stateBackend);*/
        SourceFunction source = new ShapeRandomSource();
        DataStream<Shape> stream = env.addSource(source);
        //DataStream<Double> dStream = env.addSource(new MathRandomSource());
        //stream = stream.broadcast();
        stream.keyBy(Shape::getColor)
                .map(color -> Thread.currentThread().getId() + " " + color)
                .print();
        //dStream.print();
        env.execute();
    }
}
