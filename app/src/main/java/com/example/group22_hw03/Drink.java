/*
 * Group 22 Homework 03
 * Drink.java
 * Ken Stanley & Stephanie Karp
 */
package com.example.group22_hw03;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Calendar;

public class Drink implements Parcelable {
    public static final double SIZE_SMALL = 1.0;
    public static final double SIZE_MEDIUM = 5.0;
    public static final double SIZE_LARGE = 12.0;

    public final double drinkSize;
    public final double drinkAlcoholPercent;
    public final long dateTime;

    public Drink(double size, double alcohol) {
        this.drinkSize = size;
        this.drinkAlcoholPercent = alcohol;
        this.dateTime = Calendar.getInstance().getTimeInMillis();
    }

    protected Drink(Parcel in) {
        drinkSize = in.readDouble();
        drinkAlcoholPercent = in.readDouble();
        dateTime = in.readLong();
    }

    public static final Creator<Drink> CREATOR = new Creator<Drink>() {
        @Override
        public Drink createFromParcel(Parcel in) {
            return new Drink(in);
        }

        @Override
        public Drink[] newArray(int size) {
            return new Drink[size];
        }
    };

    public double getLiquidOunces() {
        return (drinkAlcoholPercent * drinkSize) / 100;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeDouble(drinkSize);
        parcel.writeDouble(drinkAlcoholPercent);
        parcel.writeLong(dateTime);
    }
}
