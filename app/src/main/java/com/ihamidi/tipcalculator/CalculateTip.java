package com.ihamidi.tipcalculator;

public interface CalculateTip {
    double getPreTaxTotal();

    boolean includeTax();

    boolean roundUp();

    boolean gratuityIncluded();
}
