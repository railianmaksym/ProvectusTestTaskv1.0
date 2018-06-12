package com.railian.maksym.provectustesttask.repository

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Created by fluffy on 04.07.17.
 */


@Entity
class DatabaseModel {

    //Getters and Setters
    @PrimaryKey
    var id: Int = 0

    var gender: String = ""

    var title: String = ""

    var first: String = ""

    var last: String = ""

    var street: String = ""

    var city: String = ""

    var state: String = ""

    var postcode: String = ""

    var email: String = ""

    var username: String = ""

    var dob: String = ""

    var registered: String = ""

    var phone: String = ""

    var cell: String = ""

    var thumbnail: String = ""

    var medium: String = ""

    var large: String = ""

    var nat: String = ""
}