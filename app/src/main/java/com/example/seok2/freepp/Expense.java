package com.example.seok2.freepp;

import android.os.Parcel;
import android.os.Parcelable;

public class Expense implements Parcelable {
    String date;
    String name;
    String tel;

    int num_category;

    public Expense(String date, String name, String tel, int num_category){
        this.date = date;
        this.name = name;
        this.tel = tel;
        this.num_category = num_category;
    }

    protected Expense(Parcel in) {
        date = in.readString();
        name = in.readString();
        tel = in.readString();
        num_category = in.readInt();
    }

    public static final Creator<Expense> CREATOR = new Creator<Expense>() {
        @Override
        public Expense createFromParcel(Parcel in) {
            return new Expense(in);
        }

        @Override
        public Expense[] newArray(int size) {
            return new Expense[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(date);
        parcel.writeString(name);
        parcel.writeString(tel);
        parcel.writeInt(num_category);
    }
}