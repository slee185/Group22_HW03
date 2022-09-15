/*
 * Group 22 Homework 03
 * MainActivity.java
 * Ken Stanley & Stephanie Karp
 */
package com.example.group22_hw03;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.group22_hw03.fragments.AddDrinkFragment;
import com.example.group22_hw03.fragments.BACCalculatorFragment;
import com.example.group22_hw03.fragments.SetProfileFragment;
import com.example.group22_hw03.fragments.ViewDrinksFragment;

public class MainActivity extends AppCompatActivity implements BACCalculatorFragment.iListener, AddDrinkFragment.iListener, SetProfileFragment.iListener, ViewDrinksFragment.iListener {
    Drink drink;
    Profile profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.containerView, new BACCalculatorFragment())
                .addToBackStack(null)
                .commit();
    }


}