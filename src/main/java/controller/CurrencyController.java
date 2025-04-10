import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import model.Currency;
import service.CurrencyService;

import java.util.List;

@RequestMapping("/api/currencies")
@RequiredArgsConstructor
@RestController
public class CurrencyController {

    private final CurrencyService currencyService;

    @GetMapping
    public ResponseEntity<List<Currency>> getAllCurrencies() {
        List<Currency> currencies = currencyService.getAllCurrencies();
        return ResponseEntity.ok(currencies);
    }

    @PostMapping
    public ResponseEntity<Currency> createNewCurrency(@RequestBody Currency currency) {
        if (currency.getName() == null  currency.getCode() == null  currency.getExchangeRate() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(currency);
        }
        Currency createdCurrency = currencyService.addCurrency(currency);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCurrency);
    }

    @GetMapping("/{currencyId}")
    public ResponseEntity<Currency> getCurrencyById(@PathVariable String currencyId) {
        Currency currency = currencyService.getCurrencyById(currencyId);
        if (currency == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(currency);
    }

    @PutMapping("/{currencyId}")
    public ResponseEntity<Currency> updateCurrencyData(
            @PathVariable String currencyId,
            @RequestBody Currency updatedCurrency) {
        Currency existing = currencyService.getCurrencyById(currencyId);
        if (existing == null) {
            return ResponseEntity.notFound().build();
        }
        Currency updatedCurrencyData = currencyService.updateCurrency(currencyId, updatedCurrency);
        return ResponseEntity.ok(updatedCurrencyData);
    }

    @DeleteMapping("/{currencyId}")
    public ResponseEntity<Void> deleteCurrency(@PathVariable String currencyId) {
        Currency existing = currencyService.getCurrencyById(currencyId);
        if (existing == null) {
            return ResponseEntity.notFound().build();
        }
        currencyService.deleteCurrency(currencyId);
        return ResponseEntity.noContent().build();
    }
}