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

import java.util.ArrayList;

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

        // ArrayList<Drink> drinks =

        view.findViewById(R.id.viewDrinksButtonTrash).setOnClickListener(v -> {

        });

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

    iListener listener;

    public interface iListener{

    }
}
