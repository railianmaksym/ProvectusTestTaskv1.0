package com.railian.maksym.provectustesttask.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Location(@field:SerializedName("street")
               @field:Expose
               val street: String, @field:SerializedName("city")
               @field:Expose
               val city: String, @field:SerializedName("state")
               @field:Expose
               val state: String, @field:SerializedName("postcode")
               @field:Expose
               val postcode: String)

