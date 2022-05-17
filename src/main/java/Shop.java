import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.LongAdder;

public class Shop {
    private static final AtomicInteger COUNT = new AtomicInteger(1);
    private static final int MIN_SELL_COUNT = 4;
    private static final int MAX_SELL_COUNT = 10;
    private static final int MIN_PRICE = 100;
    private static final int MAX_PRICE = 10000;
    private final LongAdder account;

    private final int shopId;

    public Shop(LongAdder account) {
        this.account = account;
        this.shopId = COUNT.getAndIncrement();
    }

    public void sum() {
        Random random = new Random();
        int[] money = new int[random.nextInt(MAX_SELL_COUNT) + MIN_SELL_COUNT];
        for (int i = 0; i < money.length; i++) {
            money[i] = random.nextInt(MAX_PRICE) + MIN_PRICE;
        }
        System.out.println("Выручка магазина №" + this.shopId + ": " + Arrays.toString(money));
        Arrays.stream(money).forEach(this.account::add);
    }
}
