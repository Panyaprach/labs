package async;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.functions.async.ResultFuture;
import org.apache.flink.streaming.api.functions.async.RichAsyncFunction;
import org.apache.flink.util.ExecutorUtils;

/**
 * An sample of {@link AsyncFunction} using a thread pool and executing working
 * threads to simulate multiple async operations.
 *
 * <p>
 * For the real use case in production environment, the thread pool may stay in
 * the async client.
 */
public class SampleAsyncFunction extends RichAsyncFunction<Integer, String> {

    private static final long serialVersionUID = 2098635244857937717L;

    private transient ExecutorService executorService;

    /**
     * The result of multiplying sleepFactor with a random float is used to
     * pause the working thread in the thread pool, simulating a time consuming
     * async operation.
     */
    private final long sleepFactor;

    /**
     * The ratio to generate an exception to simulate an async error. For
     * example, the error may be a TimeoutException while visiting HBase.
     */
    private final float failRatio;

    private final long shutdownWaitTS;

    SampleAsyncFunction(long sleepFactor, float failRatio, long shutdownWaitTS) {
        this.sleepFactor = sleepFactor;
        this.failRatio = failRatio;
        this.shutdownWaitTS = shutdownWaitTS;
    }

    @Override
    public void open(Configuration parameters) throws Exception {
        super.open(parameters);

        executorService = Executors.newFixedThreadPool(30);
    }

    @Override
    public void close() throws Exception {
        super.close();
        ExecutorUtils.gracefulShutdown(shutdownWaitTS, TimeUnit.MILLISECONDS, executorService);
    }

    @Override
    public void asyncInvoke(final Integer input, final ResultFuture<String> resultFuture) {
        executorService.submit(() -> {
            // wait for while to simulate async operation here
            long sleep = (long) (ThreadLocalRandom.current().nextFloat() * sleepFactor);
            try {
                Thread.sleep(sleep);

                if (ThreadLocalRandom.current().nextFloat() < failRatio) {
                    resultFuture.completeExceptionally(new Exception("wahahahaha..."));
                } else {
                    resultFuture.complete(
                            Collections.singletonList("key-" + (input % 10)));
                }
            } catch (InterruptedException e) {
                resultFuture.complete(new ArrayList<>(0));
            }
        });
    }
}
