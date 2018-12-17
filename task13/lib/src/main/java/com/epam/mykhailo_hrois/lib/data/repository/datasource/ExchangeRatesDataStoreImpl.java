package com.epam.mykhailo_hrois.lib.data.repository.datasource;

import com.epam.mykhailo_hrois.lib.data.entity.ExchangeRates;
import com.sun.corba.se.spi.orb.StringPair;

import java.util.ArrayList;
import java.util.List;

import static com.epam.mykhailo_hrois.lib.data.repository.datasource.Constants.*;

public class ExchangeRatesDataStoreImpl implements ExchangeRatesDataStore {

    private List<ExchangeRates> exchangeRates;

    public ExchangeRatesDataStoreImpl(){
        exchangeRates = initList();
    }

    @Override
    public List<ExchangeRates> ratesList() {
        return exchangeRates;
    }

    @Override
    public ExchangeRates rate(int id) {
        return exchangeRates.get(id);
    }

    private List<ExchangeRates> initList() {
        List<ExchangeRates> rates = new ArrayList<>();
        ExchangeRates rates1;
        for (int i = 0; i < 5; i++) {
            rates1 = new ExchangeRates();
            rates1.setId(i);
            rates.add(rates1);
        }
        //okay let's go
        //USD to UAH
        rates.get(1).setExchangePair(new StringPair(USD, UAH));
        rates.get(1).setCurrentCourse(26.8);
        rates.get(1).setChangeCourse(0.1);
        //EUR to UAH
        rates.get(2).setExchangePair(new StringPair(EUR, UAH));
        rates.get(1).setCurrentCourse(31.2);
        rates.get(1).setChangeCourse(-0.1);
        //RUB to UAH
        rates.get(2).setExchangePair(new StringPair(RUB, UAH));
        rates.get(1).setCurrentCourse(0.4);
        rates.get(1).setChangeCourse(-0.01);
        //UAH to RUB
        rates.get(2).setExchangePair(new StringPair(UAH, RUB));
        rates.get(1).setCurrentCourse(2.3256);
        rates.get(1).setChangeCourse(-0.01);
        //UAH to USD
        rates.get(2).setExchangePair(new StringPair(UAH, USD));
        rates.get(1).setCurrentCourse(0.0355);
        rates.get(1).setChangeCourse(0.001);
        return rates;
    }
}
