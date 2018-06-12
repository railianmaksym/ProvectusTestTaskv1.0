package com.railian.maksym.provectustesttask.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Name(@field:SerializedName("title")
           @field:Expose
           var title: String?, @field:SerializedName("first")
           @field:Expose
           val first: String, @field:SerializedName("last")
           @field:Expose
           val last: String)
