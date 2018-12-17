package com.epam.mykhailo_hrois.task13.presenter;

import com.epam.mykhailo_hrois.task13.repository.Repository;
import com.epam.mykhailo_hrois.task13.repository.RepositoryImpl;
import com.epam.mykhailo_hrois.task13.repository.model.RatesModel;
import com.epam.mykhailo_hrois.task13.view.activity.ViewContract;

public class ExchangeRatePresenter implements Presenter {

    private ViewContract viewContract;
    private Repository repository;

    public ExchangeRatePresenter(ViewContract viewContract) {
        this.viewContract = viewContract;
        this.repository = new RepositoryImpl();
    }

    @Override
    public void textEdited(String first, String second) {
        RatesModel ratesModel = repository.makeRatesModel(first, second);
        if (ratesModel.getChangeCourse() >= 0.0) {
            viewContract.showRate(ratesModel, true);
        } else {
            viewContract.showRate(ratesModel, false);
        }
    }
}
