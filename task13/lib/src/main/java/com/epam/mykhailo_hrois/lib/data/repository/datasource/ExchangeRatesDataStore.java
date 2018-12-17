package com.epam.mykhailo_hrois.lib.data.repository.datasource;

import com.epam.mykhailo_hrois.lib.data.entity.ExchangeRates;

import java.util.List;

public interface ExchangeRatesDataStore {
    List<ExchangeRates> ratesList();

    ExchangeRates rate(final int id);
}
