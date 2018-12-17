package com.epam.mykhailo_hrois.lib.domain;

import com.sun.corba.se.spi.orb.StringPair;

public class Rates {
    private final int id;

    public Rates(int id){
        this.id = id;
    }

    private StringPair exchangePair;
    private Double currentCourse;
    private Double changeCourse;


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
}
