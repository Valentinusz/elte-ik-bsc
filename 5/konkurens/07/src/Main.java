import java.math.BigDecimal;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Random;

public class Main {
    private static final Random RANDOM = new Random();

    public static void main(String[] args) {
        TradeHouse tradeHouse = new TradeHouse();
        for (Stock stock : Stock.values()) {
            tradeHouse.introduce(stock, BigDecimal.valueOf(20.0 + RANDOM.nextDouble() * 80));
        }

        new Thread(() -> {
            while (true) {
                unsafeSleep(Duration.of(100, ChronoUnit.MILLIS));
                tradeHouse.saveEvent(randomStock(), BigDecimal.valueOf(20 * RANDOM.nextDouble() - 10.0));
            }
        }, "Event generator").start();

        new Thread(() -> {
            while (true) {
                unsafeSleep(Duration.of(10, ChronoUnit.SECONDS));
                tradeHouse.actualise();
            }
        }, "Actualizer").start();

        for (int i = 0; i < Runtime.getRuntime().availableProcessors() * 4; ++i) {
            new Thread(() -> {
                Stock lastSeen = null;
                while (true) {
                    unsafeSleep(Duration.ofMillis(RANDOM.nextInt(50, 300)));
                    int rnd = RANDOM.nextInt(100);
                    if (rnd < 1) {
                        buyStock(randomStock(), tradeHouse);
                    } else if (rnd < 2) {
                        sellStock(randomStock(), tradeHouse);
                    } else if (rnd < 97) {
                        lastSeen = tradeHouse.mostValuable();
                    } else if (rnd < 98) {
                        lastSeen = tradeHouse.mostValuableAfterActualisation();
                    } else if (rnd < 99) {
                        lastSeen = tradeHouse.mostValuableAfterActualisationIfNeeded();
                    } else {
                        if (lastSeen != null) {
                            if (lastSeen != tradeHouse.mostValuable()) {
                                sellStock(lastSeen, tradeHouse);
                                lastSeen = null;
                            } else {
                                buyStock(lastSeen, tradeHouse);
                            }
                        } else {
                            lastSeen = tradeHouse.mostValuable();
                        }
                    }
                }
            }, "Treader - " + (i + 1)).start();
        }
    }

    private static final Stock randomStock() {
        return Stock.values()[RANDOM.nextInt(Stock.values().length)];
    }

    private static void unsafeSleep(Duration duration) {
        try {
            Thread.sleep(duration.toMillis());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static void sellStock(Stock stock, TradeHouse tradeHouse) {
        tradeHouse.saveEvent(stock, BigDecimal.valueOf(-0.5));
    }

    private static void buyStock(Stock stock, TradeHouse tradeHouse) {
        tradeHouse.saveEvent(stock, BigDecimal.valueOf(+0.5));
    }
}
