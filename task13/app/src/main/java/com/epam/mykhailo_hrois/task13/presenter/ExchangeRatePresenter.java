package com.epam.mykhailo_hrois.task13.presenter;

import com.epam.mykhailo_hrois.task13.repository.Repository;
import com.epam.mykhailo_hrois.task13.repository.RepositoryImpl;
import com.epam.mykhailo_hrois.task13.repository.model.RatesModel;
import com.epam.mykhailo_hrois.task13.view.activity.View;

public class ExchangeRatePresenter implements Presenter {

    private View view;
    private Repository repository;

    public ExchangeRatePresenter(View view) {
        this.view = view;
        this.repository = new RepositoryImpl();
    }

    @Override
    public void textEdited(String first, String second) {
        RatesModel ratesModel = repository.makeRatesModel(first, second);
        if (ratesModel.getChangeCourse() >= 0.0) {
            view.showRate(ratesModel, true);
        } else {
            view.showRate(ratesModel, false);
        }
    }
}
