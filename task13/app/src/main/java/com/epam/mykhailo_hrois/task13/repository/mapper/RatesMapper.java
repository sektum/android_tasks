package com.epam.mykhailo_hrois.task13.repository.mapper;

import com.epam.mykhailo_hrois.lib.domain.Rates;
import com.epam.mykhailo_hrois.task13.repository.model.RatesModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class RatesMapper {

    public RatesMapper() {
    }

    public RatesModel transform(Rates rates) {
        if (rates == null) {
            throw new IllegalArgumentException("Cannot transform a null value");
        }
        final RatesModel rateModel = new RatesModel();
        rateModel.setChangeCourse(rates.getChangeCourse());
        rateModel.setCurrentCourse(rates.getCurrentCourse());
        return rateModel;
    }

    public Collection<RatesModel> transform(Collection<Rates> ratesModels) {
        Collection<RatesModel> ratesModelCollection;

        if (ratesModels != null && !ratesModels.isEmpty()) {
            ratesModelCollection = new ArrayList<>();
            for (Rates rate : ratesModels) {
                ratesModelCollection.add(transform(rate));
            }
        } else {
            ratesModelCollection = Collections.emptyList();
        }

        return ratesModelCollection;
    }
}
