import java.math.BigDecimal;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class TradeHouse {
    private static final Comparator<Map.Entry<Stock, BigDecimal>> MOST_VALUABLE = Map.Entry.<Stock, BigDecimal>comparingByValue().reversed();
    private final Map<Stock, BigDecimal> prices = new HashMap<>();
    private final List<StockChange> changes = new ArrayList<>();
    private final ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock(true);

    public void introduce(Stock stock, BigDecimal price) {
        if (prices.containsKey(stock)) {
            throw new IllegalArgumentException("Stock (" + stock + ") is already on the market");
        }

        readWriteLock.writeLock().lock();

        try {
            prices.put(stock, price);
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    public Map<Stock, BigDecimal> currentPrices() {
        readWriteLock.readLock().lock();

        Map<Stock, BigDecimal> result;
        try {
             result = new HashMap<>(prices);
        } finally {
            readWriteLock.readLock().unlock();
        }

        for (StockChange change : changes) {
            result.computeIfPresent(change.stock(), (k, v) -> v.add(change.change()));
        }

        return result;
    }

    public void actualise() {
        readWriteLock.readLock().lock();

        Map<Stock, BigDecimal> newPrices;
        try {
             newPrices = currentPrices();
        } finally {
            readWriteLock.readLock().unlock();
        }

        readWriteLock.writeLock().lock();
        try {

        } finally {

        }

        changes.clear();
        prices.clear();
        prices.putAll(newPrices);
    }

    public void saveEvent(Stock stock, BigDecimal change) {
        changes.add(new StockChange(stock, Instant.now(), change));
    }

    public BigDecimal priceOf(Stock stock) {
        return changes.stream().filter(c -> stock.equals(stock)).map(StockChange::change).reduce(prices.get(stock), BigDecimal::add);
    }

    public Stock mostValuable() {
        return currentPrices().entrySet().stream().max(MOST_VALUABLE).orElseThrow().getKey();
    }

    public Stock mostValuableAfterActualisation() {
        actualise();
        return mostValuable();
    }

    public Stock mostValuableAfterActualisationIfNeeded() {
        if(!changes.isEmpty()) {
            actualise();
        }
        return mostValuable();
    }
}
