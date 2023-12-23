import java.math.BigDecimal;
import java.time.Instant;

public record StockChange (Stock stock, Instant at, BigDecimal change) {

}