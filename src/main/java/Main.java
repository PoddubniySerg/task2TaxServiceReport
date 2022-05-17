import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.LongAdder;

public class Main {
    public static final int SHOP_COUNT = 3;

    public static void main(String[] args) throws InterruptedException {
        LongAdder account = new LongAdder();
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        for (int i = 0; i < SHOP_COUNT; i++) {
            executorService.submit(() -> new Shop(account, executorService).sum());
        }
        executorService.awaitTermination(3, TimeUnit.SECONDS);
        executorService.shutdown();
        System.out.println("Общая выручка всех магазинов сегодня: " + account.sum());
    }
}