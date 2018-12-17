package com.epam.mykhailo_hrois.task13.repository;

import com.epam.mykhailo_hrois.lib.domain.StringPair;
import com.epam.mykhailo_hrois.lib.domain.interactor.ExchangeRatesUseCase;
import com.epam.mykhailo_hrois.task13.repository.mapper.RatesMapper;
import com.epam.mykhailo_hrois.task13.repository.model.RatesModel;

import java.util.List;

public class RepositoryImpl implements Repository {

    private final ExchangeRatesUseCase ratesUseCase;
    private final RatesMapper ratesMapper;

    public RepositoryImpl() {
        ratesUseCase = new ExchangeRatesUseCase();
        ratesMapper = new RatesMapper();
    }

    @Override
    public List<Double> showAllExchangeRates() {
        return ratesUseCase.showAllExchangeRates();
    }

    @Override
    public List<Double> showAllRateChanges() {
        return ratesUseCase.showAllRateChanges();
    }

    @Override
    public RatesModel makeRatesModel(String first, String second) {
        return ratesMapper.transform(ratesUseCase.showRateChange(new StringPair(first, second)));
    }
}
