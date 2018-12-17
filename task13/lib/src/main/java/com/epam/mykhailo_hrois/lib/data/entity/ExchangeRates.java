package com.epam.mykhailo_hrois.lib.data.entity;

import com.sun.corba.se.spi.orb.StringPair;

public class ExchangeRates {
    private int id;
    private StringPair exchangePair;
    private Double currentCourse;
    private Double changeCourse;

    public ExchangeRates(){

    }

    public StringPair getExchangePair() {
        return exchangePair;
    }

    public void setExchangePair(StringPair exchangePair) {
        this.exchangePair = exchangePair;
    }

    public Double getCurrentCourse() {
        return currentCourse;
    }

    public void setCurrentCourse(Double currentCourse) {
        this.currentCourse = currentCourse;
    }

    public Double getChangeCourse() {
        return changeCourse;
    }

    public void setChangeCourse(Double changeCourse) {
        this.changeCourse = changeCourse;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
