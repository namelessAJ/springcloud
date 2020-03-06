package hystrix;

import java.util.Random;
import java.util.concurrent.*;

/**
 *
 */
public class TestHystrixCommand {

    public static void main(String[] args) {
        // 启动一个线程池
        ExecutorService executor = new ThreadPoolExecutor(4, 10, 10, TimeUnit.SECONDS,
                new LinkedBlockingDeque<>());

        for(int i=0; i<10;i++){
            // 线程池提交任务
            Future<Integer> future = executor.submit(new Callable<Integer>() {
                /**
                 * Computes a result, or throws an exception if unable to do so.
                 *
                 * @return computed result
                 * @throws Exception if unable to compute a result
                 */
                @Override
                public Integer call() throws Exception {
                    return new Random().nextInt();
                }
            });

            // 获取结果
            try {
                Integer result = future.get();
                System.out.println(result);
            } catch (InterruptedException e) {
            } catch (ExecutionException e) {
            }
        }

        executor.shutdown();
    }


}
