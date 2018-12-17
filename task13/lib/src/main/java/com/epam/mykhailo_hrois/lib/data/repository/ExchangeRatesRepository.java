package com.epam.mykhailo_hrois.lib.data.repository;

import com.epam.mykhailo_hrois.lib.data.entity.ExchangeRates;
import com.epam.mykhailo_hrois.lib.data.entity.mapper.ExchangeRatesMapper;
import com.epam.mykhailo_hrois.lib.data.repository.datasource.ExchangeRatesDataStore;
import com.epam.mykhailo_hrois.lib.data.repository.datasource.ExchangeRatesDataStoreImpl;
import com.epam.mykhailo_hrois.lib.domain.Rates;
import com.epam.mykhailo_hrois.lib.domain.repository.RatesRepository;

import java.util.List;

public class ExchangeRatesRepository implements RatesRepository {

    private final ExchangeRatesMapper exchangeRatesMapper;
    private final ExchangeRatesDataStore exchangeRatesDataStore;

    public ExchangeRatesRepository() {
        exchangeRatesMapper = new ExchangeRatesMapper();
        exchangeRatesDataStore = new ExchangeRatesDataStoreImpl();
    }

    @Override
    public List<Rates> rates() {
        List<ExchangeRates> exchangeRates = exchangeRatesDataStore.ratesList();
        return exchangeRatesMapper.transform(exchangeRates);
    }

    @Override
    public Rates rate(int id) {
        ExchangeRates exchangeRate = exchangeRatesDataStore.rate(id);
        return exchangeRatesMapper.transform(exchangeRate);
    }
}
