package com.epam.mykhailo_hrois.lib.domain.interactor;

import com.epam.mykhailo_hrois.lib.data.repository.ExchangeRatesRepository;
import com.epam.mykhailo_hrois.lib.domain.Rates;
import com.epam.mykhailo_hrois.lib.domain.StringPair;
import com.epam.mykhailo_hrois.lib.domain.repository.RatesRepository;

import java.util.ArrayList;
import java.util.List;

public class ExchangeRatesUseCase {
    private RatesRepository repository;

    public ExchangeRatesUseCase() {
        repository = new ExchangeRatesRepository();
    }

    public Rates showRateChange(StringPair currencies) {
        Rates rateCount = null;
        List<Rates> rates = repository.rates();
        for (Rates rate :
                rates) {
            if (rate.getExchangePair().equals(currencies)) {
                rateCount = rate;
            }
        }
        return rateCount;
    }

    public List<Double> showAllRateChanges() {
        List<Double> rateCountList = new ArrayList<>();
        for (Rates rate :
                repository.rates()) {
            rateCountList.add(rate.getChangeCourse());
        }
        return rateCountList;
    }

    public List<Double> showAllExchangeRates() {
        List<Double> rateCountList = new ArrayList<>();
        for (Rates rate :
                repository.rates()) {
            rateCountList.add(rate.getCurrentCourse());
        }
        return rateCountList;
    }
}
