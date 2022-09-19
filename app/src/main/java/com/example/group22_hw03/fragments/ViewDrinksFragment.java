/*
 * Group 22 Homework 03
 * ViewDrinksFragment.java
 * Ken Stanley & Stephanie Karp
 */
package com.example.group22_hw03.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.group22_hw03.Drink;
import com.example.group22_hw03.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class ViewDrinksFragment extends Fragment {

    TextView viewAlcoholPercent;
    TextView viewCurrentDrinkNumber;
    TextView viewDateAdded;
    TextView viewDrinkSize;
    Button viewDrinksButtonClose;
    ImageButton viewDrinksButtonTrash;
    ImageButton viewDrinksButtonPrevious;
    ImageButton viewDrinksButtonNext;

    int currentDrinkNumber = 0;
    ArrayList<Drink> drinks;

    public ViewDrinksFragment(ArrayList<Drink> drinks) {
        this.drinks = drinks;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_viewdrinks, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewAlcoholPercent = view.findViewById(R.id.viewAlcoholPercent);
        viewCurrentDrinkNumber = view.findViewById(R.id.viewCurrentDrinkNumber);
        viewDateAdded = view.findViewById(R.id.viewDateAdded);
        viewDrinkSize = view.findViewById(R.id.viewDrinkSize);
        viewDrinksButtonClose = view.findViewById(R.id.viewDrinksButtonClose);
        viewDrinksButtonTrash = view.findViewById(R.id.viewDrinksButtonTrash);
        viewDrinksButtonPrevious = view.findViewById(R.id.viewDrinksButtonPrevious);
        viewDrinksButtonNext = view.findViewById(R.id.viewDrinksButtonNext);

        view.findViewById(R.id.viewDrinksButtonTrash).setOnClickListener(v -> trashDrink(drinks));

        view.findViewById(R.id.viewDrinksButtonClose).setOnClickListener(v -> listener.viewDrinksButtonCloseClicked(drinks));

        view.findViewById(R.id.viewDrinksButtonPrevious).setOnClickListener(v -> previousDrink(drinks));

        view.findViewById(R.id.viewDrinksButtonNext).setOnClickListener(v -> nextDrink(drinks));

        updateView(drinks);
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

    public void updateView(ArrayList<Drink> drinks) {
        Drink currentDrink = drinks.get(currentDrinkNumber);
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm a", Locale.US);

        Date drinkDate = Calendar.getInstance().getTime();
        drinkDate.setTime(currentDrink.dateTime);

        viewCurrentDrinkNumber.setText(getString(R.string.view_current_drink_number, currentDrinkNumber + 1, drinks.size()));
        viewAlcoholPercent.setText(getString(R.string.view_alcohol_percent, currentDrink.drinkAlcoholPercent));
        viewDrinkSize.setText(getString(R.string.view_drink_size, currentDrink.drinkSize));
        viewDateAdded.setText(getString(R.string.view_date_added, dateFormat.format(drinkDate)));
    }

    public void previousDrink(ArrayList<Drink> drinks) {
        currentDrinkNumber--;
        if (currentDrinkNumber < 0) {
            currentDrinkNumber = drinks.size() - 1;
        }

        updateView(drinks);
    }

    public void nextDrink(ArrayList<Drink> drinks) {
        currentDrinkNumber++;
        if (currentDrinkNumber >= drinks.size()) {
            currentDrinkNumber = 0;
        }

        updateView(drinks);
    }

    public void trashDrink(ArrayList<Drink> drinks) {
        drinks.remove(currentDrinkNumber);

        if (drinks.isEmpty()) {
            viewDrinksButtonClose.performClick();
            return;
        }
        viewDrinksButtonPrevious.performClick();
    }

    iListener listener;

    public interface iListener {
        void viewDrinksButtonCloseClicked(ArrayList<Drink> drinks);
    }
}
