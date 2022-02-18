package com.ihamidi.tipcalculator;

import android.widget.TextView;

public interface CalculateTip {
    double getPreTaxTotal();

    boolean includeTax();

    boolean roundUp();

    boolean gratuityIncluded();

    double calculateGoodTip(double preTaxAmt);

    double calculateGreatTip(double preTaxAmt);

    double calculateOkayTip(double preTaxAmt);

    void setTip(TextView tipText,String tipAmt);

    void setGreatTip(String tipAmt);

    void setOkayTip(String tipAmt);
}
