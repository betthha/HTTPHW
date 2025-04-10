import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

@Entity
@Table(name = "currency")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Currency {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(name = "code", nullable = false, unique = true, length = 3)
    private String baseCurrency;

    @Column(name = "exchange_rate", nullable = false, precision = 19, scale = 6)
    private BigDecimal priceChangeRange;

    @Column(length = 100)
    private String description;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBaseCurrency() {
        return baseCurrency;
    }

    public void setBaseCurrency(String baseCurrency) {
        this.baseCurrency = baseCurrency;
    }

    public String getPriceChangeRange() {
        return priceChangeRange;
    }

    public void setPriceChangeRange(String priceChangeRange) {
        this.priceChangeRange = priceChangeRange;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}