package com.epam.mykhailo_hrois.task13.view.activity;

import com.epam.mykhailo_hrois.task13.repository.model.RatesModel;

public interface ViewContract {

    void showRate(RatesModel ratesModel, boolean isPositive);
}
