package com.railian.maksym.provectustesttask.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class Result {

    // Getters & Setters

    @SerializedName("gender")
    @Expose
    var gender: String = ""


    @SerializedName("name")
    @Expose
    var name: Name? = null


    @SerializedName("location")
    @Expose
    var location: Location? = null


    @SerializedName("email")
    @Expose
    var email: String = ""


    @SerializedName("login")
    @Expose
    var login: Login? = null

    @SerializedName("dob")
    @Expose
    var dob: String = ""

    @SerializedName("registered")
    @Expose
    var registered: String = ""

    @SerializedName("phone")
    @Expose
    var phone: String = ""

    @SerializedName("cell")
    @Expose
    var cell: String = ""

    @SerializedName("picture")
    @Expose
    var picture: Picture? = null

    @SerializedName("nat")
    @Expose
    var nat: String = ""

}
