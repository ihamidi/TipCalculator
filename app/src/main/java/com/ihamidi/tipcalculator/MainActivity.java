package com.ihamidi.tipcalculator;

import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.snackbar.Snackbar;
import com.ihamidi.tipcalculator.databinding.ActivityMainBinding;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity implements CalculateTip {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        EditText etText = findViewById(R.id.enterPreTaxTotal);
        etText.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(5, 2)});

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);



        binding.info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public double getPreTaxTotal() {
        EditText preTaxTotal = findViewById(R.id.enterPreTaxTotal);
        if (Double.parseDouble(preTaxTotal.getText().toString()) > 0)
            return Double.parseDouble(preTaxTotal.getText().toString());
        return 0;
    }

    @Override
    public boolean includeTax() {
        SwitchCompat includeTax = findViewById(R.id.includetax);
        return includeTax.isChecked();
    }

    @Override
    public boolean roundUp() {
        SwitchCompat roundUp = findViewById(R.id.roundUp);
        return roundUp.isChecked();
    }

    @Override
    public boolean gratuityIncluded() {
        SwitchCompat gratIncluded = findViewById(R.id.gratIncluded);
        return gratIncluded.isChecked();
    }

    @Override
    public double calculateGoodTip(double preTaxAmt) {
        double tipRate = 0.15;
        /*Implement location based tax later*/
//        if(includeTax())
//            preTaxAmt
        if(gratuityIncluded())
            tipRate/=2;

        if(roundUp())
            return Math.ceil(preTaxAmt * tipRate);
        return preTaxAmt * tipRate;
    }

    @Override
    public double calculateGreatTip(double preTaxAmt) {
        double tipRate = 0.2;
        if(gratuityIncluded())
            tipRate/=2;

        if(roundUp())
            return Math.ceil(preTaxAmt * tipRate);
        return preTaxAmt * tipRate;
    }

    @Override
    public double calculateOkayTip(double preTaxAmt) {
        double tipRate = 0.1;
        if(gratuityIncluded())
            tipRate/=2;

        if(roundUp())
            return Math.floor(preTaxAmt * tipRate);
        return preTaxAmt * tipRate;
    }

    @Override
    public void setTip(TextView tipText,String tipAmt) {
        tipText.append(tipAmt);
    }

    @Override
    public void setGreatTip(String tipAmt) {
        TextView greatTipText = findViewById(R.id.greatTipText);
        greatTipText.append(tipAmt);
    }

    @Override
    public void setOkayTip(String tipAmt) {
        TextView okayTipText = findViewById(R.id.okayTipText);
        okayTipText.append(tipAmt);
    }
}
class DecimalDigitsInputFilter implements InputFilter {
    private Pattern mPattern;
    DecimalDigitsInputFilter(int digitsBeforeZero, int digitsAfterZero) {
        mPattern = Pattern.compile("[0-9]{0," + (digitsBeforeZero - 1) + "}+((\\.[0-9]{0," + (digitsAfterZero - 1) + "})?)||(\\.)?");
    }
    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
        Matcher matcher = mPattern.matcher(dest);
        if (!matcher.matches())
            return "";
        return null;
    }
}