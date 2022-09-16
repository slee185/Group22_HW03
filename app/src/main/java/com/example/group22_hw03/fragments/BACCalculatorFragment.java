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

    TextView bacLevelView;
    TextView numDrinksView;
    TextView statusView;
    TextView weightView;
    Button buttonDrinkAdd;
    Button buttonDrinkView;
    Button buttonSetWeight;
    Button buttonReset;

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
        numDrinksView = view.findViewById(R.id.bacCalculatorNumDrinksView);
        statusView = view.findViewById(R.id.bacCalculatorStatusView);
        buttonDrinkAdd = view.findViewById(R.id.bacCalculatorButtonAddDrink);
        buttonDrinkView = view.findViewById(R.id.bacCalculatorButtonViewDrinks);
        buttonSetWeight = view.findViewById(R.id.bacCalculatorButtonSet);
        buttonReset = view.findViewById(R.id.bacCalculatorButtonReset);
        weightView = view.findViewById(R.id.bacCalculatorWeightView);

        buttonReset.setOnClickListener(v -> listener.resetButtonClicked());
        buttonSetWeight.setOnClickListener(v -> listener.setButtonClicked());
        buttonDrinkAdd.setOnClickListener(v -> listener.addDrinkButtonClicked());
        buttonDrinkView.setOnClickListener(v -> listener.viewDrinksButtonClicked());

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
        void resetButtonClicked();

        void setButtonClicked();

        void addDrinkButtonClicked();

        void viewDrinksButtonClicked();
    }
}
