package com.ihamidi.tipcalculator;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.ihamidi.tipcalculator.databinding.CalculatedtipscreenBinding;

public class TipCalculatorResult extends Fragment {
    private MainActivity calculateTip;
    private CalculatedtipscreenBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        calculateTip = (MainActivity) getActivity();
        binding = CalculatedtipscreenBinding.inflate(inflater, container, false);
        return binding.getRoot();


    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        double preTaxAmount = calculateTip.getPreTaxTotal();

        double goodTip = calculateTip.calculateGoodTip(preTaxAmount);
        double greatTip = calculateTip.calculateGreatTip(preTaxAmount);
        double okayTip = calculateTip.calculateOkayTip(preTaxAmount);

        TextView goodTipText = view.findViewById(R.id.goodTipText);
        TextView greatTipText = view.findViewById(R.id.greatTipText);
        TextView okayTipText = view.findViewById(R.id.okayTipText);
        calculateTip.setTip(goodTipText,String.format("%.2f", goodTip));
        calculateTip.setTip(greatTipText,String.format("%.2f", greatTip));
        calculateTip.setTip(okayTipText,String.format("%.2f", okayTip));

        binding.buttonSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(TipCalculatorResult.this)
                        .navigate(R.id.action_SecondFragment_to_FirstFragment);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}