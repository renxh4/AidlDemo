package com.xiao.hui.aidldemo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by jh on 2017/8/14.
 */

public class Student implements Parcelable {
    protected Student(Parcel in) {

    }

    public static final Creator<Student> CREATOR = new Creator<Student>() {
        @Override
        public Student createFromParcel(Parcel in) {
            return new Student(in);
        }

        @Override
        public Student[] newArray(int size) {
            return new Student[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }


}
