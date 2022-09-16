/*
 * Group 22 Homework 03
 * MainActivity.java
 * Ken Stanley & Stephanie Karp
 */
package com.example.group22_hw03;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import com.example.group22_hw03.fragments.AddDrinkFragment;
import com.example.group22_hw03.fragments.BACCalculatorFragment;
import com.example.group22_hw03.fragments.SetProfileFragment;
import com.example.group22_hw03.fragments.ViewDrinksFragment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements BACCalculatorFragment.iListener, AddDrinkFragment.iListener, SetProfileFragment.iListener, ViewDrinksFragment.iListener {
    Profile profile;

    public ArrayList<Drink> drinkArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.containerView, new BACCalculatorFragment(), "calculator")
                .commit();
    }

    // from BACCalculatorFragment
    @Override
    public void bacCalculatorButtonResetClicked() {
        BACCalculatorFragment fragment = (BACCalculatorFragment)getSupportFragmentManager().findFragmentByTag("calculator");

        if (fragment != null) {
            profile = null;
            drinkArrayList.clear();
            fragment.resetCalculator();
        }
    }

    @Override
    public void bacCalculatorButtonSetClicked() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.containerView, new SetProfileFragment(), "set profile")
                .addToBackStack("set profile")
                .commit();
    }

    @Override
    public void bacCalculatorButtonAddDrinkClicked() {

    }

    @Override
    public void bacCalculatorButtonViewDrinksClicked() {

    }

// from SetProfileFragment

    @Override
    public void setProfileButtonCancelClicked() {
        getSupportFragmentManager().popBackStack();
    }

    @Override
    public void profileSet(Profile profile) {
        /* This is returning null... */
        BACCalculatorFragment fragment = (BACCalculatorFragment)getSupportFragmentManager().findFragmentByTag("calculator");

        getSupportFragmentManager()
                .popBackStack("set profile", FragmentManager.POP_BACK_STACK_INCLUSIVE);

        if (fragment != null) {
            fragment.setWeight(profile);
        }
    }

// from AddDrinkFragment


// from ViewDrinksFragment

}