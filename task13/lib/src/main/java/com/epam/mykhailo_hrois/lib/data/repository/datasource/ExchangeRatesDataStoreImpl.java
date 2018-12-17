package com.epam.mykhailo_hrois.lib.data.repository.datasource;

import com.epam.mykhailo_hrois.lib.data.entity.ExchangeRates;
import com.epam.mykhailo_hrois.lib.domain.StringPair;

import java.util.ArrayList;
import java.util.List;

import static com.epam.mykhailo_hrois.lib.data.repository.datasource.Constants.EUR;
import static com.epam.mykhailo_hrois.lib.data.repository.datasource.Constants.RUB;
import static com.epam.mykhailo_hrois.lib.data.repository.datasource.Constants.UAH;
import static com.epam.mykhailo_hrois.lib.data.repository.datasource.Constants.USD;

public class ExchangeRatesDataStoreImpl implements ExchangeRatesDataStore {

    private List<ExchangeRates> exchangeRates;

    public ExchangeRatesDataStoreImpl() {
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
        //okay let's go
        ExchangeRates rates1 = new ExchangeRates();
        rates1.setId(1);
        rates.add(rates1);
        ExchangeRates rates2 = new ExchangeRates();
        rates1.setId(2);
        rates.add(rates2);
        ExchangeRates rates3 = new ExchangeRates();
        rates1.setId(3);
        rates.add(rates3);
        ExchangeRates rates4 = new ExchangeRates();
        rates1.setId(4);
        rates.add(rates4);
        ExchangeRates rates5 = new ExchangeRates();
        rates1.setId(5);
        rates.add(rates5);
        ExchangeRates rates6 = new ExchangeRates();
        rates1.setId(6);
        rates.add(rates6);
        ExchangeRates rates7 = new ExchangeRates();
        rates1.setId(7);
        rates.add(rates7);
        ExchangeRates rates8 = new ExchangeRates();
        rates1.setId(8);
        rates.add(rates8);
        //USD to UAH
        rates.get(0).setExchangePair(new StringPair(USD, UAH));
        rates.get(0).setCurrentCourse(26.8);
        rates.get(0).setChangeCourse(0.1);
        //EUR to UAH
        rates.get(1).setExchangePair(new StringPair(EUR, UAH));
        rates.get(1).setCurrentCourse(31.2);
        rates.get(1).setChangeCourse(-0.1);
        //RUB to UAH
        rates.get(2).setExchangePair(new StringPair(RUB, UAH));
        rates.get(2).setCurrentCourse(0.4);
        rates.get(2).setChangeCourse(-0.01);
        //UAH to RUB
        rates.get(3).setExchangePair(new StringPair(UAH, RUB));
        rates.get(3).setCurrentCourse(2.3256);
        rates.get(3).setChangeCourse(-0.01);
        //UAH to USD
        rates.get(4).setExchangePair(new StringPair(UAH, USD));
        rates.get(4).setCurrentCourse(0.0355);
        rates.get(4).setChangeCourse(0.001);
        //UAH to USD
        rates.get(5).setExchangePair(new StringPair(USD, RUB));
        rates.get(5).setCurrentCourse(64.6512);
        rates.get(5).setChangeCourse(-0.111);
        //UAH to USD
        rates.get(6).setExchangePair(new StringPair(USD, EUR));
        rates.get(6).setCurrentCourse(0.8729);
        rates.get(6).setChangeCourse(-0.0666);
        //UAH to USD
        rates.get(7).setExchangePair(new StringPair(EUR, RUB));
        rates.get(7).setCurrentCourse(72.5581);
        rates.get(7).setChangeCourse(0.00333);
        return rates;
    }
}
