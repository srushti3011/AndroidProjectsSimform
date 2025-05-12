package com.example.learningproject

import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable

//data class User(
//    val name: String,
//    val age: Int,
//    val city: String,
//    val occupation: String
//): Serializable

data class User(
    val name: String,
    val age: Int,
    val city: String,
    val occupation: String,
    val img: Int
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readInt() ?: 0,
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readInt() ?: 0
    ) {

    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(name)
        dest.writeInt(age)
        dest.writeString(city)
        dest.writeString(occupation)
        dest.writeInt(img)
    }

    companion object CREATOR : Parcelable.Creator<User> {
        override fun createFromParcel(parcel: Parcel): User {
            return User(parcel)
        }
        override fun newArray(size: Int): Array<User?> {
            return arrayOfNulls(size)
        }
    }
}