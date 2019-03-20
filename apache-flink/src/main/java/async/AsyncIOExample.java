package async;

import java.io.File;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;
import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.core.fs.Path;
import org.apache.flink.runtime.state.filesystem.FsStateBackend;
import org.apache.flink.streaming.api.CheckpointingMode;
import org.apache.flink.streaming.api.TimeCharacteristic;
import org.apache.flink.streaming.api.datastream.AsyncDataStream;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.async.AsyncFunction;
import org.apache.flink.util.Collector;

/**
 *
 * Created by Panyaprach Tularak on Mar 20, 2019
 */
public class AsyncIOExample {

    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        File checkpoints = new File("checkpoints");
        env.setStateBackend(new FsStateBackend(new Path(checkpoints.toURI())));
        env.enableCheckpointing(1000L, CheckpointingMode.EXACTLY_ONCE);
        env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime);
        DataStream<Integer> inputStream = env.addSource(new SimpleSource(100000));
        int sleepFactor = 100;
        float failRatio = 0.001f;
        int shutdownWaitTs = 20000;
        AsyncFunction<Integer, String> function = new SampleAsyncFunction(sleepFactor, failRatio, shutdownWaitTs);

        DataStream<String> result;
        result = AsyncDataStream.orderedWait(inputStream, function, 10000L, TimeUnit.MILLISECONDS);
        result.flatMap(new FlatMapFunction<String, Tuple2<String, Integer>>() {
            private static final long serialVersionUID = -938116068682344455L;

            @Override
            public void flatMap(String value, Collector<Tuple2<String, Integer>> out) throws Exception {
                out.collect(new Tuple2<>(value, 1));
            }
        }).keyBy(0).sum(1).print();

        // execute the program
        env.execute("Async IO Example");
    }
}
