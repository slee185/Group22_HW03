/*
 * Group 22 Homework 03
 * BACCalculatorFragment.java
 * Ken Stanley & Stephanie Karp
 */
package com.example.group22_hw03.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.group22_hw03.Profile;
import com.example.group22_hw03.R;

import java.util.ArrayList;

public class BACCalculatorFragment extends Fragment {
    private TextView bacLevelView;
    private TextView numDrinksView;
    private TextView statusView;
    private TextView weightView;
    private Button buttonDrinkAdd;
    private Button buttonDrinkView;
    private Button buttonSetWeight;
    private Button buttonReset;

    Profile profile;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_baccalculator, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        bacLevelView = view.findViewById(R.id.bacCalculatorBacLevelView);
        buttonDrinkAdd = view.findViewById(R.id.bacCalculatorButtonAddDrink);
        buttonDrinkView = view.findViewById(R.id.bacCalculatorButtonViewDrinks);
        buttonReset = view.findViewById(R.id.bacCalculatorButtonReset);
        buttonSetWeight = view.findViewById(R.id.bacCalculatorButtonSet);
        numDrinksView = view.findViewById(R.id.bacCalculatorNumDrinksView);
        statusView = view.findViewById(R.id.bacCalculatorStatusView);
        weightView = view.findViewById(R.id.bacCalculatorWeightView);

        buttonDrinkAdd.setOnClickListener(v -> listener.bacCalculatorButtonAddDrinkClicked());
        buttonDrinkView.setOnClickListener(v -> listener.bacCalculatorButtonViewDrinksClicked());
        buttonReset.setOnClickListener(v -> listener.bacCalculatorButtonResetClicked());
        buttonSetWeight.setOnClickListener(v -> listener.bacCalculatorButtonSetClicked());

        buttonReset.performClick();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if (context instanceof iListener) {
            listener = (iListener) context;
        } else {
            throw new RuntimeException(context + getString(R.string.listener_throw_message));
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        if (profile != null) {
            weightView.setText(getString(R.string.weight_view_label_label, profile.weight, profile.gender));
            buttonDrinkView.setEnabled(true);
            buttonDrinkAdd.setEnabled(true);
        }
    }

    public void resetCalculator() {
        weightView.setText(R.string.weight_view_label_reset);
        numDrinksView.setText(getString(R.string.num_drinks_view, 0));
        bacLevelView.setText(getString(R.string.bac_level_view, 0.0));
        buttonDrinkAdd.setEnabled(false);
        buttonDrinkView.setEnabled(false);
    }

    public void setWeight(Profile profile) {
        this.profile = profile;
    }

    iListener listener;

    public interface iListener {
        void bacCalculatorButtonResetClicked();

        void bacCalculatorButtonSetClicked();

        void bacCalculatorButtonAddDrinkClicked();

        void bacCalculatorButtonViewDrinksClicked();
    }
}
