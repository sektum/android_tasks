package com.epam.mykhailo_hrois.task13.repository;

import com.epam.mykhailo_hrois.task13.repository.model.RatesModel;

import java.util.List;

public interface Repository {
    List<Double> showAllExchangeRates();

    List<Double> showAllRateChanges();

    RatesModel makeRatesModel(String first, String second);
}
