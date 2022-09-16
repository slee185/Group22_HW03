/*
 * Group 22 Homework 03
 * BACCalculatorFragment.java
 * Ken Stanley & Stephanie Karp
 */
package com.example.group22_hw03.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.group22_hw03.Profile;
import com.example.group22_hw03.R;

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
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        bacLevelView = view.findViewById(R.id.bacLevelView);
        numDrinksView = view.findViewById(R.id.numDrinksView);
        statusView = view.findViewById(R.id.statusView);
        buttonDrinkAdd = view.findViewById(R.id.buttonDrinkAdd);
        buttonDrinkView = view.findViewById(R.id.buttonDrinkView);
        buttonSetWeight = view.findViewById(R.id.buttonSetWeight);
        buttonReset = view.findViewById(R.id.buttonReset);
        weightView = view.findViewById(R.id.weightView);

        view.findViewById(R.id.buttonReset).setOnClickListener(v -> listener.resetButtonClicked());

        view.findViewById(R.id.buttonSetWeight).setOnClickListener(v -> listener.setButtonClicked());

        view.findViewById(R.id.buttonDrinkAdd).setOnClickListener(v -> listener.addDrinkButtonClicked());

        view.findViewById(R.id.buttonDrinkView).setOnClickListener(v -> listener.viewDrinksButtonClicked());

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if (context instanceof iListener){
            listener = (iListener)context;
        } else {
            throw new RuntimeException(context + getString(R.string.listener_throw_message));
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        if (profile != null) {
            weightView.setText(getString(R.string.weight_view_label_label, profile.weight, profile.gender));
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
        weightView.setText(getString(R.string.weight_view_label_label, profile.weight, profile.gender));
    }

    iListener listener;

    public interface iListener{
        void resetButtonClicked();

        void setButtonClicked();

        void addDrinkButtonClicked();

        void viewDrinksButtonClicked();
    }
}
