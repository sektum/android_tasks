package com.epam.mykhailo_hrois.lib.domain.repository;

import com.epam.mykhailo_hrois.lib.domain.Rates;

import java.util.List;

public interface RatesRepository {

    List<Rates> rates();

    Rates rate(final int id);
}
