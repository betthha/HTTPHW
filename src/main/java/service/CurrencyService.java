import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import model.Currency;
import repository.CurrencyRepository;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class CurrencyService {

    private final CurrencyRepository currencyRepository;

    public CurrencyService(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    public List<Currency> getAllCurrencies() {
        return currencyRepository.findAll();
    }

    public Currency addCurrency(Currency currency) {
        if (currency.getId() != null && currencyRepository.existsById(currency.getId())) {
            return currency;
        }

        if (currency.getId() == null) {
            currency.setId(generateUniqueId());
        }

        return currencyRepository.save(currency);
    }

    public Currency getCurrencyById(String id) {
        return currencyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Валюта с ID " + id + " не найдена"));
    }

    public Currency updateCurrency(String id, Currency newCurrencyData) {
        Currency currency = getCurrencyById(id);
        updateCurrencyDetails(currency, newCurrencyData);
        return currencyRepository.save(currency);
    }

    public void deleteCurrency(String id) {
        Currency currency = getCurrencyById(id);
        currencyRepository.delete(currency);
    }

    private String generateUniqueId() {
        return UUID.randomUUID().toString();
    }

    private void updateCurrencyDetails(Currency existingCurrency, Currency updatedData) {
        existingCurrency.setName(updatedData.getName());
        existingCurrency.setBaseCurrency(updatedData.getBaseCurrency());
        existingCurrency.setPriceChangeRange(updatedData.getPriceChangeRange());
        existingCurrency.setDescription(updatedData.getDescription());
    }
}