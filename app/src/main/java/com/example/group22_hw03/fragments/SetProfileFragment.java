/*
 * Group 22 Homework 03
 * SetProfileFragment.java
 * Ken Stanley & Stephanie Karp
 */
package com.example.group22_hw03.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.group22_hw03.Profile;
import com.example.group22_hw03.R;

public class SetProfileFragment extends Fragment {

    EditText weightWidget;
    RadioGroup genderGroup;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_setprofile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        weightWidget = view.findViewById(R.id.setProfileWeightWidget);
        genderGroup = view.findViewById(R.id.setProfileGenderGroup);

        view.findViewById(R.id.setProfileButtonCancel).setOnClickListener(v -> listener.setProfileButtonCancelClicked());

        view.findViewById(R.id.setProfileButtonSet).setOnClickListener(v -> {
            if (!validateWeight()) {
                Toast.makeText(getActivity(), getString(R.string.validation_weight), Toast.LENGTH_SHORT).show();
                return;
            }

            if (!validateGender()) {
                Toast.makeText(getActivity(), getString(R.string.validation_gender), Toast.LENGTH_SHORT).show();
                return;
            }

            String value = weightWidget.getText().toString();
            int userWeight = Integer.parseInt(value);

            String gender;

            int genderChoice = genderGroup.getCheckedRadioButtonId();

            // select gender
            if (genderChoice == R.id.setProfileGenderFemale) {
                gender = getString(R.string.gender_group_female);
            } else if (genderChoice == R.id.setProfileGenderMale) {
                gender = getString(R.string.gender_group_male);
            } else {
                throw new IllegalStateException(getString(R.string.exception_illegal_state_gender));
            }

            Profile profile = new Profile(userWeight, gender);

            listener.profileSet(profile);
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

    /**
     * Validate the following rules:
     * <p>
     * 1. The gender is either male or female.
     *
     * @return boolean
     */
    @SuppressLint("NonConstantResourceId")
    private boolean validateGender() {
        switch (genderGroup.getCheckedRadioButtonId()) {
            case R.id.setProfileGenderFemale:
            case R.id.setProfileGenderMale:
                return true;
            default:
                return false;
        }
    }

    /**
     * Validate the following rules:
     * <p>
     * 1. The number entered is valid.
     * 2. The number entered is greater than 0.
     *
     * @return boolean
     */
    private boolean validateWeight() {
        try {
            int weight = Integer.parseInt(weightWidget.getText().toString());
            return weight > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    iListener listener;

    public interface iListener {
        void setProfileButtonCancelClicked();

        void profileSet(Profile profile);
    }
}
