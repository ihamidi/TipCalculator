package com.ihamidi.tipcalculator;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.ihamidi.tipcalculator.databinding.TotalentryBinding;

public class TipCalculatorMain extends Fragment {
    private MainActivity calculateTip;
    private TotalentryBinding binding;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        calculateTip = (MainActivity) getActivity();
        binding = TotalentryBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonFirst.setOnClickListener(view1 -> {
            double preTaxAmount = calculateTip.getPreTaxTotal();

            Toast notifyUserOfAmount = Toast.makeText(getContext(),"Calculating tip for: "+preTaxAmount, Toast.LENGTH_LONG);
            notifyUserOfAmount.show();
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            NavHostFragment.findNavController(TipCalculatorMain.this)
                    .navigate(R.id.action_FirstFragment_to_SecondFragment);
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}