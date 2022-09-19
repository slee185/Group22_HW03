/*
 * Group 22 Homework 03
 * BACCalculatorFragment.java
 * Ken Stanley & Stephanie Karp
 */
package com.example.group22_hw03.fragments;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.group22_hw03.Drink;
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

    public double bac;

    ArrayList<Drink> drinks = new ArrayList<>();
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

        // set buttons to be disabled on start
        buttonDrinkView.setEnabled(false);
        buttonDrinkAdd.setEnabled(false);

        buttonDrinkAdd.setOnClickListener(v -> listener.bacCalculatorButtonAddDrinkClicked());
        buttonDrinkView.setOnClickListener(v -> {
            if (drinks.isEmpty()) {
                Toast.makeText(getActivity(), R.string.validation_drink_list_empty, Toast.LENGTH_SHORT).show();
                return;
            }
            listener.bacCalculatorButtonViewDrinksClicked(drinks);
        });
        buttonReset.setOnClickListener(v -> listener.bacCalculatorButtonResetClicked());
        buttonSetWeight.setOnClickListener(v -> listener.bacCalculatorButtonSetClicked());

        bac = 0.0;
        double totalLiquidOunces = 0.0;

        for (Drink drink : drinks) {
            totalLiquidOunces += drink.drinkSize * drink.drinkAlcoholPercent / 100;
        }

        if (profile != null) {
            bac = (totalLiquidOunces * 5.14 / (profile.weight * profile.getGenderConstant(this)));
        }

        numDrinksView.setText(getString(R.string.num_drinks_view, drinks.size()));
        bacLevelView.setText(getString(R.string.bac_level_view, bac));
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
            this.updateBacLevelView(bac);
        } else {
            bacLevelView.setText(getString(R.string.bac_level_view, 0.0));
        }
    }

    public void updateBacLevelView(double bac) {
        GradientDrawable viewBackground = (GradientDrawable) statusView.getBackground();

        if (0 <= bac && bac <= 0.08) {
            // You're Safe.
            statusView.setText(R.string.status_view_safe);
            viewBackground.setColor(getResources().getColor(R.color.success));
        } else if (0.08 < bac && bac <= 0.2) {
            // Be Careful.
            statusView.setText(R.string.status_view_careful);
            viewBackground.setColor(getResources().getColor(R.color.warning));
        } else if (0.2 < bac) {
            // Over the limit!
            statusView.setText(R.string.status_view_danger);
            viewBackground.setColor(getResources().getColor(R.color.danger));

            if (0.25 <= bac) {
                Toast.makeText(getActivity(), R.string.status_view_over, Toast.LENGTH_SHORT).show();
                buttonDrinkAdd.setEnabled(false);
            }
        }
    }

    public void resetCalculator() {
        weightView.setText(R.string.weight_view_label_reset);
        numDrinksView.setText(getString(R.string.num_drinks_view, 0));
        bacLevelView.setText(getString(R.string.bac_level_view, 0.0));

        this.updateBacLevelView(0);

        profile = null;
        drinks.clear();

        buttonDrinkAdd.setEnabled(false);
        buttonDrinkView.setEnabled(false);
    }

    public void updateDrinks(ArrayList<Drink> drinks, Profile profile) {
        this.drinks = drinks;
        this.profile = profile;
    }

    iListener listener;

    public interface iListener {
        void bacCalculatorButtonResetClicked();

        void bacCalculatorButtonSetClicked();

        void bacCalculatorButtonAddDrinkClicked();

        void bacCalculatorButtonViewDrinksClicked(ArrayList<Drink> drinks);
    }
}
