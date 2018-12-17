package com.epam.mykhailo_hrois.lib.data.entity.mapper;

import com.epam.mykhailo_hrois.lib.data.entity.ExchangeRates;
import com.epam.mykhailo_hrois.lib.domain.Rates;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ExchangeRatesMapper {

    public ExchangeRatesMapper() {
    }

    public Rates transform(ExchangeRates rates) {
        Rates rate = null;
        if (rates != null) {
            rate = new Rates(rates.getId());
            rate.setExchangePair(rates.getExchangePair());
            rate.setCurrentCourse(rates.getCurrentCourse());
            rate.setChangeCourse(rates.getChangeCourse());
        }
        return rate;
    }

    public List<Rates> transform(Collection<ExchangeRates> exchangeRatesCollection) {
        List<Rates> ratesList = new ArrayList<>();
        for (ExchangeRates exchangeRates : exchangeRatesCollection) {
            Rates rate = transform(exchangeRates);
            if (rate != null) {
                ratesList.add(rate);
            }
        }
        return ratesList;
    }
}
