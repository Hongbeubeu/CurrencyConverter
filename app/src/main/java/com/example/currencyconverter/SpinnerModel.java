package com.example.currencyconverter;

public class SpinnerModel {
     String symbol;
     double value;

    public SpinnerModel(String symbol, double value) {
        this.symbol = symbol;
        this.value = value;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
    public double getRate(SpinnerModel spinnerModel){
        return (double)Math.floor((this.value/spinnerModel.value)*100)/100;
    }
}
