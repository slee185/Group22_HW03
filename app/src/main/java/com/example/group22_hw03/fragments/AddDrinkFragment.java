/*
 * Group 22 Homework 03
 * AddDrinkFragment.java
 * Ken Stanley & Stephanie Karp
 */
package com.example.group22_hw03.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.SeekBar;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.group22_hw03.Drink;
import com.example.group22_hw03.R;

public class AddDrinkFragment extends Fragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_adddrink, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RadioGroup addDrinkSizeGroup = view.findViewById(R.id.addDrinkSizeGroup);
        SeekBar addDrinkAlcoholPercentBar = view.findViewById(R.id.addDrinkAlcoholPercentBar);

        view.findViewById(R.id.addDrinkButtonCancel).setOnClickListener(v -> listener.addDrinkButtonCancelClicked());
        view.findViewById(R.id.addDrinkButtonSet).setOnClickListener(v -> {
            double size;

            if (addDrinkSizeGroup.getCheckedRadioButtonId() == R.id.addDrinkSizeOptionSmall) {
                size = Drink.SIZE_SMALL;
            } else if (addDrinkSizeGroup.getCheckedRadioButtonId() == R.id.addDrinkSizeOptionMedium) {
                size = Drink.SIZE_MEDIUM;
            } else if (addDrinkSizeGroup.getCheckedRadioButtonId() == R.id.addDrinkSizeOptionLarge) {
                size = Drink.SIZE_LARGE;
            } else {
                throw new IllegalStateException("Invalid drink size selected");
            }

            listener.addDrinkButtonSetClicked(new Drink(size, addDrinkAlcoholPercentBar.getProgress()));
        });
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

    iListener listener;

    public interface iListener {
        void addDrinkButtonSetClicked(Drink drink);

        void addDrinkButtonCancelClicked();
    }
}
