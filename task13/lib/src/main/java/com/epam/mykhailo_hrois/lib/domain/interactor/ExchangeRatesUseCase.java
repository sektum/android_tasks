package com.epam.mykhailo_hrois.lib.domain.interactor;

import com.epam.mykhailo_hrois.lib.data.repository.ExchangeRatesRepository;
import com.epam.mykhailo_hrois.lib.domain.Rates;
import com.epam.mykhailo_hrois.lib.domain.repository.RatesRepository;
import com.sun.corba.se.spi.orb.StringPair;

import java.util.ArrayList;
import java.util.List;

public class ExchangeRatesUseCase {
    private RatesRepository repository;

    ExchangeRatesUseCase() {
        repository = new ExchangeRatesRepository();
    }

    public Double showRateChange(StringPair currencies) {
        Double rateCount = 0.;
        List<Rates> rates = repository.rates();
        for (Rates rate :
                rates) {
            if (rate.getExchangePair().equals(currencies)) {
                rateCount = rate.getChangeCourse();
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
