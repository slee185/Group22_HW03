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

import androidx.fragment.app.Fragment;

import com.example.group22_hw03.R;

public class AddDrinkFragment extends Fragment {
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof iListener){
            listener = (iListener)context;
        } else {
            throw new RuntimeException(context + getString(R.string.listener_throw_message));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_baccalculator, container, false);
    }

    iListener listener;

    public interface iListener{

    }
}
